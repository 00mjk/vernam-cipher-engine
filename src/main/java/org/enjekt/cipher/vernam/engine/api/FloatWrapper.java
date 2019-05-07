package org.enjekt.cipher.vernam.engine.api;

public class FloatWrapper {


    private final Float encryptedValue;
    private final int[] oneTimePad;

    public FloatWrapper(Float encryptedValue, int[] oneTimePad) {
        this.encryptedValue = encryptedValue;
        this.oneTimePad = oneTimePad;
    }

    public Float getEncryptedValue() {
        return encryptedValue;
    }

    public int[] getOneTimePad() {
        return oneTimePad;
    }


}
