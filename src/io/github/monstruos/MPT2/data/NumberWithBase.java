package io.github.monstruos.MPT2.data;

import io.github.monstruos.MPT1.Converter;

import java.util.Objects;

public class NumberWithBase implements Calculable<NumberWithBase> {
    private static final int MIN_BASE = Converter.MIN_BASE;
    private static final int MAX_BASE = Converter.MAX_BASE;
    private static final int MAX_PRECISION = Converter.MAX_PRECISION;

    private final double number;
    private final int base;
    private final int precision;

    public NumberWithBase(double number, int base, int precision) {
        if (MIN_BASE > base || base > MAX_BASE) {
            throw new IllegalArgumentException("Base must be in range [" + MIN_BASE + ", " + MAX_BASE + "]");
        }

        if (0 > precision || precision > MAX_PRECISION) {
            throw new IllegalArgumentException("Precision is not supported");
        }

        this.number = number;
        this.base = base;
        this.precision = precision;
    }

    @Override
    public NumberWithBase add(NumberWithBase other) {
        if (base != other.base) {
            throw new IllegalArgumentException("Terms must be in same base");
        }

        return new NumberWithBase(number + other.number, base, Math.max(precision, other.precision));
    }

    @Override
    public NumberWithBase sub(NumberWithBase other) {
        if (base != other.base) {
            throw new IllegalArgumentException("Terms must be in same base");
        }

        return new NumberWithBase(number - other.number, base, Math.max(precision, other.precision));
    }

    @Override
    public NumberWithBase mul(NumberWithBase other) {
        if (base != other.base) {
            throw new IllegalArgumentException("Terms must be in same base");
        }

        return new NumberWithBase(number * other.number, base, Math.max(precision, other.precision));
    }

    @Override
    public NumberWithBase div(NumberWithBase other) {
        if (base != other.base) {
            throw new IllegalArgumentException("Terms must be in same base");
        }

        return new NumberWithBase(number / other.number, base, Math.max(precision, other.precision));
    }

    @Override
    public NumberWithBase sqr() {
        return new NumberWithBase(number * number, base, precision);
    }

    @Override
    public NumberWithBase inv() {
        if (isZero()) {
            throw new IllegalArgumentException("Division by zero!");
        }

        return new NumberWithBase(1.0 / number, base, precision);
    }

    @Override
    public NumberWithBase neg() {
        return new NumberWithBase(-number, base, precision);
    }

    @Override
    public boolean isZero() {
        return number == 0.0;
    }

    @Override
    public String toString() {
        return Converter.convertToString(number, base, precision);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NumberWithBase that = (NumberWithBase) o;

        return Double.compare(that.number, number) == 0 && base == that.base && precision == that.precision;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, base, precision);
    }
}
