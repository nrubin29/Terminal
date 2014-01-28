package me.nrubin29.terminal.server;

import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.level.Level;

import java.util.ArrayList;

public abstract class Server {

	protected Level level;
	
    private final String ip;
    protected final FileSystem fs;

    private final ArrayList<String> users;

    public Server(Level level, String ip) {
    	this.level = level;
        this.ip = ip;
        this.fs = new FileSystem();
        this.users = new ArrayList<String>();
    }

    public boolean login(String username) {
        return users.contains(username);
    }

    public final void addUser(String username) {
        users.add(username);
    }

    public final String getIP() {
        return ip;
    }

    public final FileSystem getFileSystem() {
        return fs;
    }

    public void connect() { }

    /*
     * TODO: Stop trace.
     */
    public void disconnect() { }
}