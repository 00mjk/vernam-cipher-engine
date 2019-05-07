package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntConsumer;

import static org.enjekt.cipher.vernam.engine.internal.util.MathOps.MINUS_UTF8;

public class IntCollector implements IntConsumer {
    private final int[] values;
    private int counter;


    public IntCollector(int length) {
        this.values = new int[length];
    }


    @Override
    public void accept(int value) {

        if (value != MINUS_UTF8) values[counter++] = value;
    }

    public int[] getValues() {
        return values;
    }
}
