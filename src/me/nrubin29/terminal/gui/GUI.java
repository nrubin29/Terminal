package me.nrubin29.terminal.gui;

import me.nrubin29.terminal.Utils;
import me.nrubin29.terminal.cmd.CommandParser;

import javax.swing.*;
import javax.swing.text.*;
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

    private Filter f = new Filter();

    public GUI() {
        super("terminal");

        text = new JTextPane();
        text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        text.setForeground(Color.GREEN);
        text.setBackground(Color.BLACK);
        text.setCaretColor(Color.GREEN);
        text.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) { e.consume(); }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        final String in = text.getText().split("\n")[text.getText().split("\n").length - 1];

                        new Thread(new Runnable() {
                            public void run() {
                                CommandParser.getInstance().parse(in);
                            }
                        }).start();
                    }
                    catch (Exception ex) { ex.printStackTrace(); }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(text);
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

    public void write(String txt, MessageType t, boolean newLine) {
        text.setFocusable(false);

        ((AbstractDocument) text.getDocument()).setDocumentFilter(null);
        text.setEditable(false);

        try {
            for (char c : txt.toCharArray()) {
                text.getDocument().insertString(text.getDocument().getLength(), String.valueOf(c), t.getAttributes());
                Utils.pause(20);
            }
            if (newLine) text.getDocument().insertString(text.getDocument().getLength(), "\n", null);
        }
        catch (Exception ex) { ex.printStackTrace(); }

        ((AbstractDocument) text.getDocument()).setDocumentFilter(f);
        text.setEditable(true);
        setCaret();

        //setFocusable(false);
        //setFocusable(true);
        //requestFocus();
        //text.requestFocusInWindow();

        text.setFocusable(true);
        text.requestFocusInWindow();
    }

    public void write(String txt, MessageType t) {
        write(txt, t, true);
    }

    private class Filter extends DocumentFilter {
        public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr)
                throws BadLocationException {
            if (getLineStartOffset(getLineOfOffset(offset)) == getLineStartOffset(getLineOfOffset(text.getDocument().getLength()))) {
                // if (getLineStartOffset(getLineOfOffset(offset)) + "terminal~ ".length() <= offset)
                super.insertString(fb, text.getDocument().getLength(), string, MessageType.USER.getAttributes());
            }
            setCaret();
        }

        public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
            if (getLineStartOffset(getLineOfOffset(offset)) == getLineStartOffset(getLineOfOffset(text.getDocument().getLength()))) {
                super.remove(fb, offset, length);
            }
            setCaret();
        }

        public void replace(final FilterBypass fb, final int offset, final int length, final String string, final AttributeSet attrs)
                throws BadLocationException {
            if (getLineStartOffset(getLineOfOffset(offset)) == getLineStartOffset(getLineOfOffset(text.getDocument().getLength()))) {
                super.replace(fb, offset, length, string, MessageType.USER.getAttributes());
            }
            setCaret();
        }
    }

    private int getLineOfOffset(int offset) throws BadLocationException {
        Document doc = text.getDocument();
        if (offset < 0) {
            throw new BadLocationException("Can't translate offset to line", -1);
        } else if (offset > doc.getLength()) {
            throw new BadLocationException("Can't translate offset to line", doc.getLength() + 1);
        } else {
            Element map = doc.getDefaultRootElement();
            return map.getElementIndex(offset);
        }
    }

    private int getLineStartOffset(int line) throws BadLocationException {
        Element map = text.getDocument().getDefaultRootElement();
        if (line < 0) {
            throw new BadLocationException("Negative line", -1);
        } else if (line > map.getElementCount()) {
            throw new BadLocationException("Given line too big", text.getDocument().getLength() + 1);
        } else {
            Element lineElem = map.getElement(line);
            return lineElem.getStartOffset();
        }
    }

    private void setCaret() {
        try { text.setCaretPosition(text.getDocument().getLength()); }
        catch (Exception e) { e.printStackTrace(); }
    }
}