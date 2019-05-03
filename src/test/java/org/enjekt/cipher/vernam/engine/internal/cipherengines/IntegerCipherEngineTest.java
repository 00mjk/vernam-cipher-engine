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
    public void testValidLength() {
        Integer zip = 78757;

        IntegerWrapper wrapper = engine.encrypt(zip);

        assertNotEquals(zip, wrapper.getEncryptedValue());
        //   System.out.println(wrapper.getEncryptedValue().toString());

        assertEquals(zip.toString().length(), wrapper.getEncryptedValue().toString().length());

    }

    @Test
    public void testNegative() {
        Integer underTest = -190;

        Integer decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);
        //  System.out.println(decrypted);
    }

    @Test
    public void testBoundariesRoundTrip() {
        Integer underTest = 190;

        Integer decrypted = doRoundTrip(underTest);
        assertEquals(underTest, decrypted);

        //System.out.println(decrypted);

    }

    @Test
    public void timeTestCycles() {
        int numberOfCycles = 10000000;
        System.out.println("Running bulk timed test of encyrpt/decrypt of " + numberOfCycles + " cylces.");

        List<Integer> testNumbers = new ArrayList<>(numberOfCycles);
        for (int i = 0; i < numberOfCycles; i++)
            testNumbers.add(RandomNumberGenerator.nextInt());

        Clock clock = Clock.systemUTC();
        Instant start = clock.instant();

        List<IntegerWrapper> wrappers = new ArrayList<>(numberOfCycles);
        for (int i = 0; i < numberOfCycles; i++) {

            Integer underTest = testNumbers.get(i);
            wrappers.add(engine.encrypt(underTest));
        }
        Instant end = clock.instant();
        double seconds = (end.toEpochMilli() - start.toEpochMilli()) / 1000;
        System.out.println(numberOfCycles + " encrypt operations in " + seconds + " seconds.");
        double calculationsPerSecond = numberOfCycles / (end.toEpochMilli() - start.toEpochMilli());
        System.out.println("Number of encrypt operations per millisecond: " + calculationsPerSecond);

        start = clock.instant();
        for (IntegerWrapper wrapper : wrappers) {
            Integer decrypted = engine.decrypt(wrapper);
        }
        end = clock.instant();
        seconds = (end.toEpochMilli() - start.toEpochMilli()) / 1000;
        System.out.println(numberOfCycles + " decrypt operations in " + seconds + " seconds.");
        calculationsPerSecond = numberOfCycles / (end.toEpochMilli() - start.toEpochMilli());
        System.out.println("Number of decrypt operations per millisecond: " + calculationsPerSecond);


    }

    @Test
    public void testBulkRandom() {
        int numberOfCycles = 100000;
        System.out.println("Running bulk encyrpt/decrypt of " + numberOfCycles + " cylces.");
        for (int i = 0; i < numberOfCycles; i++) {

            Integer underTest = RandomNumberGenerator.nextInt();
            IntegerWrapper wrapper = engine.encrypt(underTest);
            assertNotEquals(wrapper.getEncryptedValue(), underTest);
            //  System.out.println(underTest+","+wrapper.getEncryptedValue());
            Integer decrypted = engine.decrypt(wrapper);
            if (!decrypted.equals(underTest))
                System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

            assertEquals(underTest, decrypted);
        }

    }

    @Test
    public void testMinMax() {
        Integer underTest = Integer.MAX_VALUE;
        Integer decrypted = doRoundTrip(underTest);
        if (!decrypted.equals(underTest))
            System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

        assertEquals(underTest, decrypted);

        //TODO The lower bound is 1 less than upper and the cipher engine should
        //be cahnged to handle it.
        underTest = Integer.MIN_VALUE + 1;
        decrypted = doRoundTrip(underTest);
        if (!decrypted.equals(underTest))
            System.out.println("Exepected: " + underTest + ", Got: " + decrypted);

        assertEquals(underTest, decrypted);
    }

    public Integer doRoundTrip(Integer value) {
        IntegerWrapper wrapper = engine.encrypt(value);

        Integer decrypted = engine.decrypt(wrapper);
        return decrypted;
    }
}