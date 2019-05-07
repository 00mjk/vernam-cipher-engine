package org.enjekt.cipher.vernam.engine.internal.cipherengines;


import org.enjekt.cipher.vernam.engine.api.StringWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.AddWrapLimit;
import org.enjekt.cipher.vernam.engine.internal.functions.IntCollector;
import org.enjekt.cipher.vernam.engine.internal.functions.StringComposer;
import org.enjekt.cipher.vernam.engine.internal.functions.SubtractWrapLimit;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;

import java.util.Arrays;

/**
 * The type String cipher engine. Generates values from lower to upper limit specified for encryption
 * and does the wrapping as needed to keep values in range.
 */
public class StringCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 32;
    private static final int UPPER_UTF8_LIMIT = 126;
    private static final int lowerKeyRange = 0;
    private static final int upperKeyRange = UPPER_UTF8_LIMIT - LOWER_UTF8_LIMIT;



    /**
     * Encrypt string wrapper. Returns the encrypted String value and the integer keys
     * required for decryption.
     *
     * @param value the value
     * @return the string wrapper
     */
    public StringWrapper encipher(String value) {

        EncryptedResultsHolder holder = encipher(value.chars().toArray());

        StringComposer composer = new StringComposer();
        Arrays.stream(holder.getValues()).forEach(composer);
        return new StringWrapper(composer.getString(), holder.getOneTimePad());

    }


    /**
     * Decrypt string.
     *
     * @param wrapper the wrapper
     * @return the string
     */
    public String decipher(StringWrapper wrapper) {

        int[] utf8Values = decipher(wrapper.getEncryptedText().chars().toArray(), wrapper.getEncryptionKeys());
        StringComposer composer = new StringComposer();
        Arrays.stream(utf8Values).forEach(composer);
        return composer.getString();
    }

    /**
     * Decrypt value composer.
     *
     * @param values     the toDecrypt
     * @param oneTimePad the encryption keys
     * @return the value composer
     */
    public int[] decipher(int[] values, int[] oneTimePad) {

        IntCollector collector = new IntCollector(values.length);
        Arrays.stream(values).map(new SubtractWrapLimit(oneTimePad, LOWER_UTF8_LIMIT, upperKeyRange + 1)).forEach(collector);

        return collector.getValues();


    }

    /**
     * Encrypt encrypted results holder.
     *
     * @param values the value
     * @return the encrypted results holder
     */
    public EncryptedResultsHolder encipher(int[] values) {
        int[] oneTimePad = RandomNumberGenerator.ints(values.length, lowerKeyRange, upperKeyRange).toArray();
        EncryptedResultsHolder holder = new EncryptedResultsHolder(oneTimePad);
        Arrays.stream(values).map(new AddWrapLimit(oneTimePad, UPPER_UTF8_LIMIT, upperKeyRange + 1)).forEach(holder);

        return holder;


    }

}

