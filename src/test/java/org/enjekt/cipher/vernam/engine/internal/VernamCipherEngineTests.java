package org.enjekt.cipher.vernam.engine.internal;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.api.StringWrapper;
import org.enjekt.cipher.vernam.engine.api.VernamCipherEngine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VernamCipherEngineTests {

    private static final String FOX = "The quick brown fox jumped over the lazy dog in 1,2,3 and yelped: @##$^&!";
    private VernamCipherEngine engine;

    @Before
    public void init() {
        engine = new VernamCipherEngineImpl();

    }

    @Test
    public void testStringEncryption() {

        StringWrapper msg = engine.encrypt(FOX);
        assertNotNull(msg);
        assertNotNull(msg.getEncryptedText());
        assertNotNull(msg.getEncryptionKeys());
        assertEquals(msg.getEncryptedText().length(), FOX.length());
        //System.out.println("Encrypted: " + msg.getMessage());
    }

    @Test
    public void thereAndBackAgain() {

        StringWrapper msg = engine.encrypt(FOX);
        assertNotNull(msg);
        System.out.println("Encrypted: " + msg.getEncryptedText());
        System.out.println(msg.getEncryptedText().length());

        String decrypted = engine.decrypt(msg);
        System.out.println("Decrypted: " + decrypted);
        System.out.println(decrypted.length());
        assertFalse(FOX == decrypted);
        assertTrue(FOX.equals(decrypted));

    }


    @Test
    public void testRoundTripIntegerEncryptDecrypt() {
        Integer zip = 78757;


        //for(int i=0;i<100;i++) {
        IntegerWrapper wrapper = engine.encrypt(zip);


        Integer decryptzip = engine.decrypt(wrapper);
        assertEquals(zip, decryptzip);

        System.out.println(decryptzip);
        //}


    }


}