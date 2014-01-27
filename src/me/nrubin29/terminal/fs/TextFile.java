package me.nrubin29.terminal.fs;

import me.nrubin29.terminal.Terminal;

public class TextFile extends File {

    private String text;

    public TextFile(String name, Folder parent, String text) {
        super(name, parent);

        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void open() {
        Terminal.getInstance().write(text, Terminal.MessageType.MESSAGE);
    }
}