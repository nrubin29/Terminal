package me.nrubin29.terminal.level.tutorial;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.server.ServerManager;

public class Tutorial extends Level {

    private TutorialServer server;

    public void start() {
        server = new TutorialServer();

        ServerManager.getInstance().addServer(server);

        Terminal.getInstance().getGUI().write(
                "Hello, and welcome to the United States Department of Defense. We are glad that you have decided to join us as an ethical hacker. " +
                "Your first assignment will familiarize you with our terminal. We have set up a server for our newbies to test with. " +
                "The IP address is 532.tutorial. The username is my favorite type of Italian food. " +
                "You can use the SSH command to connect to the server. Good luck, rookie. - N"
                , GUI.MessageType.MESSAGE);
    }

    @Override
    public void stop() {
        ServerManager.getInstance().removeServer(server);
    }
}