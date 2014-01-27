package me.nrubin29.terminal.level.tutorial;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.fs.TextFile;
import me.nrubin29.terminal.server.Server;

public class TutorialServer extends Server {

    public TutorialServer() {
        super("532.tutorial");

        addUser("pasta");
        addUser("validation");
    }

    public FileSystem setupFS() {
        FileSystem fs = new FileSystem();

        Folder folder = new Folder("files", fs.getRootFolder());

        new TextFile("instructions.txt", folder,
                "You're really getting the hang of this. We've hidden a file in this folder that we need you to download. " +
                "It's a text file. It is named after my favorite American food. Read the file for information about downloading. " +
                "Off you go. - N"
                );

        TextFile hidden = new TextFile("hamburger.txt", folder,
                "Glad we hired you. You're pretty quick with this. Forgot to mention we're monitoring you, so don't try anything... " +
                "Now that you found the file, use the download command to download it. " +
                "Once you have the file, disconnect with the disconnect command. Finally, send the file to validation@532.tutorial. " +
                "As always, good luck. - N"
        );

        hidden.setHidden(true);

        return fs;
    }

    public void connect() {
        Terminal.getInstance().write(
                "Good job so far. There is a file on this server that contains the next set of directions. " +
                "You can use the ls command to list all files and folders in the current folder. " +
                "You can use the cd command to change your working directory. " +
                "You can use the cat command to print the contents of a file. Good luck, rookie. - N"
                , Terminal.MessageType.MESSAGE);
    }
}