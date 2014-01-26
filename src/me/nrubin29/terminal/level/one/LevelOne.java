package me.nrubin29.terminal.level.one;

import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.Game;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.level.Level;

public class LevelOne extends Level {

    public LevelOne() {
        super(1);
    }

    public void start() {
        ServerManager.getInstance().addServer(new TutorialServer());

        Game.getInstance().getGUI().write(
                "Hello, and welcome to Hack Corp. We are glad that you have decided to join us as a Junior Hacker. " +
                "Your first assignment will familiarize you with our terminal. We have set up a server for our newbies to test with. " +
                "The IP address is 532.tutorial. The username is my favorite type of Italian food. " +
                "You can use the SSH command to connect to the server. Good luck, rookie."
                , GUI.MessageType.MESSAGE);
    }

    public void stop() {

    }
}