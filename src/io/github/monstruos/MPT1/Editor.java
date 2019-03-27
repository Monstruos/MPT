package io.github.monstruos.MPT1;

public class Editor {
    private static String ZERO = "0";
    private static char SEPARATOR = Converter.SEPARATOR;
    private int inputBase;
    private int precision = 0;
    private String inputValue = ZERO;
    private boolean isDouble = false;

    private History history;

    public Editor(History history, int inputBase) {
        this.history = history;
        this.inputBase = inputBase;
    }

    public void setInputBaseValue(Integer value) {
        inputValue = convert(value);
        inputBase = value;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void clear() {
        inputValue = ZERO;
        precision = 0;
        isDouble = false;
    }

    public void backspace() {
        if (!inputValue.startsWith("#")) {
            inputValue = inputValue.substring(0, inputValue.length() - 1);

            precision = Math.max(0, precision - 1);
            if (precision == 0) {
                isDouble = false;
            }

            if (inputValue.isEmpty()) {
                inputValue = ZERO;
            }
        } else {
            clear();
        }
    }


    public void addDigit(int digit) {
        if (digit < inputBase && precision < Converter.maxPrecisionForBase(inputBase)) {
            if (inputValue.equals(ZERO) || inputValue.startsWith("#")) {
                clear();
                inputValue = "";
            }
            inputValue += Converter.convertDigit(digit, inputBase);
            if (isDouble) {
                precision++;
            }
        }
    }

    public void addSeparator() {
        if (!inputValue.startsWith("#")) {
            if (inputValue.indexOf(SEPARATOR) == -1) {
                inputValue += SEPARATOR;
            }
            isDouble = true;
        }
    }

    public String convert(int resultBase) {
        try {
            final double value = Converter.convertToDouble(inputValue, inputBase);
            final String resultValue = Converter.convertToString(value, resultBase, precision);

            history.addEntry(inputValue + ", " + inputBase + " => " + resultValue + ", " + resultBase);

            return resultValue;
        } catch (NumberFormatException e) {
            return "#ERRTLNG";
        }
    }
}
