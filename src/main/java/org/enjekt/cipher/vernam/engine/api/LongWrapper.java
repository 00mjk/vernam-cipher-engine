package org.enjekt.cipher.vernam.engine.api;

public class LongWrapper {


    private final Long encryptedValue;
    private final int[] oneTimePad;

    public LongWrapper(Long encryptedValue, int[] oneTimePad) {
        this.encryptedValue = encryptedValue;
        this.oneTimePad = oneTimePad;
    }

    public Long getEncryptedValue() {
        return encryptedValue;
    }

    public int[] getOneTimePad() {
        return oneTimePad;
    }
}
