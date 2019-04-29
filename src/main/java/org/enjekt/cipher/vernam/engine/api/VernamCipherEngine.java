package org.enjekt.cipher.vernam.engine.api;

public interface VernamCipherEngine {

    StringWrapper encrypt(String messageToEncryt);

    String decrypt(StringWrapper message);

    Integer decrypt(IntegerWrapper integer);

    IntegerWrapper encrypt(Integer value);
}
