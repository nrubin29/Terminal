package me.nrubin29.terminal.level.tutorial;

import me.nrubin29.terminal.Game;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.event.Event;
import me.nrubin29.terminal.event.EventDispatcher;
import me.nrubin29.terminal.event.Listener;
import me.nrubin29.terminal.event.PlayerSendFileEvent;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.server.Server;

public class TutorialServer extends Server {

    public TutorialServer() {
        super("532.tutorial");

        addUser("pasta");
        addUser("validation");

        EventDispatcher.getInstance().registerListener(new Listener(PlayerSendFileEvent.class) {
            public void onEvent(Event event) {
                PlayerSendFileEvent e = (PlayerSendFileEvent) event;

                if (e.getFile().getName().equals("hamburger.txt") && e.getTo().equals("validation@532.tutorial")) {
                    Utils.pause(Utils.SECOND);

                    Game.getInstance().getGUI().write(
                            "Congratulations, rookie. You have successfully completed training. Welcome aboard."
                            , GUI.MessageType.MESSAGE);
                }
            }
        });
    }

    public FileSystem setupFS() {
        Folder root = new Folder("root", null);

        Folder folder = new Folder("files", root);

        File.createTextFile("instructions.txt", folder,
                "You're really getting the hang of this. We've hidden a file in this folder that we need you to download. " +
                "It's a text file. It is named after my favorite American food. Read the file for information about downloading. " +
                "Off you go."
                );

        File hidden = File.createTextFile("hamburger.txt", folder,
                "Glad we hired you. You're pretty quick with this. Forgot to mention we're monitoring you, so don't try anything... " +
                "Now that you found the file, use the download command to download it. " +
                "Once you have the file, disconnect with the disconnect command. Finally, send the file to validation@532.tutorial. " +
                "As always, good luck."
        );

        hidden.setHidden(true);

        return new FileSystem(root);
    }

    public void connect() {
        Game.getInstance().getGUI().write(
                "Good job so far. There is a file on this server that contains the next set of directions. " +
                "You can use the ls command to list all files and folders in the current folder. " +
                "You can use the cd command to change your working directory. " +
                "You can use the cat command to print the contents of a file. Good luck, rookie."
                , GUI.MessageType.MESSAGE);
    }

    public void disconnect() {
        //Game.getInstance().getGUI().write("Thank you for visiting!", GUI.MessageType.NORMAL);
    }
}