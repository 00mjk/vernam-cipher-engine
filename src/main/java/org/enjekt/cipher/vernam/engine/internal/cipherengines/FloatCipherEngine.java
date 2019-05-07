package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.FloatWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;


/**
 * The type Flat cipher engine.
 */
public class FloatCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 48;

    private static final int[] MAX_UTF8;
    private static final int[] MAX_DIGITS;

    static {
        MAX_UTF8 = Float.toString(Float.MAX_VALUE).chars().toArray();
        MAX_DIGITS = Float.toString(Float.MAX_VALUE).chars().map(i -> i - LOWER_UTF8_LIMIT).toArray();
    }

    private static final DigitStreamCipher digitStreamCipher = new DigitStreamCipher(MAX_DIGITS);

    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public FloatWrapper encipher(Float value) {

        String floatStr = value.toString();

        NumberComposer numberComposer = new NumberComposer();
        int[] oneTimePad = digitStreamCipher.encipher(value.toString().chars().toArray(), numberComposer);
        return new FloatWrapper(numberComposer.getFloat(), oneTimePad);

    }

    /**
     * Decrypt integer.
     *
     * @param message the message
     * @return the integer
     */
    public Float decipher(FloatWrapper message) {
        String floatStr = message.getEncryptedValue().toString();

        NumberComposer numberComposer = new NumberComposer();

        digitStreamCipher.decipher(floatStr.chars().toArray(), message.getOneTimePad(), numberComposer);


        return numberComposer.getFloat();

    }


}

