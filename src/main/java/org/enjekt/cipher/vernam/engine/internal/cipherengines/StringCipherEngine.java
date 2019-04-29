package org.enjekt.cipher.vernam.engine.internal.cipherengines;


import org.enjekt.cipher.vernam.engine.api.StringWrapper;

public class StringCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 32;
    private static final int UPPER_UTF8_LIMIT = 126;
    private static final int LOWER_LIMIT = 0;


    UTF8CipherEngine cipherEngine = new UTF8CipherEngine(LOWER_UTF8_LIMIT, UPPER_UTF8_LIMIT, LOWER_LIMIT, UPPER_UTF8_LIMIT);


    public StringWrapper encrypt(String value) {

        EncryptedResultsHolder holder = cipherEngine.encrypt(value);
        return new StringWrapper(holder.getComposer().getString(), holder.getKeys());

    }


    public String decrypt(StringWrapper wrapper) {
        return cipherEngine.decrypt(wrapper.getEncryptedText(), wrapper.getEncryptionKeys()).getString();
    }


}

