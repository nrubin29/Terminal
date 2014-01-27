package me.nrubin29.terminal.level.one;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.cmd.AptGet;
import me.nrubin29.terminal.cmd.CommandParser;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.server.ServerManager;

public class One extends Level {

    private DoDServer server;

    public void start() {
        Terminal.getInstance().getGUI().write(
                "Time for your first real project. We just updated out webserver and we need to make sure it's still secure. " +
                "The URL is dod.gov. You can discover the actual IP with the ping command. Next, you need to install the brute force program. " +
                "You can use apt-get to do that. I think you can guess the package name. Once brute force is installed, connect to the server with SSH. " +
                "You can use the username govt. Once you connect, the brute force program should work its magic. " +
                "If this works, you'll get more directions once you connect. - N"
                , GUI.MessageType.MESSAGE);

        server = new DoDServer();
        ServerManager.getInstance().addServer(server);

        ((AptGet) (CommandParser.getInstance().getCommand("apt-get"))).addProgram("bruteforce");
    }

    @Override
    public void stop() {
        ServerManager.getInstance().removeServer(server);

        ((AptGet) (CommandParser.getInstance().getCommand("apt-get"))).removeProgram("bruteforce");
    }
}