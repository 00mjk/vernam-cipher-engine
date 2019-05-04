package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.internal.functions.DigitDecryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.DigitEncryptor;
import org.enjekt.cipher.vernam.engine.internal.functions.NumberComposer;

import java.util.Arrays;

public class DigitStreamCipher {
    private static final int LOWER_UTF8_LIMIT = 48;
    private final int[] maximumDigitsForDataType;

    public DigitStreamCipher(int[] maxDigits) {
        this.maximumDigitsForDataType = maxDigits;
    }


    public int[] encrypt(String value, NumberComposer composer) {
        boolean isNegative = value.contains("-");
        int[] values = getInts(value, isNegative);
        int[] oneTimePad = new int[values.length];

        composer.setNegative(isNegative);
        Arrays.stream(values).map(i -> i - LOWER_UTF8_LIMIT).map(new DigitEncryptor(maximumDigitsForDataType, oneTimePad)).map(i -> i + LOWER_UTF8_LIMIT).forEach(composer);
        return oneTimePad;
    }

    public NumberComposer decrypt(String value, int[] oneTimePad) {
        boolean isNegative = value.contains("-");
        int[] values = getInts(value, isNegative);
        NumberComposer composer = new NumberComposer().setNegative(isNegative);
        Arrays.stream(values).map(new DigitDecryptor(oneTimePad)).forEach(composer);
        return composer;
    }


    private int[] getInts(String value, boolean isNegative) {
        if (isNegative)
            return value.substring(1).chars().toArray();
        else
            return value.chars().toArray();
    }
}
