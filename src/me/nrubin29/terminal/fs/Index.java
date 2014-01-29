package me.nrubin29.terminal.fs;

import me.nrubin29.terminal.web.WebManager;

public class Index extends TextFile {
	
	private String web;

    public Index(Folder parent, String def, String web) {
        super("index.html", parent, def);
        
        this.web = web;
    }

    @Override
    public void setText(String text) {
        super.setText(text);

        WebManager.getInstance().getWebsite(web).setContents(text);
    }
}