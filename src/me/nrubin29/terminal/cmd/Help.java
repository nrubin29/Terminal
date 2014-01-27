package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;

public class Help extends Command {

    public Help() {
        super("help", "List commands with descriptions.");
    }

    public void run(String[] args) {
        for (Command c : CommandParser.getInstance().getCommands()) {
            if (c.isEnabled()) {
                Terminal.getInstance().write(c.getName() + " - " + c.getDescription(), Terminal.MessageType.NORMAL);
            }
        }
    }
}