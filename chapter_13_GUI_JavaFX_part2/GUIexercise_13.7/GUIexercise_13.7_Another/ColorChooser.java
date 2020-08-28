/* =====================================================================================
 *       Filename:  ColorChooser.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.7 - Main application class that loads and 
                                 displays the ColorChooser GUI
                             
                             
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

public class ColorChooser extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      ColorChooserView     sceneCreator = new ColorChooserView();
      ColorChooserController controller = new ColorChooserController(sceneCreator);
      controller.initialize();
      
      Scene scene = sceneCreator.createScene();
      stage.setTitle("Color Chooser");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
