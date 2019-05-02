package org.enjekt.cipher.vernam.engine.internal.util;

import java.security.SecureRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class RandomNumberGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();


    public static void nextBytes(byte[] bytes) {
        secureRandom.nextBytes(bytes);
    }

/*

    public static SecureRandom getInstanceStrong() throws NoSuchAlgorithmException {
    //TODO This should be configured in the init...
        return SecureRandom.getInstanceStrong();
    }
*/

    public static int nextInt() {
        return secureRandom.nextInt();
    }

    public static int nextInt(int bound) {
        return secureRandom.nextInt(bound);
    }

    public static long nextLong() {
        return secureRandom.nextLong();
    }

    public static boolean nextBoolean() {
        return secureRandom.nextBoolean();
    }

    public static float nextFloat() {
        return secureRandom.nextFloat();
    }

    public static double nextDouble() {
        return secureRandom.nextDouble();
    }

    public static double nextGaussian() {
        return secureRandom.nextGaussian();
    }

    public static IntStream ints(long streamSize) {
        return secureRandom.ints(streamSize);
    }

    public static IntStream ints() {
        return secureRandom.ints();
    }

    public static IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
        return secureRandom.ints(streamSize, randomNumberOrigin, randomNumberBound);
    }

    public static IntStream ints(int randomNumberOrigin, int randomNumberBound) {
        return secureRandom.ints(randomNumberOrigin, randomNumberBound);
    }

    public static LongStream longs(long streamSize) {
        return secureRandom.longs(streamSize);
    }

    public static LongStream longs() {
        return secureRandom.longs();
    }

    public static LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
        return secureRandom.longs(streamSize, randomNumberOrigin, randomNumberBound);
    }

    public static LongStream longs(long randomNumberOrigin, long randomNumberBound) {
        return secureRandom.longs(randomNumberOrigin, randomNumberBound);
    }

    public static DoubleStream doubles(long streamSize) {
        return secureRandom.doubles(streamSize);
    }

    public static DoubleStream doubles() {
        return secureRandom.doubles();
    }

    public static DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
        return secureRandom.doubles(streamSize, randomNumberOrigin, randomNumberBound);
    }

    public static DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
        return secureRandom.doubles(randomNumberOrigin, randomNumberBound);
    }


}
