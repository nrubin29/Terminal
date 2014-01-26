package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.FileSystemObject;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.ServerManager;

public class List extends Command {

    public List() {
        super("ls", "List files in working directory.");
    }

    public void run(String[] args) {
        FileSystem fs;

        if (ServerManager.getInstance().getCurrentServer() != null) fs = ServerManager.getInstance().getCurrentServer().getFileSystem();
        else fs = Terminal.getInstance().getLocalFS();

        for (FileSystemObject fso : fs.getCurrentFolder().getFiles()) {
            if (!fso.isHidden()) Terminal.getInstance().getGUI().write(fso.getName(), GUI.MessageType.NORMAL);
        }
    }
}