package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.LongWrapper;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LongCipherEngineTest {

    private LongCipherEngine engine;
    private RandomNumberGenerator random = new RandomNumberGenerator();

    @Before
    public void init() {
        engine = new LongCipherEngine();
    }

    @Test
    public void testValidLength() {
        Long zip = 78757L;

        LongWrapper wrapper = engine.encrypt(zip);

        assertNotEquals(zip, wrapper.getEncryptedValue());

        assertEquals(zip.toString().length(), wrapper.getEncryptedValue().toString().length());

    }

    @Test
    public void testNegative() {
        Long underTest = (long) -190;

        Long decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);
        //System.out.println(decrypted);
    }

    @Test
    public void testBoundariesRoundTrip() {
        Long underTest = 190L;

        Long decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);

        //System.out.println(decrypted);

    }


    @Test
    public void testBulkRandom() {


        for (int i = 0; i < 10000; i++) {

            Long underTest = RandomNumberGenerator.nextLong();
            LongWrapper wrapper = engine.encrypt(underTest);
            //   System.out.println("Encrypted: " + wrapper.getEncryptedValue());
            Long decrypted = engine.decrypt(wrapper);
            //    System.out.println("Decrypted: " + decrypted);
            if (!decrypted.equals(underTest))
                System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

            assertEquals(underTest, decrypted);
            // System.out.println();

        }


    }

    @Test
    public void testMax() {
        Long underTest = Long.MAX_VALUE;
        Long decrypted = doRoundTrip(underTest);
        if (!decrypted.equals(underTest))
            System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

        assertEquals(underTest, decrypted);

    }

    @Test
    public void testMin() {
        //be cahnged to handle it.
        Long underTest = Long.MIN_VALUE;
        Long decrypted = doRoundTrip(underTest);
        if (!decrypted.equals(underTest))
            System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

        assertEquals(underTest, decrypted);
    }

    private Long doRoundTrip(Long value) {
        LongWrapper wrapper = engine.encrypt(value);

        Long decrypted = engine.decrypt(wrapper);
        return decrypted;
    }
}