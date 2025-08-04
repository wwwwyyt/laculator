package tech.cano.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CellModel {
    
    private final StringProperty text = new SimpleStringProperty("");  // 与用户输入进行绑定
    private final StringProperty expression = new SimpleStringProperty("");  // 备份上次更新的表达式
    private final StringProperty result = new SimpleStringProperty("");  // 存储表达式的结果
    private final StringProperty label = new SimpleStringProperty("");  // 存储单元格的标签
    
    public StringProperty getText() {
        return text;
    }

    public StringProperty getLabel() {
        return label;
    }

    public void setResult(String resultString) {
        result.setValue(resultString);
    }

    public void showResult() {
        expression.setValue(text.get());
        text.setValue(result.get());
    }

    public void showExpression() {
        text.setValue(expression.get());
    }
}
