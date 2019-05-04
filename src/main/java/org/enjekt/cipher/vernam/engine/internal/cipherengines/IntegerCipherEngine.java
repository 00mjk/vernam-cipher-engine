package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;


/**
 * The type Integer cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then asesmbled in the composer.
 */
public class IntegerCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 48;

    private static final int[] MAX_UTF8;
    private static final int[] MAX_DIGITS;

    static {
        MAX_UTF8 = Integer.toString(Integer.MAX_VALUE).chars().toArray();
        MAX_DIGITS = Integer.toString(Integer.MAX_VALUE).chars().map(i -> i - LOWER_UTF8_LIMIT).toArray();
    }

    private static final DigitStreamCipher digitStreamCipher = new DigitStreamCipher(MAX_DIGITS);

    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public IntegerWrapper encipher(Integer value) {
        NumberComposer numberComposer = new NumberComposer();
        int[] oneTimePad = digitStreamCipher.encipher(value.toString(), numberComposer);
        return new IntegerWrapper(numberComposer.getInteger(), oneTimePad);

    }
    /**
     * Decrypt integer.
     *
     * @param message the message
     * @return the integer
     */
    public Integer decipher(IntegerWrapper message) {
        Integer value = message.getEncryptedValue();
        NumberComposer composer = digitStreamCipher.decipher(value.toString(), message.getOneTimePad());
        return composer.getInteger();

    }


}

