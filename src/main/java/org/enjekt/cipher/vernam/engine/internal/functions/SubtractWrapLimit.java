package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntUnaryOperator;

public class SubtractWrapLimit implements IntUnaryOperator {

    private final int[] keys;
    private final int limit;
    private final int wrap;
    private int counter = 0;

    public SubtractWrapLimit(int[] keys, int limit, int wrap) {
        this.keys = keys;
        this.limit = limit;
        this.wrap = wrap;
    }

    @Override
    public int applyAsInt(int operand) {

        int result = (operand - keys[counter++]);
        // System.out.println("Subtract result: "+operand +"," + keys[counter-1] +","+result);
        if (result < limit) {
            //  System.out.println("Low result: "+operand +"," + keys[counter-1] +","+result);
            result += wrap;
            // System.out.println("Modified subtract: "+result);

        }
        return result;

    }

}

