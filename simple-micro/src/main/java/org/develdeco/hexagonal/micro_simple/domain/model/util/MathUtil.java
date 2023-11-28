package org.develdeco.hexagonal.micro_simple.domain.model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {

    public static final int DEFAULT_DECIMAL_POSITIONS = 2;

    public static Double roundDecimals(Double number, int positions) {
        return new BigDecimal(number).setScale(positions, RoundingMode.DOWN).doubleValue();
    }

    public static Double roundDecimals(Double number) {
        return roundDecimals(number, DEFAULT_DECIMAL_POSITIONS);
    }
}
