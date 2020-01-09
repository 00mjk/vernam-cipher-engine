package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.enjekt.cipher.vernam.engine.api.ObjectWrapper;
import org.enjekt.cipher.vernam.engine.internal.FooBar;
import org.junit.Before;
import org.junit.Test;

public class ObjectCipherEngineTest {
	ObjectCipherEngine engine;

	@Before
	public void init() {
		engine = new ObjectCipherEngine();

	}


	@Test
	public void testObjectEnicpher() {
		FooBar fooBar = new FooBar();
		fooBar.setName("Merlin");
		fooBar.setRank(1);
		fooBar.setSerialNumber(1111111111113333L);
		ObjectWrapper wrapper = engine.encipher(fooBar);
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			outputStream.write(wrapper.getPadded());
			outputStream.writeTo(System.out);
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(wrapper);
		FooBar fooBarReturned = (FooBar) engine.decipher(wrapper);
		assertNotNull(fooBarReturned);
		System.out.println(fooBarReturned);
		
		
	}
}
