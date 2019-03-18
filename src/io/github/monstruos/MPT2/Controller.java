package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.data.Calculable;
import io.github.monstruos.MPT2.editors.Editor;

import static io.github.monstruos.MPT2.Calculator.Operation;

public class Controller<T extends Calculable<T>> {
    public enum SupportedOperation {ADD, SUB, DIV, MUL}

    public enum SupportedFunction {SQR, INV}

    private Editor<T> editor;
    private boolean currentOperandIsLeft = true;
    private boolean isOperationSet = false;

    private Calculator<T> calculator = new Calculator<>(null, null);

    private void switchOperandIfNeeded() {
        if (isOperationSet && currentOperandIsLeft) {
            editor.clear();
            currentOperandIsLeft = false;
        }
    }

    public void setEditor(Editor<T> editor) {
        this.editor = editor;
    }

    public void execute() {
        if (isOperationSet) {
            Calculable<T> right;

            if (currentOperandIsLeft) {
                right = calculator.getLeftOperand();
            } else {
                right = editor.getNumberValue();
            }

            calculator.setRightOperand(right);
            calculator.apply();

            editor.setValue(calculator.getLeftOperand().toString());

            currentOperandIsLeft = true;
        }
    }

    public String getCurrentOperand() {
        return editor.getStringValue();
    }

    public void changeSign() {
        switchOperandIfNeeded();
        editor.changeSign();
    }

    public void applyFunction(SupportedFunction function) {

    }

    public void setOperation(SupportedOperation operation) {
        T left = editor.getNumberValue();
        calculator.setLeftOperand(left);

        if (isOperationSet && !currentOperandIsLeft) {
            execute();
        }

        isOperationSet = true;

        switch (operation) {
            case ADD:
                calculator.setOperation(Operation.ADD);
                break;
            case SUB:
                calculator.setOperation(Operation.SUB);
                break;
            case DIV:
                calculator.setOperation(Operation.DIV);
                break;
            case MUL:
                calculator.setOperation(Operation.MUL);
                break;
        }
    }

    public void reset() {
        editor.reset();
        calculator.reset();
    }

    public void clear() {
        editor.clear();
    }

    public void backspace() {
        switchOperandIfNeeded();
        editor.backspace();
    }

    public void addSeparator() {
        switchOperandIfNeeded();
        editor.addSeparator();
    }

    public void addDigit(int digit) {
        switchOperandIfNeeded();
        editor.addDigit(digit);
    }

    public void memoryClear() {

    }

    public void memoryRead() {

    }

    public void memorySave() {

    }

    public void memoryAdd() {

    }

    public boolean isMemoryEnabled() {
        return true;
    }
}
