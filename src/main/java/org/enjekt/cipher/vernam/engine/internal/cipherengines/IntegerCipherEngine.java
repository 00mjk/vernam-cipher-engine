package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitDecryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitEncyryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitValidator;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;

import java.util.Arrays;


/**
 * The type Integer cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then assmbled in the composer with a StringBuffer.
 */
public class IntegerCipherEngine {

    private static final int[] MAX = new Integer(Integer.MAX_VALUE).toString().chars().toArray();
    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;

    private int counter;
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
        int[] keys = new int[values.length];

        NumberComposer composer = new NumberComposer(negative);
        Arrays.stream(values).map(new DigitEncyryptor(keys, new DigitValidator(values.length, MAX))).forEach(composer);

        return new IntegerWrapper(composer.getInteger(), keys);

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

        NumberComposer composer = new NumberComposer(negative);
        Arrays.stream(value.toString().chars().toArray()).map(new DigitDecryptor(message.getOneTimePad())).forEach(composer);
        return composer.getInteger();

    }


}

