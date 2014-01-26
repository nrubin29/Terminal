package me.nrubin29.terminal.fs;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.gui.GUI;

public abstract class File extends FileSystemObject {

    public File(String name, Folder parent) {
        super(name, parent);
    }

    public static File createTextFile(String name, Folder parent, final String text) {
        return new File(name, parent) {
            public void open() {
                Terminal.getInstance().getGUI().write(text, GUI.MessageType.MESSAGE);
            }
        };
    }

    public abstract void open();
}