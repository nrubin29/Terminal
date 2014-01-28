package me.nrubin29.terminal.level.two;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.cmd.*;
import me.nrubin29.terminal.event.*;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.web.WebManager;
import me.nrubin29.terminal.web.Website;

public class Two extends Level {

    private TerroristServer server;

    public Two() {
        super("Terrorist");
    }

    /*
    √ Install tracker with apt-get.
    √ Try to connect; fails.
    √ Connect with web account, works.
    Add script to index.
    Open url with web command; ip added to database.
    Connect; successful.
    Delete files.
    Disconnect.
     */
    public void start() {
        Terminal.getInstance().write(
                "Alright, time for a real challenge. We have discovered a terrorist sleeper cell by the name Terror Inc. Their leader, Dave Mulligan, is a ruthless killer. " +
                "He and over 500 other members commit heinous crimes against human life every day. We have discovered that they have a somewhat open data server. " +
                "We want you to hack this server and delete the files. That should set them back a bit. Before you do anything, we need you to install a tracker program. " +
                "Please install govt-tracker so we can monitor you outside of our network. Once you install it, connect to the server. - N"
                , Terminal.MessageType.MESSAGE);

        server = new TerroristServer();
        ServerManager.getInstance().addServer(server);

        ((AptGet) (CommandParser.getInstance().getCommand("apt-get"))).addProgram("govt-tracker");

        WebManager.getInstance().addWebsite(new Website("terror.inc", "500.terrorinc", "terror.inc: Internal Terror Inc. Communication Framework"));

        for (Command cmd : CommandParser.getInstance().getCommands()) {
            if (!(cmd instanceof AptGet) && !(cmd instanceof Help)) cmd.setEnabled(false);
        }

        EventDispatcher.getInstance().registerListener(new Listener(ApplicationInstallEvent.class) {
            public void onEvent(Event event) {
                ApplicationInstallEvent e = (ApplicationInstallEvent) event;

                if (e.getApplication().equalsIgnoreCase("govt-tracker")) {
                    Terminal.getInstance().write("Good. Now we can monitor you. Proceed with connecting to the server. - N ", Terminal.MessageType.MESSAGE);

                    for (Command cmd : CommandParser.getInstance().getCommands()) {
                        cmd.setEnabled(true);
                    }

                    requestRemove();
                }
            }
        });

        EventDispatcher.getInstance().registerListener(new Listener(LoginFailEvent.class) {
            public void onEvent(Event event) {
                LoginFailEvent e = (LoginFailEvent) event;

                if (e.getUser().equalsIgnoreCase("dave")) {
                    Terminal.getInstance().write(
                            "Damn. Looks like you can't get in without whitelisting your IP. I happen to know that they have an internal webserver for communication. " +
                            "If you can gain access and change the index page to a special script, we can whitelist your IP. " +
                            "The website should be running off the same server that we tried connecting to, just under a different account. " +
                            "If you can get in, we'll tell you what to do next. - N"
                            , Terminal.MessageType.MESSAGE);
                }
            }
        });
    }

    @Override
    public void stop() {
        ServerManager.getInstance().removeServer(server);

        ((Trace) (CommandParser.getInstance().getCommand("trace"))).stopTrace();
    }
}