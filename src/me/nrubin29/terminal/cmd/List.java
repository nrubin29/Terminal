package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.Game;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.FileSystemObject;

public class List extends Command {

    public List() {
        super("ls");
    }

    public void run(String[] args) {
        FileSystem fs;

        if (ServerManager.getInstance().getCurrentServer() != null) fs = ServerManager.getInstance().getCurrentServer().getFileSystem();
        else fs = Game.getInstance().getLocalFS();

        for (FileSystemObject fso : fs.getCurrentFolder().getFiles()) {
            if (!fso.isHidden()) Game.getInstance().getGUI().write(fso.getName(), GUI.MessageType.NORMAL);
        }
    }
}