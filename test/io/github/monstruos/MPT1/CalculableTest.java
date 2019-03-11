package io.github.monstruos.MPT1;

import io.github.monstruos.MPT2.data.Complex;
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

        assertEquals("123", new NumberWithBase(27, 4, 3).toString());
        assertEquals("123.200", new NumberWithBase(27.5, 4, 3).toString());
        assertEquals("123.200", new NumberWithBase(123.2, 10, 3).toString());
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 1, 3));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 17, 3));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 4, -1));

        assertEquals("1.0+i*2.0", new Complex(1, 2).toString());
        assertEquals("1.0-i*2.0", new Complex(1, -2).toString());
    }

    @Test
    void add() {
        assertEquals(new Fraction(3, 4), new Fraction(1, 2).add(new Fraction(1, 4)));
        assertEquals(new Fraction(1, 4).add(new Fraction(1, 2)), new Fraction(1, 2).add(new Fraction(1, 4)));
        assertEquals(new Fraction(-1, 4), new Fraction(1, 2).add(new Fraction(-3, 4)));

        assertEquals(new NumberWithBase(246, 4, 4), new NumberWithBase(123, 4, 3).add(new NumberWithBase(123, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 5, 3).add(new NumberWithBase(123, 4, 3)));

        assertEquals(new Complex(3, 4), new Complex(1, 2).add(new Complex(2, 2)));
        assertEquals(new Complex(2, 0).add(new Complex(0, 2)), new Complex(1, 1).add(new Complex(1, 1)));
        assertEquals(new Complex(-2, 6), new Complex(1, 2).add(new Complex(-3, 4)));
    }

    @Test
    void sub() {
        assertEquals(new Fraction(0, 1), new Fraction(1, 2).sub(new Fraction(1, 2)));

        assertEquals(new NumberWithBase(1, 4, 4), new NumberWithBase(123, 4, 3).sub(new NumberWithBase(122, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(123, 5, 3).sub(new NumberWithBase(122, 4, 3)));

        assertEquals(new Complex(0, 0), new Complex(1, 2).sub(new Complex(1, 2)));
    }

    @Test
    void mul() {
        assertEquals(new Fraction(2, 9), new Fraction(2, 3).mul(new Fraction(1, 3)));

        assertEquals(new NumberWithBase(4, 4, 4), new NumberWithBase(2, 4, 3).mul(new NumberWithBase(2, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(2, 5, 3).mul(new NumberWithBase(2, 4, 3)));

        assertEquals(new Complex(-5, -1), new Complex(2, 3).mul(new Complex(-1, 1)));
        assertEquals(new Complex(2, 4), new Complex(1, 2).mul(new Complex(2, 0)));
    }

    @Test
    void div() {
        assertEquals(new Fraction(2, 1), new Fraction(1, 2).div(new Fraction(1, 4)));
        assertThrows(IllegalArgumentException.class, () -> new Fraction(1, 2).div(new Fraction(0, 4)));

        assertEquals(new NumberWithBase(2, 4, 4), new NumberWithBase(4, 4, 3).div(new NumberWithBase(2, 4, 4)));
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(4, 5, 3).div(new NumberWithBase(2, 4, 3)));

        assertEquals(new Complex(-1.5, -0.5), new Complex(-2, 1).div(new Complex(1, -1)));
        assertThrows(IllegalArgumentException.class, () -> new Complex(1, 2).div(new Complex(0, 0)));
    }

    @Test
    void sqr() {
        assertEquals(new Fraction(4, 1), new Fraction(2, 1).sqr());
        assertEquals(new Fraction(1, 4), new Fraction(1, 2).sqr());

        assertEquals(new NumberWithBase(4, 4, 3), new NumberWithBase(2, 4, 3).sqr());
        assertEquals(new NumberWithBase(0.25, 4, 3), new NumberWithBase(0.5, 4, 3).sqr());

        assertEquals(new Complex(-5, 12), new Complex(2, 3).sqr());
        assertEquals(new Complex(3, 2).mul(new Complex(3, 2)), new Complex(3, 2).sqr());
    }

    @Test
    void inv() {
        assertEquals(new Fraction(4, 1), new Fraction(1, 4).inv());
        assertThrows(IllegalArgumentException.class, () -> new Fraction(0, 1).inv());

        assertEquals(new NumberWithBase(4, 4, 3), new NumberWithBase(0.25, 4, 3).inv());
        assertEquals(new NumberWithBase(0.25, 4, 3), new NumberWithBase(4, 4, 3).inv());
        assertThrows(IllegalArgumentException.class, () -> new NumberWithBase(0, 4, 3).inv());

        assertEquals(new Complex(4.0 / 17, -1.0 / 17), new Complex(4, 1).inv());
        assertEquals(new Complex(1, 0).div(new Complex(4, 1)), new Complex(4, 1).inv());
        assertThrows(IllegalArgumentException.class, () -> new Complex(0, 0).inv());
    }

    @Test
    void isZero() {
        assertTrue(new Fraction(0, 1).isZero());
        assertFalse(new Fraction(1, 1).isZero());

        assertTrue(new NumberWithBase(0, 10, 0).isZero());
        assertFalse(new NumberWithBase(1, 10, 0).isZero());

        assertTrue(new Complex(0, 0).isZero());
        assertFalse(new Complex(1, 0).isZero());
        assertFalse(new Complex(0, 1).isZero());
    }
}
