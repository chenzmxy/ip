package ComSci.main;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set dialog text and style
        dialog.setText(text);
        dialog.setWrapText(true); // Ensures text wraps within the dialog box
        dialog.setStyle("-fx-background-color: #F6D55C; -fx-text-fill: #000000;"
                + "-fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Set profile image with circular cropping
        displayPicture.setImage(img);
        Circle clip = new Circle(25, 25, 25); // Circular crop: radius = 25px
        displayPicture.setClip(clip);
        displayPicture.setFitWidth(50); // Resize image to a consistent size
        displayPicture.setFitHeight(50);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getComSciDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #F6D55C; -fx-text-fill: #000000;" // Example: yellow for bot
                + "-fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        return db;

    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(); // Flip user dialog (image on the right, text on the left)
        db.dialog.setStyle("-fx-background-color: #20639B; -fx-text-fill: #FFFFFF;" // Example: blue for user
                + "-fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        return db;

    }
}
