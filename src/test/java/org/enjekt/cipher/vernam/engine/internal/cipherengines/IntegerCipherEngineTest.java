package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.IntegerWrapper;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IntegerCipherEngineTest {

    RandomNumberGenerator random = new RandomNumberGenerator();
    private IntegerCipherEngine engine;

    @Before
    public void init() {
        engine = new IntegerCipherEngine();
    }

    @Test
    // @Ignore
    public void testValidLength() {
        Integer zip = 78757;

        IntegerWrapper wrapper = engine.encipher(zip);

        assertNotEquals(zip, wrapper.getEncryptedValue());
        //   System.out.println(wrapper.getEncryptedValue().toString());

        assertEquals(zip.toString().length(), wrapper.getEncryptedValue().toString().length());

    }

    @Test
    //  @Ignore
    public void testNegative() {
        Integer underTest = -190;

        Integer decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);
        //  System.out.println(decrypted);
    }

    @Test
    //@Ignore
    public void testBoundariesRoundTrip() {
        Integer underTest = 190;

        Integer decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);

        //System.out.println(decrypted);

    }

    @Test
    // @Ignore
    public void testBulkRandom() {
        int numberOfCycles = 1000000;
        //2,147,483,647
        System.out.println("Running bulk encyrpt/decrypt of " + numberOfCycles + " cylces.");
        System.out.println(Integer.valueOf(Integer.MAX_VALUE).toString().chars().toArray().length);
        for (int i = 0; i < numberOfCycles; i++) {
            Integer underTest = RandomNumberGenerator.nextInt();
            IntegerWrapper wrapper = engine.encipher(underTest);
            //TODO It is possible that the pad will result in the same number.
            //  assertNotEquals(wrapper.getEncryptedValue(), underTest);
            // System.out.printf("Encrypted value: %d\n",wrapper.getEncryptedValue());
            if (wrapper.getEncryptedValue().equals(underTest))
                System.out.println(underTest + "," + wrapper.getEncryptedValue());
            Integer decrypted = engine.decipher(wrapper);
            if (!decrypted.equals(underTest))
                System.out.println("Exepected: " + underTest + ", Got: " + decrypted);
            assertEquals(underTest, decrypted);
        }

    }

    @Test
    // @Ignore
    public void timeTestCycles() {

        for (int i = 0; i < 100; i++)
            timedTest();
    }

    private void timedTest() {
        int numberOfCycles = 100000;
        System.out.println("Running bulk timed test of encyrpt/decrypt of " + numberOfCycles + " cylces.");

        List<Integer> testNumbers = new ArrayList<>(numberOfCycles);
        for (int i = 0; i < numberOfCycles; i++)
            testNumbers.add(RandomNumberGenerator.nextInt());

        Clock clock = Clock.systemUTC();
        Instant start = clock.instant();

        List<IntegerWrapper> wrappers = new ArrayList<>(numberOfCycles);
        for (int i = 0; i < numberOfCycles; i++) {

            Integer underTest = testNumbers.get(i);
            wrappers.add(engine.encipher(underTest));
        }
        Instant end = clock.instant();
        double milliseconds = (end.toEpochMilli() - start.toEpochMilli());
        System.out.println(numberOfCycles + " encrypt operations in " + milliseconds + " milliseconds.");
        int calculationsPerSecond = (int) (numberOfCycles / milliseconds);
        System.out.println("Number of encrypt operations per millisecond: " + calculationsPerSecond);

        start = clock.instant();
        for (IntegerWrapper wrapper : wrappers) {
            Integer decrypted = engine.decipher(wrapper);
        }
        end = clock.instant();
        milliseconds = (end.toEpochMilli() - start.toEpochMilli());
        System.out.println(numberOfCycles + " decrypt operations in " + milliseconds + " milliseconds.");
        calculationsPerSecond = (int) (numberOfCycles / milliseconds);
        System.out.println("Number of decrypt operations per milliseconds: " + calculationsPerSecond);
        System.out.println("*****************");
    }

    @Test
    public void testMAXValue() {
        Integer underTest = Integer.MAX_VALUE;
        Integer decrypted = doRoundTrip(underTest);
        if (!decrypted.equals(underTest))
            System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

        assertEquals(underTest, decrypted);

    }

    @Test
    // @Ignore
    public void testMINValue() {

        Integer underTest = Integer.MIN_VALUE;
        System.out.println(underTest);
        for (int i = 0; i < 10000; i++) {
            Integer decrypted = doRoundTrip(underTest);
            if (!decrypted.equals(underTest))
                System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

            assertEquals(underTest, decrypted);
        }
    }

    private Integer doRoundTrip(Integer value) {
        IntegerWrapper wrapper = engine.encipher(value);

        Integer decrypted = engine.decipher(wrapper);
        return decrypted;
    }
}