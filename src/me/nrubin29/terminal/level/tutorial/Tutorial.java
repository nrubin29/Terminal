package me.nrubin29.terminal.level.tutorial;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.event.Event;
import me.nrubin29.terminal.event.EventDispatcher;
import me.nrubin29.terminal.event.FileSendEvent;
import me.nrubin29.terminal.event.Listener;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.level.LevelManager;
import me.nrubin29.terminal.server.ServerManager;

public class Tutorial extends Level {

    private TutorialServer server;

    public Tutorial() {
        super("Tutorial");
    }

    public void start() {
        Terminal.getInstance().write(
                "Hello, and welcome to the Department of Defense. We are glad that you have decided to join us as an ethical hacker. " +
                "Your first assignment will familiarize you with our terminal. We have set up a server for our newbies to test with. " +
                "The IP address is 532.tutorial. The username is my favorite type of Italian food. " +
                "You can use the SSH command to connect to the server. Good luck, rookie. - N"
                , Terminal.MessageType.MESSAGE);

        server = new TutorialServer();
        ServerManager.getInstance().addServer(server);

        EventDispatcher.getInstance().registerListener(new Listener(FileSendEvent.class) {
            public void onEvent(Event event) {
                FileSendEvent e = (FileSendEvent) event;

                if (e.getFile().getName().equals("hamburger.txt") && e.getTo().equals("validation@532.tutorial")) {
                    Utils.pause(Utils.SECOND);

                    Terminal.getInstance().write(
                            "Congratulations, rookie. You have successfully completed training. Welcome aboard. - N"
                            , Terminal.MessageType.MESSAGE);

                    requestRemove();
                    LevelManager.getInstance().nextLevel();
                }
            }
        });
    }

    @Override
    public void stop() {
        ServerManager.getInstance().removeServer(server);
    }
}