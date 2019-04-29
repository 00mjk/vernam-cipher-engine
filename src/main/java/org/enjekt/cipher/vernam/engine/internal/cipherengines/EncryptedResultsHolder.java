package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.internal.functions.ValueComposer;

public class EncryptedResultsHolder {
    private final ValueComposer composer;
    private final int[] keys;

    public EncryptedResultsHolder(ValueComposer composer, int[] keys) {
        this.composer = composer;
        this.keys = keys;
    }

    public ValueComposer getComposer() {
        return composer;
    }

    public int[] getKeys() {
        return keys;
    }
}
