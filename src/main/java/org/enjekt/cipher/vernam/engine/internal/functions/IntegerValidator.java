package org.enjekt.cipher.vernam.engine.internal.functions;


import java.util.function.IntUnaryOperator;

//TODO Make this general for all numeric types.
public class IntegerValidator implements IntUnaryOperator {
    private static final int[] MAX = new Integer(Integer.MAX_VALUE).toString().chars().toArray();
    private static final int LOWER_UTF8_LIMIT = 48;


    private final int[] collector;
    private int count;

    public IntegerValidator(int length) {
        collector = new int[length];
    }

    @Override
    public int applyAsInt(int operand) {
        collector[count++] = operand;
        return operand;
    }

    public int[] getCollector() {
        return collector;
    }

    public Boolean isValid() {

        if (collector[0] == LOWER_UTF8_LIMIT) return Boolean.FALSE;
        if (collector.length < MAX.length) return Boolean.TRUE;
        for (int i = 0; i < collector.length; i++) {
            if (MAX[i] > collector[i])
                return Boolean.TRUE;
            if (MAX[i] < collector[i])
                return Boolean.FALSE;
            //if equal we loop to next digit.
        }
        //Only happens if encrypted value is equal to maximum.
        return Boolean.TRUE;
    }

}
