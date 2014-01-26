package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.ServerManager;

public class Download extends Command {

    public Download() {
        super("download", "Download a file the server to which you are currently connected.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().getGUI().write("Usage: download filename", GUI.MessageType.BAD);
            return;
        }

        if (ServerManager.getInstance().getCurrentServer() == null) {
            Terminal.getInstance().getGUI().write("You can only use the download command on servers.", GUI.MessageType.BAD);
            return;
        }

        String file = args[0];
        Folder currentFolder = ServerManager.getInstance().getCurrentServer().getFileSystem().getCurrentFolder();

        for (FileSystemObject fso : currentFolder.getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof File) {
                    Terminal.getInstance().getGUI().write("Downloading file...", GUI.MessageType.NORMAL);

                    Utils.pause(Utils.SECOND);

                    Terminal.getInstance().getLocalFS().getRootFolder().addFileSystemObject(fso);

                    Terminal.getInstance().getGUI().write("Download successful.", GUI.MessageType.GOOD);
                }

                else {
                    Terminal.getInstance().getGUI().write("Cannot download folder.", GUI.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().getGUI().write("Could not find file " + file + ".", GUI.MessageType.BAD);
    }
}