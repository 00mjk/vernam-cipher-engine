package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.BigDecimalWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.Digit;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;

import java.math.BigDecimal;


/**
 * The type float cipher engine.
 *
 */

//  TODO This is still a work in progress and we have to ensure that the enciphering of MAX/MIN values works correctly.
//   This is currently technically inaccurate and shouldn't be used.


public class FloatCipherEngine {


    private static final int[] MAX_UTF8;
    private static final int[] MAX_DIGITS;

    static {
        MAX_UTF8 = new BigDecimal(Float.MAX_VALUE).toString().chars().toArray();
        MAX_DIGITS = new BigDecimal(Float.MAX_VALUE).toString().chars().map(Digit::toInt).toArray();
    }

    private static final DigitStreamCipher digitStreamCipher = new DigitStreamCipher(MAX_DIGITS);

    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public BigDecimalWrapper encipher(BigDecimal value) {

        NumberComposer numberComposer = new NumberComposer();
        int[] oneTimePad = digitStreamCipher.encipher(value.toPlainString(), numberComposer);
        return new BigDecimalWrapper(numberComposer.getBigDecimal(), oneTimePad);

    }

    /**
     * Decrypt integer.
     *
     * @param message the message
     * @return the integer
     */
    public BigDecimal decipher(BigDecimalWrapper message) {
        String floatStr = message.getEncryptedValue().toString();
        if (floatStr.contains("E"))
            floatStr = message.getEncryptedValue().toString();

        NumberComposer numberComposer = new NumberComposer();

        digitStreamCipher.decipher(floatStr.chars().toArray(), message.getOneTimePad(), numberComposer);


        return numberComposer.getBigDecimal();

    }


}

