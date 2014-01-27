package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.server.ServerManager;

public class Disconnect extends Command {

    public Disconnect() {
        super("disconnect", "Disconnect from the server to which you are currently connected.");
    }

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() == null) {
            Terminal.getInstance().write("You are not connected to a server.", Terminal.MessageType.BAD);
            return;
        }

        ServerManager.getInstance().getCurrentServer().disconnect();
        ServerManager.getInstance().setCurrentServer(null);

        Terminal.getInstance().write("Disconnected.", Terminal.MessageType.GOOD);
    }
}