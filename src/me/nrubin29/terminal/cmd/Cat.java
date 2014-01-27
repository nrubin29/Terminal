package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.TextFile;
import me.nrubin29.terminal.server.ServerManager;

public class Cat extends Command {

    public Cat() {
        super("cat", "Print the contents of a file.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().write("You must specify a file name.", Terminal.MessageType.BAD);
            return;
        }

        String file = args[0];

        for (FileSystemObject fso : ServerManager.getInstance().getCurrentFS().getCurrentFolder().getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof TextFile) {
                    fso.open();
                }

                else if (fso instanceof File) {
                    Terminal.getInstance().write("Cannot read this type of file.", Terminal.MessageType.BAD);
                }

                else {
                    Terminal.getInstance().write("Attempted to read folder.", Terminal.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().write("Could not find file with name " + file + ".", Terminal.MessageType.BAD);
    }
}