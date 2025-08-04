package tech.cano.ui;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import tech.cano.model.*;
import tech.cano.ui.LaculatorView;

public class LaculatorController {

    private final LaculatorView view;
    private final CellList cellList = new CellList();
    private final ObservableList<CellModel> items = cellList.getItems();
    private final static String[] VIEW_STATUS = {"编辑", "计算"};  // VIEW_STATUS[0] 为不可编辑状态显示的文本，VIEW_STATUS[1] 为可编辑状态显示的文本。

    public LaculatorController(LaculatorView view) {
        this.view = view;
    }

    public void init() {
        view.listView.setItems(items);
        view.listView.editableProperty().bindBidirectional(cellList.getEditableProp());
        view.listView.setStyle("-fx-background-color: #f4f4f4;");
        view.listView.setCellFactory(p -> {
            return new ListCell<CellModel>() {
                private final Label label = new Label();
                private final TextArea textArea = new TextArea();
                private final VBox vbox = new VBox(label, textArea);
                private StringProperty currentTextAreaTextProp = null;
                private StringProperty currentLabelTextProp = null;
                
                {
                    textArea.setPrefRowCount(1);
                    textArea.setFont(view.fonts.fontLarge);
                }

                @Override
                protected void updateItem(CellModel item, boolean empty) {
                    super.updateItem(item, empty);

                    if (currentTextAreaTextProp != null) {
                        textArea.textProperty().unbindBidirectional(currentTextAreaTextProp);
                        currentTextAreaTextProp = null;
                    }

                    if (currentLabelTextProp != null) {
                        label.textProperty().unbindBidirectional(currentLabelTextProp);
                        currentLabelTextProp = null;
                    }

                    if (item == null || empty) {
                        setGraphic(null);
                        setStyle(null);
                    } else {
                        currentLabelTextProp = item.getLabel();
                        label.textProperty().bindBidirectional(currentLabelTextProp);
                        label.setText(String.valueOf(items.size()));

                        currentTextAreaTextProp = item.getText();
                        textArea.textProperty().bindBidirectional(currentTextAreaTextProp);
                        textArea.editableProperty().bindBidirectional(view.listView.editableProperty());
                        setGraphic(vbox);
                    }

                    setStyle("-fx-background-color: #f4f4f4;");
                }
            };
        });
        view.addCellBtn.setOnAction(event -> {
            System.out.println("添加单元格按钮被点击：");
            items.add(new CellModel());
        });
        view.delCellBtn.setOnAction(event -> {
            System.out.println("减少单元格按钮被点击：");
            if (!items.isEmpty()) {
                items.remove(items.size() - 1);
            }
        });
        view.caculateBtn.setOnAction(event -> {
            System.out.println("计算/编辑按钮被点击：");
            view.listView.setEditable(!view.listView.isEditable());
            view.caculateBtn.setText(VIEW_STATUS[view.listView.isEditable() ? 1 : 0]);
            if (view.listView.isEditable()) {
                cellList.edit();
            } else {
                cellList.caculate();
            }
        });
    }
}
