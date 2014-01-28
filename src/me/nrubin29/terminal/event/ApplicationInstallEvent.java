package me.nrubin29.terminal.event;

public class ApplicationInstallEvent extends Event {

    private String application;

    public ApplicationInstallEvent(String application) {
        this.application = application;
    }

    public String getApplication() {
        return application;
    }
}