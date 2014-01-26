package me.nrubin29.terminal.fs;

import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.Game;

public abstract class File extends FileSystemObject {

    public File(String name, Folder parent) {
        super(name, parent);
    }

    public static File createTextFile(String name, Folder parent, final String text) {
        return new File(name, parent) {
            public void open() {
                Game.getInstance().getGUI().write(text, GUI.MessageType.MESSAGE);
            }
        };
    }

    public abstract void open();
}