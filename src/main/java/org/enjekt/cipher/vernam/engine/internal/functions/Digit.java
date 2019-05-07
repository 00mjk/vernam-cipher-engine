package org.enjekt.cipher.vernam.engine.internal.functions;

import static org.enjekt.cipher.vernam.engine.internal.util.MathOps.LOWER_UTF8_LIMIT;

public class Digit {

    public static int toInt(int operand) {
        return operand - LOWER_UTF8_LIMIT;
    }

    public static int toUTF(int operand) {
        return operand + LOWER_UTF8_LIMIT;
    }
}
