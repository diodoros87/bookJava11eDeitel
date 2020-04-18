/* =====================================================================================
 *       Filename:  DrawShield.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 6.1 - Main application class that loads and 
                                 displays the DrawShield GUI
                             
                             
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

public class DrawShield extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // loads DrawShield.fxml and configures the DrawShieldController
      Parent root = FXMLLoader.load(getClass().getResource("DrawShield.fxml"));

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing Shield"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      launch(args); // create a DrawShield object and call its start method
   }
}
