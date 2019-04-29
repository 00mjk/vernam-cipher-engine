package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IntegerCipherEngineTest {

    private IntegerCipherEngine engine;

    @Before
    public void init() {
        engine = new IntegerCipherEngine();
    }

    @Test
    public void testEncrypt() {
        Integer zip = 78757;


        IntegerWrapper wrapper = engine.encrypt(zip);

        assertNotEquals(zip, wrapper.getEncryptedValue());
        assertEquals(zip.toString().length(), wrapper.getEncryptedValue().toString().length());
        // System.out.println(wrapper.getEncryptedValue().toString());

    }

    @Test
    public void testRoundTripIntegerEncryptDecrypt() {
        Integer zip = 78757;


        for (int i = 0; i < 1000; i++) {
            IntegerWrapper wrapper = engine.encrypt(zip);


            Integer decryptzip = engine.decrypt(wrapper);
            assertEquals(zip, decryptzip);

            // System.out.println(decryptzip);
        }


    }

}