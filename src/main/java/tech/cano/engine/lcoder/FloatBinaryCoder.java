package tech.cano.engine.lcoder;

public class FloatBinaryCoder {

    private static boolean checkFormat(final String s) {
        return s != null && s.matches("[01]+");
    }
    
    public static String toIEEE754(final Float floatNum) {
        Integer bits = Float.floatToIntBits(floatNum);
        String ieee754 = String.format("%32s", Integer.toBinaryString(bits)).replace(' ', '0');
        return ieee754;
    }

    public static String toIEEE754(final Double doubleNum) {
        Long bits = Double.doubleToLongBits(doubleNum);
        String ieee754 = String.format("%64s", Long.toBinaryString(bits)).replace(' ', '0');
        return ieee754;
    }

    public static String fromIEEE754_32(final String binaryStr) {
        if (!checkFormat(binaryStr)) {
            return "非法的 IEEE754 字符串格式";
        }
        Integer bits = Integer.parseUnsignedInt(binaryStr, 2);
        Float floatNum = Float.intBitsToFloat(bits);
        return floatNum.toString();
    }

    public static String fromIEEE754_64(final String binaryStr) {
        if (!checkFormat(binaryStr)) {
            return "非法的 IEEE754 字符串格式";
        }        
        Long bits = Long.parseUnsignedLong(binaryStr, 2);
        Double floatNum = Double.longBitsToDouble(bits);
        return floatNum.toString();
    }
}
