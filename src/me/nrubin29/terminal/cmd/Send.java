package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.event.EventDispatcher;
import me.nrubin29.terminal.event.FileSendEvent;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.server.Server;
import me.nrubin29.terminal.server.ServerManager;

public class Send extends Command {

    public Send() {
        super("send", "Send a file to a user on a server.");
    }

    public void run(String[] args) {
        if (args.length < 2) {
            Terminal.getInstance().write("Usage: send filename user@server", Terminal.MessageType.BAD);
            return;
        }

        /*
         * TODO: isOnLocalhost method.
         */
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().write("You can only use the send command on your localhost.", Terminal.MessageType.BAD);
            return;
        }

        String file = args[0], email = args[1];
        Folder currentFolder = ServerManager.getInstance().getLocalFS().getCurrentFolder();

        if (!email.contains("@")) {
            Terminal.getInstance().write("Invalid email address.", Terminal.MessageType.BAD);
            return;
        }

        for (FileSystemObject fso : currentFolder.getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof File) {
                    Terminal.getInstance().write("Validating email address " + email + ".", Terminal.MessageType.NORMAL);

                    Utils.pause(Utils.SECOND);

                    Server s = ServerManager.getInstance().getServer(email.split("@")[1]);

                    if (s == null) {
                        Terminal.getInstance().write("Could not find server at IP " + email.split("@")[1] + ".", Terminal.MessageType.BAD);
                        return;
                    }

                    if (!s.login(email.split("@")[0])) {
                        Terminal.getInstance().write("Could not find user " + email.split("@")[0] + " on server.", Terminal.MessageType.BAD);
                        return;
                    }

                    Terminal.getInstance().write("Validation successful. Sending file...", Terminal.MessageType.NORMAL);

                    Utils.pause(Utils.SECOND);

                    Terminal.getInstance().write("File sent successfully.", Terminal.MessageType.NORMAL);

                    EventDispatcher.getInstance().callEvent(new FileSendEvent((File) fso, email));
                }

                else {
                    Terminal.getInstance().write("Cannot send folder.", Terminal.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().write("Could not find file " + file + ".", Terminal.MessageType.BAD);
    }
}