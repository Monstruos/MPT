package io.github.monstruos.MPT2.editors;

import io.github.monstruos.MPT2.data.BasedNumber;

public class BasedEditor implements Editor<BasedNumber> {
    private static String ZERO = "0";

    private int base;
    private String value = ZERO;

    public BasedEditor(int base) {
        this.base = base;
    }

    public void changeBase(int newBase) {
        clear();
        base = newBase;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
        checkIfNowValid();
    }

    @Override
    public String getStringValue() {
        checkIfNowValid();
        return value;
    }

    private void checkIfNowValid() {
        try {
            BasedNumber.valueOf(value, base);
        } catch (NumberFormatException e) {
            value = "#ERR";
        }
    }

    @Override
    public BasedNumber getNumberValue() {
        try {
            return BasedNumber.valueOf(getStringValue(), base);
        } catch (Exception e) {
            return BasedNumber.valueOf(ZERO, base);
        }
    }

    @Override
    public void clear() {
        value = ZERO;
    }

    @Override
    public void backspace() {
        if (!value.startsWith("#")) {
            value = value.substring(0, value.length() - 1);

            if (value.isEmpty() || value.equals("-")) {
                value = ZERO;
            }
        } else {
            clear();
        }
    }

    @Override
    public void addSeparator() {
        if (!value.startsWith("#")) {
            char SEPARATOR = BasedNumber.SEPARATOR;
            if (value.indexOf(SEPARATOR) == -1) {
                value += SEPARATOR;
            }
        }
    }

    @Override
    public void addDigit(int digit) {
        if (digit < base) {
            if (value.equals(ZERO) || value.startsWith("#")) {
                clear();
                value = "";
            }

            value += BasedNumber.convertDigit(digit, base);
        }
    }

    @Override
    public void reset() {
        clear();
    }

    @Override
    public void changeSign() {
        if (value.startsWith("-")) {
            value = value.substring(1);
        } else {
            if (!value.equals(ZERO)) {
                value = "-" + value;
            }
        }
    }
}
