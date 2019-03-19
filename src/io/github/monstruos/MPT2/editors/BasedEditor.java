package io.github.monstruos.MPT2.editors;

import io.github.monstruos.MPT2.data.BasedNumber;

public class BasedEditor implements Editor<BasedNumber> {
    private static String ZERO = "0";

    private int base;
    private String value = ZERO;

    private int precision = 0;
    private boolean isDouble = false;

    public BasedEditor(int base) {
        this.base = base;
    }

    public void changeBase(int newBase) {
        clear();
        base = newBase;
    }

    @Override
    public void setValue(String value) {
        this.value = BasedNumber.valueOf(value, base, precision).toString();
    }

    @Override
    public String getStringValue() {
        return value;
    }

    @Override
    public BasedNumber getNumberValue() {
        return BasedNumber.valueOf(getStringValue(), base, precision);
    }

    @Override
    public void clear() {
        precision = 0;
        value = ZERO;
        isDouble = false;
    }

    @Override
    public void backspace() {
        value = value.substring(0, value.length() - 1);

        precision = Math.max(0, precision - 1);
        if (precision == 0) {
            isDouble = false;
        }

        if (value.isEmpty()) {
            value = ZERO;
        }
    }

    @Override
    public void addSeparator() {
        char SEPARATOR = BasedNumber.SEPARATOR;
        if (value.indexOf(SEPARATOR) == -1) {
            value += SEPARATOR;
        }
        isDouble = true;
    }

    @Override
    public void addDigit(int digit) {
        if (digit < base && precision < BasedNumber.maxPrecisionForBase(base)) {
            if (value.equals(ZERO)) {
                value = "";
            }

            value += BasedNumber.convertDigit(digit, base);

            if (isDouble) {
                precision++;
            }
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
