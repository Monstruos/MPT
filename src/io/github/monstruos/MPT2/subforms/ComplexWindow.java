package io.github.monstruos.MPT2.subforms;

import io.github.monstruos.MPT2.Controller;
import io.github.monstruos.MPT2.Controller.SupportedFunction;
import io.github.monstruos.MPT2.Controller.SupportedOperation;
import io.github.monstruos.MPT2.data.Complex;
import io.github.monstruos.MPT2.editors.ComplexEditor;

import javax.swing.*;

public class ComplexWindow extends CalculableWindow {
    private JPanel rootPanel;
    private JRadioButton mIndicator;
    private JTextField inputField;
    private JButton BSButton;
    private JButton CEButton;
    private JButton clearButton;
    private JButton MCButton;
    private JButton sqrButton;
    private JButton MPlusButton;
    private JButton MSButton;
    private JButton MRButton;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton divButton;
    private JButton mulButton;
    private JButton subButton;
    private JButton addButton;
    private JButton eqButton;
    private JButton invButton;
    private JButton a4Button;
    private JButton a2Button;
    private JButton iButton;
    private JButton a5Button;
    private JButton a0Button;
    private JButton a3Button;
    private JButton a6Button;
    private JButton a1Button;
    private JButton signButton;
    private JButton dotButton;

    private ComplexEditor editor = new ComplexEditor();
    private Controller<Complex> controller = new Controller<>(editor);

    public ComplexWindow() {
        setContentPane(rootPanel);

        setController(controller);
        setInput(inputField);
        setMemoryIndicator(mIndicator);

        reset();

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

        iButton.addActionListener(e -> addComplexSeparator());
        dotButton.addActionListener(e -> addSeparator());
        signButton.addActionListener(e -> changeSign());

        BSButton.addActionListener(e -> deleteSymbolFromInput());
        CEButton.addActionListener(e -> clear());
        clearButton.addActionListener(e -> reset());

        addButton.addActionListener(e -> setOperation(SupportedOperation.ADD));
        subButton.addActionListener(e -> setOperation(SupportedOperation.SUB));
        mulButton.addActionListener(e -> setOperation(SupportedOperation.MUL));
        divButton.addActionListener(e -> setOperation(SupportedOperation.DIV));

        invButton.addActionListener(e -> applyFunction(SupportedFunction.INV));
        sqrButton.addActionListener(e -> applyFunction(SupportedFunction.SQR));

        MCButton.addActionListener(e -> memClear());
        MRButton.addActionListener(e -> memRead());
        MSButton.addActionListener(e -> memSave());
        MPlusButton.addActionListener(e -> memAdd());

        eqButton.addActionListener(e -> execute());
    }

    private void addComplexSeparator() {
        editor.addComplexSeparator();
        inputField.setText(controller.getCurrentOperand());
    }
}
