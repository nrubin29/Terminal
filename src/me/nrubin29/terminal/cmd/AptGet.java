package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.server.ServerManager;

import java.util.HashMap;

public class AptGet extends Command {

    public AptGet() {
        super("apt-get", "Install a program.");
    }

    private HashMap<String, Boolean> programs = new HashMap<String, Boolean>();

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().write("You can only use this command on your localhost.", Terminal.MessageType.BAD);
            return;
        }

        if (args.length == 0) {
            Terminal.getInstance().write("You must specify a program name.", Terminal.MessageType.BAD);
            return;
        }

        if (!programs.containsKey(args[0])) {
            Terminal.getInstance().write("Could not find program " + args[0] + ".", Terminal.MessageType.BAD);
            return;
        }

        if (programs.get(args[0])) {
            Terminal.getInstance().write("This program has already been installed.", Terminal.MessageType.BAD);
            return;
        }

        Terminal.getInstance().write("Installing " + args[0] + "...", Terminal.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        programs.put(args[0], true);

        Terminal.getInstance().write("Installed " + args[0] + ".", Terminal.MessageType.GOOD);
    }

    public boolean isInstalled(String program) {
        return programs.get(program);
    }

    public void addProgram(String program) {
        programs.put(program, false);
    }

    public void removeProgram(String program) {
        programs.remove(program);
    }
}