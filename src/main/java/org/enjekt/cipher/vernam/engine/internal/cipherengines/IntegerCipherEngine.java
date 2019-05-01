package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.IntegerComposer;
import org.enjekt.cipher.vernam.engine.internal.functions.IntegerDecryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.IntegerEncryptor;

import java.util.Arrays;


/**
 * The type Integer cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then assmbled in the composer with a StringBuffer.
 */
public class IntegerCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;


    /**

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

        IntegerComposer composer = new IntegerComposer(negative);
        Arrays.stream(values).map(new IntegerEncryptor(keys)).forEach(composer);
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

        IntegerComposer composer = new IntegerComposer(negative);
        Arrays.stream(value.toString().chars().toArray()).map(new IntegerDecryptor(message.getEncryptionKeys())).forEach(composer);
        return composer.getInteger();

    }


}

