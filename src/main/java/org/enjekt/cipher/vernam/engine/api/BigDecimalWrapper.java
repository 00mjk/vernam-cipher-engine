package org.enjekt.cipher.vernam.engine.api;

import java.math.BigDecimal;

public class BigDecimalWrapper {

    private final BigDecimal encryptedValue;
    private final int[] oneTimePad;

    public BigDecimalWrapper(BigDecimal encryptedValue, int[] oneTimePad) {
        this.encryptedValue = encryptedValue;
        this.oneTimePad = oneTimePad;
    }

    public BigDecimal getEncryptedValue() {
        return encryptedValue;
    }

    public int[] getOneTimePad() {
        return oneTimePad;
    }


}
