package org.enjekt.cipher.vernam.engine.internal;

import java.math.BigDecimal;

import org.enjekt.cipher.vernam.engine.api.ObjectWrapper;
import org.enjekt.cipher.vernam.engine.internal.cipherengines.ObjectCipherEngine;

public class VernamEngineImpl {

		ObjectCipherEngine cipherEngine = new ObjectCipherEngine();

	    public ObjectWrapper encipher(String messageToEncrypt) {
	        return cipherEngine.encipher(messageToEncrypt);
	    }


	    public String decipherString(ObjectWrapper wrapper) {
	        return (String) cipherEngine.decipher(wrapper);
	    }


	    public ObjectWrapper encipher(Integer value) {
	        return cipherEngine.encipher(value);
	    }


	    public Integer decipherInteger(ObjectWrapper message) {
	        return (Integer) cipherEngine.decipher(message);
	    }


	    public ObjectWrapper encipher(Long value) {
	        return cipherEngine.encipher(value);
	    }


	    public Long decipherLong(ObjectWrapper wrapper) {
	        return (Long) cipherEngine.decipher(wrapper);
	    }


	    public ObjectWrapper encipher(Boolean value) {
	        return cipherEngine.encipher(value);
	    }


	    public Boolean decipherBoolean(ObjectWrapper message) {
	        return (Boolean) cipherEngine.decipher(message);
	    }



	    public ObjectWrapper encipher(BigDecimal value) {
	        return cipherEngine.encipher(value);
	    }


	    public BigDecimal decipherBigDecimal(ObjectWrapper message) {
	        return (BigDecimal) cipherEngine.decipher(message);
	    }

}
