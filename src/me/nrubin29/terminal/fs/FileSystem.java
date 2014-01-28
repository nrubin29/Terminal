package me.nrubin29.terminal.fs;

public class FileSystem {

    private final Folder rootFolder;
    private Folder currentFolder;

    public FileSystem() {
        this.rootFolder = new Folder("root", null);
        this.currentFolder = rootFolder;
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