package org.enjekt.cipher.vernam.engine.internal.functions;

import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;

import java.util.function.IntUnaryOperator;

import static org.enjekt.cipher.vernam.engine.internal.util.MathOps.BASE;
import static org.enjekt.cipher.vernam.engine.internal.util.MathOps.UPPER_RANGE;

public class DigitEncryptor implements IntUnaryOperator {

    private final int[] oneTimePad;
    private int counter;
    private DigitValidator validator;

    public DigitEncryptor(int[] MAX_DIGITS, int[] oneTimePad) {
        this.oneTimePad = oneTimePad;
        this.validator = new DigitValidator(oneTimePad.length, MAX_DIGITS);
    }


    @Override
    public int applyAsInt(int operand) {
        if (operand < 0 || operand > 9) return operand; //skip non digits like "-" or "."


        int pad = 0;
        int encryptVal = 0;
        do {
            pad = RandomNumberGenerator.nextInt(UPPER_RANGE);
            encryptVal = (pad + operand) % BASE;
            //Only in the extreme cases of MAX or MIN values for a data type will we run into situations
            //where the encrypted value isn't considered valid for a position or if we are at the leading
            //digit, a zero isn't valid. In that case, if a number is 5 digits, only the first digit needs
            //to ensure it is not a leading zero. So only 1 of 5 encryptions will be concerned. On that first encryption
            //the probability is on 1 in 10 that a leading zero encrypted value will be generated. If it is, we simply
            //loop and repeat. We could do more calculation in the future but it is really squeezing blood from a stone.
        } while (!validator.isValid(counter, encryptVal));

        oneTimePad[counter++] = pad;

        return encryptVal;

    }



}
