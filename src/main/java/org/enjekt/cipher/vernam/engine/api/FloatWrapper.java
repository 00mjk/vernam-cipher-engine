package org.enjekt.cipher.vernam.engine.api;

public class FloatWrapper {


    private final Float encryptedValue;
    private final int[] wholeOneTimePad;
    private final int[] fractionalOneTimePad;

    public FloatWrapper(Float encryptedValue, int[] wholeOneTimePad, int[] fractionalOneTimePad) {
        this.encryptedValue = encryptedValue;
        this.wholeOneTimePad = wholeOneTimePad;
        this.fractionalOneTimePad = fractionalOneTimePad;
    }

    public int[] getFractionalOneTimePad() {
        return fractionalOneTimePad;
    }

    public Float getEncryptedValue() {
        return encryptedValue;
    }

    public int[] getWholeOneTimePad() {
        return wholeOneTimePad;
    }
}
