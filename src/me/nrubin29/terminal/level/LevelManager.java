package me.nrubin29.terminal.level;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.level.one.One;
import me.nrubin29.terminal.level.tutorial.Tutorial;

import java.util.ArrayList;

public class LevelManager {

    private LevelManager() { setup(); }

    private static LevelManager instance = new LevelManager();

    public static LevelManager getInstance() {
        return instance;
    }

    private ArrayList<Level> levels = new ArrayList<Level>();
    private int index;
    private Level currentLevel;

    private void setup() {
        levels.add(new Tutorial());
    	levels.add(new One());

        index = 0;
    }

    public Level[] getLevels() {
        return levels.toArray(new Level[levels.size()]);
    }

    public boolean setLevel(String level) {
        boolean foundLevel = false;
        Level newLevel = null;

        for (Level l : getLevels()) {
            if (l.getName().equalsIgnoreCase(level)) {
                newLevel = l;
                foundLevel = true;
                break;
            }
        }

        if (!foundLevel) return false;

        this.currentLevel = newLevel;

        Terminal.getInstance().write("booting terminal...", Terminal.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        Terminal.getInstance().write("terminal booted successfully.", Terminal.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        newLevel.start();

        return foundLevel;
    }

    public void nextLevel() {
        if (currentLevel != null) currentLevel.stop();

        if (levels.size() == index++) {
            Terminal.getInstance().write(
                    "Congratulations! You beat all the levels in Terminal so far. " +
                    "Use the update command from your localhost to check for an update; there may be more levels. - N"
                    , Terminal.MessageType.GOOD);
        }

        else {
            setLevel(levels.get(index).getName());
        }
    }
}