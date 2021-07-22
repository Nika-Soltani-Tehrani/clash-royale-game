package menu;

import javafx.scene.image.Image;

/**
 * The type Card.
 */
// Book.java
public class Card {
   private String title; // book title
   private String thumbImage; // source of book cover's thumbnail image
   private Image boardImage;
   private int cost;

    /**
     * Instantiates a new Card.
     *
     * @param title      the title
     * @param thumbImage the thumb image
     * @param boardName  the board name
     * @param cost       the cost
     */
    public Card(String title, String thumbImage, String boardName, int cost) {
      this.title = title;
      this.thumbImage = thumbImage;
      this.boardImage = new Image(getClass().getResourceAsStream("/images/board/"+boardName));
      this.cost = cost;

   }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {return title;}

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * Gets thumb image.
     *
     * @return the thumb image
     */
    public String getThumbImage() {return thumbImage;}

    /**
     * Sets thumb image.
     *
     * @param thumbImage the thumb image
     */
    public void setThumbImage(String thumbImage) {this.thumbImage = thumbImage;}

    /**
     * Get board image image.
     *
     * @return the image
     */
    public Image getBoardImage(){
      return this.boardImage;
   }

   @Override
   public String toString() {return getTitle();}

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public int getCost() {
      return cost;
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
 **************************************************************************/
