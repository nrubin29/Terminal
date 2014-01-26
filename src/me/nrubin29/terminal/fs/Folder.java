package me.nrubin29.terminal.fs;

import me.nrubin29.terminal.server.ServerManager;

import java.util.ArrayList;

public class Folder extends FileSystemObject {

    private final ArrayList<FileSystemObject> objs = new ArrayList<FileSystemObject>();

    public Folder(String name, Folder parent) {
        super(name, parent);
    }

    public final ArrayList<FileSystemObject> getFiles() {
        return (ArrayList<FileSystemObject>) objs.clone();
    }

    public final void addFileSystemObject(FileSystemObject fso) {
        objs.add(fso);
    }

    public void open() {
        ServerManager.getInstance().getCurrentServer().getFileSystem().setCurrentFolder(this);
    }
}