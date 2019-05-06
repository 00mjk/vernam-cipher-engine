package org.enjekt.cipher.vernam.engine.internal;

import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.LongBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.BitSet;

public class ScratchPad {
    private static final int LOWER_UTF8_LIMIT = 48;
    private static final RandomNumberGenerator secureRandom = new RandomNumberGenerator();
    String toTest = "I see dead sheep...sometimes...";


    @Test
    public void doBigDecimal() {
        Float f = 123.4567f;
        int[] digits = f.toString().chars().map(c -> c - LOWER_UTF8_LIMIT).toArray();
        Arrays.stream(digits).forEach(d -> System.out.print(d));
        System.out.println();
        // BigDecimal bd = new BigDecimal(f);
        digits = Float.toString(Float.MAX_VALUE).chars().map(i -> i - LOWER_UTF8_LIMIT).toArray();
        Arrays.stream(digits).forEach(d -> System.out.print(d));

    }
    @Test
    @Ignore
    public void testBits() {
        Long longVal = new Long(78757);
        String longValStr = longVal.toString();
        LongBuffer lBuffer = LongBuffer.allocate(longValStr.length());
        longValStr.chars().forEach(i -> lBuffer.put(i - LOWER_UTF8_LIMIT));
        System.out.println();
        long[] vals = lBuffer.array();
        BitSet bs = BitSet.valueOf(vals);
        long[] otp = RandomNumberGenerator.longs(vals.length, 0, 9).toArray();
        // System.out.println(otp.length);
        BitSet pad = BitSet.valueOf(otp);
        Arrays.stream(bs.toLongArray()).forEach(l -> System.out.print(l));
        System.out.println();
        bs.xor(pad);
        Arrays.stream(bs.toLongArray()).forEach(l -> System.out.print(l));
        System.out.println();
        bs.xor(pad);
        StringBuffer buffer = new StringBuffer();
        Arrays.stream(bs.toLongArray()).forEach(l -> buffer.append(l));
        System.out.println(buffer.toString());
    }

    @Test
    @Ignore
    public void scratch() {
        char[] test = toTest.toCharArray();
    }


    private void doInteger(Integer value) {
        int length = value.toString().length();
        int lowerBound = (int) Math.pow(10, length - 1);
        //System.out.println(lowerBound);
        int rnd = lowerBound + RandomNumberGenerator.nextInt(9 * lowerBound);
        //System.out.println(rnd);
        int tempValue = (value ^ rnd);
        System.out.println(tempValue);
        tempValue = (value) ^ rnd;

        System.out.println(value);
        System.out.println();

    }

    private void encipher(String msg) {
        byte[] plainBytes = msg.getBytes(StandardCharsets.UTF_8);

        byte[] keyBytes = new byte[plainBytes.length];
        StringBuffer buffer = new StringBuffer();
        //	secureRandom.ints(plainBytes.length,32,126).forEach(c -> buffer.append((char)c));
        //	keyBytes = buffer.toString().getBytes(charSet);
        RandomNumberGenerator.nextBytes(keyBytes);

        byte[] cipherBytes = new byte[plainBytes.length];
        for (int i = 0; i < plainBytes.length; i++) {

            cipherBytes[i] = (byte) (plainBytes[i] ^ keyBytes[i]);
        }
        String cipherText = new String(cipherBytes, StandardCharsets.UTF_8);
        System.out.println(cipherText);
        for (int i = 0; i < cipherBytes.length; i++)
            plainBytes[i] = (byte) (cipherBytes[i] ^ keyBytes[i]);
        String plainText = new String(plainBytes, StandardCharsets.UTF_8); // <= make sure same charset both ends
        System.out.println(plainText);

    }

}

