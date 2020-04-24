package game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ViewKeyListener implements KeyListener {

    private ScreenDrawer _out;
    private JTextPane _viewPane;
    private char _currentKey;

    public char CurrentKey() { return _currentKey; }
    public void SetCurrentKey(char val) { _currentKey = val; }

    public ViewKeyListener(ScreenDrawer out){
        _out = out;
        _viewPane = _out.gui.ViewPane();
        _viewPane.addKeyListener(this);
    }

    public void keyPressed(KeyEvent e) {
        try {
            _currentKey = e.getKeyChar();
        } catch (Exception exception) {
            _out.printError(exception.toString());
        }
    }

    public void keyReleased(KeyEvent e){
        //Nothing
    }

    public void keyTyped(KeyEvent e){
        //Nothing
    }
}
