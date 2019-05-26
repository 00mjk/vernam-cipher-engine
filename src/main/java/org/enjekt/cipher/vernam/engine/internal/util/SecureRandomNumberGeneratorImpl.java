package org.enjekt.cipher.vernam.engine.internal.util;

import java.security.SecureRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SecureRandomNumberGeneratorImpl implements RandomNumberGenerator {
    private SecureRandom secureRandom = new SecureRandom();


    @Override
    public void nextBytes(byte[] bytes) {
        secureRandom.nextBytes(bytes);
    }

/*

    public SecureRandom getInstanceStrong() throws NoSuchAlgorithmException {
    //TODO This should be configured in the init...
        return SecureRandom.getInstanceStrong();
    }
*/

    @Override
    public int nextInt() {
        return secureRandom.nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return secureRandom.nextInt(bound);
    }

    @Override
    public long nextLong() {
        return secureRandom.nextLong();
    }

    @Override
    public boolean nextBoolean() {
        return secureRandom.nextBoolean();
    }

    @Override
    public float nextFloat() {
        return secureRandom.nextFloat();
    }

    @Override
    public double nextDouble() {
        return secureRandom.nextDouble();
    }

    @Override
    public double nextGaussian() {
        return secureRandom.nextGaussian();
    }

    @Override
    public IntStream ints(long streamSize) {
        return secureRandom.ints(streamSize);
    }

    @Override
    public IntStream ints() {
        return secureRandom.ints();
    }

    @Override
    public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
        return secureRandom.ints(streamSize, randomNumberOrigin, randomNumberBound);
    }

    @Override
    public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
        return secureRandom.ints(randomNumberOrigin, randomNumberBound);
    }

    @Override
    public LongStream longs(long streamSize) {
        return secureRandom.longs(streamSize);
    }

    @Override
    public LongStream longs() {
        return secureRandom.longs();
    }

    @Override
    public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
        return secureRandom.longs(streamSize, randomNumberOrigin, randomNumberBound);
    }

    @Override
    public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
        return secureRandom.longs(randomNumberOrigin, randomNumberBound);
    }

    @Override
    public DoubleStream doubles(long streamSize) {
        return secureRandom.doubles(streamSize);
    }

    @Override
    public DoubleStream doubles() {
        return secureRandom.doubles();
    }

    @Override
    public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
        return secureRandom.doubles(streamSize, randomNumberOrigin, randomNumberBound);
    }

    @Override
    public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
        return secureRandom.doubles(randomNumberOrigin, randomNumberBound);
    }


}
