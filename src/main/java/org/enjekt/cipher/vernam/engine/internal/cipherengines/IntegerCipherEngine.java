package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.functions.ValueComposer;

import java.util.Arrays;


public class IntegerCipherEngine {

    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;


    UTF8CipherEngine cipherEngine = new UTF8CipherEngine(LOWER_UTF8_LIMIT, UPPER_UTF8_LIMIT);

    public Integer decrypt(IntegerWrapper message) {
        return cipherEngine.decrypt(message.getEncryptedValue().toString(), message.getEncryptionKeys()).getInteger();

    }

    public IntegerWrapper encrypt(Integer value) {

        EncryptedResultsHolder holder = cipherEngine.encrypt(value.toString());
        ValueComposer composer = new ValueComposer();
        Arrays.stream(holder.getValues()).forEach(composer);
        return new IntegerWrapper(composer.getInteger(), holder.getKeys());

    }


}

