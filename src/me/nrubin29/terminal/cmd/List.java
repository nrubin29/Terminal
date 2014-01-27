package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.server.ServerManager;

public class List extends Command {

    public List() {
        super("ls", "List files in working directory.");
    }

    public void run(String[] args) {
        for (FileSystemObject fso : ServerManager.getInstance().getCurrentFS().getCurrentFolder().getFiles()) {
            if (!fso.isHidden()) Terminal.getInstance().write(fso.getName(), Terminal.MessageType.NORMAL);
        }
    }
}