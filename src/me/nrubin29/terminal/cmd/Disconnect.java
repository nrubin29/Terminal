package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Game;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.ServerManager;

public class Disconnect extends Command {

    public Disconnect() {
        super("disconnect");
    }

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() == null) {
            Game.getInstance().getGUI().write("You are not connected to a server.", GUI.MessageType.BAD);
            return;
        }

        ServerManager.getInstance().getCurrentServer().disconnect();
        ServerManager.getInstance().setCurrentServer(null);

        Game.getInstance().getGUI().write("Disconnected.", GUI.MessageType.GOOD);
    }
}