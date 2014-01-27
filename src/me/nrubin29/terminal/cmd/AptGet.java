package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.ServerManager;

import java.util.HashMap;

public class AptGet extends Command {

    public AptGet() {
        super("apt-get", "Install a program.");
    }

    private HashMap<String, Boolean> programs = new HashMap<>();

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().getGUI().write("You can only use this command on your localhost.", GUI.MessageType.BAD);
            return;
        }

        if (args.length == 0) {
            Terminal.getInstance().getGUI().write("You must specify a program name.", GUI.MessageType.BAD);
            return;
        }

        if (!programs.containsKey(args[0])) {
            Terminal.getInstance().getGUI().write("Could not find program " + args[0] + ".", GUI.MessageType.BAD);
            return;
        }

        if (programs.get(args[0])) {
            Terminal.getInstance().getGUI().write("This program has already been installed.", GUI.MessageType.BAD);
            return;
        }

        programs.put(args[0], true);

        Terminal.getInstance().getGUI().write("Installed " + args[0] + ".", GUI.MessageType.GOOD);
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