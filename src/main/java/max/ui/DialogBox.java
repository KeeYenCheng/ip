package max.ui;

import java.io.IOException;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
/**
 * A reusable dialog row displayed in the conversation area.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        FXMLLoader loader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load dialog box layout", e);
        }
        dialog.setText(text);
        if (image != null) {
            displayPicture.setFitHeight(75);
            displayPicture.setFitWidth(75);
            displayPicture.setPreserveRatio(true);
            Circle clip = new Circle(37.5, 37.5, 37.5);
            displayPicture.setClip(clip); 
            displayPicture.setImage(image);
        } else {
            displayPicture.setManaged(false);
            displayPicture.setVisible(false);
        }
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /** Creates a dialog row for user input. */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(Objects.requireNonNullElse(text, ""), image);
    }

    /** Creates a dialog row for Max's response. */
    public static DialogBox getMaxDialog(String text, Image image) {
        DialogBox db = new DialogBox(Objects.requireNonNullElse(text, ""), image);
        db.flip();
        return db;
    }

}
