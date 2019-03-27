package io.github.monstruos.MPT2.data;

public interface Calculable<CalcT extends Calculable> {
    Calculable<CalcT> add(final Calculable<CalcT> second);

    Calculable<CalcT> sub(final Calculable<CalcT> second);

    Calculable<CalcT> mul(final Calculable<CalcT> second);

    Calculable<CalcT> div(final Calculable<CalcT> second);

    Calculable<CalcT> sqr();

    Calculable<CalcT> inv();

    Calculable<CalcT> neg();

    Calculable<CalcT> zero();

    boolean isZero();
}
