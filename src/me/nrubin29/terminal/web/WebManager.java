package me.nrubin29.terminal.web;

import java.util.ArrayList;

public class WebManager {

    private WebManager() { }

    private static WebManager instance = new WebManager();

    public static WebManager getInstance() {
        return instance;
    }

    private ArrayList<Website> websites = new ArrayList<Website>();

    public Website getWebsite(String url) {
        for (Website w : websites) {
            if (w.getUrl().equalsIgnoreCase(url)) return w;
        }

        return null;
    }

    public void addWebsite(Website website) {
        websites.add(website);
    }

    public boolean exists(String url) {
        return getWebsite(url) != null;
    }
}