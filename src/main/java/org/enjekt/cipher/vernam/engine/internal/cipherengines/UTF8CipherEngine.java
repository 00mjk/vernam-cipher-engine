package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.internal.functions.AddWrapLimit;
import org.enjekt.cipher.vernam.engine.internal.functions.SubtractWrapLimit;
import org.enjekt.cipher.vernam.engine.internal.functions.ValueComposer;
import org.enjekt.cipher.vernam.engine.internal.util.RandomSequenceGenerator;

import java.util.Arrays;

public class UTF8CipherEngine {

    private static final RandomSequenceGenerator generator = new RandomSequenceGenerator();

    private final int lowerUTF8Limit;
    private final int upperUTF8Limit;
    private final int lowerKeyRange;
    private final int upperKeyRange;

    public UTF8CipherEngine(int lowerUTF8Limit, int upperUTF8Limit) {
        this.lowerUTF8Limit = lowerUTF8Limit;
        this.upperUTF8Limit = upperUTF8Limit;
        this.lowerKeyRange=0;
        this.upperKeyRange=upperUTF8Limit-lowerUTF8Limit;
    }


    public ValueComposer decrypt(String message, int[] encryptionKeys) {
        int[] encryptedValues = message.chars().toArray();

        ValueComposer composer = new ValueComposer();

        Arrays.stream(encryptedValues).map(new SubtractWrapLimit(encryptionKeys, lowerUTF8Limit, upperKeyRange+1)).forEach(composer);

        return composer;


    }

    public EncryptedResultsHolder encrypt(String value) {
        int[] toEncrypt = value.chars().toArray();
        int[] keys = generator.nextRandomInts(toEncrypt.length, lowerKeyRange, upperKeyRange);
        EncryptedResultsHolder holder = new EncryptedResultsHolder(keys);
        Arrays.stream(toEncrypt).map(new AddWrapLimit(keys, upperUTF8Limit, upperKeyRange+1)).forEach(holder);

        return holder;


    }


}
