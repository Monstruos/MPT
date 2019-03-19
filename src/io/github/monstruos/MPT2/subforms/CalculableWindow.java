package io.github.monstruos.MPT2.subforms;

import io.github.monstruos.MPT2.Controller;

import javax.swing.*;

abstract class CalculableWindow extends JFrame {
    private Controller controller;
    private JTextField input;
    private JRadioButton memoryIndicator;

    protected void setController(Controller controller) {
        this.controller = controller;
    }

    protected void setInput(JTextField input) {
        this.input = input;
    }

    public void setMemoryIndicator(JRadioButton memoryIndicator) {
        this.memoryIndicator = memoryIndicator;
    }

    protected void execute() {
        controller.execute();
        input.setText(controller.getCurrentOperand());
    }

    protected void changeSign() {
        controller.changeSign();
        input.setText(controller.getCurrentOperand());
    }

    protected void applyFunction(Controller.SupportedFunction function) {
        controller.applyFunction(function);
        input.setText(controller.getCurrentOperand());
    }

    protected void setOperation(Controller.SupportedOperation operation) {
        controller.setOperation(operation);
        input.setText(controller.getCurrentOperand());
    }

    protected void reset() {
        controller.reset();
    }

    protected void clear() {
        controller.clear();
        input.setText(controller.getCurrentOperand());
    }

    protected void deleteSymbolFromInput() {
        controller.backspace();
        input.setText(controller.getCurrentOperand());
    }

    protected void addSeparator() {
        controller.addSeparator();
        input.setText(controller.getCurrentOperand());
    }

    protected void addDigit(int digit) {
        controller.addDigit(digit);
        input.setText(controller.getCurrentOperand());
    }

    protected void memClear() {
        controller.memoryClear();
        memoryIndicator.setSelected(controller.isMemoryEnabled());
    }

    protected void memRead() {
        controller.memoryRead();
        memoryIndicator.setSelected(controller.isMemoryEnabled());
    }

    protected void memSave() {
        controller.memoryWrite();
        memoryIndicator.setSelected(controller.isMemoryEnabled());
    }

    protected void memAdd() {
        controller.memoryAdd();
        memoryIndicator.setSelected(controller.isMemoryEnabled());
    }
}
