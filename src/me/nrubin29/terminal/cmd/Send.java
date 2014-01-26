package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.event.EventDispatcher;
import me.nrubin29.terminal.event.PlayerSendFileEvent;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.Server;
import me.nrubin29.terminal.server.ServerManager;

public class Send extends Command {

    public Send() {
        super("send", "Send a file to a user on a server.");
    }

    public void run(String[] args) {
        if (args.length < 2) {
            Terminal.getInstance().getGUI().write("Usage: send filename user@server", GUI.MessageType.BAD);
            return;
        }

        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().getGUI().write("You can only use the send command on your localhost.", GUI.MessageType.BAD);
            return;
        }

        String file = args[0], email = args[1];
        Folder currentFolder = Terminal.getInstance().getLocalFS().getCurrentFolder();

        if (!email.contains("@")) {
            Terminal.getInstance().getGUI().write("Invalid email address.", GUI.MessageType.BAD);
            return;
        }

        for (FileSystemObject fso : currentFolder.getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof File) {
                    Terminal.getInstance().getGUI().write("Validating email address " + email + ".", GUI.MessageType.NORMAL);

                    Utils.pause(Utils.SECOND);

                    Server s = ServerManager.getInstance().getServer(email.split("@")[1]);

                    if (s == null) {
                        Terminal.getInstance().getGUI().write("Could not find server at IP " + email.split("@")[1] + ".", GUI.MessageType.BAD);
                        return;
                    }

                    if (!s.login(email.split("@")[0])) {
                        Terminal.getInstance().getGUI().write("Could not find user " + email.split("@")[0] + " on server.", GUI.MessageType.BAD);
                        return;
                    }

                    Terminal.getInstance().getGUI().write("Validation successful. Sending file...", GUI.MessageType.NORMAL);

                    Utils.pause(Utils.SECOND);

                    Terminal.getInstance().getGUI().write("File sent successfully.", GUI.MessageType.GOOD);

                    EventDispatcher.getInstance().callEvent(new PlayerSendFileEvent((File) fso, email));
                }

                else {
                    Terminal.getInstance().getGUI().write("Cannot send folder.", GUI.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().getGUI().write("Could not find file " + file + ".", GUI.MessageType.BAD);
    }
}