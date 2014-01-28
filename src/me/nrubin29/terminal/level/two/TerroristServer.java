package me.nrubin29.terminal.level.two;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.cmd.CommandParser;
import me.nrubin29.terminal.cmd.Trace;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.TextFile;
import me.nrubin29.terminal.server.Server;

import java.util.Calendar;

public class TerroristServer extends Server {

    public TerroristServer() {
        super("500.terrorinc");

        addUser("dave");
        addUser("web");
    }

    private boolean scriptRan = false;

    public FileSystem setupFS() {
        FileSystem fs = new FileSystem();

        Calendar c = Calendar.getInstance();

        new TextFile("target.txt", fs.getRootFolder(), "Next target: Grande Waterfall Date: " + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR));

        return fs;
    }

    public void connect() {
        Terminal.getInstance().write("TERROR INC. DATA SERVER. LEAVE IF YOU DON'T BELONG.", Terminal.MessageType.MESSAGE);
    }

    @Override
    public boolean login(String user) {
        boolean good = super.login(user);

        if (!good) return false;

        if (user.equalsIgnoreCase("web")) return true;

        Terminal.getInstance().write("Validating IP address against whitelist...", Terminal.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (scriptRan) {
            Terminal.getInstance().write("IP is whitelisted. Starting trace for safety...", Terminal.MessageType.GOOD);

            ((Trace) (CommandParser.getInstance().getCommand("trace"))).startTrace(120);

            return true;
        }

        else {
            Terminal.getInstance().write("IP is not whitelisted.", Terminal.MessageType.BAD);

            return false;
        }
    }
}