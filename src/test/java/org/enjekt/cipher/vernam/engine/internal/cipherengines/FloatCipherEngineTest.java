package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.FloatWrapper;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FloatCipherEngineTest {

    RandomNumberGenerator random = new RandomNumberGenerator();
    private FloatCipherEngine engine;

    @Before
    public void init() {
        engine = new FloatCipherEngine();
    }

    @Test
    // @Ignore
    public void testValidLength() {
        Float zip = 78757.0f;

        FloatWrapper wrapper = engine.encipher(zip);
        assertEquals(zip.toString().length(), wrapper.getEncryptedValue().toString().length());

    }

    @Test
    //  @Ignore
    public void testNegative() {
        Float underTest = -190.1f;

        Float decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);
        //System.out.println(decrypted);
    }

    @Test
    //@Ignore
    public void testBoundariesRoundTrip() {
        Float underTest = 190.1f;

        Float decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);

        //System.out.println(decrypted);

    }


    private Float doRoundTrip(Float value) {
        FloatWrapper wrapper = engine.encipher(value);

        Float decrypted = engine.decipher(wrapper);
        return decrypted;
    }
}