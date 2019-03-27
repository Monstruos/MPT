package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.data.Calculable;
import io.github.monstruos.MPT2.editors.Editor;

import static io.github.monstruos.MPT2.Calculator.*;

public class Controller<T extends Calculable<T>> {
    public enum SupportedOperation {ADD, SUB, DIV, MUL}

    public enum SupportedFunction {SQR, INV}

    private enum State {EditingLeftOperand, OperationSet, EditingRightOperand, OperationCompleted}

    private Editor<T> editor;
    private Calculator<T> calculator;
    private Memory<T> memory;

    private State state = State.EditingLeftOperand;

    public Controller(Editor<T> editor) {
        this.editor = editor;

        final Calculable<T> initValue = editor.getNumberValue().zero();

        calculator = new Calculator<>(initValue, initValue);
        memory = new Memory<>(initValue);
    }

    private void switchOperandIfNeeded() {
        switch (state) {
            case OperationSet:
                calculator.setLeftOperand(editor.getNumberValue());
                editor.clear();

                state = State.EditingRightOperand;
                break;

            case OperationCompleted:
                editor.clear();

                state = State.EditingLeftOperand;
                break;

            case EditingLeftOperand:
            case EditingRightOperand:
                break;
        }
    }

    public void execute() {
        if (state != State.EditingLeftOperand) {
            Calculable<T> right;

            switch (state) {
                case OperationSet:
                    switchOperandIfNeeded();
                    right = calculator.getLeftOperand();
                    calculator.setRightOperand(right);
                    break;

                case EditingRightOperand:
                    right = editor.getNumberValue();
                    calculator.setRightOperand(right);
                    break;

                case OperationCompleted:
                    break;
            }

            try {
                calculator.apply();
                editor.setValue(calculator.getLeftOperand().toString());
            } catch (IllegalArgumentException e) {
                editor.setValue("ERROR");
            }

            state = State.OperationCompleted;
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
        final Calculable<T> oldValue = editor.getNumberValue();

        calculator.setRightOperand(oldValue);
        try {
            switch (function) {
                case SQR:
                    calculator.apply(Function.SQR);
                    break;

                case INV:
                    calculator.apply(Function.INV);
                    break;
            }
            final Calculable<T> newValue = calculator.getRightOperand();

            editor.setValue(newValue.toString());
        } catch (IllegalArgumentException e) {
            editor.setValue("ERROR");
        }
    }

    public void setOperation(SupportedOperation operation) {
        if (state == State.EditingRightOperand) {
            execute();
        }

        state = State.OperationSet;

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
        memory.clear();
    }

    public void memoryRead() {
        editor.setValue(memory.getNumber().toString());
    }

    public void memoryWrite() {
        memory.setNumber(editor.getNumberValue());
    }

    public void memoryAdd() {
        try {
            memory.add(editor.getNumberValue());
        } catch (Exception e) {
            memory.clear();
        }
    }

    public boolean isMemoryEnabled() {
        return memory.isEnabled();
    }
}
