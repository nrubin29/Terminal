package me.nrubin29.terminal.level.one;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.cmd.AptGet;
import me.nrubin29.terminal.cmd.CommandParser;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.fs.TextFile;
import me.nrubin29.terminal.server.Server;

public class DoDServer extends Server {

    public DoDServer() {
        super("284.dod");

        addUser("govt");
    }

    public FileSystem setupFS() {
        FileSystem fs = new FileSystem();

            Folder var = new Folder("var", fs.getRootFolder());
                Folder www = new Folder("www", var);
                    new Index(www);
                new Folder("mail", var);

            Folder usr = new Folder("usr", fs.getRootFolder());
                Folder include = new Folder("include", usr);
                    Folder video = new Folder("video", include);
                        new TextFile("hfw.h", video, "printf(\"Hello World\\n\");");

            Folder sys = new Folder("sys", fs.getRootFolder());
                Folder kernel = new Folder("kernet", sys);
                    new TextFile("exec", kernel, "Dbdbe3cd8987268989bYbG&F&F*FS&Yvyqfd78q2d27YU&F&Df7*fgdUYGFDu7eg28geiuwqwfh19372h!hdihd");

            Folder root = new Folder("root", fs.getRootFolder());
                new TextFile("perms.txt", root, "You do not have permission to view the files in this folder.");

            Folder home = new Folder("home", fs.getRootFolder());
                Folder dod = new Folder("dod", home);
                    Folder documents = new Folder("documents", dod);
                        new TextFile("story.txt", documents, "Once upon a time... the end.");
                    File hidden = new TextFile("hidden.txt", dod, "How did you find me? Lucky guess?"); hidden.setHidden(true);

        return fs;
    }

    public void connect() {
        Terminal.getInstance().write(
                "You made it. Poke around here to find the index file of the website. If you have any knowledge of webservers, that should be a breeze. " +
                "Once you find it, use the nano command to change the file. Once that's done, disconnect and use the web command to look at the website. " +
                "Should be pretty easy for you. - N"
                , Terminal.MessageType.MESSAGE);
    }

    @Override
    public boolean login(String user) {
        boolean good = super.login(user);

        if (!good) return false;

        Terminal.getInstance().write("Please enter your password.", Terminal.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (!((AptGet) (CommandParser.getInstance().getCommand("apt-get"))).isInstalled("bruteforce")) {
            Terminal.getInstance().write("Input timed out.", Terminal.MessageType.BAD);
            return false;
        }

        else {
            Terminal.getInstance().write("**********", Terminal.MessageType.USER);

            Utils.pause(Utils.SECOND);

            Terminal.getInstance().write("Password accepted.", Terminal.MessageType.GOOD);
            return true;
        }
    }
}