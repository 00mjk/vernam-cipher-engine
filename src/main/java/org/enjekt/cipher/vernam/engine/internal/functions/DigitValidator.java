package org.enjekt.cipher.vernam.engine.internal.functions;


public class DigitValidator {
    private final int[] MAX_DIGIT;
    private Boolean allValid;

    public DigitValidator(int length, int[] MAX_DIGIT) {
        this.allValid = length < MAX_DIGIT.length;
        this.MAX_DIGIT = MAX_DIGIT;
    }

    public Boolean isValid(int position, int value) {

        if (position == 0 && value == 0) return Boolean.FALSE;
        if (allValid) return Boolean.TRUE;
        if (MAX_DIGIT[position] < value) return Boolean.FALSE;

        if (MAX_DIGIT[position] > value) allValid = Boolean.TRUE;

        //if we are here then the value was either less than or equal to the maximum for the position.
        return Boolean.TRUE;

    }


}

