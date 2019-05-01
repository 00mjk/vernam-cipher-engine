package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.IntegerComposer;

import java.security.SecureRandom;
import java.util.Arrays;


/**
 * The type Integer cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then assmbled in the composer with a StringBuffer.
 */
public class RawIntegerCipherEngine {


    private final static int[] MAX = new Integer(Integer.MAX_VALUE).toString().chars().toArray();
    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;
    private static final int MODULO = 10;
    private static SecureRandom secureRandom = new SecureRandom();

    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public IntegerWrapper encrypt(Integer value) {

        Boolean negative = value < 0;
        if (negative)
            value = -value;

        int[] values = value.toString().chars().toArray();
        int[] oneTimePad;

        int[] results = new int[values.length];
        IntegerComposer composer = new IntegerComposer(negative);

        do {
            oneTimePad = secureRandom.ints(values.length, 0, 9).toArray();

            for (int i = 0; i < results.length; i++) {
                results[i] = (values[i] + oneTimePad[i]);
                if (results[i] > UPPER_UTF8_LIMIT) results[i] -= MODULO;
            }

        } while (!isValid(results));
        Arrays.stream(results).forEach(composer);
        return new IntegerWrapper(composer.getInteger(), oneTimePad);

    }

    private Boolean isValid(int[] results) {
        if (results[0] == LOWER_UTF8_LIMIT) return Boolean.FALSE; //No leading zero permitted.
        if (MAX.length > results.length) return Boolean.TRUE; //Less that largest Integer value permitted due to length.

        for (int i = 0; i < MAX.length; i++) {
            if (MAX[i] > results[i]) return Boolean.TRUE; //We are at max length but the digit is less than max
            if (MAX[i] < results[i]) return Boolean.FALSE; //We are at max length and padded result exeeds acceptable.
            //otherwise we are equal to max length and the max digit is equal
            //so loop to check the next digit
        }
        return Boolean.TRUE; //Exactly equal to max length. May or may not be acceptable.
    }


    /**
     * Decrypt integer.
     *
     * @param message the message
     * @return the integer
     */
    public Integer decrypt(IntegerWrapper message) {
        Integer value = message.getEncryptedValue();
        Boolean negative = value < 0;
        if (negative)
            value = -value;

        IntegerComposer composer = new IntegerComposer(negative);
        int[] values = value.toString().chars().toArray();
        int[] keys = message.getEncryptionKeys();
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] - keys[i];
            if (values[i] < LOWER_UTF8_LIMIT) values[i] += MODULO;
        }
        Arrays.stream(values).forEach(composer);
        return composer.getInteger();

    }


}

