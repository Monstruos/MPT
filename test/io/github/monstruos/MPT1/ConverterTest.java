package io.github.monstruos.MPT1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void whenZeroInCorrectBaseThenDoubleValueIsZero() {
        for (int base = Converter.MIN_BASE; base < Converter.MAX_BASE; ++base) {
            assertEquals(0.0, Converter.convertToDouble("0", base));
            assertEquals(0.0, Converter.convertToDouble("0.0", base));
        }
    }

    @Test
    void whenDecimalPIThenDoubleValueIsPI() {
        assertEquals(3.1415926, Converter.convertToDouble("3.1415926", 10));
    }

    @Test
    void whenEmptyStringThenNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> Converter.convertToDouble("", 10));
    }

    @Test
    void whenCorrectDigitInCorrectBaseThenConvertedDigit() {
        for (int base = Converter.MIN_BASE; base < Converter.MAX_BASE; ++base) {
            for (int digit = 0; digit < base; ++digit) {
                if (digit < 10) {
                    assertEquals('0' + digit, Converter.convertDigit(digit, base));
                } else {
                    assertEquals('A' + digit - 10, Converter.convertDigit(digit, base));
                }
            }
        }
    }
}