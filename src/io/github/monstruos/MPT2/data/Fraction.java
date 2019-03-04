package io.github.monstruos.MPT2.data;

public class Fraction implements Calculable<Fraction> {
    private int numer;
    private int denom;

    private static final Fraction ZERO = new Fraction(0, 1);

    public Fraction(final int numer, final int denom) {
        if (denom == 0) {
            throw new ArithmeticException("Trying to create (" + numer + "/0) fraction");
        }
        this.numer = numer;
        this.denom = denom;
        shortify();
    }

    private void shortify() {
        int k = gcd(numer, denom);
        numer /= k;
        denom /= k;
    }

    private static int gcd(int a, int b) {
        return b > 0 ? gcd(b, a%b) : a;
    }

    @Override
    public Fraction add(Fraction other) {

        return null;
    }

    @Override
    public Fraction sub(Fraction other) {
        return null;
    }

    @Override
    public Fraction mul(Fraction other) {
        return null;
    }

    @Override
    public Fraction div(Fraction other) {
        return null;
    }

    @Override
    public boolean isZero() {
        return false;
    }
}
