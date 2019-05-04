package org.enjekt.cipher.vernam.engine.api;

public interface VernamCipherEngine {

    StringWrapper encipher(String messageToEncryt);

    String decipher(StringWrapper wrapper);

    IntegerWrapper encipher(Integer value);

    Integer decipher(IntegerWrapper wrapper);

    LongWrapper encipher(Long value);

    Long decipher(LongWrapper wrapper);
}
