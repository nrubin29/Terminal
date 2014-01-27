package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.server.ServerManager;

public class About extends Command {

    public About() {
        super("about", "Information about Terminal.");
    }

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().write("You can only use this command on your localhost.", Terminal.MessageType.BAD);
            return;
        }

        Terminal.getInstance().write("___ ____ ____ _  _ _ _  _ ____ _", Terminal.MessageType.NORMAL);
        Terminal.getInstance().write(" |  |___ |__/ |\\/| | |\\ | |__| |", Terminal.MessageType.NORMAL);
        Terminal.getInstance().write(" |  |___ |  \\ |  | | | \\| |  | |___", Terminal.MessageType.NORMAL);
        Terminal.getInstance().write("", Terminal.MessageType.NORMAL);
        Terminal.getInstance().write("by Noah Rubin - Version " + Terminal.VERSION, Terminal.MessageType.NORMAL);
    }
}