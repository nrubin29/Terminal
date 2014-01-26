package me.nrubin29.terminal.event;

import me.nrubin29.terminal.fs.File;

public class PlayerSendFileEvent extends Event {

    private File file;
    private String to;

    public PlayerSendFileEvent(File file, String to) {
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