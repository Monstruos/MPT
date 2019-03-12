package io.github.monstruos.MPT2.editors;

import io.github.monstruos.MPT2.data.Calculable;

public class Editor<T extends Calculable<T>> {
    private Calculable<T> leftOperand;
    private Calculable<T> rightOperand;
    private Operation operation;

    public Editor() {

    }

    enum Operation {None, Add, Sub, Mul, Div}

    enum Function {Inv, Sqr}
}
