package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.event.EventDispatcher;
import me.nrubin29.terminal.event.WebEvent;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.web.WebManager;

public class Web extends Command {

    public Web() {
        super("web", "Read a website on the internet.");
    }

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().write("You can only use this command on your localhost.", Terminal.MessageType.BAD);
            return;
        }

        if (args.length == 0) {
            Terminal.getInstance().write("Usage: web url", Terminal.MessageType.BAD);
            return;
        }

        if (!WebManager.getInstance().exists(args[0])) {
            Terminal.getInstance().write("Could not find website " + args[0] + ".", Terminal.MessageType.BAD);
            return;
        }

        Terminal.getInstance().write(WebManager.getInstance().getWebsite(args[0]).getContents(), Terminal.MessageType.GOVT);

        EventDispatcher.getInstance().callEvent(new WebEvent(WebManager.getInstance().getWebsite(args[0])));
    }
}