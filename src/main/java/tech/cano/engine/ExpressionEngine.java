package tech.cano.engine;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.*;
import java.util.ArrayList;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import tech.cano.engine.formatter.*;

public class ExpressionEngine {
    
    private LinkedHashMap<String, String> cellMap;
    private LinkedHashMap<String, String> choiceMap;
    private Pattern pattern = Pattern.compile("\\$\\d+");
    private String currentCell;
    private String formatChoice;
    private StringFormatter stringFormatter = new StringFormatter();

    public String[] caculate(final LinkedHashMap<String, String> cellMap) {
        this.cellMap = cellMap;
        choiceMap = new LinkedHashMap<>();
        String[] answeStrings = new String[cellMap.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : cellMap.entrySet()) {
            currentCell = entry.getKey();
            String expressionString = entry.getValue();
            formatChoice = findFormatChoice(expressionString);
            choiceMap.put(currentCell, formatChoice);
            expressionString = tailAfterColon(expressionString);
            answeStrings[i++] = expression2Answer(currentCell, formatChoice, expressionString);
        }
        return answeStrings;
    }
    
    private String expression2Answer(String label, String choice, String expressionString) {
        String cellLabel = "";
        try {
            if (expressionString == "") {
                return "";
            }
            String[] cellReference = findCellReference(expressionString);

            for (String var : cellReference) {
                cellLabel = var.replace("$", "");
                
                if (cellLabel.equals(currentCell)) {
                    System.out.println("单元格 " + cellLabel + "：无穷递归引用");
                    return "单元格 " + cellLabel + "： 无穷递归引用";
                }
                if (!cellMap.containsKey(cellLabel)) {
                    System.out.println("单元格 " + cellLabel + "：不存在的引用");
                    return "单元格 " + cellLabel + "：不存在的引用";
                }
                
                expressionString = expressionString.replaceAll("\\" + var, expression2Answer(cellLabel, choiceMap.get(cellLabel), cellMap.get(cellLabel)));
            }
            System.out.println("表达式：" + expressionString);
            if (expressionString.matches("^[+-]?[0-9a-fA-F]+$")) {
                System.out.println("格式化");
                return stringFormatter.format(expressionString, choice);
            }

            ExpressionBuilder eb = new ExpressionBuilder(expressionString);
            Expression e = eb.build();
            double result = e.evaluate();
            return stringFormatter.format(Double.toString(result), formatChoice);

        } catch (Exception e) {
            cellLabel = currentCell;
            System.out.println("单元格 " + cellLabel + "：无效表达式");
            System.out.println("错误信息: " + e.getMessage());
            // e.printStackTrace();
            return "单元格 " + cellLabel + "：无效表达式";
        }
    }

    private String[] findCellReference(String expressionString) {
        Matcher matcher = pattern.matcher(expressionString);
        ArrayList<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list.toArray(new String[0]);
    }

    private String findFormatChoice(String expressionString) {
        String[] parts = expressionString.split(":");
        if (parts.length < 2) {
            return "default";
        } else {
            return parts[0];
        }
    }

    private String tailAfterColon(String src) {
        int idx = src.indexOf(':');
        return idx == -1 ? src : src.substring(idx + 1);
    }
}
