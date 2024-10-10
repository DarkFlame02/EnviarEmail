package dad.enviaremail;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnviarEmailApp extends Application {

    private final RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mis Amigos");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }
}
