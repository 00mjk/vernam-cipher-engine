package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.IntegerComposer;

import java.util.Arrays;


/**
 * The type Integer cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then assmbled in the composer with a StringBuffer.
 */
public class IntegerCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;
    public static final int NEGATIVE = 45;


    /**
     * The Cipher engine. Generlized generator for crating UTF8 values in the correct range and
     * handling the add/subtract/modulo operations appropriate for those characters.
     */
    private UTF8CipherEngine cipherEngine = new UTF8CipherEngine(LOWER_UTF8_LIMIT, UPPER_UTF8_LIMIT);

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

        int[] returnValues = cipherEngine.decrypt(value.toString().chars().toArray(), message.getEncryptionKeys());
        //We need the - sign unencrypted for correct number parsing.

        IntegerComposer composer = new IntegerComposer(negative);
        Arrays.stream(returnValues).forEach(composer);
        return composer.getInteger();

    }

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
        EncryptedResultsHolder holder = cipherEngine.encrypt(value.toString().chars().toArray());

        //No leading zero...
        while (holder.getValues()[0] == LOWER_UTF8_LIMIT)
            holder = cipherEngine.encrypt(value.toString().chars().toArray());

        IntegerComposer composer = new IntegerComposer(negative);
        Arrays.stream(holder.getValues()).forEach(composer);

        return new IntegerWrapper(composer.getInteger(), holder.getKeys());

    }


}

