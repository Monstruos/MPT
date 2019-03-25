package io.github.monstruos.MPT2.subforms;

import io.github.monstruos.MPT2.Controller;
import io.github.monstruos.MPT2.data.Fraction;
import io.github.monstruos.MPT2.editors.FractionEditor;

import javax.swing.*;

public class FractionWindow extends CalculableWindow {
    private JTextField inputField;
    private JButton BSButton;
    private JButton CEButton;
    private JButton clearButton;
    private JButton addButton;
    private JButton subButton;
    private JButton divButton;
    private JButton mulButton;
    private JButton invButton;
    private JButton sqrButton;
    private JButton MCButton;
    private JButton MRButton;
    private JButton MSButton;
    private JButton MPlusButton;
    private JButton a0Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton slashButton;
    private JButton eqButton;
    private JButton signButton;
    private JRadioButton mIndicator;
    private JPanel rootPanel;

    private FractionEditor editor = new FractionEditor();
    private Controller<Fraction> controller = new Controller<>(editor);

    public FractionWindow() {
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

        slashButton.addActionListener(e -> addSeparator());
        signButton.addActionListener(e -> changeSign());

        BSButton.addActionListener(e -> deleteSymbolFromInput());
        CEButton.addActionListener(e -> clear());
        clearButton.addActionListener(e -> reset());

        addButton.addActionListener(e -> setOperation(Controller.SupportedOperation.ADD));
        subButton.addActionListener(e -> setOperation(Controller.SupportedOperation.SUB));
        mulButton.addActionListener(e -> setOperation(Controller.SupportedOperation.MUL));
        divButton.addActionListener(e -> setOperation(Controller.SupportedOperation.DIV));

        invButton.addActionListener(e -> applyFunction(Controller.SupportedFunction.INV));
        sqrButton.addActionListener(e -> applyFunction(Controller.SupportedFunction.SQR));

        MCButton.addActionListener(e -> memClear());
        MRButton.addActionListener(e -> memRead());
        MSButton.addActionListener(e -> memSave());
        MPlusButton.addActionListener(e -> memAdd());

        eqButton.addActionListener(e -> execute());
    }
}
