package io.github.monstruos.MPT2;

import io.github.monstruos.MPT2.data.BasedNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {
    @Test
    void memoryAndGettersTest() {
        BasedNumber testNumber = new BasedNumber(1.0, 16);
        Memory<BasedNumber> testMemory = new Memory<>(testNumber);
        testMemory.setNumber(testNumber);

        assertEquals(testMemory.getNumber(), testNumber);
        assertTrue(testMemory.isEnabled());

        testMemory.clear();

        assertEquals(testMemory.getNumber(), new BasedNumber(0.0, 16));
        assertFalse(testMemory.isEnabled());
    }

    @Test
    void addTest() {
        BasedNumber testNumber = new BasedNumber(1.0, 16);
        BasedNumber testNumber2 = new BasedNumber(2.5, 16);
        Memory<BasedNumber> testMemory = new Memory<>(testNumber);
        testMemory.setNumber(testNumber);

        assertEquals(testMemory.getNumber(), testNumber);

        testMemory.add(testNumber2);

        assertEquals(testMemory.getNumber(), new BasedNumber(3.5, 16));
    }
}
