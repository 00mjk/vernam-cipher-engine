package org.enjekt.cipher.vernam.engine.api;

public interface VernamCipherEngine {

    StringWrapper encrypt(String messageToEncryt);

    String decrypt(StringWrapper wrapper);

    IntegerWrapper encrypt(Integer value);

    Integer decrypt(IntegerWrapper wrapper);

    LongWrapper encrypt(Long value);

    Long decrypt(LongWrapper wrapper);
}
