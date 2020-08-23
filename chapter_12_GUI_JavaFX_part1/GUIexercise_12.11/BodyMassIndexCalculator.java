/* =====================================================================================
 *       Filename:  BodyMassIndexCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.11 - Main application class that loads and 
                                 displays the BodyMassIndexCalculator GUI
                             
                             
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

public class BodyMassIndexCalculator extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("BodyMassIndexCalculator.fxml"));

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("BMI calculator"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      // create a BodyMassIndexCalculator object and call its start method
      launch(args); 
   }
}
