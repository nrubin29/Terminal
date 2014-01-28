package me.nrubin29.terminal.server;

import me.nrubin29.terminal.fs.FileSystem;

import java.util.ArrayList;

public class ServerManager {

    private ServerManager() { setup(); }

    private static ServerManager instance = new ServerManager();

    public static ServerManager getInstance() {
        return instance;
    }

    private Server currentServer;
    private ArrayList<Server> servers = new ArrayList<Server>();
    
    private FileSystem localFS;
    
    private void setup() {
    	this.localFS = new FileSystem();
    }

    public FileSystem getCurrentFS() {
        if (getCurrentServer() == null) return getLocalFS();
        else return getCurrentServer().getFileSystem();
    }
    
    public FileSystem getLocalFS() {
    	return localFS;
    }

    public Server getCurrentServer() {
        return currentServer;
    }

    public void setCurrentServer(Server server) {
        this.currentServer = server;
    }

    public Server getServer(String ip) {
        for (Server s : servers) {
            if (s.getIP().equalsIgnoreCase(ip)) return s;
        }

        return null;
    }

    public void addServer(Server s) {
        servers.add(s);
    }

    public void removeServer(Server s) {
        servers.remove(s);
    }
}