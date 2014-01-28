package me.nrubin29.terminal.level;

import java.util.ArrayList;

public abstract class Level {

    private final String name;
    
    private final ArrayList<Checkpoint> checkpoints;
    private Checkpoint currentCheckpoint;
    private int cursor = 0;

    public Level(String name) {
        this.name = name;
        this.checkpoints = new ArrayList<Checkpoint>();
    }

    public final String getName() {
        return name;
    }
    
    public final Checkpoint getCurrentCheckpoint() {
    	return currentCheckpoint;
    }
    
    public final void addCheckpoint(String hint, String answer) {
    	checkpoints.add(new Checkpoint(hint, answer));
    }
    
    public final void nextCheckpoint() {
    	this.currentCheckpoint = checkpoints.get(cursor++);
    }

    public void start() {
    	nextCheckpoint();
    }

    public void stop() {

    }
}