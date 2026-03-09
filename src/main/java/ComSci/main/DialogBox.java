package ComSci.main;

import java.io.IOException;
import java.util.Collections;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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

    private static final String USER_STYLE =
            "-fx-background-color: #2563EB;"
                    + "-fx-text-fill: white;"
                    + "-fx-padding: 12 16 12 16;"
                    + "-fx-background-radius: 18;"
                    + "-fx-border-radius: 18;"
                    + "-fx-font-size: 13px;";

    private static final String BOT_STYLE =
            "-fx-background-color: white;"
                    + "-fx-text-fill: #1F2937;"
                    + "-fx-padding: 12 16 12 16;"
                    + "-fx-background-radius: 18;"
                    + "-fx-border-radius: 18;"
                    + "-fx-font-size: 13px;"
                    + "-fx-border-color: #E5E7EB;"
                    + "-fx-border-width: 1;";

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setMaxWidth(300);

        displayPicture.setImage(img);
        displayPicture.setPreserveRatio(true);

        DoubleBinding avatarSize = (DoubleBinding) Bindings.min(
                Bindings.max(widthProperty().multiply(0.08), 56), 100
        );

        displayPicture.fitWidthProperty().bind(avatarSize);
        displayPicture.fitHeightProperty().bind(avatarSize);

        Circle clip = new Circle();
        clip.centerXProperty().bind(displayPicture.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(displayPicture.fitHeightProperty().divide(2));
        clip.radiusProperty().bind(displayPicture.fitWidthProperty().divide(2));
        displayPicture.setClip(clip);


        setSpacing(8);
        setMaxWidth(Double.MAX_VALUE);

        // Bubble width changes with the dialog row width
        dialog.maxWidthProperty().bind(widthProperty().multiply(0.6));
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
        db.dialog.setStyle(BOT_STYLE);
        return db;

    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(); // Flip user dialog (image on the right, text on the left)
        db.dialog.setStyle(USER_STYLE);
        return db;
    }
}
