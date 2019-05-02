package org.enjekt.cipher.vernam.engine.internal;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.api.LongWrapper;
import org.enjekt.cipher.vernam.engine.api.StringWrapper;
import org.enjekt.cipher.vernam.engine.api.VernamCipherEngine;
import org.enjekt.cipher.vernam.engine.internal.cipherengines.IntegerCipherEngine;
import org.enjekt.cipher.vernam.engine.internal.cipherengines.LongCipherEngine;
import org.enjekt.cipher.vernam.engine.internal.cipherengines.StringCipherEngine;
import org.osgi.service.component.annotations.Component;

@Component
public class VernamCipherEngineImpl implements VernamCipherEngine {

    private final StringCipherEngine stringCipherEngine = new StringCipherEngine();
    private final IntegerCipherEngine integerCipherEngine = new IntegerCipherEngine();
    private final LongCipherEngine longCipherEngine = new LongCipherEngine();

    @Override
    public StringWrapper encrypt(String messageToEncrypt) {
        return stringCipherEngine.encrypt(messageToEncrypt);
    }

    @Override
    public String decrypt(StringWrapper wrapper) {
        return stringCipherEngine.decrypt(wrapper);
    }

    @Override
    public IntegerWrapper encrypt(Integer value) {
        return integerCipherEngine.encrypt(value);
    }

    @Override
    public Integer decrypt(IntegerWrapper message) {
        return integerCipherEngine.decrypt(message);
    }

    @Override
    public LongWrapper encrypt(Long value) {
        return longCipherEngine.encrypt(value);
    }

    @Override
    public Long decrypt(LongWrapper wrapper) {
        return longCipherEngine.decrypt(wrapper);
    }


}
