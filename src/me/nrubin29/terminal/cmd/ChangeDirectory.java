package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.Game;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.Folder;

public class ChangeDirectory extends Command {

    public ChangeDirectory() {
        super("cd");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Game.getInstance().getGUI().write("You must specify a folder name or .. to go to the current folder's parent.", GUI.MessageType.BAD);
            return;
        }

        FileSystem fs = ServerManager.getInstance().getCurrentServer().getFileSystem();
        String to = args[0];

        if (to.equals("..")) {
            if (fs.getCurrentFolder().getParent() == null) {
                Game.getInstance().getGUI().write("Current folder does not have parent.", GUI.MessageType.BAD);
                return;
            }

            fs.setCurrentFolder(fs.getCurrentFolder().getParent());
            return;
        }

        for (FileSystemObject fso : fs.getCurrentFolder().getFiles()) {
            if (fso.getName().equalsIgnoreCase(to)) {
                if (fso instanceof Folder) {
                    fso.open();
                }

                else {
                    Game.getInstance().getGUI().write("Attempted to change directory to file.", GUI.MessageType.BAD);
                }

                return;
            }
        }

        Game.getInstance().getGUI().write("Could not find folder with name " + to + ".", GUI.MessageType.BAD);
    }
}