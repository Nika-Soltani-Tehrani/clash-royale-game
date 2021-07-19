package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class CellViewerController extends ListCell<Card> {

    @FXML
    private VBox box;
    @FXML
    private HBox imgBox;

    @FXML
    private Label label;

    @FXML
    private ImageView thumbImageView;

    private FXMLLoader mLLoader;

    protected void updateItem(Card item, boolean empty) {
        // required to ensure that cell displays properly
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null); // don't display anything
        }
        else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("CellViewer.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            // set ImageView's thumbnail image
            thumbImageView.setImage(new Image(item.getThumbImage()));
            label.setText(item.getTitle()); // configure Label's text
            setGraphic(box); // attach custom layout to ListView cell
        }
    }
}
