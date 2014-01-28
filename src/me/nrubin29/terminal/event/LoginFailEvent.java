package me.nrubin29.terminal.event;

public class LoginFailEvent extends Event {

    private String user;

    public LoginFailEvent(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}