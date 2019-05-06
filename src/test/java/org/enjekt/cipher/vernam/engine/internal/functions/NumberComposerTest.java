package org.enjekt.cipher.vernam.engine.internal.functions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberComposerTest {

    @Test
    public void testFloat() {
        Float f = 123.45678f;
        String fStr = f.toString();
        System.out.println(fStr);
        String[] baseAndMantissa = fStr.split("\\.");
        //  System.out.println(baseAndMantissa[0]);
        //  System.out.println(baseAndMantissa[1]);

        assertEquals(3, baseAndMantissa[0].length());
        assertEquals(5, baseAndMantissa[1].length());
        NumberComposer composer = new NumberComposer();
        baseAndMantissa[0].chars().forEach(composer);
        composer.append(".");
        baseAndMantissa[1].chars().forEach(composer);

        Float reconstituted = composer.getFloat();
        assertEquals(reconstituted, f);
        System.out.println(reconstituted);
    }
}
