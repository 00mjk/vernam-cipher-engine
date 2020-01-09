package org.enjekt.cipher.vernam.engine.internal;

import static org.junit.Assert.*;

import org.enjekt.cipher.vernam.engine.api.LongWrapper;
import org.enjekt.cipher.vernam.engine.api.ObjectWrapper;
import org.enjekt.cipher.vernam.engine.api.StringWrapper;
import org.enjekt.cipher.vernam.engine.internal.VernamEngineImpl;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGeneratorFactory;
import org.junit.Before;
import org.junit.Test;

public class ObjectCipherEngineTest {
	private static final String FOX = "The quick brown fox jumped over the lazy dog in 1,2,3 and yelped: @##$^&!";
	VernamEngineImpl engine;

	@Before
	public void init() {
		engine = new VernamEngineImpl();

	}

	@Test
	public void thereAndBackAgainString() {

		ObjectWrapper msg = engine.encipher(FOX);
		assertNotNull(msg);

		Object decrypted = engine.decipherString(msg);
		System.out.println("Decrypted: " + decrypted);

		assertFalse(FOX == decrypted);
		assertTrue(FOX.equals(decrypted));

	}

	@Test
	public void thereAndBackAgainInteger() {

		ObjectWrapper msg = engine.encipher(99);
		assertNotNull(msg);

		Integer decrypted = engine.decipherInteger(msg);
		System.out.println("Decrypted: " + decrypted);

		assertTrue(decrypted.equals(99));

	}

	@Test
	public void testNegative() {
		Long underTest = (long) -190;

		Long decrypted = doRoundTripLong(underTest);
		assertEquals(underTest, decrypted);
		// System.out.println(decrypted);
	}

	@Test
	public void testBoundariesRoundTrip() {
		Long underTest = 190L;

		Long decrypted = doRoundTripLong(underTest);
		assertEquals(underTest, decrypted);

		// System.out.println(decrypted);

	}

	@Test
	public void testBulkRandomLong() {

		for (int i = 0; i < 10000; i++) {

			Long underTest = RandomNumberGeneratorFactory.getGenerator().nextLong();
			ObjectWrapper wrapper = engine.encipher(underTest);
	
			Long decrypted = engine.decipherLong(wrapper);
			if (!decrypted.equals(underTest))
				System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

			assertEquals(underTest, decrypted);

		}

	}

	@Test
	public void testMaxLong() {
		Long underTest = Long.MAX_VALUE;
		Long decrypted = doRoundTripLong(underTest);
		if (!decrypted.equals(underTest))
			System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

		assertEquals(underTest, decrypted);

	}

	@Test
	public void testMinLong() {
	
		Long underTest = Long.MIN_VALUE;
		Long decrypted = doRoundTripLong(underTest);
		if (!decrypted.equals(underTest))
			System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

		assertEquals(underTest, decrypted);
	}

	private Long doRoundTripLong(Long value) {
		ObjectWrapper wrapper = engine.encipher(value);

		Long decrypted = engine.decipherLong(wrapper);
		return decrypted;
	}
}
