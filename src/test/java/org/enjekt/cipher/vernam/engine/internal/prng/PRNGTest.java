package org.enjekt.cipher.vernam.engine.internal.prng;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.junit.Ignore;
import org.junit.Test;

public class PRNGTest {

    public static void doChiSquareTest(double[] expected, long[] observed) {
        double alpha = 0.01; // confidence level 99%

        System.out.println();

        System.out.printf("%15.15s: ", "Observed");
        for (int i = 0; i < observed.length; i++) {
            System.out.printf("%-6d ", observed[i]);
        }

        System.out.println();

        System.out.printf("%15.15s: ", "Expected");
        for (int i = 0; i < expected.length; i++) {
            System.out.printf("%-5.1f ", expected[i]);
        }

        System.out.println();
        System.out.println();

        ChiSquareTest t = new ChiSquareTest();

        double pval = t.chiSquareTest(expected, observed);
        System.out.printf("p-value: %.9f\n", pval);

        boolean rejected = t.chiSquareTest(expected, observed, alpha);
        System.out.println("X^2 Test: " + ((!rejected) ? ("PASS") : ("FAIL")));
    }

    @Test
    @Ignore
    public void checkDistribution() {
        int totalRecords = 10000;
        long[] ndigits = new long[10];
        double[] expected = new double[10];
        for (int x = 0; x < 10; x++)
            expected[x] = totalRecords / 10;

        PRNG prng = new PRNG();
        for (int i = 0; i <= totalRecords; i++) {
            int r = prng.take();
            System.out.print(r);
            ndigits[r]++;
        }
       /* System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + ndigits[i]);
        }*/

        doChiSquareTest(expected, ndigits);
    }


}