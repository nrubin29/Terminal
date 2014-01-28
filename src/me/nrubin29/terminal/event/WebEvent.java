package me.nrubin29.terminal.event;

import me.nrubin29.terminal.web.Website;

public class WebEvent extends Event {

    private Website website;

    public WebEvent(Website website) {
        this.website = website;
    }

    public Website getWebsite() {
        return website;
    }
}