package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.BigDecimalWrapper;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

//@Ignore
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
        BigDecimal zip = new BigDecimal(78757.0f);

        BigDecimalWrapper wrapper = engine.encipher(zip);
        assertEquals(zip.toString().length(), wrapper.getEncryptedValue().toString().length());

    }

    @Test
    //  @Ignore
    public void testNegative() {
        BigDecimal underTest = new BigDecimal(-190.1f);
        System.out.println("Under test:  " + underTest);
        BigDecimalWrapper wrapper = engine.encipher(underTest);
        BigDecimal decrypted = engine.decipher(wrapper);
        assertEquals(underTest, decrypted);
        System.out.println(decrypted);
    }

    @Test
    //@Ignore
    public void testBoundariesRoundTrip() {
        BigDecimal underTest = new BigDecimal(190.1f);

        BigDecimal decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);

        //System.out.println(decrypted);

    }

    @Test
    //   @Ignore
    public void testMax() {
        BigDecimal underTest = new BigDecimal(340282346638528859811704183484516925440f);
        // Float underTest = new Float(Float.MAX_VALUE);
        for (int i = 0; i < 10; i++) {


            BigDecimal decrypted = doRoundTrip(underTest);
            //    System.out.println("Decrypted: "+new BigDecimal(decrypted).toString());
            assertEquals(underTest, decrypted);


        }
    }

    @Test
    public void typeConversion() {
        Float underTest = -190.1234567f;//new Float(340282346638528859811704183484516925440f);
        System.out.println(underTest);
        BigDecimal bd = new BigDecimal(underTest);
        int decimal = 0;

        System.out.println(underTest);
        System.out.println(decimal);

        System.out.println(bd.toString());
        System.out.println(bd.scale());
    }

    private BigDecimal doRoundTrip(BigDecimal value) {
        BigDecimalWrapper wrapper = engine.encipher(value);
        System.out.print("One Time Pad: ");
        Arrays.stream(wrapper.getOneTimePad()).forEach(c -> System.out.print(c));
        String enciphered = wrapper.getEncryptedValue().toString();
        System.out.println("\nEnciphered: " + enciphered);
        System.out.printf("Length of pad and enciphered: %d,%d\n", wrapper.getOneTimePad().length, enciphered.length());
        BigDecimal decrypted = engine.decipher(wrapper);
        return decrypted;
    }
}