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


    public int[] encipher(String value, NumberComposer composer) {

        int[] values = value.chars().toArray();
        int[] oneTimePad = new int[getPadLength(value)];
//System.out.printf("%s,%d,%d\n", value,values.length,oneTimePad.length);
        Arrays.stream(values).map(i -> i - LOWER_UTF8_LIMIT).map(new DigitEncryptor(maximumDigitsForDataType, oneTimePad)).map(i -> i + LOWER_UTF8_LIMIT).forEach(composer);
        return oneTimePad;
    }

    private int getPadLength(String value) {
        int padLength = value.length();
        if (value.contains("-")) padLength--;
        if (value.contains(".")) padLength--;
        return padLength;
    }

    public NumberComposer decipher(String value, int[] oneTimePad) {
        int[] values = value.chars().toArray();

        NumberComposer composer = new NumberComposer();
        Arrays.stream(values).map(new DigitDecryptor(oneTimePad)).forEach(composer);
        return composer;
    }


}
