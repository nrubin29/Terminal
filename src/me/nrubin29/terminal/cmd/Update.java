package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.server.ServerManager;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Update extends Command {

    public Update() {
        super("update", "Check for an update to Terminal.");
    }

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() != null) {
            Terminal.getInstance().write("You can only use this command on your localhost.", Terminal.MessageType.BAD);
            return;
        }

        String version = Utils.readRemoteFile("version").get(0);

        if (!version.equals(Terminal.VERSION)) {
            Terminal.getInstance().write("Found new version! Beginning download...", Terminal.MessageType.NORMAL);

            try {
                File jar = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());

                URL url = new URL(Utils.BASE_URL + "terminal.jar");
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(jar);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();

                Terminal.getInstance().write("Download successful! Quitting in five seconds...", Terminal.MessageType.GOOD);

                Utils.pause(Utils.SECOND * 5);

                System.exit(0);
            }

            catch (Exception e) {
                Terminal.getInstance().write("An error occurred while downloading the update.", Terminal.MessageType.BAD);
            }
        }

        else Terminal.getInstance().write("You are up to date.", Terminal.MessageType.GOOD);
    }
}