package org.enjekt.cipher.vernam.engine.internal;

import org.enjekt.cipher.vernam.engine.api.*;
import org.enjekt.cipher.vernam.engine.internal.cipherengines.*;
import org.osgi.service.component.annotations.Component;

import java.math.BigDecimal;

@Component
public class VernamCipherEngineImpl implements VernamCipherEngine {

    private final StringCipherEngine stringCipherEngine = new StringCipherEngine();
    private final IntegerCipherEngine integerCipherEngine = new IntegerCipherEngine();
    private final LongCipherEngine longCipherEngine = new LongCipherEngine();
    private final FloatCipherEngine floatCipherEngine = new FloatCipherEngine();
    private final BooleanCipherEngine booleanCipherEngine = new BooleanCipherEngine();

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

    @Override
    public BooleanWrapper encipher(Boolean value) {
        return booleanCipherEngine.encipher(value);
    }

    @Override
    public Boolean decipher(BooleanWrapper message) {
        return booleanCipherEngine.decipher(message);
    }


    @Override
    public BigDecimalWrapper encipher(BigDecimal value) {
        return floatCipherEngine.encipher(value);
    }

    @Override
    public BigDecimal decipher(BigDecimalWrapper message) {
        return floatCipherEngine.decipher(message);
    }

}
