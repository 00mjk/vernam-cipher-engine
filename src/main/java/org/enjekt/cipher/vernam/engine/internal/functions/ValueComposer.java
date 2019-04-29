package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntConsumer;

public class ValueComposer implements IntConsumer {
    private final StringBuffer buffer = new StringBuffer();

    public ValueComposer() {

    }

    @Override
    public void accept(int value) {
        buffer.append((char) value);
    }

    public String getString() {
        return buffer.toString();
    }

    public Integer getInteger() {
        return Integer.valueOf(buffer.toString());
    }

}
