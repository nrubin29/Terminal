package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.web.WebManager;

public class Ping extends Command {

    public Ping() {
        super("ping", "Ping a website.");
    }

    public void run(String[] args) {
        if (args.length == 0) {
            Terminal.getInstance().write("Usage: ping url", Terminal.MessageType.BAD);
            return;
        }

        if (WebManager.getInstance().exists(args[0])) {
            Terminal.getInstance().write("PING " + args[0] + " (" + WebManager.getInstance().getWebsite(args[0]).getIP() + "): SUCCESS.", Terminal.MessageType.GOOD);
        }

        else {
            Terminal.getInstance().write("PING " + args[0] + ": FAILED.", Terminal.MessageType.BAD);
        }
    }
}