package me.nrubin29.terminal;

import me.nrubin29.terminal.cmd.CommandParser;
import me.nrubin29.terminal.gui.SmartScroller;
import me.nrubin29.terminal.level.Level;
import me.nrubin29.terminal.level.LevelManager;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Terminal extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public enum MessageType {
		NORMAL(Color.GREEN),
        BAD(Color.RED),

        GOVT(Color.ORANGE),
        HACK(Color.WHITE),
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
	
	private static Terminal instance = new Terminal();
	
	public static Terminal getInstance() {
		return instance;
	}
	
	public static final String VERSION = "g4s3";

    private JTextPane text;
    private Filter f = new Filter();

    private String lastInput;

    private Terminal() {
        super("terminal version " + VERSION);

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
                        lastInput = text.getText().split("\n")[text.getText().split("\n").length - 1];

                        new Thread(new Runnable() {
                            public void run() {
                                if (hijack) result = lastInput;
                                else CommandParser.getInstance().parse(lastInput);
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

    private void setup() {
        write("Please choose a level to load by typing the level name. Choices:", MessageType.NORMAL);

        for (Level l : LevelManager.getInstance().getLevels()) {
            write(l.getName(), MessageType.NORMAL);
        }

        boolean worked = false;

        while (!worked) {
            if (LevelManager.getInstance().setLevel(prompt())) {
                worked = true;
            }

            else {
                write("Invalid level.", MessageType.BAD);
            }
        }
    }

    private boolean hijack = false;
    private String result = null;

    public String prompt() {
        hijack = true;

        while (result == null) {
            Utils.pause(Utils.SECOND / 10);
        }

        hijack = false;

        String localResult = result;
        result = null;
        return localResult;
    }

    public void write(String txt, MessageType t) {
        text.setFocusable(false);

        ((AbstractDocument) text.getDocument()).setDocumentFilter(null);
        text.setEditable(false);

        try {
            for (char c : txt.toCharArray()) {
                text.getDocument().insertString(text.getDocument().getLength(), String.valueOf(c), t.getAttributes());
                Utils.pause(20);
            }
            text.getDocument().insertString(text.getDocument().getLength(), "\n", null);
        }
        catch (Exception ex) { ex.printStackTrace(); }

        ((AbstractDocument) text.getDocument()).setDocumentFilter(f);
        text.setEditable(true);
        setCaret();

        text.setFocusable(true);
        text.requestFocusInWindow();
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
    
    public static void main(String[] args) {
        Terminal.getInstance().setup();
    }
}