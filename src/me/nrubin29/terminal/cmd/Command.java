package me.nrubin29.terminal.cmd;

public abstract class Command {

    private final String name, description;
    private boolean enabled = true;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract void run(String[] args);
}