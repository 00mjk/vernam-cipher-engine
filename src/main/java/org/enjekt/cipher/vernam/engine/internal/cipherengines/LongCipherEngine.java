package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.LongWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitDecryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitEncryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;

import java.util.Arrays;


/**
 * The type Long cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then asesmbled in the composer.
 */
public class LongCipherEngine {


    private static final int LOWER_UTF8_LIMIT = 48;

    private static final int[] MAX_UTF8;
    private static final int[] MAX_DIGITS;

    static {
        MAX_UTF8 = Long.toString(Long.MAX_VALUE).chars().toArray();
        MAX_DIGITS = Long.toString(Long.MAX_VALUE).chars().map(i -> i - LOWER_UTF8_LIMIT).toArray();
    }

    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public LongWrapper encrypt(Long value) {

        boolean isNegative = value < 0;


        int[] values = getInts(value, isNegative);
        int[] oneTimePad = new int[values.length];

        NumberComposer composer = new NumberComposer(isNegative);
        Arrays.stream(values).map(i -> i - LOWER_UTF8_LIMIT).map(new DigitEncryptor(MAX_DIGITS, oneTimePad)).map(i -> i + LOWER_UTF8_LIMIT).forEach(composer);

        return new LongWrapper(composer.getLong(), oneTimePad);

    }

    private int[] getInts(Long value, boolean isNegative) {

        if (isNegative)
            return value.toString().substring(1).chars().toArray();
        else
            return value.toString().chars().toArray();
    }

    /**
     * Decrypt integer.
     *
     * @param wrapper the wrapper
     * @return the integer
     */
    public Long decrypt(LongWrapper wrapper) {
        Long value = wrapper.getEncryptedValue();
        boolean isNegative = value < 0;

        int[] values = getInts(value, isNegative);
        NumberComposer composer = new NumberComposer(isNegative);
        Arrays.stream(values).map(new DigitDecryptor(wrapper.getOneTimePad())).forEach(composer);
        return composer.getLong();

    }


}

