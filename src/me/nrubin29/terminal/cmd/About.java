package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.ServerManager;

public class About extends Command {

    public About() {
        super("about", "Information about Terminal.");
    }

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().getGUI().write("You can only use this command on your localhost.", GUI.MessageType.BAD);
            return;
        }

        Terminal.getInstance().getGUI().write("___ ____ ____ _  _ _ _  _ ____ _", GUI.MessageType.NORMAL);
        Terminal.getInstance().getGUI().write(" |  |___ |__/ |\\/| | |\\ | |__| |", GUI.MessageType.NORMAL);
        Terminal.getInstance().getGUI().write(" |  |___ |  \\ |  | | | \\| |  | |___", GUI.MessageType.NORMAL);
        Terminal.getInstance().getGUI().write("", GUI.MessageType.NORMAL);
        Terminal.getInstance().getGUI().write("by Noah Rubin - v" + Terminal.VERSION, GUI.MessageType.NORMAL);
    }
}