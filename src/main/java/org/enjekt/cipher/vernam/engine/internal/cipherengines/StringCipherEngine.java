package org.enjekt.cipher.vernam.engine.internal.cipherengines;


import org.enjekt.cipher.vernam.engine.api.StringWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.StringComposer;

import java.util.Arrays;

/**
 * The type String cipher engine. Generates values from lower to upper limit specified for encryption
 * and does the wrapping as needed to keep values in range.
 */
public class StringCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 32;
    private static final int UPPER_UTF8_LIMIT = 126;


    /**
     * The Cipher engine which handles UTF8 and key generation.
     */
    private final UTF8CipherEngine cipherEngine = new UTF8CipherEngine(LOWER_UTF8_LIMIT, UPPER_UTF8_LIMIT);


    /**
     * Encrypt string wrapper. Returns the encrypted String value and the integer keys
     * required for decryption.
     *
     * @param value the value
     * @return the string wrapper
     */
    public StringWrapper encrypt(String value) {

        EncryptedResultsHolder holder = cipherEngine.encrypt(value.chars().toArray());

        StringComposer composer = new StringComposer();
        Arrays.stream(holder.getValues()).forEach(composer);
        return new StringWrapper(composer.getString(), holder.getKeys());

    }


    /**
     * Decrypt string.
     *
     * @param wrapper the wrapper
     * @return the string
     */
    public String decrypt(StringWrapper wrapper) {

        int[] utf8Values = cipherEngine.decrypt(wrapper.getEncryptedText().chars().toArray(), wrapper.getEncryptionKeys());
        StringComposer composer = new StringComposer();
        Arrays.stream(utf8Values).forEach(composer);
        return composer.getString();
    }


}

