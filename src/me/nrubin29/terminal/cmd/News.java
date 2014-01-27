package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.server.ServerManager;

import java.util.ArrayList;

public class News extends Command {

    public News() {
        super("news", "Retrieve latest news update from remote data server.");
    }

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().write("You can only use this command on your localhost.", Terminal.MessageType.BAD);
            return;
        }

        ArrayList<String> lines = Utils.readRemoteFile("news");

        if (lines != null) {
            for (String line : lines) {
                if (!line.equals("<END>")) Terminal.getInstance().write(line, Terminal.MessageType.MESSAGE);
                else break;
            }
        }
    }
}