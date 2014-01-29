package me.nrubin29.terminal.level.two;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.cmd.CommandParser;
import me.nrubin29.terminal.cmd.Trace;
import me.nrubin29.terminal.fs.File;
import me.nrubin29.terminal.fs.Folder;
import me.nrubin29.terminal.fs.Index;
import me.nrubin29.terminal.fs.TextFile;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.server.Server;

public class PrismServer extends Server {

    protected File backup;

    public PrismServer(Level level) {
        super(level, "29.prismsec");

        addUser("dave");
        addUser("web");

        Folder var = new Folder("var", fs.getRootFolder());
            Folder www = new Folder("www", var);
                new Index(www, "PrismSec: Committed to exposing government plots.", "prism.sec");
            Folder bak = new Folder("bak", fs.getRootFolder());
                backup = new TextFile("backup.bak", bak, "0&&2hd2fh&^@6eg2ge8*GD*7g872f62fe6FD^&F^&F67F&&^f26f^&EF^&Wfd");
    }

    boolean scriptRan = false;
	private boolean webLogin = false;

    public void connect() {
        Terminal.getInstance().write("PRISMSEC DATA SERVER. LEAVE IF YOU DON'T BELONG.", Terminal.MessageType.NORMAL);
        
        if (webLogin) {
        	Terminal.getInstance().write(
                    "Good, you made it in. Use your knowledge of webservers to find their index file. Make sure to read it first to get the URL. Once you're ready, set the site contents to this script:\n" +
                    "ftp connect:~/db stmnt:INSERT 829.govt INTO whitelist\n" +
                    "Once you do that, open the website and the script will run. Your IP will be whitelisted and you'll be good to login as Dave. - N"
                    , Terminal.MessageType.GOVT);

            level.nextCheckpoint();
        }
        
        else {
        	Terminal.getInstance().write(
                    "Looks like they started a trace. You'll need to move quickly because once they trace you, they can kick you. " +
                    "You can see when the trace will complete with the trace command. You need to download the backup from the server and delete it with the rm command. Hurry! - N"
                    , Terminal.MessageType.GOVT);

            level.nextCheckpoint();
        }
    }

    @Override
    public boolean login(String user) {
    	webLogin = false;
    	
        boolean good = super.login(user);

        if (!good) return false;

        if (user.equalsIgnoreCase("web")) {
        	webLogin = true;
        	return true;
        }

        Terminal.getInstance().write("Validating IP address against whitelist...", Terminal.MessageType.NORMAL);

        Utils.pause(Utils.SECOND);

        if (scriptRan) {
            Terminal.getInstance().write("IP is whitelisted. Starting trace for safety...", Terminal.MessageType.NORMAL);

            ((Trace) (CommandParser.getInstance().getCommand("trace"))).startTrace(120);

            return true;
        }

        else {
            Terminal.getInstance().write("IP is not whitelisted.", Terminal.MessageType.BAD);

            return false;
        }
    }
}