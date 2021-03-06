package io.github.monstruos.MPT1;

import static java.lang.Math.log10;
import static java.lang.Math.round;

public class Converter {
    public static final int MIN_BASE = 2;
    public static final int MAX_BASE = 16;
    public static final Character SEPARATOR = '.';

    private static final int MAX_DECIMAL_PRECISION = 10;

    public static String convertToString(double number, int base, int precision) {
        if (MIN_BASE > base || base > MAX_BASE) {
            throw new IllegalArgumentException("Base must be in range [" + MIN_BASE + ", " + MAX_BASE + "]");
        }

        if (0 > precision || precision > maxPrecisionForBase(base)) {
            throw new IllegalArgumentException("Precision is not supported");
        }

        StringBuilder strNumber = new StringBuilder();

        boolean isNegative = false;
        if (number < 0) {
            isNegative = true;
            number *= -1;
        }

        long intPart = (long) number;
        double fracPart = number - intPart;

        while (intPart > 0) {
            char digit = (char) (intPart % base);

            strNumber.append(convertDigit(digit, base));

            intPart /= base;
        }

        if (strNumber.length() == 0) {
            strNumber.append('0');
        } else {
            strNumber.reverse();
        }

        if (fracPart != 0) {
            strNumber.append(SEPARATOR);

            for (int exp = 0; exp < precision - 1; ++exp) {
                char digit = (char) (fracPart *= base);

                strNumber.append(convertDigit(digit, base));

                fracPart -= digit;
            }

            char digit = (char) round(fracPart * base);

            if (digit == base) {
                digit--;
            }

            strNumber.append(convertDigit(digit, base));
        }

        return isNegative ? '-' + strNumber.toString() : strNumber.toString();
    }

    public static double convertToDouble(String number, int base) {
        if (MIN_BASE > base || base > MAX_BASE) {
            throw new IllegalArgumentException("Base must be in range [" + MIN_BASE + ", " + MAX_BASE + "]");
        }

        int pos = number.indexOf(SEPARATOR);

        double result;

        if (pos != -1) {
            long intPart = Long.parseLong(number.substring(0, pos), base);
            double fracPart = 0.0;

            String fracSubstr = number.substring(pos + 1);

            if (fracSubstr.length() > 0) {
                int maxPrecision = maxPrecisionForBase(base);

                if (fracSubstr.length() > maxPrecision) {
                    fracSubstr = fracSubstr.substring(0, maxPrecision);
                }

                fracPart = Long.parseLong(fracSubstr, base);

                while (fracPart >= 1) {
                    fracPart /= base;
                }

                for (int idx = 0; idx < fracSubstr.length() && fracSubstr.charAt(idx) == '0'; ++idx) {
                    fracPart /= base;
                }
            }
            result = intPart + fracPart;
        } else {
            result = Long.parseLong(number, base);
        }

        return number.startsWith("-") ? -result : result;
    }

    public static char convertDigit(int digit, int base) {
        if (digit < 0 || digit > base) {
            throw new IllegalArgumentException("Digit must be in range [0, " + base + ")");
        }

        if (MIN_BASE > base || base > MAX_BASE) {
            throw new IllegalArgumentException("Base must be in range [" + MIN_BASE + ", " + MAX_BASE + "]");
        }

        return (char) (digit < 10 ? '0' + digit : 'A' + digit - 10);
    }

    public static int maxPrecisionForBase(int base) {
        return (int) (MAX_DECIMAL_PRECISION / log10(base));
    }
}
