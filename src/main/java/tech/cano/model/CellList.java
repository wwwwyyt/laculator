package tech.cano.model;

import java.util.LinkedHashMap;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tech.cano.engine.ExpressionEngine;
import tech.cano.model.CellModel;

public class CellList {
    
    private final ObservableList<CellModel> items = FXCollections.observableArrayList();
    private final BooleanProperty editable = new SimpleBooleanProperty(true);
    private final ExpressionEngine engine = new ExpressionEngine();

    public ObservableList<CellModel> getItems() {
        return this.items;
    }

    public BooleanProperty getEditableProp() {
        return this.editable;
    }

    public void caculate() {
        System.out.println("进行计算");
        LinkedHashMap<String, String> cellMap = new LinkedHashMap<>();
        for (CellModel c : items) {
            cellMap.put(c.getLabel().get(), c.getText().get());
        }
        String[] results = engine.caculate(cellMap);
        int i = 0;
        for (CellModel c : items) {
            c.setResult(results[i++]);
            c.showResult();
        }
    }

    public void edit() {
        System.out.println("进行编辑");
        for (CellModel c : items) {
            c.showExpression();
        }
    }
}
