/* =====================================================================================
 *       Filename:  DrawRainbow.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 7.5 - Main application class that loads and 
                                 displays the DrawRainbow GUI
                             
                             
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

public class DrawRainbow extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // loads DrawRainbow.fxml and configures the DrawRainbowController
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("DrawRainbow.fxml"));
      Parent root = loader.load(); 

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing rainbow"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      launch(args); // create a DrawRainbow object and call its start method
   }
}
