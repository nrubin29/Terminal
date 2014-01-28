package me.nrubin29.terminal.level.tutorial;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.event.EventDispatcher;
import me.nrubin29.terminal.event.FileReadEvent;
import me.nrubin29.terminal.event.FileSendEvent;
import me.nrubin29.terminal.event.Listener;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.level.LevelManager;
import me.nrubin29.terminal.server.ServerManager;

public class Tutorial extends Level {

    private TutorialServer server;

    public Tutorial() {
        super("Tutorial");
        
        addCheckpoint(
        		"You were told to use the ssh command to connect to a server. You know the IP address, but you need to guess the username. Use the hint given.",
        		"ssh pasta@532.tutorial"
        		);
        
        addCheckpoint(
        		"Now that you're on the server, you need to find and read a specific instruction file. Use the ls, cd, and cat commands to help you.",
        		"ls\ncd files\nls\ncat instructions.txt"
        		);
        
        addCheckpoint(
        		"There's a hidden text file in this folder with the next set of instructions. You'll have to guess the name. Use the given hint.",
        		"cat hamburger.txt"
        		);
        
        addCheckpoint(
        		"You're almost done. You now need to use the download command to download the file, the disconnect command to leave the server, and the send command to send the file. No guessing here.",
        		"download hamburger.txt\ndisconnect\nsend hamburger.txt validation@532.tutorial"
        		);
    }

    public void start() {
    	super.start();
    	
        Terminal.getInstance().write(
                "Hello, and welcome to the Department of Defense. We are glad that you have decided to join us as an ethical hacker. " +
                "Your first assignment will familiarize you with our terminal. We have set up a server for our newbies to test with. " +
                "The IP address is 532.tutorial. The username is my favorite type of Italian food. " +
                "You can use the SSH command to connect to the server. Good luck, rookie. - N"
                , Terminal.MessageType.GOVT);

        server = new TutorialServer(this);
        ServerManager.getInstance().addServer(server);

        EventDispatcher.getInstance().registerListener(new Listener<FileReadEvent>(FileReadEvent.class) {
            boolean instRead = false, hamRead = false;

            public void onEvent(FileReadEvent e) {
                if (e.getFile().getName().equals("instructions.txt")) {
                    nextCheckpoint();
                    instRead = true;
                }

                else if (e.getFile().getName().equals("hamburger.txt")) {
                    nextCheckpoint();
                    hamRead = true;
                }

                if (instRead && hamRead) requestRemove();
            }
        });

        EventDispatcher.getInstance().registerListener(new Listener<FileSendEvent>(FileSendEvent.class) {
            public void onEvent(FileSendEvent e) {
                if (e.getFile().getName().equals("hamburger.txt") && e.getTo().equals("validation@532.tutorial")) {
                    Utils.pause(Utils.SECOND);

                    Terminal.getInstance().write(
                            "Congratulations, rookie. You have successfully completed training. Welcome aboard. - N"
                            , Terminal.MessageType.GOVT);

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