/* =====================================================================================
 *       Filename:  DrawSmiley.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 6.4 - Main application class that loads and 
                                 displays the DrawSmiley GUI
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawSmiley extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // loads DrawSmiley.fxml and configures the DrawSmileyController
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../fxmlDrawSmiley/DrawSmiley.fxml"));
      Parent root = loader.load(); 

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing smiley"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      launch(args); // create a DrawSmiley object and call its start method
   }
}
