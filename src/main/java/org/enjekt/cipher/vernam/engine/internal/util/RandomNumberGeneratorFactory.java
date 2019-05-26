package org.enjekt.cipher.vernam.engine.internal.util;

public class RandomNumberGeneratorFactory {

    private static RandomNumberGenerator generator = new SecureRandomNumberGeneratorImpl();

    public static RandomNumberGenerator getGenerator() {
        return generator;
    }

    public static void setGenerator(RandomNumberGenerator generator) {
        RandomNumberGeneratorFactory.generator = generator;
    }
}
