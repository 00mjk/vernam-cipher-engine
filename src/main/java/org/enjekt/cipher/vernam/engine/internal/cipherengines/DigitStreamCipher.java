package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.internal.functions.DigitDecryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitEncryptor;

import java.util.Arrays;
import java.util.function.IntConsumer;

/**
 * The type Digit stream cipher works for all digit streams created from long, Integer, Short.
 * The Encryptor/Decryptors skip any non 0-9 digits during processing so that - and . are simply
 * ignored.
 * <p>
 * The cipher engine using this will pass in an array of the maximum digits legal for a maximum length
 * number of its type. For example, the maximum size of an Integer is 2,147,483,647. The array would thus
 * be {2,1,4,7,4,8,3,6,4,7} or 10 digits long. Any integer to be enciphered which has a length of 10 must then
 * ensure that the first digit is 2 or less but not 0. Each cipher engine keeps a static final array of the
 * valid array of int for its type and passes it into the DigitStreamCipher when it instantiates it for processing.
 * <p>
 * The DigitStreamCipher is stateless and thread safe.
 */
public class DigitStreamCipher {
    private static final int LOWER_UTF8_LIMIT = 48;
    private final int[] maximumDigitsForDataType;

    /**
     * Instantiates a new Digit stream cipher.
     *
     * @param maxDigits the max digits
     */
    public DigitStreamCipher(int[] maxDigits) {
        this.maximumDigitsForDataType = maxDigits;
    }


    /**
     * Encipher int [ ].
     *
     * @param value    the value
     * @param composer the composer
     * @return the int [ ]
     */
    public int[] encipher(String value, IntConsumer composer) {

        int[] values = value.chars().toArray();
        int[] oneTimePad = new int[getPadLength(value)];
        //We do a number of numeric operations on the digit for wrapping, leading zero, max value and so on
        //and it is simpler and faster to strip the UTF8 values off and manipulate the digits as 0..9 int and
        //then simply add the lower value of 0 in UTF8 back as the last step.
        Arrays.stream(values).map(i -> i - LOWER_UTF8_LIMIT).map(new DigitEncryptor(maximumDigitsForDataType, oneTimePad)).map(i -> i + LOWER_UTF8_LIMIT).forEach(composer);
        return oneTimePad;
    }

    private int getPadLength(String value) {
        int padLength = value.length();
        if (value.contains("-")) padLength--;
        if (value.contains(".")) padLength--;
        return padLength;
    }

    /**
     * Decipher number composer.
     *
     * @param value      the value
     * @param oneTimePad the one time pad
     * @return the number composer
     */

    public void decipher(String value, int[] oneTimePad, IntConsumer composer) {
        int[] values = value.chars().toArray();

        //We don't subtract UTF8 as in the encipher operation because we are not doing calculations of valid pad
        //values for leading zero or max values in this case. We are simply subtracting the oneTimePad and then
        //doing a modulo operation. So we avoid two extra operations per digit this way.
        Arrays.stream(values).map(new DigitDecryptor(oneTimePad)).forEach(composer);
    }


}
