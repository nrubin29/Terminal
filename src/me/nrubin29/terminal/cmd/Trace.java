package me.nrubin29.terminal.cmd;

import me.nrubin29.terminal.Terminal;
import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.server.ServerManager;

public class Trace extends Command {

    public Trace() {
        super("trace", "Check your current trace.");
    }

    private int trace = -1;
    private Thread thread;
    private boolean shouldRun = true;

    public void run(String[] args) {
        if (ServerManager.getInstance().getCurrentServer() == null) {
            Terminal.getInstance().write("You can only use this command on a server.", Terminal.MessageType.BAD);
            return;
        }

        if (trace == -1) {
            Terminal.getInstance().write("There is no trace on you right now.", Terminal.MessageType.NORMAL);
            return;
        }

        Terminal.getInstance().write("You will be traced in " + trace + " seconds.", Terminal.MessageType.NORMAL);
    }

    public void startTrace(int time) {
        this.trace = time;

        this.thread = new Thread(new Runnable() {
            public void run() {
                while (trace > 0 && shouldRun) {
                    trace--;
                    Utils.pause(Utils.SECOND);
                }

                ServerManager.getInstance().getCurrentServer().disconnect();
                ServerManager.getInstance().setCurrentServer(null);

                Terminal.getInstance().write("You have been traced. You were kicked from the server.", Terminal.MessageType.BAD);

                trace = -1;
            }
        });

        thread.start();
    }

    public void stopTrace() {
        shouldRun = false;
    }
}