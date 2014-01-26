package me.nrubin29.terminal;

import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.level.tutorial.Tutorial;

public class Game {

    private Game() { }

    private static Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

    private GUI gui;
    private FileSystem localFS;

    private void setup() {
        this.gui = new GUI();
        this.localFS = new FileSystem();

        gui.write("booting terminal...", GUI.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        gui.write("terminal booted successfully.", GUI.MessageType.NORMAL);

        new Tutorial().start();
    }

    public GUI getGUI() {
        return gui;
    }

    public FileSystem getLocalFS() {
        return localFS;
    }

    public static void main(String[] args) {
        Game.getInstance().setup();
    }
}