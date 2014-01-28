package me.nrubin29.terminal.event;

import me.nrubin29.terminal.fs.File;

public class FileSendEvent extends Event {

    private File file;
    private String to;

    public FileSendEvent(File file, String to) {
        this.file = file;
        this.to = to;
    }

    public File getFile() {
        return file;
    }

    public String getTo() {
        return to;
    }
}