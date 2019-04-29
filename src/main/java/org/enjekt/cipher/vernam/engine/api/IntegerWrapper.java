package org.enjekt.cipher.vernam.engine.api;

public class IntegerWrapper {


    private final Integer encryptedValue;
    private final int[] encryptionKeys;

    public IntegerWrapper(Integer encryptedValue, int[] encryptionKeys) {
        this.encryptedValue = encryptedValue;
        this.encryptionKeys = encryptionKeys;
    }

    public Integer getEncryptedValue() {
        return encryptedValue;
    }

    public int[] getEncryptionKeys() {
        return encryptionKeys;
    }
}
