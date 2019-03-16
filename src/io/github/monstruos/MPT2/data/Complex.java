package io.github.monstruos.MPT2.data;

import java.util.Objects;

public class Complex implements Calculable<Complex> {
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);

    private final double re;
    private final double im;

    public Complex(double real, double imaginary) {
        this.re = real;
        this.im = imaginary;
    }

    @Override
    public Calculable<Complex> add(Calculable<Complex> second) {
        Complex other = (Complex) second;

        return new Complex(re + other.re, im + other.im);
    }

    @Override
    public Calculable<Complex> sub(Calculable<Complex> second) {
        Complex other = (Complex) second;

        return new Complex(re - other.re, im - other.im);
    }

    @Override
    public Calculable<Complex> mul(Calculable<Complex> second) {
        Complex other = (Complex) second;

        return new Complex(re * other.re - im * other.im, re * other.im + im * other.re);
    }

    @Override
    public Calculable<Complex> div(Calculable<Complex> second) {
        Complex other = (Complex) second;

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
    public Calculable<Complex> sqr() {
        return new Complex(re * re - im * im, 2 * re * im);
    }

    @Override
    public Calculable<Complex> inv() {
        return ONE.div(this);
    }

    @Override
    public Calculable<Complex> neg() {
        return new Complex(-re, -im);
    }

    @Override
    public Calculable<Complex> zero() {
        return ZERO;
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
