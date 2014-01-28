package me.nrubin29.terminal.event;

public abstract class Listener<T extends Event> {

    private final Class<? extends Event> eventClass;

    private boolean shouldRemove = false;

    public Listener(Class<? extends Event> eventClass) {
        this.eventClass = eventClass;
    }

    public final Class<? extends Event> getEventClass() {
        return eventClass;
    }

    public boolean shouldRemove() {
        return shouldRemove;
    }

    public void requestRemove() {
        this.shouldRemove = true;
    }

    public abstract void onEvent(T event);
}