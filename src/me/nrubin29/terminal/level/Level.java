package me.nrubin29.terminal.level;

public abstract class Level {

    private int num;

    public Level(int num) {
        this.num = num;
    }

    public int getNumber() {
        return num;
    }

    public abstract void start();

    public abstract void stop();
}