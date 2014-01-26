package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.Game;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.FileSystemObject;

public class Cat extends Command {

    public Cat() {
        super("cat");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Game.getInstance().getGUI().write("You must specify a file name.", GUI.MessageType.BAD);
            return;
        }

        FileSystem fs = ServerManager.getInstance().getCurrentServer().getFileSystem();
        String file = args[0];

        for (FileSystemObject fso : fs.getCurrentFolder().getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof File) {
                    fso.open();
                }

                else {
                    Game.getInstance().getGUI().write("Attempted to read folder.", GUI.MessageType.BAD);
                }

                return;
            }
        }

        Game.getInstance().getGUI().write("Could not find file with name " + file + ".", GUI.MessageType.BAD);
    }
}