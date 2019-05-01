package org.enjekt.cipher.vernam.engine.internal.functions;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class IntegerEncryptorTest {
    SecureRandom random = new SecureRandom();

    @Test
    public void testBulkRandom() {
        int totalCount = 1000000;

        for (int i = 0; i < totalCount; i++) {

            //   System.out.print(i%totalCount);
            // Integer underTest = random.nextInt(upperbound - lowerbound) + lowerbound;
            Integer underTest = random.nextInt();
            if (underTest < 0) underTest = -underTest;
            int[] values = underTest.toString().chars().toArray();
            int[] keys = new int[values.length];


            Integer encryptedInteger = invokeIntegerEncrypt(values, keys);

            if (underTest.toString().length() != encryptedInteger.toString().length())
                System.out.println("\n" + underTest + "," + encryptedInteger.toString());
            assertEquals(underTest.toString().length(), encryptedInteger.toString().length());

            Integer reconstructedInteger = decryptInteger(keys, encryptedInteger);
            if (!reconstructedInteger.equals(underTest)) {
                System.out.println("\nExpected : " + underTest);
                System.out.println("Encrypted  : " + encryptedInteger);
                System.out.print("Encyrpt keys:");
                Arrays.stream(keys).forEach(x -> System.out.print(x));
                System.out.println("\nReconstructed: " + reconstructedInteger);
            }
            assertEquals(underTest, reconstructedInteger);

        }
        System.out.println("Ran " + totalCount + " random tests.");

    }

    private Integer decryptInteger(int[] keys, Integer encryptedInteger) {
        int[] values = encryptedInteger.toString().chars().toArray();
        IntegerComposer composer = new IntegerComposer(Boolean.FALSE);
        Arrays.stream(values).map(new IntegerDecryptor(keys)).forEach(composer);
        return composer.getInteger();
    }

    private Integer invokeIntegerEncrypt(int[] values, int[] keys) {
        IntegerComposer composer = new IntegerComposer(Boolean.FALSE);
        Arrays.stream(values).map(new IntegerEncryptor(keys)).forEach(composer);
        return composer.getInteger();
    }
}