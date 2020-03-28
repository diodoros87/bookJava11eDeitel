/* =====================================================================================
 *       Filename:  DrawLines.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 4.1a - Main application class that loads and 
                                 displays the DrawLines GUI
                             
                             
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

public class DrawLines extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // loads DrawLines.fxml and configures the DrawLinesController
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../fxmlDrawLines/DrawLines.fxml"));
      Parent root = loader.load(); 

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Draw Lines"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      launch(args); // create a DrawLines object and call its start method
   }
}
