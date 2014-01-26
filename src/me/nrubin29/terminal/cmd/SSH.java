package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.*;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.Server;
import me.nrubin29.terminal.server.ServerManager;

public class SSH extends Command {

    public SSH() {
        super("ssh");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Game.getInstance().getGUI().write("Usage: ssh user@server", GUI.MessageType.BAD);
            return;
        }

        String full = args[0];

        if (!full.contains("@")) {
            Game.getInstance().getGUI().write("Usage: ssh user@server", GUI.MessageType.BAD);
            return;
        }

        String user = full.split("@")[0], ip = full.split("@")[1];
        Server server = ServerManager.getInstance().getServer(ip);

        Game.getInstance().getGUI().write("Attempting connection to " + ip + ".", GUI.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (server == null) {
            Game.getInstance().getGUI().write("Could not find server at IP " + ip + ".", GUI.MessageType.BAD);
            return;
        }

        Game.getInstance().getGUI().write("Connection successful. Attempting login with username " + user + ".", GUI.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (!server.login(user)) {
            Game.getInstance().getGUI().write("Server rejected username " + user + ".", GUI.MessageType.BAD);
            return;
        }

        Game.getInstance().getGUI().write("Login successful.", GUI.MessageType.GOOD);

        Utils.pause(Utils.SECOND);

        ServerManager.getInstance().setCurrentServer(server);
        server.connect();
    }
}