package me.nrubin29.terminal.gui;

import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.cmd.CommandParser;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

    public enum MessageType {
        GOOD(Color.WHITE),
        BAD(Color.RED),
        NORMAL(Color.GREEN),

        MESSAGE(Color.ORANGE),
        USER(Color.CYAN);

        private SimpleAttributeSet attributes;

        MessageType(Color color) {
            attributes = new SimpleAttributeSet();
            StyleConstants.setForeground(attributes, color);
        }

        public SimpleAttributeSet getAttributes() {
            return attributes;
        }
    }

    private JTextPane text;
    private JTextField input;

    public GUI() {
        super("terminal");

        text = new JTextPane();
        text.setEditable(false);
        text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        text.setBackground(Color.BLACK);
        text.setCaretColor(Color.GREEN);

        input = new JTextField();
        input.setBorder(null);
        input.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        input.setForeground(Color.GREEN);
        input.setBackground(Color.BLACK);
        input.setCaretColor(Color.GREEN);
        input.setMinimumSize(new Dimension(640, 20));
        input.setMaximumSize(new Dimension(640, 20));

        input.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final String in = input.getText();

                    new Thread(new Runnable() {
                        public void run() {
                            write(in, MessageType.USER);
                            CommandParser.getInstance().parse(in);
                        }
                    }).start();

                    input.setText("");
                }
            }
        });

        JScrollPane scroll = new JScrollPane(text);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        new SmartScroller(scroll);

        add(scroll);
        add(input);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void write(String txt, MessageType t) {
        input.setEnabled(false);

        try {
            for (char c : txt.toCharArray()) {
                text.getDocument().insertString(text.getDocument().getLength(), String.valueOf(c), t.getAttributes());
                Utils.pause(30);
            }
            text.getDocument().insertString(text.getDocument().getLength(), "\n", null);
        }
        catch (Exception ex) { ex.printStackTrace(); }

        input.setEnabled(true);
        input.requestFocusInWindow();
    }
}