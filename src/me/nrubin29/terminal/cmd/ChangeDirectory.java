package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.server.ServerManager;

public class ChangeDirectory extends Command {

    public ChangeDirectory() {
        super("cd", "Change your working directory.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().write("Usage: cd foldername or .. to go to the current folder's parent.", Terminal.MessageType.BAD);
            return;
        }

        FileSystem fs = ServerManager.getInstance().getCurrentFS();
        String to = args[0];

        if (to.equals("..")) {
            if (fs.getCurrentFolder().getParent() == null) {
                Terminal.getInstance().write("Current folder does not have parent.", Terminal.MessageType.BAD);
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
                    Terminal.getInstance().write("Attempted to change directory to file.", Terminal.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().write("Could not find folder with name " + to + ".", Terminal.MessageType.BAD);
    }
}