package org.enjekt.cipher.vernam.engine.internal.functions;


public class DigitValidator {
    private final int[] MAX;
    private static final int LOWER_UTF8_LIMIT = 48;
    private Boolean allValid;

    public DigitValidator(int length, int[] MAX) {
        this.allValid = length < MAX.length;
        this.MAX = MAX;
    }

    public Boolean isValid(int position, int value) {

        if (position == 0 && value == LOWER_UTF8_LIMIT) return Boolean.FALSE;
        if (allValid) return Boolean.TRUE;
        if (MAX[position] < value) return Boolean.FALSE;

        if (MAX[position] > value) allValid = Boolean.TRUE;

        //if we are here then the value was either less than or equal to the maximum for the position.
        return Boolean.TRUE;

    }


}

