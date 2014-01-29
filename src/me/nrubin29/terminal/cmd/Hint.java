package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.level.LevelManager;

public class Hint extends Command {

    public Hint() {
        super("hint", "Get a hint for the current part of the current level.");
    }

    public void run(String[] args) {
    	Terminal.getInstance().write(LevelManager.getInstance().getCurrentLevel().getCurrentCheckpoint().getHint(), Terminal.MessageType.NORMAL);
    }
}