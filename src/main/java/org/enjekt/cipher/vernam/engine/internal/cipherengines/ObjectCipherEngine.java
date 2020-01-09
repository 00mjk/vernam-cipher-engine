package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import org.enjekt.cipher.vernam.engine.api.ObjectWrapper;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGeneratorFactory;

public class ObjectCipherEngine {
	private final RandomNumberGenerator randomNumberGenerator = RandomNumberGeneratorFactory.getGenerator();


	public ObjectWrapper encipher(Object toEncipher) {
			
		byte[] bytes = getBytes(toEncipher);
		byte[] pad = new byte[bytes.length];
		randomNumberGenerator.nextBytes(pad);
		
		byte[] padded = xor(bytes,pad);
	
		return new ObjectWrapper(padded,pad);
	}
	public Object decipher(ObjectWrapper msg) {
		return getObject(xor(msg.getPadded(),msg.getPad()));
	}

	private Object getObject(byte[] xorBytes) {
		Object returnObj=null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(xorBytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			returnObj = ois.readObject();
		} catch (Exception e) {
			
		}
		return returnObj;
	}
	private byte[] xor(byte[] bytes, byte[] pad) {
		byte[] padded = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			for(int i=0;i<bytes.length;i++)
				bos.write(bytes[i]^pad[i]);
			bos.flush();
			padded=bos.toByteArray();
		} catch (IOException e) {

		}
		return padded;
	}
	
	private byte[] getBytes(Object toEncipher) {
		
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			oos.writeObject(toEncipher);
			oos.flush();
			
			
			bytes = bos.toByteArray();
		} catch (IOException e) {
			//TODO...
		}
		
		return bytes;
	}



}
