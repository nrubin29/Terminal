package me.nrubin29.terminal.level.one;

import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.fs.TextFile;
import me.nrubin29.terminal.web.WebManager;

public class Index extends TextFile {

    public Index(Folder parent) {
        super("index.html", parent, "Welcome to the Department of Defense. We are committed to protecting our country.");
    }

    @Override
    public void setText(String text) {
        super.setText(text);

        WebManager.getInstance().getWebsite("dod.gov").setContents(text);
    }
}