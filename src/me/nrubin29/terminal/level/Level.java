package me.nrubin29.terminal.level;

public abstract class Level {

    private String name;

    public Level(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public abstract void start();

    public void stop() {

    }
}