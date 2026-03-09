package ComSci.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for ComSci using FXML.
 */
public class Main extends Application {

    private final ComSci duke = new ComSci();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap, 420, 650);

            stage.setTitle("ComSci Assistant");
            stage.setScene(scene);

            // Allow resizing
            stage.setResizable(true);

            // Optional but recommended
            stage.setMinWidth(350);
            stage.setMinHeight(500);

            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}