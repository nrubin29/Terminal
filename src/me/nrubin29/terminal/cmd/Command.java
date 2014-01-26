package me.nrubin29.terminal.cmd;

public abstract class Command {

    private final String name;
    private boolean enabled = true;

    public Command(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract void run(String[] args);
}