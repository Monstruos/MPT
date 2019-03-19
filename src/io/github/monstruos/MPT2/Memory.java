package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.data.Calculable;

public class Memory<T extends Calculable<T>> {
    private Calculable<T> number;

    public Memory(final Calculable<T> number) {
        this.number = number;
    }

    public void setNumber(final Calculable<T> number) {
        this.number = number;
    }

    public Calculable<T> getNumber() {
        return number;
    }

    public boolean isEnabled() {
        return number.isZero();
    }

    public void add(final Calculable<T> number) {
        this.number = this.number.add(number);
    }

    public void clear() {
        number = number.zero();
    }
}
