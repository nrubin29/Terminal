package me.nrubin29.terminal.fs;

public abstract class File extends FileSystemObject {

    public File(String name, Folder parent) {
        super(name, parent);
    }

    public abstract void open();
}