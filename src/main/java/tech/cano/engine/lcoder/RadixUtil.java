package tech.cano.engine.lcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RadixUtil {

    public static String toBinary(final Long longNum) {
        StringBuilder sb = new StringBuilder();
        Long tmp = longNum;
        if (tmp < 0) {
            sb.append('-');
            tmp = -tmp;
        }
        sb.append(Long.toBinaryString(tmp));
        return sb.toString();
    }

    public static String toBinary(final Double doubleNum) {
        if (doubleNum == 0.0d) {
            return "0.0";
        }
        StringBuilder sb = new StringBuilder();
        Double tmp = doubleNum;
        if (tmp < 0) {
            sb.append('-');
            tmp = -tmp;
        }
        Long intPart = tmp.longValue();
        Double fracPart = tmp - intPart;
        
        sb.append(Long.toBinaryString(intPart));

        sb.append('.');
        int maxBits = 32;
        while (fracPart > 0 && maxBits-- > 0) {
            fracPart *= 2;
            if (fracPart >= 1) {
                sb.append('1');
                fracPart -= 1;
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public static String toOctal(final Long longNum) {
        StringBuilder sb = new StringBuilder();
        Long tmp = longNum;
        if (tmp < 0) {
            sb.append('-');
            tmp = -tmp;
        }
        sb.append(Long.toOctalString(tmp));
        return sb.toString();
    }

    public static String toOctal(final Double doubleNum) {
        if (doubleNum == 0.0) {
            return "0.0";
        }
        StringBuilder sb = new StringBuilder();
        Double tmp = doubleNum;
        if (tmp < 0) {
            sb.append('-');
            tmp = -tmp;
        }
        Long intPart = tmp.longValue();
        Double fracPart = tmp - intPart;
        
        sb.append(Long.toOctalString(intPart));

        sb.append('.');
        int maxBits = 32;
        while (fracPart > 0 && maxBits-- > 0) {
            fracPart *= 8;
            Long digit = fracPart.longValue();
            sb.append(digit);
            fracPart -= digit;
        }
        return sb.toString();
    }

    public static String toHex(final Long longNum) {
        StringBuilder sb = new StringBuilder();
        Long tmp = longNum;
        if (tmp < 0) {
            sb.append('-');
            tmp = -tmp;
        }
        sb.append(Long.toHexString(tmp));
        return sb.toString();
    }

    public static String toHex(final Double doubleNum) {
        if (doubleNum == 0.0) {
            return "0.0";
        }
        StringBuilder sb = new StringBuilder();
        Double tmp = doubleNum;
        if (tmp < 0) {
            sb.append('-');
            tmp = -tmp;
        }
        Long intPart = tmp.longValue();
        Double fracPart = tmp - intPart;
        
        sb.append(Long.toOctalString(intPart));

        sb.append('.');
        int maxBits = 32;
        char[] digitMap = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (fracPart > 0 && maxBits-- > 0) {
            fracPart *= 16;
            Long digit = fracPart.longValue();
            Character digitChar = digitMap[digit.intValue()];
            sb.append(digitChar);
            fracPart -= digit;
        }
        return sb.toString();
    }

    public static String fromBinary(final String binaryStr) {
        if (!binaryStr.matches("[+-]?[01]*")) {
            return "非法的二进制字符串格式";
        }

        String[] parts = binaryStr.split("\\.");
        String intPart = parts[0];
        String fracPart = "";
        if (parts.length > 1) {
            fracPart =  parts[1];
        }

        StringBuilder sb = new StringBuilder();

        sb.append(Long.parseLong(intPart, 2));
        sb.append('.');
        Double frac = 0.0;
        for (int i = fracPart.length() - 1; i >= 0; --i) {
            int digit = fracPart.charAt(i) - '0';
            frac += digit;
            frac /= 2;
        }
        sb.append(frac.toString().substring(2));
        return sb.toString();
    }

    public static String fromOctal(final String octalStr) {
        if (!octalStr.matches("[+-]?[0-7]*")) {
            return "非法的八进制字符串格式";
        }

        String[] parts = octalStr.split("\\.");
        String intPart = parts[0];
        String fracPart = "";
        if (parts.length > 1) {
            fracPart =  parts[1];
        }

        StringBuilder sb = new StringBuilder();

        sb.append(Long.parseLong(intPart, 8));
        sb.append('.');
        Double frac = 0.0;
        for (int i = fracPart.length() - 1; i >= 0; --i) {
            int digit = fracPart.charAt(i) - '0';
            frac += digit;
            frac /= 8;
        }
        sb.append(frac.toString().substring(2));
        return sb.toString();
    }

    public static String fromHex(final String hexStr) {
        if (!hexStr.matches("[+-]?[0-9A-Fa-f]*")) {
            return "非法的十六进制字符串格式";
        }

        HashMap<Character, Integer> digitMap = new HashMap<>();
        digitMap.put('0', 0);
        digitMap.put('1', 1);
        digitMap.put('2', 2);
        digitMap.put('3', 3);
        digitMap.put('4', 4);
        digitMap.put('5', 5);
        digitMap.put('6', 6);
        digitMap.put('7', 7);
        digitMap.put('8', 8);
        digitMap.put('9', 9);
        digitMap.put('A', 10);
        digitMap.put('B', 11);
        digitMap.put('C', 12);
        digitMap.put('D', 13);
        digitMap.put('E', 14);
        digitMap.put('F', 15);


        String[] parts = hexStr.split("\\.");
        String intPart = parts[0];
        String fracPart = "";
        if (parts.length > 1) {
            fracPart =  parts[1];
        }

        StringBuilder sb = new StringBuilder();

        sb.append(Long.parseLong(intPart, 16));
        sb.append('.');
        Double frac = 0.0;
        for (int i = fracPart.length() - 1; i >= 0; --i) {
            Character digitChar = fracPart.charAt(i);
            frac += digitMap.get(digitChar);
            frac /= 16;
        }
        sb.append(frac.toString().substring(2));
        return sb.toString();
    }
}
