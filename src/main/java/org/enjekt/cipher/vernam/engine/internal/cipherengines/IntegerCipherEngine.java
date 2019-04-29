package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.ValueComposer;

import java.util.Arrays;


/**
 * The type Integer cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then assmbled in the composer with a StringBuffer.
 */
public class IntegerCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;


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
        Boolean isNegative = Boolean.FALSE;
        if (value < 0) {
            isNegative = Boolean.TRUE;
            value = -value;
        }
        Integer returnValue = cipherEngine.decrypt(value.toString(), message.getEncryptionKeys()).getInteger();
        if (isNegative)
            returnValue = -returnValue;
        return returnValue;

    }

    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public IntegerWrapper encrypt(Integer value) {

        Boolean isNegative = Boolean.FALSE;
        if (value < 0) {
            isNegative = Boolean.TRUE;
            value = -value;
        }

        EncryptedResultsHolder holder = cipherEngine.encrypt(value.toString());


        ValueComposer composer = new ValueComposer();
        Arrays.stream(holder.getValues()).forEach(composer);
        Integer returnValue = composer.getInteger();
        if (isNegative)
            returnValue = -returnValue;

        return new IntegerWrapper(returnValue, holder.getKeys());

    }


}

