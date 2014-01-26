package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.Game;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.Folder;

public class Download extends Command {

    public Download() {
        super("download");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Game.getInstance().getGUI().write("Usage: download filename", GUI.MessageType.BAD);
            return;
        }

        if (ServerManager.getInstance().getCurrentServer() == null) {
            Game.getInstance().getGUI().write("You can only use the download command on servers.", GUI.MessageType.BAD);
            return;
        }

        String file = args[0];
        Folder currentFolder = ServerManager.getInstance().getCurrentServer().getFileSystem().getCurrentFolder();

        for (FileSystemObject fso : currentFolder.getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof File) {
                    Game.getInstance().getGUI().write("Downloading file...", GUI.MessageType.NORMAL);

                    Utils.pause(Utils.SECOND);

                    Game.getInstance().getLocalFS().getRootFolder().addFileSystemObject(fso);

                    Game.getInstance().getGUI().write("Download successful.", GUI.MessageType.GOOD);
                }

                else {
                    Game.getInstance().getGUI().write("Cannot download folder.", GUI.MessageType.BAD);
                }

                return;
            }
        }

        Game.getInstance().getGUI().write("Could not find file " + file + ".", GUI.MessageType.BAD);
    }
}