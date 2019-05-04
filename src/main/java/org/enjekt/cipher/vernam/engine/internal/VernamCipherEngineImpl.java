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
    public StringWrapper encipher(String messageToEncrypt) {
        return stringCipherEngine.encipher(messageToEncrypt);
    }

    @Override
    public String decipher(StringWrapper wrapper) {
        return stringCipherEngine.decipher(wrapper);
    }

    @Override
    public IntegerWrapper encipher(Integer value) {
        return integerCipherEngine.encipher(value);
    }

    @Override
    public Integer decipher(IntegerWrapper message) {
        return integerCipherEngine.decipher(message);
    }

    @Override
    public LongWrapper encipher(Long value) {
        return longCipherEngine.encipher(value);
    }

    @Override
    public Long decipher(LongWrapper wrapper) {
        return longCipherEngine.decipher(wrapper);
    }


}
