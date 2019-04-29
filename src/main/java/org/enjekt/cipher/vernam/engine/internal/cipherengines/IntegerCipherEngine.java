package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;


public class IntegerCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 9;

    UTF8CipherEngine cipherEngine = new UTF8CipherEngine(LOWER_UTF8_LIMIT, UPPER_UTF8_LIMIT, LOWER_LIMIT, UPPER_LIMIT);

    public Integer decrypt(IntegerWrapper message) {

        return cipherEngine.decrypt(message.getEncryptedValue().toString(), message.getEncryptionKeys()).getInteger();

    }

    public IntegerWrapper encrypt(Integer value) {

        EncryptedResultsHolder holder = cipherEngine.encrypt(value.toString());
        return new IntegerWrapper(holder.getComposer().getInteger(), holder.getKeys());

    }


}

