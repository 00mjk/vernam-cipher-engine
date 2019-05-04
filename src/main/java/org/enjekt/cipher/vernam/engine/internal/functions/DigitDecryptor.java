package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntUnaryOperator;

public class DigitDecryptor implements IntUnaryOperator {

    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;
    private static final int MODULO = (UPPER_UTF8_LIMIT - LOWER_UTF8_LIMIT + 1);
    private final int[] keys;
    private int count = 0;

    public DigitDecryptor(int[] keys) {
        this.keys = keys;

    }

    @Override
    public int applyAsInt(int operand) {
        return dodecipher(operand, keys[count++]);

    }


    private int dodecipher(int operand, int encryptionKey) {
        int result = operand - encryptionKey;
        //System.out.println(result +" = " + operand + " - " + encryptionKey);
        if (result < LOWER_UTF8_LIMIT) {
            result += MODULO;

        }

        return result;
    }


}
