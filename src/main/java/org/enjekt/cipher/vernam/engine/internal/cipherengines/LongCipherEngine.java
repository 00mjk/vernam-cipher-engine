package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.LongWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;


/**
 * The type Long cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then asesmbled in the composer.
 */
public class LongCipherEngine {


    private static final int LOWER_UTF8_LIMIT = 48;

    private static final int[] MAX_UTF8;
    private static final int[] MAX_DIGITS;

    static {
        MAX_UTF8 = Long.toString(Long.MAX_VALUE).chars().toArray();
        MAX_DIGITS = Long.toString(Long.MAX_VALUE).chars().map(i -> i - LOWER_UTF8_LIMIT).toArray();
    }

    private static final DigitStreamCipher digitStreamCipher = new DigitStreamCipher(MAX_DIGITS);


    public LongWrapper encipher(Long value) {
        NumberComposer numberComposer = new NumberComposer();
        int[] oneTimePad = digitStreamCipher.encipher(value.toString(), numberComposer);
        return new LongWrapper(numberComposer.getLong(), oneTimePad);

    }

    public Long decipher(LongWrapper message) {
        Long value = message.getEncryptedValue();
        NumberComposer composer = digitStreamCipher.decipher(value.toString(), message.getOneTimePad());
        return composer.getLong();

    }


}

