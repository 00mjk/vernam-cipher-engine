package org.enjekt.cipher.vernam.engine.internal.cipherengines;

import org.enjekt.cipher.vernam.engine.api.BooleanWrapper;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGenerator;
import org.enjekt.cipher.vernam.engine.internal.util.RandomNumberGeneratorFactory;


public class BooleanCipherEngine {


    private RandomNumberGenerator randomNumberGenerator;


    public BooleanWrapper encipher(Boolean value) {

        Boolean oneTimePad = randomNumberGenerator.nextBoolean();
        Boolean enciphered = value ^ oneTimePad;
        return new BooleanWrapper(enciphered, oneTimePad);

    }

    public Boolean decipher(BooleanWrapper message) {
        return message.getEncryptedValue() ^ message.getOneTimePad();
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }


    public RandomNumberGenerator getRandomNumberGenerator() {
        if (randomNumberGenerator == null) randomNumberGenerator = RandomNumberGeneratorFactory.getGenerator();
        return randomNumberGenerator;
    }


}

