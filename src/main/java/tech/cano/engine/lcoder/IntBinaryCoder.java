package tech.cano.engine.lcoder;

public class IntBinaryCoder {

    public static String toTwosComplement(final Long longNum) {
        String towsComplement = String.format("%64s", Long.toBinaryString(longNum)).replace(' ', '0');
        return towsComplement;
    }

    public static String toOnesComplement(final Long longNum) {
        Long tmp = longNum;
        if (tmp < 0) {
            tmp -= 1;
        }
        String onesComplement = String.format("%64s", Long.toBinaryString(tmp)).replace(' ', '0');
        return onesComplement;
    }

    public static String toSignMagnitude(final Long longNum) {
        Long tmp = longNum;
        if (tmp < 0) {
            tmp -= 1;
            tmp = ~tmp;
            tmp |= 0x8000000000000000L;
        }
        String signMagnitude = String.format("%64s", Long.toBinaryString(tmp)).replace(' ', '0');
        return signMagnitude;
    }

    public static String fromTwosComplement(final String binaryStr) {
        if (!binaryStr.matches("[01]+")) {
            return "非法的补码字符串格式";
        }
        String towsComplement = String.format("%64s", binaryStr).replace(' ', binaryStr.charAt(0));
        Long tmp = 0L;
        for (int i = 0; i < towsComplement.length(); ++i) {
            Character c = towsComplement.charAt(i);
            if (c == '1') {
                tmp += 1;
            }
            if (i == towsComplement.length() - 1) {
                continue;
            }
            tmp <<= 1;
        }
        String longStr = Long.toString(tmp);
        return longStr;
    }

}
