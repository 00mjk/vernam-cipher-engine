package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.FloatWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;


/**
 * The type Flat cipher engine.
 *
 * TODO FloatCipherEngine is in rudimentary, crude form and isn't currently usable.
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
        NumberComposer numberComposer = new NumberComposer();
        String[] wholeAndFractional = value.toString().split("\\.");
        int[] wholeOneTimePad = digitStreamCipher.encipher(wholeAndFractional[0], numberComposer);
        numberComposer.append(".");
        int[] fractionalOneTimePad = digitStreamCipher.encipher(wholeAndFractional[1], numberComposer);

        return new FloatWrapper(numberComposer.getFloat(), wholeOneTimePad, fractionalOneTimePad);

    }

    /**
     * Decrypt integer.
     *
     * @param message the message
     * @return the integer
     */
    public Float decipher(FloatWrapper message) {
        Float value = message.getEncryptedValue();
        NumberComposer numberComposer = new NumberComposer();
        String[] wholeAndFractional = value.toString().split("\\.");
        digitStreamCipher.decipher(wholeAndFractional[0], message.getWholeOneTimePad(), numberComposer);
        numberComposer.append(".");
        digitStreamCipher.decipher(wholeAndFractional[1], message.getFractionalOneTimePad(), numberComposer);

        return numberComposer.getFloat();

    }


}

