package me.nrubin29.terminal.level.one;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.cmd.AptGet;
import me.nrubin29.terminal.cmd.CommandParser;
import me.nrubin29.terminal.event.Event;
import me.nrubin29.terminal.event.EventDispatcher;
import me.nrubin29.terminal.event.Listener;
import me.nrubin29.terminal.event.WebEvent;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.level.LevelManager;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.web.WebManager;
import me.nrubin29.terminal.web.Website;

public class One extends Level {

    private DoDServer server;

    public One() {
        super("Webserver");
    }

    public void start() {
        Terminal.getInstance().write(
                "Time for your first real project. We just updated out webserver and we need to make sure it's still secure. " +
                "The URL is dod.gov. You can discover the actual IP with the ping command. Next, you need to install the brute force program. " +
                "You can use apt-get to do that. I think you can guess the package name. Once brute force is installed, connect to the server with SSH. " +
                "You can use the username govt. Once you connect, the brute force program should work its magic. " +
                "If this works, you'll get more directions once you connect. - N"
                , Terminal.MessageType.MESSAGE);

        server = new DoDServer();
        ServerManager.getInstance().addServer(server);

        ((AptGet) (CommandParser.getInstance().getCommand("apt-get"))).addProgram("bruteforce");

        WebManager.getInstance().addWebsite(new Website("dod.gov", "284.dod", "Welcome to the Department of Defense. We are committed to protecting our country."));

        EventDispatcher.getInstance().registerListener(new Listener(WebEvent.class) {
            public void onEvent(Event event) {
                WebEvent e = (WebEvent) event;

                if (e.getWebsite().getUrl().equalsIgnoreCase("dod.gov") && !e.getWebsite().getContents().equals("Welcome to the Department of Defense. We are committed to protecting our country.")) {
                    Utils.pause(Utils.SECOND);

                    Terminal.getInstance().write(
                            "Good job. Looks like you hacked our webserver. Time for your next assignment... - N"
                            , Terminal.MessageType.MESSAGE);

                    requestRemove();
                    LevelManager.getInstance().nextLevel();
                }
            }
        });
    }

    @Override
    public void stop() {
        ServerManager.getInstance().removeServer(server);

        // ((AptGet) (CommandParser.getInstance().getCommand("apt-get"))).removeProgram("bruteforce");
    }
}