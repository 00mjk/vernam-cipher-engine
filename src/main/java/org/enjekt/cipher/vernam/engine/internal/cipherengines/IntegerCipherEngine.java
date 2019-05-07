package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;


/**
 * The type Integer cipher engine. Individual digits are converted to 0 to 9 from their UTF8 to minimize
 * calculations during enciphering and simplify logic. The deciphering is simpler and keeps UTF8 values for
 * processing. As with all the cipher engines, this one is stateless and thread safe.
 *
 * The common DigitStreamCipher is used as for all whole number data type and the maximum digit size
 * is passed in on the constructor.
 *
 *  The maximum size of an Integer is 2,147,483,647. The array is thus
 *  be {2,1,4,7,4,8,3,6,4,7} or 10 digits long. Any integer to be enciphered which has a length of 10 must then
 *  ensure that the first digit is 2 or less but not 0. Each cipher engine keeps a static final array of the
 *  valid array of int for its type and passes it into the DigitStreamCipher when it instantiates it for processing.
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
        int[] oneTimePad = digitStreamCipher.encipher(value.toString().chars().toArray(), numberComposer);
        return new IntegerWrapper(numberComposer.getInteger(), oneTimePad);

    }
    /**
     * Decrypt integer.
     *
     * @param message the message
     * @return the integer
     */
    public Integer decipher(IntegerWrapper message) {
        NumberComposer numberComposer = new NumberComposer();

        Integer value = message.getEncryptedValue();
        digitStreamCipher.decipher(value.toString().chars().toArray(), message.getOneTimePad(), numberComposer);
        return numberComposer.getInteger();

    }


}

