package io.github.monstruos.MPT2.subforms;

import io.github.monstruos.MPT2.Controller;
import io.github.monstruos.MPT2.data.BasedNumber;
import io.github.monstruos.MPT2.editors.BasedEditor;

import javax.swing.*;

import static io.github.monstruos.MPT2.Controller.*;

public class BasedWindow extends CalculableWindow {
    private JPanel rootPanel;
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
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    private JButton eButton;
    private JButton fButton;
    private JButton dotButton;
    private JButton eqButton;
    private JButton signButton;
    private JRadioButton mIndicator;

    private JSlider baseSlider;
    private BasedEditor editor;
    private Controller<BasedNumber> controller;

    public BasedWindow() {
        setContentPane(rootPanel);

        int startBase = 10;
        baseSlider.setValue(startBase);

        editor = new BasedEditor(startBase);
        controller = new Controller<>(editor);

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
        aButton.addActionListener(e -> addDigit(10));
        bButton.addActionListener(e -> addDigit(11));
        cButton.addActionListener(e -> addDigit(12));
        dButton.addActionListener(e -> addDigit(13));
        eButton.addActionListener(e -> addDigit(14));
        fButton.addActionListener(e -> addDigit(15));

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

        baseSlider.addChangeListener(e -> setBaseValue(baseSlider.getValue()));
    }

    private void setBaseValue(int base) {
        editor.changeBase(base);
        inputField.setText(controller.getCurrentOperand());
    }

    @Override
    protected void setOperation(Controller.SupportedOperation operation) {
        super.setOperation(operation);
        baseSlider.setEnabled(false);
    }

    @Override
    protected void execute() {
        super.execute();
        baseSlider.setEnabled(true);
    }
}
