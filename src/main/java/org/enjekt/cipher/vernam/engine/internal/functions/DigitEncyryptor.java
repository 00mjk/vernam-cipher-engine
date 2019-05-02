package org.enjekt.cipher.vernam.engine.internal.functions;

import java.security.SecureRandom;
import java.util.function.IntUnaryOperator;

public class DigitEncyryptor implements IntUnaryOperator {

    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;
    private static final int UPPER_RANGE = 9;
    private static final int MODULO = 10;
    private static SecureRandom secureRandom = new SecureRandom();
    private final int[] keys;
    int count = 0;
    private final DigitValidator validator;

    public DigitEncyryptor(int[] keys, DigitValidator validator) {
        this.keys = keys;
        this.validator = validator;

    }

    private static int counter;
    @Override
    public int applyAsInt(int operand) {
        int pad = getPad();
        int encryptVal = doEncrypt(operand, pad);
        while (!validator.isValid(count, encryptVal)) {
            pad = getPad();
            encryptVal = doEncrypt(operand, pad);
        }
        keys[count++] = pad;
        return encryptVal;

    }


    private int getPad() {
        return secureRandom.nextInt(UPPER_RANGE);
    }

    private int doEncrypt(int operand, int encryptionKey) {
        // System.out.println( operand + ","+ encryptionKey);
        int result = operand + encryptionKey;
        if (result > UPPER_UTF8_LIMIT) {
            result -= MODULO;

        }
        return result;
    }


}
