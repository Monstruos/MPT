package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.data.Calculable;

public class Memory<T extends Calculable<T>> {
    private Calculable<T> number;
    private boolean state;

    public Memory(final Calculable<T> number) {
        this.number = number;
        this.state = false;
    }

    public void write(final Calculable<T> number) {
        this.number = number;
        state = true;
    }

    public Calculable<T> get() {
        state = true;
        return number;
    }

    public boolean getState() {
        return state;
    }

    public void add(final Calculable<T> number) {
        this.number = this.number.add(number);
        state = true;
    }

    public void clear() {
        number = number.zero();
        state = false;
    }

    public String readMemoryState() {
        return state ? "On" : "Off";
    }

    public Calculable<T> getNumber() {
        return number;
    }
}
