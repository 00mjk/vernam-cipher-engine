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

        Integer encyrptionValue = value;
        if (value < 0)
            encyrptionValue = -value;

        int[] returnValues = cipherEngine.decrypt(encyrptionValue.toString().chars().toArray(), message.getEncryptionKeys());
        //We need the - sign unencrypted for correct number parsing.

        ValueComposer composer = new ValueComposer();
        Arrays.stream(returnValues).forEach(composer);
        Integer returnVal = composer.getInteger();
        //We need the - sign unencrypted for correct number parsing.
        if (value < 0)
            returnVal = -returnVal;

        return returnVal;

    }

    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public IntegerWrapper encrypt(Integer value) {

        Integer encyrptionValue = value;
        if (value < 0)
            encyrptionValue = -value;

        EncryptedResultsHolder holder = cipherEngine.encrypt(encyrptionValue.toString().chars().toArray());

        //No leading zero...
        while (holder.getValues()[0] == LOWER_UTF8_LIMIT)
            holder = cipherEngine.encrypt(encyrptionValue.toString().chars().toArray());

        ValueComposer composer = new ValueComposer();
        Arrays.stream(holder.getValues()).forEach(composer);

        Integer returnVal = composer.getInteger();
        //We need the - sign unencrypted for correct number parsing.
        if (value < 0)
            returnVal = -returnVal;
        return new IntegerWrapper(returnVal, holder.getKeys());

    }


}

