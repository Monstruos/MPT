package io.github.monstruos.MPT2.editors;

import io.github.monstruos.MPT2.data.Fraction;

public class FractionEditor implements Editor<Fraction> {
    private static String ZERO = "0";

    private String value = ZERO;

    public FractionEditor() {}

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
            char SEPARATOR = Fraction.SEPARATOR;
            if (value.indexOf(SEPARATOR) == -1) {
                value += SEPARATOR;
            }
        }
    }

    @Override
    public void addDigit(int digit) {
        if (value.equals(ZERO) || value.startsWith("#")) {
            clear();
            value = "";
        }

        value += Fraction.convertDigit(digit);
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
            if (value.indexOf(Fraction.SEPARATOR) == (value.length() - 1)) {
                Fraction.valueOf(value + "1");
            } else {
                Fraction.valueOf(value);
            }
        } catch (NumberFormatException e) {
            value = "#ERR";
        }
    }

    @Override
    public Fraction getNumberValue() {
        try {
            return Fraction.valueOf(getStringValue());
        } catch (Exception e) {
            return Fraction.valueOf(ZERO);
        }
    }
}
