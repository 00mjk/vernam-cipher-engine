package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntConsumer;

public class IntegerComposer implements IntConsumer {
    public static final int NEGATIVE = 45;
    private final StringBuffer buffer = new StringBuffer();

    public IntegerComposer(Boolean isNegative) {
        if (isNegative)
            buffer.append((char) NEGATIVE);
    }

    @Override
    public void accept(int value) {
        buffer.append((char) value);
    }

    public Integer getInteger() {
        return Integer.valueOf(buffer.toString());
    }

}
