package io.github.monstruos.MPT2.data;

import java.util.Objects;

public class Complex implements Calculable<Complex> {
    private final double re;
    private final double im;

    private static final Complex ONE = new Complex(1, 0);

    public Complex(double real, double imaginary) {
        this.re = real;
        this.im = imaginary;
    }

    @Override
    public Complex add(Complex other) {
        return new Complex(re + other.re, im + other.im);
    }

    @Override
    public Complex sub(Complex other) {
        return new Complex(re - other.re, im - other.im);
    }

    @Override
    public Complex mul(Complex other) {
        return new Complex(re * other.re - im * other.im, re * other.im + im * other.re);
    }

    @Override
    public Complex div(Complex other) {
        if (other.isZero()) {
            throw new IllegalArgumentException("Division by zero");
        }

        double denominator = other.re * other.re + other.im * other.im;

        return new Complex(
                (re * other.re + im * other.im) / denominator,
                (im * other.re - re * other.im) / denominator
        );
    }

    @Override
    public Complex sqr() {
        return new Complex(re * re - im * im, 2 * re * im);
    }

    @Override
    public Complex inv() {
        return ONE.div(this);
    }

    @Override
    public boolean isZero() {
        return re == 0 && im == 0;
    }

    @Override
    public String toString() {
        String imaginaryPart;

        if (Math.signum(im) < 0.0) {
            imaginaryPart = "-i*" + Math.abs(im);
        } else if (Math.signum(im) == 0.0) {
            imaginaryPart = "";
        } else {
            imaginaryPart = "+i*" + im;
        }

        return re + imaginaryPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Complex complex = (Complex) o;

        return Double.compare(complex.re, re) == 0 && Double.compare(complex.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
}
