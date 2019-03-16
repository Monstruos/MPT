package io.github.monstruos.MPT2.subforms;

import io.github.monstruos.MPT2.editors.Editor;

import javax.swing.*;

abstract class CalculableWindow extends JFrame {
    private Editor editor;
    private JTextField input;
    private JRadioButton memoryIndicator;

    protected void setEditor(Editor editor) {
        this.editor = editor;
    }

    protected void setInput(JTextField input) {
        this.input = input;
    }

    public void setMemoryIndicator(JRadioButton memoryIndicator) {
        this.memoryIndicator = memoryIndicator;
    }

    protected void execute() {
        editor.execute();
        input.setText(editor.getCurrentOperand());
    }

    protected void changeSign() {
        editor.changeSign();
        input.setText(editor.getCurrentOperand());
    }

    protected void applyFunction(Editor.SupportedFunction function) {
        editor.applyFunction(function);
        input.setText(editor.getCurrentOperand());
    }

    protected void setOperation(Editor.SupportedOperation operation) {
        editor.setOperation(operation);
        input.setText(editor.getCurrentOperand());
    }

    protected void reset() {
        editor.reset();
    }

    protected void clear() {
        editor.clear();
        input.setText(editor.getCurrentOperand());
    }

    protected void deleteSymbolFromInput() {
        editor.backspace();
        input.setText(editor.getCurrentOperand());
    }

    protected void addSeparator() {
        editor.addSeparator();
        input.setText(editor.getCurrentOperand());
    }

    protected void addDigit(int digit) {
        editor.addDigit(digit);
        input.setText(editor.getCurrentOperand());
    }

    protected void memClear() {
        editor.memoryClear();
        memoryIndicator.setSelected(editor.isMemoryEnabled());
    }

    protected void memRead() {
        editor.memoryRead();
        memoryIndicator.setSelected(editor.isMemoryEnabled());
    }

    protected void memSave() {
        editor.memorySave();
        memoryIndicator.setSelected(editor.isMemoryEnabled());
    }

    protected void memAdd() {
        editor.memoryAdd();
        memoryIndicator.setSelected(editor.isMemoryEnabled());
    }
}
