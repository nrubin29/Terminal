package me.nrubin29.terminal.event;

import java.util.ArrayList;

public class EventDispatcher {

    private EventDispatcher() { }

    private static EventDispatcher instance = new EventDispatcher();

    public static EventDispatcher getInstance() {
        return instance;
    }

    private ArrayList<Listener> listeners = new ArrayList<Listener>();

    public void registerListener(Listener l) {
        listeners.add(l);
    }

    public boolean callEvent(Event e) {
        boolean didCallEvent = false;
        ArrayList<Listener> remove = new ArrayList<Listener>();

        for (Listener l : listeners) {
            if (l.getEventClass().equals(e.getClass())) {
                l.onEvent(e);
                didCallEvent = true;
            }
        }

        for (Listener l : listeners) {
            if (l.shouldRemove()) remove.add(l);
        }

        listeners.removeAll(remove);

        return didCallEvent;
    }
}