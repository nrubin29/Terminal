package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.server.Server;
import me.nrubin29.terminal.server.ServerManager;

public class SSH extends Command {

    public SSH() {
        super("ssh", "Access a server.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().write("Usage: ssh user@server", Terminal.MessageType.BAD);
            return;
        }

        String full = args[0];

        if (!full.contains("@")) {
            Terminal.getInstance().write("Usage: ssh user@server", Terminal.MessageType.BAD);
            return;
        }

        String user = full.split("@")[0], ip = full.split("@")[1];
        Server server = ServerManager.getInstance().getServer(ip);

        Terminal.getInstance().write("Attempting connection to " + ip + ".", Terminal.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (server == null) {
            Terminal.getInstance().write("Could not find server at IP " + ip + ".", Terminal.MessageType.BAD);
            return;
        }

        Terminal.getInstance().write("Connection successful. Attempting login with username " + user + ".", Terminal.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (!server.login(user)) {
            Terminal.getInstance().write("Server rejected login for username " + user + ".", Terminal.MessageType.BAD);
            return;
        }

        Terminal.getInstance().write("Login successful.", Terminal.MessageType.GOOD);

        Utils.pause(Utils.SECOND);

        ServerManager.getInstance().setCurrentServer(server);
        server.connect();
    }
}