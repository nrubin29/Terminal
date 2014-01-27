package me.nrubin29.terminal.server;

import me.nrubin29.terminal.fs.FileSystem;

import java.util.ArrayList;

public abstract class Server {

    private final String ip;
    private final FileSystem fs;

    private final ArrayList<String> users;

    public Server(String ip) {
        this.ip = ip;
        this.fs = setupFS();
        this.users = new ArrayList<>();
    }

    public boolean login(String username) {
        return users.contains(username);
    }

    public final void addUser(String username) {
        users.add(username);
    }

    public abstract FileSystem setupFS();

    public final String getIP() {
        return ip;
    }

    public final FileSystem getFileSystem() {
        return fs;
    }

    public abstract void connect();

    public void disconnect() {

    }
}