package me.nrubin29.terminal.level;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.gui.GUI;
import me.nrubin29.terminal.level.tutorial.Tutorial;

import java.util.ArrayList;

public class LevelManager {

    private LevelManager() { setup(); }

    private static LevelManager instance = new LevelManager();

    public static LevelManager getInstance() {
        return instance;
    }

    private ArrayList<Level> levels = new ArrayList<>();
    private int index;
    private Level currentLevel;

    public void setup() {
        levels.add(new Tutorial());

        index = 0;
    }

    public void nextLevel() {
        if (currentLevel != null) currentLevel.stop();

        Terminal.getInstance().getGUI().write("booting terminal...", GUI.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        Terminal.getInstance().getGUI().write("terminal booted successfully.", GUI.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        Level newLevel = levels.get(index++);
        this.currentLevel = newLevel;
        if (newLevel != null) newLevel.start();
    }
}