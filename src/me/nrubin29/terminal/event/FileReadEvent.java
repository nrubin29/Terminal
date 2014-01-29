package me.nrubin29.terminal.event;

import me.nrubin29.terminal.fs.TextFile;

public class FileReadEvent extends Event {

	private TextFile file;
	
	public FileReadEvent(TextFile file) {
		this.file = file;
	}
	
	public TextFile getFile() {
		return file;
	}
}