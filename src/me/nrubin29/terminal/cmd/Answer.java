package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.level.LevelManager;

public class Answer extends Command {

    public Answer() {
        super("answer", "Get the answer to the current part of the current level.");
    }

    public void run(String[] args) {
    	Terminal.getInstance().write(LevelManager.getInstance().getCurrentLevel().getCurrentCheckpoint().getAnswer(), Terminal.MessageType.NORMAL);
    }
}