package org.enjekt.cipher.vernam.engine.internal.functions;

import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;

import java.util.function.IntUnaryOperator;

public class DigitEncryptor implements IntUnaryOperator {

    //private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;
    private static final int UPPER_RANGE = 9;
    private static final int MODULO = 10;
    private static final RandomNumberGenerator secureRandom = new RandomNumberGenerator();

    private final int[] keys;
    private final DigitValidator validator;
    private int count = 0;

    public DigitEncryptor(int[] keys, DigitValidator validator) {
        this.keys = keys;
        this.validator = validator;

    }

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
        return RandomNumberGenerator.nextInt(UPPER_RANGE);
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
