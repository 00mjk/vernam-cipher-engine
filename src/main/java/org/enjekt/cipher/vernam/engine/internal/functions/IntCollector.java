package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntConsumer;

public class IntCollector implements IntConsumer {
    private int counter;
    private final int[] values;


    public IntCollector(int length) {
        this.values = new int[length];
    }


    @Override
    public void accept(int value) {
        values[counter++] = value;
    }

    public int[] getValues() {
        return values;
    }
}
