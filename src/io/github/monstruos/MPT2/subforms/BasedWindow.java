package io.github.monstruos.MPT2.subforms;

import javax.swing.*;

public class BasedWindow extends JFrame {
    private JPanel rootPanel;
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
    private JButton dotButton;
    private JButton addButton;
    private JButton eqButton;
    private JButton cButton;
    private JButton dButton;
    private JButton eButton;
    private JButton fButton;
    private JSlider baseSlider;

    public BasedWindow() {
        setContentPane(rootPanel);

        int startBase = 10;

        baseSlider.setValue(startBase);

        initializeListeners();
    }

    private void initializeListeners() {
        a0Button.addActionListener(e -> addDigit(0));
        a1Button.addActionListener(e -> addDigit(1));
        a2Button.addActionListener(e -> addDigit(2));
        a3Button.addActionListener(e -> addDigit(3));
        a4Button.addActionListener(e -> addDigit(4));
        a5Button.addActionListener(e -> addDigit(5));
        a6Button.addActionListener(e -> addDigit(6));
        a7Button.addActionListener(e -> addDigit(7));
        a8Button.addActionListener(e -> addDigit(8));
        a9Button.addActionListener(e -> addDigit(9));
        aButton.addActionListener(e -> addDigit(10));
        bButton.addActionListener(e -> addDigit(11));
        cButton.addActionListener(e -> addDigit(12));
        dButton.addActionListener(e -> addDigit(13));
        eButton.addActionListener(e -> addDigit(14));
        fButton.addActionListener(e -> addDigit(15));
        dotButton.addActionListener(e -> addSeparator());
        BSButton.addActionListener(e -> deleteSymbolFromInput());
        CEButton.addActionListener(e -> clear());
    }

    private void clear() {

    }

    private void deleteSymbolFromInput() {

    }

    private void addSeparator() {

    }

    private void addDigit(int i) {

    }
}
