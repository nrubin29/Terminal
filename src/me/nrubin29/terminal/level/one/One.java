package me.nrubin29.terminal.level.one;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.cmd.AptGet;
import me.nrubin29.terminal.cmd.CommandParser;
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
        
        addCheckpoint(
        		"Before connecting to the server, you need to ping the given URL and install a brute force program. Once that's done, connect to the server. You have all the information you need.",
        		"ping dod.gov\napt-get bruteforce\nssh govt@284.dod"
        		);
        
        addCheckpoint(
        		"Poke around the server for the website's index file. It will be named index.html. Finally, disconnect and use the web command to check out the new dod.gov.",
        		"ls\ncd var\nls\ncd www\nls\nnano index.html New text\ndisconnect\nweb dod.gov"
        		);
    }

    public void start() {
    	super.start();
    	
        Terminal.getInstance().write(
                "Time for your first real project. We just updated out webserver and we need to make sure it's still secure. " +
                "The URL is dod.gov. You can discover the actual IP with the ping command. Next, you need to install the brute force program. " +
                "You can use apt-get to do that. I think you can guess the package name. Once brute force is installed, connect to the server with SSH. " +
                "You can use the username govt. Once you connect, the brute force program should work its magic. " +
                "If this works, you'll get more directions once you connect. - N"
                , Terminal.MessageType.GOVT);

        server = new DoDServer(this);
        ServerManager.getInstance().addServer(server);

        ((AptGet) (CommandParser.getInstance().getCommand("apt-get"))).addProgram("bruteforce");

        WebManager.getInstance().addWebsite(new Website("dod.gov", "284.dod", "Welcome to the Department of Defense. We are committed to protecting our country."));

        EventDispatcher.getInstance().registerListener(new Listener<WebEvent>(WebEvent.class) {
            public void onEvent(WebEvent e) {
                if (e.getWebsite().getUrl().equalsIgnoreCase("dod.gov") && !e.getWebsite().getContents().equals("Welcome to the Department of Defense. We are committed to protecting our country.")) {
                    Utils.pause(Utils.SECOND);

                    Terminal.getInstance().write(
                            "Good job. Looks like you hacked our webserver. Time for your next assignment... - N"
                            , Terminal.MessageType.GOVT);

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