/* =====================================================================================
 *       Filename:  Painter.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.3 - Main application class that loads and 
                                 displays the Painter GUI
                             
                             
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

public class Painter extends Application {   
   @Override
   public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("Painter.fxml"));
      
      Scene scene = new Scene(root);
      stage.setTitle("Painter"); // displayed in window's title bar
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
