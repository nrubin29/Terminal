package me.nrubin29.terminal.fs;

import me.nrubin29.terminal.server.ServerManager;

import java.util.ArrayList;

public class Folder extends FileSystemObject {

    private final ArrayList<FileSystemObject> objs = new ArrayList<FileSystemObject>();

    public Folder(String name, Folder parent) {
        super(name, parent);
    }

    public final FileSystemObject[] getFiles() {
        return objs.toArray(new FileSystemObject[objs.size()]);
    }

    public final void addFileSystemObject(FileSystemObject fso) {
        objs.add(fso);
    }

    public void open() {
        ServerManager.getInstance().getCurrentServer().getFileSystem().setCurrentFolder(this);
    }
}