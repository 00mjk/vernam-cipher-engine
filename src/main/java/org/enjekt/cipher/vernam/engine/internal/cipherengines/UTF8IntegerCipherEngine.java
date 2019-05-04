package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitDecryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;
import org.enjekt.cipher.vernam.engine.internal.functions.UTF8DigitEncryptor;

import java.util.Arrays;


/**
 * The type Integer cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then asesmbled in the composer.
 */
//TODO This was the prvious version which calculates rolling end points. It works and would
//be perferable except for the case of the simplicity.

@Deprecated
public class UTF8IntegerCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 48;

    private static final int[] MAX_UTF8;
    private static final int[] MAX_DIGITS;

    static {
        MAX_UTF8 = Integer.toString(Integer.MAX_VALUE).chars().toArray();
        MAX_DIGITS = Integer.toString(Integer.MAX_VALUE).chars().map(i -> i - LOWER_UTF8_LIMIT).toArray();
    }

    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public IntegerWrapper encrypt(Integer value) {
        boolean isNegative = value < 0;
        int[] values = getInts(value, isNegative);
        int[] oneTimePad = new int[values.length];
        NumberComposer composer = new NumberComposer().setNegative(isNegative);
        Arrays.stream(values).map(new UTF8DigitEncryptor(oneTimePad, MAX_UTF8)).forEach(composer);
        return new IntegerWrapper(composer.getInteger(), oneTimePad);

    }

    private int[] getInts(Integer value, boolean isNegative) {
        if (isNegative)
            return value.toString().substring(1).chars().toArray();
        else
            return value.toString().chars().toArray();
    }

    /**
     * Decrypt integer.
     *
     * @param message the message
     * @return the integer
     */
    public Integer decrypt(IntegerWrapper message) {
        Integer value = message.getEncryptedValue();
        boolean isNegative = value < 0;
        int[] values = getInts(value, isNegative);
        NumberComposer composer = new NumberComposer().setNegative(isNegative);
        Arrays.stream(values).map(new DigitDecryptor(message.getOneTimePad())).forEach(composer);
        return composer.getInteger();

    }


}

