package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitDecryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitEncryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;

import java.util.Arrays;


/**
 * The type Integer cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then asesmbled in the composer.
 */
public class IntegerCipherEngine {
    private static final int LOWER_UTF8_LIMIT = 48;

    private static final int[] MAX_UTF8 = Integer.toString(Integer.MAX_VALUE).chars().toArray();

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

        NumberComposer composer = new NumberComposer(isNegative);
        Arrays.stream(values).map(new DigitEncryptor(oneTimePad, MAX_UTF8)).forEach(composer);
        return new IntegerWrapper(composer.getInteger(), oneTimePad);

    }

    private int[] getInts(Integer value, boolean isNegative) {
        String valueStr = value.toString();

        int[] values;
        if (isNegative)
            values = valueStr.substring(1).chars().toArray();
        else
            values = valueStr.chars().toArray();
        return values;
    }

    /**
     * Decrypt integer.
     *
     * @param message the message
     * @return the integer
     */
    public Integer decrypt(IntegerWrapper message) {
        Integer value = message.getEncryptedValue();
        boolean negative = value < 0;
        if (negative)
            value = -value;

        NumberComposer composer = new NumberComposer(negative);
        Arrays.stream(value.toString().chars().toArray()).map(new DigitDecryptor(message.getOneTimePad())).forEach(composer);
        return composer.getInteger();

    }


}

