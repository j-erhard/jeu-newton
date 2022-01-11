import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.model.Game;
import client.view.View;

import java.io.IOException;

public class Main extends Application {
    public static Game game;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        View view = new View();
        Scene scene = new Scene(view);
        primaryStage.setTitle("Newton");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.requestFocus();

        //         stop le programme lorsque l'on quitte la fenêtre
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }
}