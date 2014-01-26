package me.nrubin29.terminal.server;

import java.util.ArrayList;

public class ServerManager {

    private ServerManager() { }

    private static ServerManager instance = new ServerManager();

    public static ServerManager getInstance() {
        return instance;
    }

    private Server currentServer;
    private ArrayList<Server> servers = new ArrayList<Server>();

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