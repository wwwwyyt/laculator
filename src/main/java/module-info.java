module tech.cano.laculator {      
    requires javafx.controls;      
    requires javafx.fxml;
    requires exp4j;

    exports tech.cano.app;         
    opens tech.cano.ui to javafx.fxml;
}