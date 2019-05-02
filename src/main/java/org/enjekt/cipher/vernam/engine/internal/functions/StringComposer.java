package org.enjekt.cipher.vernam.engine.internal.functions;

import java.util.function.IntConsumer;

public class StringComposer implements IntConsumer {
    private final StringBuffer buffer = new StringBuffer();

    public StringComposer() {

    }

    @Override
    public void accept(int value) {
        buffer.append((char) value);
    }

    public String getString() {
        return buffer.toString();
    }


}
