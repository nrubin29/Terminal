package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.ServerManager;

public class Cat extends Command {

    public Cat() {
        super("cat", "Print the contents of a file.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().getGUI().write("You must specify a file name.", GUI.MessageType.BAD);
            return;
        }

        FileSystem fs;

        if (ServerManager.getInstance().getCurrentServer() != null) fs = ServerManager.getInstance().getCurrentServer().getFileSystem();
        else fs = Terminal.getInstance().getLocalFS();

        String file = args[0];

        for (FileSystemObject fso : fs.getCurrentFolder().getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof File) {
                    fso.open();
                }

                else {
                    Terminal.getInstance().getGUI().write("Attempted to read folder.", GUI.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().getGUI().write("Could not find file with name " + file + ".", GUI.MessageType.BAD);
    }
}