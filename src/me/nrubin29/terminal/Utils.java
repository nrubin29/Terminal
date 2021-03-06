package me.nrubin29.terminal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Utils {

    public static final int SECOND = 1000;
    public static final int MINUTE = SECOND * 60;

    public static void pause(int millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static final String BASE_URL = "http://terminal.x10host.com/game/";

    public static ArrayList<String> readRemoteFile(String fileName) {
        try {
            Terminal.getInstance().write("Retrieving data from remote data server.", Terminal.MessageType.NORMAL);

            URL url = new URL(BASE_URL + fileName + ".html");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            ArrayList<String> lines = new ArrayList<String>();

            while (in.ready()) {
                String line = in.readLine();
                if (line != null) lines.add(line);
                else break;
            }

            in.close();

            return lines;
        }
        catch (Exception e) {
            Terminal.getInstance().write("An error occurred while retrieving data from the remote data server.", Terminal.MessageType.BAD);
            return null;
        }
    }
}