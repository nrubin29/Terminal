package me.nrubin29.terminal.event;

public abstract class Listener {

    private final Class<? extends Event> eventClass;

    public Listener(Class<? extends Event> eventClass) {
        this.eventClass = eventClass;
    }

    public final Class<? extends Event> getEventClass() {
        return eventClass;
    }

    public abstract void onEvent(Event event);
}