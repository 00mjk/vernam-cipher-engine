package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.LongWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitDecryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitEncryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitValidator;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;

import java.util.Arrays;


/**
 * The type Long cipher engine. All values during generation and handling are kept
 * as UTF8 ints and then asesmbled in the composer.
 */
public class LongCipherEngine {

    private static final int[] MAX = Long.toString(Long.MAX_VALUE).chars().toArray();


    /**
     * Encrypt integer wrapper.
     *
     * @param value the value
     * @return the integer wrapper
     */
    public LongWrapper encrypt(Long value) {

        boolean negative = value < 0;
        if (negative)
            value = -value;

        int[] values = value.toString().chars().toArray();
        int[] oneTimePad = new int[values.length];

        NumberComposer composer = new NumberComposer(negative);
        Arrays.stream(values).map(new DigitEncryptor(oneTimePad, new DigitValidator(values.length, MAX))).forEach(composer);

        return new LongWrapper(composer.getLong(), oneTimePad);

    }

    /**
     * Decrypt integer.
     *
     * @param wrapper the wrapper
     * @return the integer
     */
    public Long decrypt(LongWrapper wrapper) {
        Long value = wrapper.getEncryptedValue();
        boolean negative = value < 0;
        if (negative)
            value = -value;

        NumberComposer composer = new NumberComposer(negative);
        Arrays.stream(value.toString().chars().toArray()).map(new DigitDecryptor(wrapper.getOneTimePad())).forEach(composer);
        return composer.getLong();

    }


}

