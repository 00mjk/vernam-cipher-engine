package org.enjekt.cipher.vernam.engine.internal.functions;

public class ValueTrackingHolder {
    private final int[] keys;
    private final int[] values;
    private final int[] encryptedValues;

    public ValueTrackingHolder(int length) {
        this.keys = new int[length];
        this.encryptedValues = new int[length];
        this.values = new int[length];

    }

    public void addKey(int index, int key) {
        this.keys[index] = key;
    }

    public void addValue(int index, int value) {
        this.values[index] = value;
    }

    public void addEncryptedValue(int index, int encryptedValue) {
        this.encryptedValues[index] = encryptedValue;
    }

    public int[] getKeys() {
        return keys;
    }

    public int[] getValues() {
        return values;
    }

    public int[] getEncryptedValues() {
        return encryptedValues;
    }
}
