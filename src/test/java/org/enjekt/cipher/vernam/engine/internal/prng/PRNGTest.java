package org.enjekt.cipher.vernam.engine.internal.prng;

import org.junit.Test;

public class PRNGTest {

    @Test
    public void checkDistribution() {

        int[] ndigits = new int[10];

        PRNG prng = new PRNG();
        for (int i = 0; i <= 10000; i++) {
            int r = prng.take();
            System.out.print(r);
            ndigits[r]++;
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + ndigits[i]);
        }

    }
}