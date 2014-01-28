package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.event.EventDispatcher;
import me.nrubin29.terminal.event.FileRemovePreEvent;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.server.ServerManager;

public class Remove extends Command {

    public Remove() {
        super("rm", "Remove a file.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().write("Usage: rm filename", Terminal.MessageType.BAD);
            return;
        }

        String file = args[0];

        for (FileSystemObject fso : ServerManager.getInstance().getCurrentFS().getCurrentFolder().getFiles()) {
            if (fso.getName().equalsIgnoreCase(file)) {
                if (fso instanceof File) {
                    FileRemovePreEvent event = new FileRemovePreEvent((File) fso);

                    EventDispatcher.getInstance().callEvent(event);

                    if (!event.isCanceled()) {
                        fso.getParent().removeFile(fso);
                        Terminal.getInstance().write("Deleted file.", Terminal.MessageType.NORMAL);
                    }
                }

                else {
                    Terminal.getInstance().write("Attempted to remove folder.", Terminal.MessageType.BAD);
                }

                return;
            }
        }

        Terminal.getInstance().write("Could not find file with name " + file + ".", Terminal.MessageType.BAD);
    }
}