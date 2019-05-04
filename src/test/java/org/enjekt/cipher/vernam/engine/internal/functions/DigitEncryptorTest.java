package org.enjekt.cipher.vernam.engine.internal.functions;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DigitEncryptorTest {
    private static final int LOWER_UTF8_LIMIT = 48;
    int[] MAX_INT_DIGITS = Integer.toString(Integer.MAX_VALUE).chars().map(i -> i - LOWER_UTF8_LIMIT).toArray();

    @Test
    public void IntegerMax() {

        int[] pad = new int[MAX_INT_DIGITS.length];
        DigitEncryptor encryptor = new DigitEncryptor(MAX_INT_DIGITS, pad);
        IntCollector collector = new IntCollector(MAX_INT_DIGITS.length);
        Arrays.stream(MAX_INT_DIGITS).map(encryptor).forEach(collector);
        int[] values = collector.getValues();
        assertEquals(MAX_INT_DIGITS.length, values.length);

        for (int i = 0; i < collector.getValues().length; i++) {
            int encrypted = values[i];
            System.out.printf("Value %d should be less than or equal to max digit of %d for pad of %d: \n", encrypted, MAX_INT_DIGITS[i], pad[i]);
            assertTrue(encrypted <= MAX_INT_DIGITS[i]);
            if (MAX_INT_DIGITS[i] > encrypted) break;
        }
    }
}