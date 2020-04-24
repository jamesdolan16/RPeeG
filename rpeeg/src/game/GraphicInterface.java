package game;

import javax.swing.*;

public class GraphicInterface {

    private JPanel _mainPanel;
    private JTextPane _viewPane;
    private JTextPane _promptPane;
    private JPanel _viewPanel;

    public JPanel MainPanel() { return _mainPanel; }
    public JTextPane ViewPane(){ return _viewPane; }
    public JTextPane PromptPane(){ return _promptPane; }
}
