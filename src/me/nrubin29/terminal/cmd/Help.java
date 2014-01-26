package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.gui.GUI;

public class Help extends Command {

    public Help() {
        super("help", "List commands with descriptions.");
    }

    public void run(String[] args) {
        for (Command c : CommandParser.getInstance().getCommands()) {
            if (c.isEnabled()) {
                Terminal.getInstance().getGUI().write(c.getName() + " - " + c.getDescription(), GUI.MessageType.NORMAL);
            }
        }
    }
}