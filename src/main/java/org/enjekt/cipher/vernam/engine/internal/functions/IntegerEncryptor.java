package org.enjekt.cipher.vernam.engine.internal.functions;

import java.security.SecureRandom;
import java.util.function.IntUnaryOperator;

public class IntegerEncryptor implements IntUnaryOperator {

    private static SecureRandom secureRandom = new SecureRandom();


    private static final int LOWER_UTF8_LIMIT = 48;
    private static final int UPPER_UTF8_LIMIT = 57;
    private static final int UPPER_RANGE = 9;
    private static final int MODULO = 10;
    private final static int[] MAX = new Integer(Integer.MAX_VALUE).toString().chars().toArray();
    private Boolean allValid;
    private final int[] keys;

    int count = 0;

    public IntegerEncryptor(int[] keys) {
        this.keys = keys;
        //If this is less than max length for an integer then it is automatically valid for size.
        //Otherwise we need to check the individual encrypted integers.
        allValid = keys.length < MAX.length;
    }

    @Override
    public int applyAsInt(int operand) {


        int key = getKey();
        int encryptVal = doEncrypt(operand, key);


        if (!allValid) {
            //Have to be able to store the value for largest integer so it must be less.
            //Encryption value can't be leading zero.
            while (encryptVal > MAX[count] || isLeadingZero(encryptVal)) {
                key = getKey();
                encryptVal = doEncrypt(operand, key);
            }
            //If equal we need to check the next digit as well...
            allValid = MAX[count] > encryptVal;

        } else {
            while (isLeadingZero(encryptVal)) {
                key = getKey();
                encryptVal = doEncrypt(operand, key);
            }
        }
        //System.out.println(encryptVal + " = "+operand + "+" + key);

        keys[count++] = key;

        return encryptVal;

    }


    private int getKey() {
        return secureRandom.nextInt(UPPER_RANGE);
    }

    private int doEncrypt(int operand, int encryptionKey) {
        // System.out.println( operand + ","+ encryptionKey);
        int result = operand + encryptionKey;
        if (result > UPPER_UTF8_LIMIT) {
            result -= MODULO;

        }
        return result;
    }

    private Boolean isLeadingZero(int encryptVal) {
        return count == 0 && encryptVal == LOWER_UTF8_LIMIT;
    }


}
