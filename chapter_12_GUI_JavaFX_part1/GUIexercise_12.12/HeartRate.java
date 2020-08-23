/* =====================================================================================
 *       Filename:  HeartRate.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.12 - Main application class that loads and 
                                 displays the HeartRate GUI
                             
                             
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

public class HeartRate extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("HeartRate.fxml"));

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Heart rate calculator"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      // create a HeartRate object and call its start method
      launch(args); 
   }
}
