package tech.cano.engine.formatter;

import java.util.HashMap;
import tech.cano.engine.lcoder.*;

public class Formatters {

    private static final int DEFAULT = 0;
    private static final int TO_IEEE754 = 1;
    private static final int FROM_IEEE754 = 2;
    private static final int TO_TWOS_COMPLEMENT = 3;
    private static final int TO_ONES_COMPLEMENT = 4;
    private static final int TO_SIGN_MAGNITUDE = 5;
    private static final int FROM_TWOS_COMPLEMENT = 6;
    // 新增内建格式化方法
    // ...
    
    public static Formatter[] buildInFormatters = new Formatter[7];  // 新增内建格式化方法时数量 + 1
    public static HashMap<String, Integer> nameMap = new HashMap<>();

    static {
        nameMap.put("default", 0);
        nameMap.put("toIEEE754", 1);
        nameMap.put("IEEE754", 1);
        nameMap.put("fromIEEE754", 2);
        nameMap.put("IEEE754解析", 2);
        nameMap.put("toTwosComplement", 3);
        nameMap.put("补码", 3);
        nameMap.put("toOnesComplement", 4);
        nameMap.put("反码", 4);
        nameMap.put("toSignMagnitude", 5);
        nameMap.put("原码", 5);
        nameMap.put("fromTwosComplement", 6);
        nameMap.put("补码解析", 6);
        // 新增内建格式化方法名称与序号映射
        // ...

        buildInFormatters[DEFAULT] = new Formatter("default") {
            @Override
            public String apply(String primary) {
                return primary;
            }
        };

        buildInFormatters[TO_IEEE754] = new Formatter("toIEEE754") {
            @Override
            public String apply(String primary) {
                return FloatBinaryCoder.toIEEE754(Double.parseDouble(primary));
            }
        };

        buildInFormatters[FROM_IEEE754] = new Formatter("fromIEEE754") {
            @Override
            public String apply(String primary) {
                return FloatBinaryCoder.fromIEEE754_64(primary);
            }
        };

        buildInFormatters[TO_TWOS_COMPLEMENT] = new Formatter("toTwosComplement") {
            @Override
            public String apply(String primary) {
                return IntBinaryCoder.toTwosComplement(Long.parseLong(primary));
            }
        };
        
        buildInFormatters[TO_ONES_COMPLEMENT] = new Formatter("toOnesComplement") {
            @Override
            public String apply(String primary) {
                return IntBinaryCoder.toOnesComplement(Long.parseLong(primary));
            }
        };
        
        buildInFormatters[TO_SIGN_MAGNITUDE] = new Formatter("toSignMagnitude") {
            @Override
            public String apply(String primary) {
                return IntBinaryCoder.toSignMagnitude(Long.parseLong(primary));
            }
        };
        
        buildInFormatters[FROM_TWOS_COMPLEMENT] = new Formatter("fromTwosComplement") {
            @Override
            public String apply(String primary) {
                return IntBinaryCoder.fromTwosComplement(primary);
            }
        };

        // 新增内建格式化方法实现
        // ...
    }
}
