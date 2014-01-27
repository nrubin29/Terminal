package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;

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
        cmds.add(new About());
        cmds.add(new AptGet());
        cmds.add(new Cat());
        cmds.add(new ChangeDirectory());
        cmds.add(new Disconnect());
        cmds.add(new Download());
        cmds.add(new Help());
        cmds.add(new List());
        cmds.add(new Nano());
        cmds.add(new News());
        cmds.add(new Ping());
        cmds.add(new Send());
        cmds.add(new SSH());
        cmds.add(new Update());
        cmds.add(new Web());
    }

    public void parse(String input) {
        String[] all = input.split(" ");
        String cmd = all[0];
        String[] args = Arrays.copyOfRange(all, 1, all.length);

        Command c = getCommand(cmd);

        if (c == null || !c.isEnabled()) {
            Terminal.getInstance().write("Invalid command.", Terminal.MessageType.BAD);
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

    protected Command[] getCommands() {
        return cmds.toArray(new Command[cmds.size()]);
    }
}