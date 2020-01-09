package org.enjekt.cipher.vernam.engine.api;

import java.math.BigDecimal;

public interface VernamCipherEngine {

    StringWrapper encipher(String messageToEncryt);

    String decipher(StringWrapper wrapper);

    IntegerWrapper encipher(Integer value);

    Integer decipher(IntegerWrapper wrapper);

    LongWrapper encipher(Long value);

    Long decipher(LongWrapper wrapper);

    BooleanWrapper encipher(Boolean value);

    Boolean decipher(BooleanWrapper message);

    BigDecimalWrapper encipher(BigDecimal value);

    BigDecimal decipher(BigDecimalWrapper message);

	<T> T decipherObject(ObjectWrapper message, Class<T> clazz);

	ObjectWrapper encipherObject(Object value);
}
