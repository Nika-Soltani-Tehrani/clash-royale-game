package menu;// CoverViewerController.java
// Controller for Cover Viewer application
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DeckViewerController {
   // instance variables for interacting with GUI
   @FXML private ListView<Card> booksListView;
   @FXML private ImageView coverImageView;
   @FXML private ListView<Card> sortedListView;

   // stores the list of Book Objects
   private final ObservableList<Card> cards =
      FXCollections.observableArrayList();
   public final static ObservableList<Card> newCards =
           FXCollections.observableArrayList();
   public void initialize() {
      // populate the ObservableList<Book>
      cards.add(new Card("archers",
         "/images/small/archers.png","archers.png"));
      cards.add(new Card("arrows",
         "/images/small/arrows.png","arrows.png"));
      cards.add(new Card("baby dragon",
         "/images/small/baby-dragon.png","baby-dragon.png"));
      cards.add(new Card("balloon",
         "/images/small/balloon.png","balloon.png"));
      cards.add(new Card("bandit",
         "/images/small/bandit.png","bandit.png"));
      cards.add(new Card("barbarian-barrel",
         "/images/small/barbarian-barrel.png","barbarian-barrel.png"));
      cards.add(new Card("bats",
         "/images/small/bats.png","bats.png"));
      booksListView.setItems(cards); // bind booksListView to books
      // when ListView selection changes, show large cover in ImageView
      if(newCards!=null){
          sortedListView.setItems(newCards);
      }
       booksListView.getSelectionModel().selectedItemProperty().
         addListener(
            new ChangeListener<Card>() {
               @Override                                                     
               public void changed(ObservableValue<? extends Card> ov,
                                   Card oldValue, Card newValue) {
                  /*coverImageView.setImage(
                     new Image(newValue.getLargeImage()));*/
                  if(newCards.size()<8 && !newCards.contains(newValue)) {
                      newCards.add(newValue);
                      sortedListView.setItems(newCards);

                  }
               }
            }
         );
         
      // set custom ListView cell factory
      booksListView.setCellFactory(
         new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> listView) {
               return new CellViewerController();
               //return new ImageTextCell();
            }
         }
      );
      sortedListView.setCellFactory(
              new Callback<ListView<Card>, ListCell<Card>>() {
                 @Override
                 public ListCell<Card> call(ListView<Card> listView) {
                    return new CellViewerController();
                    //return new ImageTextCell();
                 }
              }
      );
   }

   @FXML
    public void reset(ActionEvent event){
       if(sortedListView.getItems() != null){
           sortedListView.getItems().clear();
           newCards.clear();
       }
   }
    public void menu(ActionEvent event) throws Exception{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
