package org.enjekt.cipher.vernam.engine.internal.util;

public class RandomNumberGeneratorFactory {

    private static RandomNumberGenerator generator;

    public static RandomNumberGenerator getGenerator() {
        if (generator == null) generator = new SecureRandomNumberGeneratorImpl();
        return generator;
    }

    public static void setGenerator(RandomNumberGenerator generator) {
        RandomNumberGeneratorFactory.generator = generator;
    }
}
