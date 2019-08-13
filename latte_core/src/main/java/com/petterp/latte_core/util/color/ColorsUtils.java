package com.petterp.latte_core.util.color;

import java.util.Random;

/**
 * @author by petterp
 * @date 2019-08-13
 */
public class ColorsUtils {
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        return "#"+R + G + B;
    }
}
