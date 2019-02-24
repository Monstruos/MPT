package io.github.monstruos.MPT1;

import static java.lang.Math.round;

public class Converter {
    public static String convertToString(double number, char base, int precision) {
        StringBuilder strNumber = new StringBuilder();

        long intPart = (long) number;
        double fracPart = number - intPart;

        while (intPart > 0) {
            char digit = (char) (intPart % base);

            strNumber.append(convertDigit(digit, base));

            intPart /= base;
        }

        strNumber.reverse();

        if (fracPart != 0) {
            strNumber.append('.');

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

        return strNumber.length() == 0 ? "0" : strNumber.toString();
    }

    public static double convertToDouble(String number, char base) {
        int pos = number.indexOf('.');

        double result;

        if (pos != -1) {
            long intPart = Long.parseLong(number.substring(0, pos), base);
            double fracPart;

            String fracSubstr = number.substring(pos + 1);
            fracPart = Long.parseLong(fracSubstr, base);

            while (fracPart >= 1) {
                fracPart /= base;
            }

            for (int idx = 0; idx < fracSubstr.length() && fracSubstr.charAt(idx) == '0'; ++idx) {
                fracPart /= base;
            }

            result = intPart + fracPart;
        } else {
            result = Long.parseLong(number, base);
        }

        return result;
    }

    public static char convertDigit(int digit, int base) {

        if (digit < 0 || digit >= base) {
            throw new IllegalArgumentException("Digit must be in range [0, " + base + ")");
        }

        if (base < 2 || base > 16) {
            throw new IllegalArgumentException("Base must be in range [0, 16]");
        }

        return (char) (digit < 10 ? '0' + digit : 'A' + digit - 10);
    }
}
