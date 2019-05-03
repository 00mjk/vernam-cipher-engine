package org.enjekt.cipher.vernam.engine.internal.functions;

import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;

import java.util.function.IntUnaryOperator;

/**
 * The type Digit encryptor. Receives UTF-8 digits, generates one time pad, and
 * encryptes the values. Special logic ensures that encrypted values remain within
 * limits of the given data type. For example, maximum value for Integer is
 * 2,147,483,647 (inclusive). As such the encryption must make sure that any encrypted
 * Integer for large values not exceed an initial digit of 2. If the first encrypted
 * value is equal to 2 then the next digit must not exceed 1, etc. Once any encrypted
 * digit is less than the maximum value for that place, then the rest of the digits can
 * simply be random 0 through 9 for the pad.
 * <p>
 * Additionally, the logic ensures that the initial digit is not 0 which ensures that the
 * encrypted value is of the same size as the original which is important for many database
 * schemas or other logic.
 */
public class DigitEncryptor implements IntUnaryOperator {

    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;
    private static final int UPPER_RANGE = 9;
    private static final int BASE = 10;

    private final int[] oneTimePad;
    private final int[] MAX;
    private Boolean isNormalRange = Boolean.FALSE;
    private int count = 0;


    /**
     * Instantiates a new Digit encryptor. The MAX value of the Integer, Long, Short
     * and so on is passed in so that maximum values can be maintained.
     *
     * @param oneTimePad the one time pad
     * @param MAX        the max
     */
    public DigitEncryptor(int[] oneTimePad, int[] MAX) {
        this.oneTimePad = oneTimePad;
        this.MAX = MAX;
    }

    /**
     * @param operand
     * @return
     */
    @Override
    public int applyAsInt(int operand) {
        int pad = RandomNumberGenerator.nextInt(UPPER_RANGE);
        if (!isNormalRange)
            pad = calculatePad(operand);
        int encryptVal = doEncrypt(operand, pad);
//        System.out.println(encryptVal);
        if (!isNormalRange) checkMaxVal(encryptVal);
        oneTimePad[count++] = pad;
        return encryptVal;

    }

    private int doEncrypt(int operand, int encryptionKey) {
        // System.out.println( operand + ","+ encryptionKey);
        int result = operand + encryptionKey;
        if (result > UPPER_UTF8_LIMIT) {
            result -= BASE;

        }
        return result;
    }

    /*******************************************
     * The methods that follow deal with edge cases and maximum constraints
     */

    private void checkMaxVal(int encryptVal) {
        if (MAX.length > oneTimePad.length || MAX[count] > encryptVal)
            isNormalRange = Boolean.TRUE;
    }


    private int calculatePad(int operand) {
        //If we are here it is usually because we are at first character
        //and want to avoid leading zeroes which will change the width
        //of the resulting encrypted value.
        int legalValue = calulateNonZeroPad(operand);
        //When we are dealing with value that is near or at the maximum
        //length of the data type we have to do special calculations.
        if (MAX.length == oneTimePad.length)
            legalValue = calculatePadForMaxValue(operand);
        //  System.out.print(legalValue+"  ");
        return legalValue;
    }

    private int calulateNonZeroPad(int operand) {
        int digit = operand - LOWER_UTF8_LIMIT;
        int pad = RandomNumberGenerator.nextInt(UPPER_RANGE);
        while ((pad + digit) % BASE == 0) {
            pad = RandomNumberGenerator.nextInt(UPPER_RANGE);
        }
        return pad;
    }

    private int calculatePadForMaxValue(int operand) {
        int offset = MAX[count] - operand;
        int digitRange = MAX[count] - LOWER_UTF8_LIMIT;
        int rnd = 0;
        if (digitRange > 0)
            rnd = RandomNumberGenerator.nextInt(digitRange);


        // System.out.print(range +",");
        int legalValue = (BASE - rnd + offset) % BASE;
        return legalValue;
    }


}
