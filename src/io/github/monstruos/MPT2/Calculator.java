package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.data.Calculable;

public class Calculator<T extends Calculable<T>> {
    public enum Operation {NONE, ADD, SUB, MUL, DIV}

    public enum Function {INV, SQR}

    private Calculable<T> leftOperand;
    private Calculable<T> rightOperand;
    private Operation operation;

    public Calculator(Calculable<T> left, Calculable<T> right) {
        leftOperand = left;
        rightOperand = right;

        operation = Operation.NONE;
    }

    public Calculable<T> getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(Calculable<T> leftOperand) {
        this.leftOperand = leftOperand;
    }

    public Calculable<T> getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(Calculable<T> rightOperand) {
        this.rightOperand = rightOperand;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void clearOperation() {
        operation = Operation.NONE;
    }

    public void reset() {
        leftOperand = leftOperand.zero();
        rightOperand = rightOperand.zero();

        operation = Operation.NONE;
    }

    public void apply() {
        switch (operation) {
            case NONE:
            default:
                break;
            case ADD:
                leftOperand = leftOperand.add(rightOperand);
                break;
            case SUB:
                leftOperand = leftOperand.sub(rightOperand);
                break;
            case MUL:
                leftOperand = leftOperand.mul(rightOperand);
                break;
            case DIV:
                leftOperand = leftOperand.div(rightOperand);
                break;
        }
    }

    public void apply(Function func) {
        switch (func) {
            case INV:
                rightOperand = rightOperand.inv();
                break;
            case SQR:
                rightOperand = rightOperand.sqr();
                break;
            default:
                break;
        }
    }
}
