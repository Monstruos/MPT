package io.github.monstruos.MPT2.editors;

import io.github.monstruos.MPT2.Calculator;
import io.github.monstruos.MPT2.data.BasedNumber;

import static io.github.monstruos.MPT2.Calculator.Operation;

public class BasedEditor implements Editor<BasedNumber> {
    private static String ZERO = "0";
    private static char SEPARATOR = BasedNumber.SEPARATOR;
    
    private int base;
    private String leftOperand = ZERO;
    private String rightOperand = "";

    private int precision = 0;
    private boolean isDouble = false;
    private boolean currentOperandIsLeft = true;
    private boolean isOperationSet = false;

    private Calculator<BasedNumber> calculator = new Calculator<>(null, null);

    public BasedEditor(int base) {
        this.base = base;
    }

    public int getBase() {
        return base;
    }

    public void changeBase(int newBase) {
        int oldBase = base;
        int oldPrecision = precision;
        final BasedNumber oldValue = BasedNumber.valueOf(getStringValue(), oldBase, oldPrecision);

        int newPrecision = (int) Math.ceil(oldPrecision * Math.log(oldBase) / Math.log(newBase));
        final BasedNumber newValue = new BasedNumber(oldValue.getDoubleValue(), newBase, newPrecision);

        setCurrentOperand(newValue.toString());

        base = newBase;
        precision = newPrecision;
    }
    
    private void setCurrentOperand(String value) {
        if (currentOperandIsLeft) {
            leftOperand = value;
        } else {
            rightOperand = value;
        }
    }

    @Override
    public String getStringValue() {
        return currentOperandIsLeft ? leftOperand : rightOperand;
    }

    @Override
    public BasedNumber getNumberValue() {
        return BasedNumber.valueOf(getStringValue(), base, precision);
    }

    @Override
    public void clear() {
        precision = 0;
        setCurrentOperand(ZERO);
        isDouble = false;
    }

    @Override
    public void backspace() {

    }

    @Override
    public void addSeparator() {

    }

    @Override
    public void addDigit(int digit) {
        String currentOperand = getStringValue();

        if (digit < base && precision < BasedNumber.maxPrecisionForBase(base)) {
            if (currentOperand.equals(ZERO)) {
                currentOperand = "";
            }

            currentOperand += BasedNumber.convertDigit(digit, base);

            if (isDouble) {
                precision++;
            }
        }

        setCurrentOperand(currentOperand);
    }

    @Override
    public void setOperation(SupportedOperation operation) {
        BasedNumber left = BasedNumber.valueOf(leftOperand, base, precision);
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

    @Override
    public void applyFunction(SupportedFunction function) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void changeSign() {

    }

    @Override
    public void execute() {
        if (isOperationSet) {
            if (currentOperandIsLeft) {
                rightOperand = leftOperand;
            }

            BasedNumber right = BasedNumber.valueOf(rightOperand, base, precision);
            calculator.setRightOperand(right);

            calculator.apply();

            leftOperand = calculator.getLeftOperand().toString();

            currentOperandIsLeft = true;
        }
    }
}
