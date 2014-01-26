package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.Game;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser {

    private CommandParser() { setup(); }

    private static CommandParser instance = new CommandParser();

    public static CommandParser getInstance() {
        return instance;
    }

    private ArrayList<Command> cmds = new ArrayList<Command>();

    public void setup() {
        cmds.add(new Cat());
        cmds.add(new ChangeDirectory());
        cmds.add(new Disconnect());
        cmds.add(new Download());
        cmds.add(new List());
        cmds.add(new Send());
        cmds.add(new SSH());
    }

    public void parse(String input) {
        String[] all = input.split(" ");
        String cmd = all[0];
        String[] args = Arrays.copyOfRange(all, 1, all.length);

        Command c = getCommand(cmd);

        if (c == null || !c.isEnabled()) {
            Game.getInstance().getGUI().write("Invalid command.", GUI.MessageType.BAD);
        }

        else {
            c.run(args);
        }
    }

    public Command getCommand(String name) {
        for (Command c : cmds) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }

        return null;
    }
}