/* =====================================================================================
 *       Filename:  RotateDrawSpiral.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 7.3 - Main application class that loads and 
                                 displays the RotateDrawSpiral GUI
                             
                             
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

public class RotateDrawSpiral extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // loads RotateDrawSpiral.fxml and configures the RotateDrawSpiralController
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("RotateDrawSpiral.fxml"));
      Parent root = loader.load();

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing spiral by arcs and spiral's rotation"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      launch(args); // create a RotateDrawSpiral object and call its start method
   }
}
