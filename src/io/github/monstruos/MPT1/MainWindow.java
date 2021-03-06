package io.github.monstruos.MPT1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {

    private JButton historyButton;
    private JButton helpButton;
    private JTextField input;
    private JSlider inputSlider;
    private JSpinner inputBaseSpinner;
    private JTextField result;
    private JSlider resultSlider;
    private JSpinner resultBaseSpinner;
    private JButton a0Button;
    private JButton a1Button;
    private JButton cButton;
    private JButton dButton;
    private JButton a9Button;
    private JButton a8Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a2Button;
    private JButton a6Button;
    private JButton aButton;
    private JButton eButton;
    private JButton a7Button;
    private JButton a3Button;
    private JButton bButton;
    private JButton fButton;
    private JButton BSButton;
    private JButton executeButton;
    private JButton CEButton;
    private JButton dotButton;
    private JPanel rootPanel;

    private Editor editor;

    private History historyWindow;
    private Helper helpWindow;

    public MainWindow() {
        setMinimumSize(new Dimension(400, 500));
        setContentPane(rootPanel);
        setVisible(true);
        rootPanel.requestFocusInWindow();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        historyWindow = new History();

        int startBase = 16;

        editor = new Editor(historyWindow, startBase);
        setInputBaseValue(startBase);
        setResultBaseValue(startBase);

        initializeListeners();
    }

    private void initializeListeners() {
        executeButton.addActionListener(e -> execute());
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
        inputBaseSpinner.addChangeListener(e -> setInputBaseValue((Integer) inputBaseSpinner.getValue()));
        resultBaseSpinner.addChangeListener(e -> setResultBaseValue((Integer) resultBaseSpinner.getValue()));
        inputSlider.addChangeListener(e -> setInputBaseValue(inputSlider.getValue()));
        resultSlider.addChangeListener(e -> setResultBaseValue(resultSlider.getValue()));
        helpButton.addActionListener(e -> showHelpWindow());
        historyButton.addActionListener(e -> showHistoryWindow());
    }

    private void showHelpWindow() {
        if (helpWindow == null) {
            helpWindow = new Helper();
        } else {
            helpWindow.setVisible(true);
        }
    }

    private void showHistoryWindow() {
        historyWindow.setVisible(true);
    }


    private void setInputBaseValue(Integer value) {
        editor.setInputBaseValue(value);
        final String newInputValue = editor.getInputValue();

        input.setText(newInputValue);

        setBaseValue(value, inputBaseSpinner, inputSlider);
    }

    private void setResultBaseValue(Integer value) {
        setBaseValue(value, resultBaseSpinner, resultSlider);
    }

    private static void setBaseValue(Integer value, JSpinner spinner, JSlider slider) {
        if (value < Converter.MIN_BASE) {
            value = Converter.MIN_BASE;
        }
        if (value > Converter.MAX_BASE) {
            value = Converter.MAX_BASE;
        }

        spinner.setValue(value);
        slider.setValue(value);
    }

    private void clear() {
        editor.clear();
        input.setText(editor.getInputValue());
    }

    private void deleteSymbolFromInput() {
        editor.backspace();


        input.setText(editor.getInputValue());
    }

    private void addDigit(int digit) {
        editor.addDigit(digit);
        input.setText(editor.getInputValue());
    }

    private void addSeparator() {
        editor.addSeparator();
        input.setText(editor.getInputValue());
    }

    private void execute() {

        final int resultBase = resultSlider.getValue();

        final String resultValue = editor.convert(resultBase);
        result.setText(resultValue);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
