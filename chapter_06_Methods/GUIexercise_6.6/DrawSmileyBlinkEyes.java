/* =====================================================================================
 *       Filename:  DrawSmileyBlinkEyes.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 6.6 - Main application class that loads and 
                                 displays the DrawSmileyBlinkEyes GUI
                             
                             
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

public class DrawSmileyBlinkEyes extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // loads DrawSmileyBlinkEyes.fxml and configures the DrawSmileyBlinkEyesController
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("DrawSmileyBlinkEyes.fxml"));
      Parent root = loader.load(); 

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing smiley and blinking eyes"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      launch(args); // create a DrawSmileyBlinkEyes object and call its start method
   }
}
