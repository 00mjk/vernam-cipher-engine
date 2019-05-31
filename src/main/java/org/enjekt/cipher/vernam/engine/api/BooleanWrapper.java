package org.enjekt.cipher.vernam.engine.api;

public class BooleanWrapper {


    private final Boolean encryptedValue;
    private final Boolean oneTimePad;

    public BooleanWrapper(Boolean encryptedValue, Boolean oneTimePad) {
        this.encryptedValue = encryptedValue;
        this.oneTimePad = oneTimePad;
    }

    public Boolean getEncryptedValue() {
        return encryptedValue;
    }

    public Boolean getOneTimePad() {
        return oneTimePad;
    }
}
