package io.github.monstruos.MPT1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    private static final double EPSILON = Math.pow(0.1, Converter.MAX_DECIMAL_PRECISION);

    @Test
    void whenZeroInCorrectBaseThenStringValueIsZero() {
        for (int base = Converter.MIN_BASE; base <= Converter.MAX_BASE; ++base) {
            for (int precision = 0; precision <= Converter.MAX_DECIMAL_PRECISION; ++precision) {
                assertEquals("0", Converter.convertToString(0, base, precision));
                assertEquals("0", Converter.convertToString(0.0, base, precision));
            }
        }
    }

    @Test
    void whenDecimalIntegerThenStringValueIsThatInteger() {
        for (int number = 1; number < Converter.MAX_BASE * 2; ++number) {
            for (int precision = 0; precision <= Converter.MAX_DECIMAL_PRECISION; ++precision) {
                assertEquals(String.valueOf(number), Converter.convertToString(number, 10, precision));
            }
        }
    }

    @Test
    void whenDecimalPIThenStringValueIsPI() {
        assertEquals("3.1415926", Converter.convertToString(3.1415926, 10, 7));
    }

    @Test
    void whenPrecisionIsIncorrectThenIllegalArgumentException() {
        for (double number = 0.1; number < Converter.MAX_BASE; number += 0.1) {
            double correctNumber = number;
            assertThrows(IllegalArgumentException.class, () -> Converter.convertToString(correctNumber, Converter.MAX_BASE, -1));
        }
    }

    @Test
    void whenZeroInCorrectBaseThenDoubleValueIsZero() {
        for (int base = Converter.MIN_BASE; base <= Converter.MAX_BASE; ++base) {
            assertEquals(0.0, Converter.convertToDouble("0", base));
            assertEquals(0.0, Converter.convertToDouble("0.0", base));
        }
    }

    @Test
    void whenDecimalIntegerThenDoubleValueIsThatInteger() {
        for (int number = 1; number < Converter.MAX_BASE * 2; ++number) {
            assertEquals(number, Converter.convertToDouble(String.valueOf(number), 10));
        }
    }

    @Test
    void whenDecimalNumberThenDoubleValueIsThatNumber() {
        for (double number = 0.1; number < Converter.MAX_BASE; number += 0.1) {
            double convertedValue = Converter.convertToDouble(String.valueOf(number), 10);
            double diff = Math.abs(number - convertedValue);

            assertTrue(diff < EPSILON, number + " is not equal to " + convertedValue);
        }
    }

    @Test
    void whenDecimalPIThenDoubleValueIsPI() {
        assertEquals(3.1415926, Converter.convertToDouble("3.1415926", 10));
    }

    @Test
    void whenDoubleValueToStringAndToDoubleThenDoubleValue() {
        for (double number = 0.1; number < Converter.MAX_BASE; number += 0.1) {
            double convertedValue = Converter.convertToDouble(Converter.convertToString(number, 10, Converter.MAX_DECIMAL_PRECISION), 10);
            double diff = Math.abs(number - convertedValue);

            assertTrue(diff < EPSILON, number + " is not equal to " + convertedValue);
        }
    }

    @Test
    void whenEmptyStringThenNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> Converter.convertToDouble("", 10));
    }

    @Test
    void whenCorrectNumberInIncorrectBaseThenIllegalArgumentException() {
        for (double number = 0.1; number < Converter.MAX_BASE; number += 0.1) {
            double correctNumber = number;
            assertThrows(IllegalArgumentException.class, () -> Converter.convertToDouble(String.valueOf(correctNumber), Converter.MIN_BASE - 1));
            assertThrows(IllegalArgumentException.class, () -> Converter.convertToDouble(String.valueOf(correctNumber), Converter.MAX_BASE + 1));
            assertThrows(IllegalArgumentException.class, () -> Converter.convertToString(correctNumber, Converter.MIN_BASE - 1, 0));
            assertThrows(IllegalArgumentException.class, () -> Converter.convertToString(correctNumber, Converter.MAX_BASE + 1, 0));
        }
    }

    @Test
    void whenCorrectDigitInCorrectBaseThenConvertedDigit() {
        for (int base = Converter.MIN_BASE; base <= Converter.MAX_BASE; ++base) {
            for (int digit = 0; digit < base; ++digit) {
                if (digit < 10) {
                    assertEquals('0' + digit, Converter.convertDigit(digit, base));
                } else {
                    assertEquals('A' + digit - 10, Converter.convertDigit(digit, base));
                }
            }
        }
    }

    @Test
    void whenCorrectDigitInIncorrectBaseThenIllegalArgumentException() {
        for (int digit = 0; digit < Converter.MAX_BASE; ++digit) {
            int correctDigit = digit;
            assertThrows(IllegalArgumentException.class, () -> Converter.convertDigit(correctDigit, Converter.MIN_BASE - 1));
            assertThrows(IllegalArgumentException.class, () -> Converter.convertDigit(correctDigit, Converter.MAX_BASE + 1));
        }
    }

    @Test
    void whenIncorrectDigitInCorrectBaseThenIllegalArgumentException() {
        for (int base = Converter.MIN_BASE; base <= Converter.MAX_BASE; ++base) {
            int correctBase = base;
            assertThrows(IllegalArgumentException.class, () -> Converter.convertDigit(-1, correctBase));
            assertThrows(IllegalArgumentException.class, () -> Converter.convertDigit(correctBase + 1, correctBase));
        }
    }

    @Test
    void whenIncorrectDigitInIncorrectBaseThenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Converter.convertDigit(-1, Converter.MIN_BASE - 1));
        assertThrows(IllegalArgumentException.class, () -> Converter.convertDigit(Converter.MAX_BASE + 1, Converter.MIN_BASE - 1));
        assertThrows(IllegalArgumentException.class, () -> Converter.convertDigit(-1, Converter.MAX_BASE + 1));
        assertThrows(IllegalArgumentException.class, () -> Converter.convertDigit(Converter.MAX_BASE + 1, Converter.MAX_BASE + 1));
    }
}