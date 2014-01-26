package me.nrubin29.terminal;

public class Utils {

    public static final int SECOND = 1000;

    public static void pause(int millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
}