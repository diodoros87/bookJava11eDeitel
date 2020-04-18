/* =====================================================================================
 *       Filename:  DrawShapes.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 6.3 - Main application class that loads and 
                                 displays the DrawShapes GUI
                             
                             
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

public class DrawShapes extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // loads DrawShapes.fxml and configures the DrawShapesController
      Parent root = FXMLLoader.load(getClass().getResource("DrawShapes.fxml"));

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing filled or outlined shapes"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      launch(args); // create a DrawShapes object and call its start method
   }
}
