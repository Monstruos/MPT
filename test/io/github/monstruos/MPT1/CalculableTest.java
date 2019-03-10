package io.github.monstruos.MPT1;

import io.github.monstruos.MPT2.data.Fraction;
import io.github.monstruos.MPT2.data.NumberWithBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculableTest {
    @Test
    void constructor() {
        assertEquals(new Fraction(1, 2), new Fraction(2, 4));
        assertEquals(new Fraction(-1, 2), new Fraction(1, -2));
        assertNotEquals(new Fraction(1, 2), new Fraction(2, 1));
        assertThrows(IllegalArgumentException.class, () -> new Fraction(1, 0));

        assertEquals(new NumberWithBase(27, 4, 3).toString(),  "123");
        assertEquals(new NumberWithBase(27.5, 4, 3).toString(),  "123.200");
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 1, 3));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 17, 3));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 4, -1));
    }

    @Test
    void add() {
        assertEquals(new Fraction(3, 4), new Fraction(1, 2).add(new Fraction(1, 4)));
        assertEquals(new Fraction(1, 4).add(new Fraction(1, 2)), new Fraction(1, 2).add(new Fraction(1, 4)));
        assertEquals(new Fraction(-1, 4), new Fraction(1, 2).add(new Fraction(-3, 4)));

        assertEquals(new NumberWithBase(246, 4, 4), new NumberWithBase(123, 4, 3).add(new NumberWithBase(123, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 5, 3).add(new NumberWithBase(123, 4, 3)));
    }

    @Test
    void sub() {
        assertEquals(new Fraction(0, 1), new Fraction(1, 2).sub(new Fraction(1, 2)));

        assertEquals(new NumberWithBase(1, 4, 4), new NumberWithBase(123, 4, 3).sub(new NumberWithBase(122, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 5, 3).sub(new NumberWithBase(122, 4, 3)));
    }

    @Test
    void mul() {
        assertEquals(new Fraction(2, 9), new Fraction(2, 3).mul(new Fraction(1, 3)));

        assertEquals(new NumberWithBase(4, 4, 4), new NumberWithBase(2, 4, 3).mul(new NumberWithBase(2, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(2, 5, 3).mul(new NumberWithBase(2, 4, 3)));
    }

    @Test
    void div() {
        assertEquals(new Fraction(2, 1), new Fraction(1, 2).div(new Fraction(1, 4)));
        assertThrows(IllegalArgumentException.class, () -> new Fraction(1, 2).div(new Fraction(0, 4)));

        assertEquals(new NumberWithBase(2, 4, 4), new NumberWithBase(4, 4, 3).div(new NumberWithBase(2, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(4, 5, 3).div(new NumberWithBase(2, 4, 3)));
    }

    @Test
    void sqr() {
        assertEquals(new Fraction(4, 1),  new Fraction(2, 1).sqr());
        assertEquals(new Fraction(1, 4),  new Fraction(1, 2).sqr());

        assertEquals(new NumberWithBase(4, 4, 3), new NumberWithBase(2, 4, 3).sqr());
        assertEquals(new NumberWithBase(0.25, 4, 3), new NumberWithBase(0.5, 4, 3).sqr());
    }

    @Test
    void inv() {
        assertEquals(new Fraction(4, 1),  new Fraction(1, 4).inv());
        assertThrows(IllegalArgumentException.class, () -> new Fraction(0, 1).inv());

        assertEquals(new NumberWithBase(4, 4, 3), new NumberWithBase(0.25, 4, 3).inv());
        assertEquals(new NumberWithBase(0.25, 4, 3), new NumberWithBase(4, 4, 3).inv());
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(0, 4, 3).inv());
    }
}
