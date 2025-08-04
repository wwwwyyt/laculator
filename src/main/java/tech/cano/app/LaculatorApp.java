package tech.cano.app;

import javafx.application.Application;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tech.cano.ui.LaculatorController;
import tech.cano.ui.LaculatorView;

public class LaculatorApp extends Application {
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        LaculatorView view = new LaculatorView();
        new LaculatorController(view).init();
        Parent root = view.createRoot();
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

