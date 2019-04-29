package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.StringWrapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCipherEngineTest {

    private static final String FOX = "The quick brown fox jumped over the lazy dog in 1,2,3 and yelped: @##$^&!";
    StringCipherEngine engine;

    @Before
    public void init() {
        engine = new StringCipherEngine();

    }

    @Test
    public void testStringEncryption() {

        StringWrapper msg = engine.encrypt(FOX);
        assertNotNull(msg);
        assertNotNull(msg.getEncryptedText());
        assertNotNull(msg.getEncryptionKeys());
        //  assertEquals(msg.getEncodedMessage().length(), msg.getEncryptionKeys().length);
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

}
