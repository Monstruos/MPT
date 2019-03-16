package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.data.Calculable;
import io.github.monstruos.MPT2.data.Fraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.github.monstruos.MPT2.Calculator.*;

class CalculatorTest {
    private Calculable<Fraction> first = new Fraction(2, 1);
    private Calculable<Fraction> second = new Fraction(3, 4);

    @Test
    void getters() {
        Calculator<Fraction> calc = new Calculator<>(first, second);

        assertEquals(calc.getLeftOperand(), first);
        assertEquals(calc.getRightOperand(), second);
        assertEquals(calc.getOperation(), Operation.NONE);
    }

    @Test
    void setters() {
        Calculator<Fraction> calc = new Calculator<>(null, null);

        calc.setLeftOperand(first);
        calc.setRightOperand(second);
        calc.setOperation(Operation.ADD);

        assertEquals(calc.getLeftOperand(), first);
        assertEquals(calc.getRightOperand(), second);
        assertEquals(calc.getOperation(), Operation.ADD);
    }

    @Test
    void operationClear() {
        Calculator<Fraction> calc = new Calculator<>(first, second);
        calc.setOperation(Operation.DIV);

        calc.reset();

        assertEquals(calc.getLeftOperand(), Fraction.ZERO);
        assertEquals(calc.getRightOperand(), Fraction.ZERO);
        assertEquals(calc.getOperation(), Operation.NONE);
    }

    @Test
    void reset() {
        Calculator<Fraction> calc = new Calculator<>(first, second);
        calc.setOperation(Operation.SUB);

        calc.clearOperation();

        assertEquals(calc.getOperation(), Operation.NONE);
    }

    @Test
    void apply() {
        Calculator<Fraction> calc = new Calculator<>(first, second);
        calc.setOperation(Operation.MUL);

        calc.apply();

        assertEquals(calc.getLeftOperand(), first.mul(second));
        assertEquals(calc.getRightOperand(), second);
        assertEquals(calc.getOperation(), Operation.MUL);
    }

    @Test
    void applyFunc() {
        Calculator<Fraction> calc = new Calculator<>(first, second);

        calc.apply(Function.INV);

        assertEquals(calc.getRightOperand(), second.inv());
    }
}