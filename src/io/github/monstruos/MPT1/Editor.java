package io.github.monstruos.MPT1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JFrame {
    private static char SEPARATOR = '.';
    private static String ZERO = "0";

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

    private History historyWindow;
    private Helper helpWindow;
    private int lastInputBase;

    public Editor() {
        setMinimumSize(new Dimension(400, 500));
        setContentPane(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        historyWindow = new History();

        inputBaseSpinner.setValue(16);
        resultBaseSpinner.setValue(16);
        lastInputBase = 16;

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
        final String prevInputValue = input.getText();
        final int precision = prevInputValue.length() - prevInputValue.indexOf('.') - 1;
        final double doubleValue = Converter.convertToDouble(prevInputValue, lastInputBase);
        final String newInputValue = Converter.convertToString(doubleValue, value, precision);
        input.setText(newInputValue);

        lastInputBase = value;

        setBaseValue(value, inputBaseSpinner, inputSlider);
    }

    private void setResultBaseValue(Integer value) {
        setBaseValue(value, resultBaseSpinner, resultSlider);
    }

    private static void setBaseValue(Integer value, JSpinner spinner, JSlider slider) {
        if (value < 2) {
            value = 2;
        }
        if (value > 16) {
            value = 16;
        }

        spinner.setValue(value);
        slider.setValue(value);
    }

    private void clear() {
        input.setText(ZERO);
    }

    private void deleteSymbolFromInput() {
        String value = input.getText();
        value = value.substring(0, value.length() - 1);

        if (value.isEmpty()) {
            value = ZERO;
        }

        input.setText(value);
    }

    private void addDigit(int digit) {
        String value = input.getText();
        final int base = inputSlider.getValue();

        if (digit < base) {
            if (value.equals(ZERO)) {
                value = "";
            }
            input.setText(value + Converter.convertDigit(digit, base));
        }
    }

    private void addSeparator() {
        final String value = input.getText();
        if (value.indexOf(SEPARATOR) == -1) {
            input.setText(value + SEPARATOR);
        }
    }

    private void execute() {
        final String inputValue = input.getText();
        final int inputBase = inputSlider.getValue();

        final int precision = inputValue.length() - inputValue.indexOf('.') - 1;

        final double value = Converter.convertToDouble(inputValue, inputBase);

        final int resultBase = resultSlider.getValue();

        final String resultValue = Converter.convertToString(value, resultBase, precision);

        historyWindow.addEntry(inputValue + ", " + inputBase + " => " + resultValue + ", " + resultBase);

        result.setText(resultValue);
    }

    public static void main(String[] args) {
        new Editor();
    }
}
