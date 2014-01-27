package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.TextFile;
import me.nrubin29.terminal.server.ServerManager;

public class Nano extends Command {

    public Nano() {
        super("nano", "Edit a file.");
    }

    public void run(String[] args) {
        if (args.length < 2) {
            Terminal.getInstance().write("You must specify a file name and next text.", Terminal.MessageType.BAD);
            return;
        }

        FileSystem fs;

        if (ServerManager.getInstance().getCurrentServer() != null) fs = ServerManager.getInstance().getCurrentServer().getFileSystem();
        else fs = ServerManager.getInstance().getLocalFS();

        String file = args[0];

        for (FileSystemObject fso : fs.getCurrentFolder().getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof TextFile) {
                    String text = "";

                    for (int i = 1; i < args.length; i++) text += args[i] + " ";

                    ((TextFile) fso).setText(text);

                    Terminal.getInstance().write("Set text to " + text, Terminal.MessageType.GOOD);
                }

                else if (fso instanceof File) {
                    Terminal.getInstance().write("Cannot edit this type of file.", Terminal.MessageType.BAD);
                }

                else {
                    Terminal.getInstance().write("Attempted to edit folder.", Terminal.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().write("Could not find file with name " + file + ".", Terminal.MessageType.BAD);
    }
}