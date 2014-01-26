package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.Server;
import me.nrubin29.terminal.server.ServerManager;

public class SSH extends Command {

    public SSH() {
        super("ssh", "Access a server.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().getGUI().write("Usage: ssh user@server", GUI.MessageType.BAD);
            return;
        }

        String full = args[0];

        if (!full.contains("@")) {
            Terminal.getInstance().getGUI().write("Usage: ssh user@server", GUI.MessageType.BAD);
            return;
        }

        String user = full.split("@")[0], ip = full.split("@")[1];
        Server server = ServerManager.getInstance().getServer(ip);

        Terminal.getInstance().getGUI().write("Attempting connection to " + ip + ".", GUI.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (server == null) {
            Terminal.getInstance().getGUI().write("Could not find server at IP " + ip + ".", GUI.MessageType.BAD);
            return;
        }

        Terminal.getInstance().getGUI().write("Connection successful. Attempting login with username " + user + ".", GUI.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (!server.login(user)) {
            Terminal.getInstance().getGUI().write("Server rejected username " + user + ".", GUI.MessageType.BAD);
            return;
        }

        Terminal.getInstance().getGUI().write("Login successful.", GUI.MessageType.GOOD);

        Utils.pause(Utils.SECOND);

        ServerManager.getInstance().setCurrentServer(server);
        server.connect();
    }
}