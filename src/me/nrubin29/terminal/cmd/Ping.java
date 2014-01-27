package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.gui.GUI;

import java.util.HashMap;

public class Ping extends Command {

    public Ping() {
        super("ping", "Ping a website.");

        ips.put("dod.gov", "284.dod");
    }

    private HashMap<String, String> ips = new HashMap<String, String>();

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().getGUI().write("You must specify a URL.", GUI.MessageType.BAD);
            return;
        }

        if (ips.containsKey(args[0])) {
            Terminal.getInstance().getGUI().write("PING " + args[0] + " (" + ips.get(args[0] + "): SUCCESS."), GUI.MessageType.NORMAL);
        }

        else {
            Terminal.getInstance().getGUI().write("PING " + args[0] + ": FAILED.", GUI.MessageType.NORMAL);
        }
    }
}