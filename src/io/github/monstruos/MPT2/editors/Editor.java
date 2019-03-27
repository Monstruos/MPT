package io.github.monstruos.MPT2.editors;

import io.github.monstruos.MPT2.data.Calculable;

public interface Editor<T extends Calculable<T>> {
    void clear();

    void backspace();

    void addSeparator();

    void addDigit(int digit);

    void reset();

    void changeSign();

    void setValue(String value);

    String getStringValue();

    T getNumberValue();
}
