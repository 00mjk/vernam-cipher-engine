package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntUnaryOperator;

import static org.enjekt.cipher.vernam.engine.internal.util.MathOps.*;

public class DigitDecryptor implements IntUnaryOperator {


    private final int[] keys;
    private int count = 0;

    public DigitDecryptor(int[] keys) {
        this.keys = keys;

    }

    @Override
    public int applyAsInt(int operand) {
        //System.out.println("Operand: : "+ operand);
        if (operand < LOWER_UTF8_LIMIT || operand > UPPER_UTF8_LIMIT) return operand; //skip non digits like "-" or "."
        return dodecipher(operand, keys[count++]);

    }


    private int dodecipher(int operand, int encryptionKey) {
        int result = operand - encryptionKey;
        //System.out.println(result +" = " + operand + " - " + encryptionKey);
        if (result < LOWER_UTF8_LIMIT) {
            result += BASE;

        }

        return result;
    }


}
