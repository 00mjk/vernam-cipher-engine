package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import java.util.function.IntConsumer;

public class EncryptedResultsHolder implements IntConsumer {
    private final int[] values;
    private final int[] oneTimePad;
    private int i = 0;

    public EncryptedResultsHolder(int[] oneTimePad) {
        this.values = new int[oneTimePad.length];
        this.oneTimePad = oneTimePad;
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
