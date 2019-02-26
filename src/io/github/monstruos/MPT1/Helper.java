package io.github.monstruos.MPT1;

import javax.swing.*;
import java.awt.*;

public class Helper extends JFrame {
    private JTextPane helpText;
    private JPanel rootPanel;

    public Helper() {
        setMinimumSize(new Dimension(200, 200));
        setContentPane(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

    }
}
