package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.gui.GUI;
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
            Terminal.getInstance().getGUI().write("You can only use this command on your localhost.", GUI.MessageType.BAD);
            return;
        }

        String version = Utils.readRemoteFile("version").get(0);

        if (!version.equals(Terminal.VERSION)) {
            Terminal.getInstance().getGUI().write("Found new version! Beginning download...", GUI.MessageType.NORMAL);

            try {
                File jar = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());

                URL url = new URL(Utils.BASE_URL + "terminal.jar");
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(jar);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();

                Terminal.getInstance().getGUI().write("Download successful! Quitting in five seconds...", GUI.MessageType.GOOD);

                Utils.pause(Utils.SECOND * 5);

                System.exit(0);
            }

            catch (Exception e) {
                Terminal.getInstance().getGUI().write("An error occurred while downloading the update.", GUI.MessageType.BAD);
            }
        }

        else Terminal.getInstance().getGUI().write("You are up to date.", GUI.MessageType.GOOD);
    }
}