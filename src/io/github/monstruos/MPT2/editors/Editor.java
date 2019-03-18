package io.github.monstruos.MPT2.editors;

public interface Editor<T> {
    enum SupportedOperation {ADD, SUB, DIV, MUL}

    enum SupportedFunction {SQR, INV}

    void clear();

    void backspace();

    void addSeparator();

    void addDigit(int digit);

    void setOperation(SupportedOperation operation);

    void applyFunction(SupportedFunction function);

    void reset();

    void changeSign();

    void execute();

    String getStringValue();

    T getNumberValue();
}
