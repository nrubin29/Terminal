package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.server.ServerManager;

public class Download extends Command {

    public Download() {
        super("download", "Download a file the server to which you are currently connected.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().write("Usage: download filename", Terminal.MessageType.BAD);
            return;
        }

        if (ServerManager.getInstance().getCurrentServer() == null) {
            Terminal.getInstance().write("You can only use the download command on servers.", Terminal.MessageType.BAD);
            return;
        }

        String file = args[0];
        Folder currentFolder = ServerManager.getInstance().getCurrentServer().getFileSystem().getCurrentFolder();

        for (FileSystemObject fso : currentFolder.getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof File) {
                    Terminal.getInstance().write("Downloading file...", Terminal.MessageType.NORMAL);

                    Utils.pause(Utils.SECOND);

                    /*
                     * TODO: Remove this file at the end of the level.
                     */
                    ServerManager.getInstance().getLocalFS().getRootFolder().addFile(fso);

                    Terminal.getInstance().write("Download successful.", Terminal.MessageType.NORMAL);
                }

                else {
                    Terminal.getInstance().write("Cannot download folder.", Terminal.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().write("Could not find file " + file + ".", Terminal.MessageType.BAD);
    }
}