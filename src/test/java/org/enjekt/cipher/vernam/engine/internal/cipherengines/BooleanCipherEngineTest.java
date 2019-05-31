package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.BooleanWrapper;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGeneratorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BooleanCipherEngineTest {

    private BooleanCipherEngine engine;

    @Mock
    RandomNumberGenerator randomNumberGenerator = Mockito.mock(RandomNumberGenerator.class);

    @Before
    public void init() {
        engine = new BooleanCipherEngine();
        engine.setRandomNumberGenerator(randomNumberGenerator);
    }

    @After
    public void end() {
        RandomNumberGeneratorFactory.setGenerator(null);
    }

    @Test
    public void testKnownValuesForXOR() {

        doRoundTrip(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
        doRoundTrip(Boolean.FALSE, Boolean.TRUE, Boolean.TRUE);
        doRoundTrip(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
        doRoundTrip(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);


    }


    private void doRoundTrip(Boolean toEncrypt, Boolean oneTimePad, Boolean expectedXOR) {
        // System.out.println(toEncrypt + " ^ " + oneTimePad + " = " + expectedXOR);
        BooleanWrapper enciphered = encipher(toEncrypt, oneTimePad);
        System.out.println(enciphered.getEncryptedValue());
        assertEquals(expectedXOR, enciphered.getEncryptedValue());

        assertEquals(toEncrypt, decipher(enciphered));
    }


    private BooleanWrapper encipher(Boolean toEncrypt, Boolean oneTimePad) {
        //System.out.println("oneTimePad " + oneTimePad);
        when(randomNumberGenerator.nextBoolean()).thenReturn(oneTimePad);

        return engine.encipher(toEncrypt);
    }

    private Boolean decipher(BooleanWrapper wrapper) {
        return engine.decipher(wrapper);
    }

}