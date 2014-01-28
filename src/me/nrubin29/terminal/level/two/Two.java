package me.nrubin29.terminal.level.two;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.cmd.*;
import me.nrubin29.terminal.event.*;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.server.ServerManager;
import me.nrubin29.terminal.web.WebManager;
import me.nrubin29.terminal.web.Website;

public class Two extends Level {

    private PrismServer server;

    public Two() {
        super("HackingGroup");
        
        addCheckpoint(
                "Hints and answers for this level coming soon.",
                "Hints and answers for this level coming soon."
        );
    }

    /*
   	+ Install tracker with apt-get.
    + Try to connect; fails.
    + Connect with web account, works.
    + Add script to index.
    + Open url with web command; ip added to database.
    + Connect; successful.
    Download and delete files.
    Disconnect.
     */
    public void start() {
    	super.start();
    	
        Terminal.getInstance().write(
                "Alright, time for a real challange. You may have heard of PrismSec, a hacking group that calls themselves a security firm. " +
                "Their leader, Dave Edge, and the other 28 members commit acts of cyber terrorism every day. " +
                "They were recently hacked by a rival and we think that we can hack them as well. They have information vital to this country's security. " +
                "We want you to hack this server and download, then delete, their latest backup. Before you do anything, we need you to install a tracker program. " +
                "Please install govt-tracker so we can monitor you outside of our network. Once you install it, connect to the server. - N"
                , Terminal.MessageType.GOVT);

        server = new PrismServer(this);
        ServerManager.getInstance().addServer(server);

        ((AptGet) (CommandParser.getInstance().getCommand("apt-get"))).addProgram("govt-tracker");

        WebManager.getInstance().addWebsite(new Website("prism.sec", "29.prismsec", "PrismSec: Committed to exposing government plots."));

        for (Command cmd : CommandParser.getInstance().getCommands()) {
            if (!(cmd instanceof AptGet) && !(cmd instanceof Help)) cmd.setEnabled(false);
        }

        EventDispatcher.getInstance().registerListener(new Listener<ApplicationInstallEvent>(ApplicationInstallEvent.class) {
            public void onEvent(ApplicationInstallEvent e) {
                if (e.getApplication().equalsIgnoreCase("govt-tracker")) {
                    Terminal.getInstance().write("Good. Now we can monitor you. Proceed with connecting to the server. - N ", Terminal.MessageType.GOVT);

                    for (Command cmd : CommandParser.getInstance().getCommands()) {
                        cmd.setEnabled(true);
                    }

                    requestRemove();
                }
            }
        });

        EventDispatcher.getInstance().registerListener(new Listener<LoginFailEvent>(LoginFailEvent.class) {
            public void onEvent(LoginFailEvent e) {
                if (e.getUser().equalsIgnoreCase("dave")) {
                    Terminal.getInstance().write(
                            "Damn. Looks like you can't get in without whitelisting your IP. I happen to know that they have an internal webserver for communication. " +
                            "If you can change the index page to a special script, we can whitelist your IP. " +
                            "The website should be running off the same server that we tried connecting to, just under a different account. " +
                            "If you can get in, we'll tell you what to do next. - N"
                            , Terminal.MessageType.GOVT);
                }
            }
        });
        
        EventDispatcher.getInstance().registerListener(new Listener<WebEvent>(WebEvent.class) {
        	public void onEvent(WebEvent e) {
        		if (e.getWebsite().getUrl().equalsIgnoreCase("prism.sec") && e.getWebsite().getContents().equalsIgnoreCase("ftp connect:~/db stmnt:INSERT 829.govt INTO whitelist ")) {
        			Terminal.getInstance().write("Script ran successfully.", Terminal.MessageType.NORMAL);
        			server.scriptRan = true;
                    requestRemove();
        		}
        	}
        });

        EventDispatcher.getInstance().registerListener(new Listener<FileRemovePreEvent>(FileRemovePreEvent.class) {
            public void onEvent(FileRemovePreEvent e) {
                if (e.getFile().getName().startsWith("backup") && e.getFile().getName().endsWith(".bak")) {
                    if (!ServerManager.getInstance().getLocalFS().getRootFolder().containsFile(e.getFile())) {
                        Terminal.getInstance().write("You need to download the file first!", Terminal.MessageType.BAD);
                        e.setCanceled(true);
                    }

                    else requestRemove();
                }
            }
        });

        EventDispatcher.getInstance().registerListener(new Listener<DisconnectEvent>(DisconnectEvent.class) {
            public void onEvent(DisconnectEvent e) {
                if (ServerManager.getInstance().getLocalFS().getRootFolder().containsFile(server.backup)) {
                    Terminal.getInstance().write("Good job, agent. That cyberterrorist group is done for.", Terminal.MessageType.GOVT);
                    requestRemove();
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