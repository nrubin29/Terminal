package me.nrubin29.terminal.event;

import me.nrubin29.terminal.fs.File;

public class FileRemovePreEvent extends Event {

    private File file;
    private boolean canceled;

    public FileRemovePreEvent(File file) {
        this.file = file;
        this.canceled = false;
    }

    public File getFile() {
        return file;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}