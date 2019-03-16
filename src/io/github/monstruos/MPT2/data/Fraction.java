package io.github.monstruos.MPT2.data;

import java.util.Objects;

public class Fraction implements Calculable<Fraction> {
    public static final Fraction ZERO = new Fraction(0, 1);

    private int numerator;
    private int denominator;

    public Fraction(final int numerator, final int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Trying to create (" + numerator + "/0) fraction");
        }

        this.numerator = numerator;
        this.denominator = denominator;

        reduce();
    }

    private static int gcd(final int a, final int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    private void reduce() {
        int gcd = gcd(numerator, denominator);

        numerator /= gcd;
        denominator /= gcd;
    }

    @Override
    public Calculable<Fraction> add(final Calculable<Fraction> second) {
        Fraction other = (Fraction) second;

        return new Fraction(
                numerator * other.denominator + denominator * other.numerator,
                denominator * other.denominator
        );
    }

    @Override
    public Calculable<Fraction> sub(final Calculable<Fraction> second) {
        Fraction other = (Fraction) second;

        return new Fraction(
                numerator * other.denominator - denominator * other.numerator,
                denominator * other.denominator
        );
    }

    @Override
    public Calculable<Fraction> mul(final Calculable<Fraction> second) {
        Fraction other = (Fraction) second;

        return new Fraction(numerator * other.numerator, denominator * other.denominator);
    }

    @Override
    public Calculable<Fraction> div(final Calculable<Fraction> second) {
        Fraction other = (Fraction) second;

        return new Fraction(numerator * other.denominator, denominator * other.numerator);
    }

    @Override
    public Calculable<Fraction> sqr() {
        return new Fraction(numerator * numerator, denominator * denominator);
    }

    @Override
    public Calculable<Fraction> inv() {
        return new Fraction(denominator, numerator);
    }

    @Override
    public Calculable<Fraction> neg() {
        return new Fraction(-numerator, denominator);
    }

    @Override
    public Calculable<Fraction> zero() {
        return ZERO;
    }

    @Override
    public boolean isZero() {
        return numerator == 0;
    }

    @Override
    public String toString() {
        return String.format("[%d/%d]", numerator, denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Fraction fraction = (Fraction) o;

        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
