package io.github.monstruos.MPT2.data;

import io.github.monstruos.MPT1.Converter;

import java.util.Objects;

public class BasedNumber implements Calculable<BasedNumber> {
    private static final int MIN_BASE = Converter.MIN_BASE;
    private static final int MAX_BASE = Converter.MAX_BASE;

    public static final char SEPARATOR = Converter.SEPARATOR;

    private final double number;
    private final int base;

    public BasedNumber(double number, int base) {
        if (MIN_BASE > base || base > MAX_BASE) {
            throw new IllegalArgumentException("Base must be in range [" + MIN_BASE + ", " + MAX_BASE + "]");
        }

        this.number = number;
        this.base = base;
    }

    public static BasedNumber valueOf(String number, int base) {
        return new BasedNumber(Converter.convertToDouble(number, base), base);
    }

    public static char convertDigit(int digit, int base) {
        return Converter.convertDigit(digit, base);
    }

    public static int maxPrecisionForBase(int base) {
        return Converter.maxPrecisionForBase(base);
    }

    @Override
    public Calculable<BasedNumber> add(Calculable<BasedNumber> second) {
        BasedNumber other = (BasedNumber) second;

        if (base != other.base) {
            throw new IllegalArgumentException("Terms must be in same base");
        }

        return new BasedNumber(number + other.number, base);
    }

    @Override
    public Calculable<BasedNumber> sub(Calculable<BasedNumber> second) {
        BasedNumber other = (BasedNumber) second;

        if (base != other.base) {
            throw new IllegalArgumentException("Terms must be in same base");
        }

        return new BasedNumber(number - other.number, base);
    }

    @Override
    public Calculable<BasedNumber> mul(Calculable<BasedNumber> second) {
        BasedNumber other = (BasedNumber) second;

        if (base != other.base) {
            throw new IllegalArgumentException("Terms must be in same base");
        }

        return new BasedNumber(number * other.number, base);
    }

    @Override
    public Calculable<BasedNumber> div(Calculable<BasedNumber> second) {
        BasedNumber other = (BasedNumber) second;

        if (base != other.base) {
            throw new IllegalArgumentException("Terms must be in same base");
        }

        return new BasedNumber(number / other.number, base);
    }

    @Override
    public Calculable<BasedNumber> sqr() {
        return new BasedNumber(number * number, base);
    }

    @Override
    public Calculable<BasedNumber> inv() {
        if (isZero()) {
            throw new IllegalArgumentException("Division by zero!");
        }

        return new BasedNumber(1.0 / number, base);
    }

    @Override
    public Calculable<BasedNumber> neg() {
        return new BasedNumber(-number, base);
    }

    @Override
    public Calculable<BasedNumber> zero() {
        return new BasedNumber(0, base);
    }

    @Override
    public boolean isZero() {
        return number == 0.0;
    }

    @Override
    public String toString() {
        return Converter.convertToString(number, base, maxPrecisionForBase(base)).replaceAll("0+$", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BasedNumber that = (BasedNumber) o;

        return Double.compare(that.number, number) == 0 && base == that.base;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, base);
    }
}
