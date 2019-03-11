package io.github.monstruos.MPT2.data;

import java.util.Objects;

public class Fraction implements Calculable<Fraction> {
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
    public Fraction add(final Fraction other) {
        return new Fraction(
                numerator * other.denominator + denominator * other.numerator,
                denominator * other.denominator
        );
    }

    @Override
    public Fraction sub(final Fraction other) {
        return new Fraction(
                numerator * other.denominator - denominator * other.numerator,
                denominator * other.denominator
        );
    }

    @Override
    public Fraction mul(final Fraction other) {
        return new Fraction(numerator * other.numerator, denominator * other.denominator);
    }

    @Override
    public Fraction div(final Fraction other) {
        return new Fraction(numerator * other.denominator, denominator * other.numerator);
    }

    @Override
    public Fraction sqr() {
        return new Fraction(numerator * numerator, denominator * denominator);
    }

    @Override
    public Fraction inv() {
        return new Fraction(denominator, numerator);
    }

    @Override
    public Fraction neg() {
        return new Fraction(-numerator, denominator);
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
