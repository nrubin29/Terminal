package me.nrubin29.terminal.web;

public class Website {

    private String url, ip, contents;

    public Website(String url, String ip, String contents) {
        this.url = url;
        this.ip = ip;
        this.contents = contents;
    }

    public String getUrl() {
        return url;
    }

    public String getIP() {
        return ip;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}