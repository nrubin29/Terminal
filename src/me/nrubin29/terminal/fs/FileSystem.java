package me.nrubin29.terminal.fs;

public class FileSystem {

    private final Folder rootFolder;
    private Folder currentFolder;

    public FileSystem(Folder rootFolder) {
        this.rootFolder = rootFolder;
        this.currentFolder = rootFolder;
    }

    public FileSystem() {
        this(new Folder("root", null));
    }

    public Folder getRootFolder() {
        return rootFolder;
    }

    public Folder getCurrentFolder() {
        return currentFolder;
    }

    public void setCurrentFolder(Folder f) {
        this.currentFolder = f;
    }
}