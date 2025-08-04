package tech.cano.ui;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class LaculatorView {

    public final Fonts fonts = new Fonts();
    public final Button addCellBtn = new Button("添加单元格");
    public final Button delCellBtn = new Button("减少单元格");
    public final Button caculateBtn = new Button("计算");
    public final VBox btnList = new VBox(addCellBtn, delCellBtn, caculateBtn);
    public final ListView listView = new ListView<>();

    {
        addCellBtn.setMaxWidth(Double.MAX_VALUE);
        addCellBtn.setFont(fonts.fontNormal);

        delCellBtn.setMaxWidth(Double.MAX_VALUE);
        delCellBtn.setFont(fonts.fontNormal);

        caculateBtn.setMaxWidth(Double.MAX_VALUE);
        caculateBtn.setFont(fonts.fontNormal);

        btnList.setMinWidth(160);
        btnList.setSpacing(10);
    }

    public HBox createRoot() {
        HBox.setHgrow(btnList, Priority.NEVER);
        HBox.setHgrow(listView, Priority.ALWAYS);
        HBox root = new HBox(btnList, listView);
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        return root;
    }

    public static class Fonts {
        public static Font fontNormal;
        public static Font fontLarge;

        public Fonts() {
            fontNormal = Font.loadFont(
                getClass().getResourceAsStream("/tech/cano/font/SourceHanSansCN-Normal.otf"), 18);

            fontLarge = Font.loadFont(
                getClass().getResourceAsStream("/tech/cano/font/SourceHanSansCN-Normal.otf"), 36);
        }
    }
}
