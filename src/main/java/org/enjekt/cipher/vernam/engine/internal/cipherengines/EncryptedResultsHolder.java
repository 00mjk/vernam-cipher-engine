package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import java.util.function.IntConsumer;

public class EncryptedResultsHolder implements IntConsumer {
    private final int[] values;
    private final int[] oneTimePad;
    private int i = 0;

    public EncryptedResultsHolder(int[] keys) {
        this.values = new int[keys.length];
        this.oneTimePad = keys;
    }

    public int[] getValues() {
        return values;
    }

    public int[] getOneTimePad() {
        return oneTimePad;
    }

    @Override
    public void accept(int value) {
        this.values[i++] = value;
    }
}
