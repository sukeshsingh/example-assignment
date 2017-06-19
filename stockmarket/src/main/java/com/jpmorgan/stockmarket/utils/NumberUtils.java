package com.jpmorgan.stockmarket.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class for Number Utils
 * @author Sukesh Singh
 */
public class NumberUtils {

    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
