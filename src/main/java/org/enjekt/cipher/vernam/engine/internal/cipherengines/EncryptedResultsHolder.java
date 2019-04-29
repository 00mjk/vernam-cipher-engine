package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.internal.functions.ValueComposer;

import java.util.function.IntConsumer;

public class EncryptedResultsHolder implements IntConsumer {
    private final int[] values;
    private final int[] keys;
    private int i=0;

    public EncryptedResultsHolder(int[] keys) {
        this.values = new int[keys.length];
        this.keys = keys;
    }

    public int[] getValues() {
        return values;
    }

    public int[] getKeys() {
        return keys;
    }

    @Override
    public void accept(int value) {
        this.values[i++]=value;
    }
}
