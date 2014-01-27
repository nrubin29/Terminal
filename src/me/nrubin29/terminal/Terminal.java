package me.nrubin29.terminal;

import me.nrubin29.terminal.fs.FileSystem;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.level.LevelManager;

public class Terminal {

    public static final String VERSION = "0.0.1";

    private Terminal() { }

    private static Terminal instance = new Terminal();

    public static Terminal getInstance() {
        return instance;
    }

    private GUI gui;
    private FileSystem localFS;

    private void setup() {
        this.gui = new GUI();
        this.localFS = new FileSystem();

        LevelManager.getInstance().nextLevel();
    }

    public GUI getGUI() {
        return gui;
    }

    public FileSystem getLocalFS() {
        return localFS;
    }

    public static void main(String[] args) {
        Terminal.getInstance().setup();
    }
}