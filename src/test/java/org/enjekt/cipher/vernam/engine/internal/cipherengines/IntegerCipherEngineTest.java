package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IntegerCipherEngineTest {

    private IntegerCipherEngine engine;

    @Before
    public void init() {
        engine = new IntegerCipherEngine();
    }

    @Test
    public void testValidLength() {
        Integer zip = 78757;


        IntegerWrapper wrapper = engine.encrypt(zip);

        assertNotEquals(zip, wrapper.getEncryptedValue());
        System.out.println(wrapper.getEncryptedValue().toString());

        assertEquals(zip.toString().length(), wrapper.getEncryptedValue().toString().length());

    }

    @Test
    public void testNegative() {
        Integer underTest = -190;

        Integer decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);
        System.out.println(decrypted);
    }

    @Test
    public void testBoundariesRoundTrip() {
        Integer underTest = 190;


        Integer decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);

        //System.out.println(decrypted);



    }

    SecureRandom random = new SecureRandom();

    @Test
    public void testBulkRandom() {
        int upperbound = 10000;
        int lowerbound = -10000;

        for (int i = 0; i < 100000; i++) {
            Integer underTest = random.nextInt(upperbound - lowerbound) + lowerbound;
            IntegerWrapper wrapper = engine.encrypt(underTest);

            Integer decrypted = engine.decrypt(wrapper);
            if (!decrypted.equals(underTest))
                System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

            assertEquals(underTest, decrypted);

        }


    }

    public Integer doRoundTrip(Integer value) {
        IntegerWrapper wrapper = engine.encrypt(value);

        Integer decrypted = engine.decrypt(wrapper);
        return decrypted;
    }
}