package io.github.monstruos.MPT2;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel rootPanel;
    private JButton settingsButton;
    private JButton helpButton;
    private JTextField inputField;
    private JButton BSButton;
    private JButton CEButton;
    private JButton clearButton;
    private JButton MCButton;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton divButton;
    private JButton sqrButton;
    private JButton MRButton;
    private JButton MSButton;
    private JButton mButton;
    private JButton aButton;
    private JButton a4Button;
    private JButton a1Button;
    private JButton a0Button;
    private JButton bButton;
    private JButton a5Button;
    private JButton a6Button;
    private JButton mulButton;
    private JButton invButton;
    private JButton a2Button;
    private JButton a3Button;
    private JButton subButton;
    private JButton signButton;
    private JButton separatorButton;
    private JButton addButton;
    private JButton eqButton;
    private JButton cButton;
    private JButton dButton;
    private JButton eButton;
    private JButton fButton;
    private JSlider baseSlider;
    private JTextField baseField;

    public MainWindow() {
        setMinimumSize(new Dimension(500, 400));
        setContentPane(rootPanel);
        setVisible(true);
        rootPanel.requestFocusInWindow();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
