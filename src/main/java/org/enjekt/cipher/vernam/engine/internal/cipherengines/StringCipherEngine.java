package org.enjekt.cipher.vernam.engine.internal.cipherengines;


import org.enjekt.cipher.vernam.engine.api.StringWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.ValueComposer;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StringCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 32;
    private static final int UPPER_UTF8_LIMIT = 126;


    UTF8CipherEngine cipherEngine = new UTF8CipherEngine(LOWER_UTF8_LIMIT, UPPER_UTF8_LIMIT);


    public StringWrapper encrypt(String value) {

        EncryptedResultsHolder holder = cipherEngine.encrypt(value);
        ValueComposer composer = new ValueComposer();
        Arrays.stream(holder.getValues()).forEach(composer);
        return new StringWrapper(composer.getString(), holder.getKeys());

    }


    public String decrypt(StringWrapper wrapper) {
        return cipherEngine.decrypt(wrapper.getEncryptedText(), wrapper.getEncryptionKeys()).getString();
    }


}

