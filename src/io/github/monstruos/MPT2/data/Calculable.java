package io.github.monstruos.MPT2.data;

public interface Calculable<CalcT> {
    CalcT add(final CalcT other);

    CalcT sub(final CalcT other);

    CalcT mul(final CalcT other);

    CalcT div(final CalcT other);

    CalcT sqr();

    CalcT inv();

    boolean isZero();
}
