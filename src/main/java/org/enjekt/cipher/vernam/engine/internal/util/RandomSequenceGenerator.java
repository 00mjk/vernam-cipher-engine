package org.enjekt.cipher.vernam.engine.internal.util;

import java.security.SecureRandom;
import java.util.Base64;

//TODO Configuration, seeding, reseeding, etc. of the random number generaator belongs here
//Also if more than one generator is used this is where it goes.
public class RandomSequenceGenerator {
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    private static SecureRandom secureRandom = new SecureRandom();

    @Deprecated
    public char[] nextRandomChars(int length) {
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        return encoder.encodeToString(bytes).toCharArray();
    }

    public int[] nextRandomInts(int length, int lowerBound, int upperBound) {
        return secureRandom.ints(length, lowerBound, upperBound).toArray();

    }
}
