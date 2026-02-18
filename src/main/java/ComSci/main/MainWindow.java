package ComSci.main;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ComSci duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaComSci.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDuke(ComSci d) {
        duke = d;
        d.run();
        showGreeting();
    }

    /**
     * Displays a greeting message in the dialog container.
     */
    private void showGreeting() {
        String greetingMessage = "Hello bro! I'm ComSci, your personal tasks management Bro. Today we do what?";
        dialogContainer.getChildren().add(DialogBox.getComSciDialog(greetingMessage, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getComSciDialog(response, dukeImage)
        );
        if (userInput.getText().equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
        userInput.clear();
    }
}
