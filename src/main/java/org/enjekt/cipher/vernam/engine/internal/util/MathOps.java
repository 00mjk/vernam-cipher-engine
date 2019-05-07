package org.enjekt.cipher.vernam.engine.internal.util;

public class MathOps {
    public static final int LOWER_UTF8_LIMIT = 48;
    public static final int UPPER_UTF8_LIMIT = 57;
    public static final int BASE = (UPPER_UTF8_LIMIT - LOWER_UTF8_LIMIT + 1);
    public static final int UPPER_RANGE = 9;
    public static final int MINUS_UTF8 = 45;


    public static boolean isValidUTFDigit(int value) {
        return value >= LOWER_UTF8_LIMIT && value <= UPPER_UTF8_LIMIT || value == MINUS_UTF8;
    }
}
