package org.enjekt.cipher.vernam.engine.internal.cipherengines;


import org.enjekt.cipher.vernam.engine.api.StringWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.AddWrapLimit;
import org.enjekt.cipher.vernam.engine.internal.functions.SubtractWrapLimit;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGeneratorFactory;

/**
 * The type String cipher engine. Generates values from lower to upper limit specified for encryption
 * and does the wrapping as needed to keep values in range.
 */
public class StringCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 32;
    private static final int UPPER_UTF8_LIMIT = 126;
    private static final int lowerKeyRange = 0;
    private static final int upperKeyRange = UPPER_UTF8_LIMIT - LOWER_UTF8_LIMIT;
    private RandomNumberGenerator randomNumberGenerator = RandomNumberGeneratorFactory.getGenerator();



    /**
     * Encrypt string wrapper. Returns the encrypted String value and the integer keys
     * required for decryption.
     *
     * @param value the value
     * @return the string wrapper
     */
    public StringWrapper encipher(String value) {

        StringBuffer buffer = new StringBuffer();
        int[] oneTimePad = randomNumberGenerator.ints(value.length(), lowerKeyRange, upperKeyRange).toArray();
        value.chars().map(new AddWrapLimit(oneTimePad, UPPER_UTF8_LIMIT, upperKeyRange + 1)).forEach(c -> buffer.append((char) c));

        return new StringWrapper(buffer.toString(), oneTimePad);

    }


    /**
     * Decrypt string.
     *
     * @param wrapper the wrapper
     * @return the string
     */
    public String decipher(StringWrapper wrapper) {
        StringBuffer buffer = new StringBuffer();
        wrapper.getEncryptedText().chars().map(new SubtractWrapLimit(wrapper.getEncryptionKeys(), LOWER_UTF8_LIMIT, upperKeyRange + 1)).forEach(c -> buffer.append((char) c));

        return buffer.toString();
    }



}

