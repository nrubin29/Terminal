package me.nrubin29.terminal.fs;

public abstract class FileSystemObject {

    private final String name;
    private final Folder parent;
    private boolean hidden;

    public FileSystemObject(String name, Folder parent) {
        this.name = name;
        this.parent = parent;

        if (parent != null) parent.addFile(this);
    }

    public final String getName() {
        return name;
    }

    public final Folder getParent() {
        return parent;
    }

    public final boolean isHidden() {
        return hidden;
    }

    public final void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public abstract void open();
}