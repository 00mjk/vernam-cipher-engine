package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntUnaryOperator;

public class AddWrapLimit implements IntUnaryOperator {

    private final int[] keys;
    private final int limit;
    private final int wrap;
    private int counter;


    public AddWrapLimit(int[] keys, int limit, int wrap) {
        this.keys = keys;
        this.limit = limit;
        this.wrap = wrap;
    }

    @Override
    public int applyAsInt(int operand) {
        int result = (operand + keys[counter++]);
        if (result > limit) {
          //  System.out.println("Add: "+ operand +"," + keys[counter-1] +","+result);
            result -= wrap;
          //  System.out.println("Modified add: "+result);

        }
        return result;
    }
}
