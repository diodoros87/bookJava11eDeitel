/* =====================================================================================
 *       Filename:  DrawSpiral.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 7.4 - Main application class that loads and 
                                 displays the DrawSpiral GUI
                             
                             
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

public class DrawSpiral extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // loads DrawSpiral.fxml and configures the DrawSpiralController
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../fxmlDrawSpiral/DrawSpiral.fxml"));
      Parent root = loader.load(); 

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing colored spiral by arcs"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      launch(args); // create a DrawSpiral object and call its start method
   }
}
