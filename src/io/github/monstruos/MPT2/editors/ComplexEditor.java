package io.github.monstruos.MPT2.editors;

import io.github.monstruos.MPT1.Converter;
import io.github.monstruos.MPT2.data.Complex;

public class ComplexEditor implements Editor<Complex> {
    private static String ZERO = "0";
    private static String CPX_SEPARATOR = Complex.SEPARATOR;
    private static String POS_CPX_SEPARATOR = '+' + CPX_SEPARATOR;
    private static String NEG_CPX_SEPARATOR = '-' + CPX_SEPARATOR;

    private String value = ZERO;
    private boolean isRealPartEditing = true;

    @Override
    public void clear() {
        value = ZERO;
        isRealPartEditing = true;
    }

    @Override
    public void backspace() {
        if (!value.startsWith("#")) {
            value = value.substring(0, value.length() - 1);

            if (isRealPartEditing) {
                if (value.isEmpty()) {
                    value = ZERO;
                }
            } else {
                if (!value.contains(CPX_SEPARATOR)) {
                    value = value.substring(0, value.length() - CPX_SEPARATOR.length());
                    isRealPartEditing = true;
                }
            }
        } else {
            clear();
        }
    }

    @Override
    public void addSeparator() {
        char FRAC_SEPARATOR = '.';

        if (!value.startsWith("#")) {
            int pos = value.indexOf(CPX_SEPARATOR);
            pos = pos == -1 ? 0 : pos + CPX_SEPARATOR.length();

            if (value.indexOf(pos, FRAC_SEPARATOR) == -1) {
                value += FRAC_SEPARATOR;
            }
        }
    }

    public void addComplexSeparator() {
        if (!value.startsWith("#")) {
            if (isRealPartEditing) {
                value += POS_CPX_SEPARATOR + '0';
                isRealPartEditing = false;
            }
        }
    }

    @Override
    public void addDigit(int digit) {
        if (value.equals(ZERO) || value.startsWith("#")) {
            clear();
            value = "";
        }

        if (!isRealPartEditing) {
            final int pos = value.indexOf(CPX_SEPARATOR) + CPX_SEPARATOR.length();
            if (value.charAt(pos) == '0') {
                value = value.substring(0, value.length() - 1);
            }
        }

        value += Converter.convertDigit(digit, 10);
    }

    @Override
    public void reset() {
        clear();
    }

    @Override
    public void changeSign() {
        if (isRealPartEditing) {
            if (value.startsWith("-")) {
                value = value.substring(1);
            } else {
                if (!value.equals(ZERO)) {
                    value = "-" + value;
                }
            }
        } else {
            if (value.contains(NEG_CPX_SEPARATOR)) {
                value = value.replace(NEG_CPX_SEPARATOR, POS_CPX_SEPARATOR);
            } else {
                value = value.replace(POS_CPX_SEPARATOR, NEG_CPX_SEPARATOR);
            }
        }
    }

    @Override
    public void setValue(String value) {
        this.value = value;
        checkIfNowValid();
    }

    private void checkIfNowValid() {
        try {
            Complex.valueOf(value);
        } catch (NumberFormatException e) {
            value = "#ERR";
        }
    }

    @Override
    public String getStringValue() {
        checkIfNowValid();
        return value;
    }

    @Override
    public Complex getNumberValue() {
        try {
            return Complex.valueOf(getStringValue());
        } catch (Exception e) {
            return Complex.valueOf(ZERO);
        }
    }
}
