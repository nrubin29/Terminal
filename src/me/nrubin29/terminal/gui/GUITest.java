package me.nrubin29.terminal.gui;

import me.nrubin29.terminal.Utils;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.awt.*;

public class GUITest extends JFrame {

    private JPanel panel;

    public GUITest() {
        super("terminal");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        new SmartScroller(scroll);

        add(scroll);

        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void write(String txt, GUI.MessageType t) {
        JTextPane text = new JTextPane();
        text.setEditable(false);
        text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        text.setForeground(Color.GREEN);
        text.setBackground(Color.BLACK);
        text.setCaretColor(Color.GREEN);

        panel.add(text);

        try {
            for (char c : txt.toCharArray()) {
                text.getDocument().insertString(text.getDocument().getLength(), String.valueOf(c), t.getAttributes());
                Utils.pause(30);
            }
            text.getDocument().insertString(text.getDocument().getLength(), "\n", null);
        }
        catch (Exception ex) { ex.printStackTrace(); }

        int textHeight = text.getFontMetrics(text.getFont()).getHeight();

        int totalCharacters = text.getText().length();
        int lineCount = (totalCharacters == 0) ? 1 : 0;

        try {
            int offset = totalCharacters;
            while (offset > 0) {
                offset = Utilities.getRowStart(text, offset) - 1;
                lineCount++;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        text.setMaximumSize(new Dimension(640, textHeight * lineCount));
    }
}