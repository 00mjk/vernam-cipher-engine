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
    public void testValidLength() {
        Integer zip = 78757;


        IntegerWrapper wrapper = engine.encrypt(zip);

        assertNotEquals(zip, wrapper.getEncryptedValue());
        assertEquals(zip.toString().length(), wrapper.getEncryptedValue().toString().length());
        // System.out.println(wrapper.getEncryptedValue().toString());

    }

    @Test
    //  @Ignore("Negative is not currently handled...")
    public void testNegative() {
        Integer underTest = -190;

        Integer decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);
        System.out.println(decrypted);
    }

    @Test
    public void testBoundariesRoundTrip() {
        Integer underTest = 190;


        for (int i = 0; i < 10000; i++) {
            Integer decryptzip = doRoundTrip(underTest);
            assertEquals(underTest, decryptzip);

            //System.out.println(decryptzip);
        }


    }

    private Integer doRoundTrip(Integer underTest) {
        IntegerWrapper wrapper = engine.encrypt(underTest);


        return engine.decrypt(wrapper);
    }

}