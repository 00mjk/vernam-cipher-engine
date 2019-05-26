package org.enjekt.cipher.vernam.engine.internal.functions;

import java.math.BigDecimal;
import java.util.function.IntConsumer;

public class NumberComposer implements IntConsumer {
    private final StringBuffer buffer = new StringBuffer();

    @Override
    public void accept(int value) {
        // System.out.print(value +",");
        buffer.append((char) value);
    }

    public void append(String toAppend) {
        buffer.append(toAppend);
    }

    public String getString() {
        return buffer.toString();
    }

    public Integer getInteger() {
        return Integer.valueOf(buffer.toString());
    }

    public Long getLong() {
        return Long.valueOf(buffer.toString());
    }

    public BigDecimal getBigDecimal() {
        return new BigDecimal(buffer.toString());
    }
}
