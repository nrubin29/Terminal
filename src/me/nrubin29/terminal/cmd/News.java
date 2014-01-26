package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.ServerManager;

import java.util.ArrayList;

public class News extends Command {

    public News() {
        super("news", "Retrieve latest news update from remote data server.");
    }

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().getGUI().write("You can only use this command on your localhost.", GUI.MessageType.BAD);
            return;
        }

        ArrayList<String> lines = Utils.readRemoteFile("news");

        if (lines != null) {
            for (String line : lines) {
                if (!line.equals("<END>")) Terminal.getInstance().getGUI().write(line, GUI.MessageType.MESSAGE);
                else break;
            }
        }
    }
}