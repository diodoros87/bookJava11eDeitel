/* =====================================================================================
 *       Filename:  DrivenKilometersCalculatorTestOK.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.10 - Main application class that loads and 
                                 displays the DrivenKilometersCalculatorTestOK GUI
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class DrivenKilometersCalculatorTestOK extends Application {
   FXMLLoader loader = new FXMLLoader();
   
   @Override
   public void start(Stage stage) throws Exception {
      loader.setLocation(getClass().getResource("DrivenKilometersCalculator.fxml"));
      Parent root = loader.load();
      DrivenKilometersCalculatorController controller = loader.getController();
      
      test(controller, root);
      showStage(stage, root);
   }
   
   private void showStage(Stage stage, Parent root) {
      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Driven kilometers calculator"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }
   
   private void test(DrivenKilometersCalculatorController controller, Parent root) {
      setText(root, "kilometersTextField", "111.5");
      setText(root, "litresTextField", "0.5");
      
      ActionEvent event = new ActionEvent();
      controller.calculateButtonPressed(event);
      
      String labelText = getTextFromLabel(controller);
      assert(labelText.equals("223.00")) : "Label text is " + labelText;
   }
   
   private Node getNode(Parent root, String nodeId) {
      Node node = root.lookup("#" + nodeId);
      if (null == node) {
         throw new RuntimeException(String.format("Can not find node with id = %s in root", nodeId));
      }
      
      return node;
   }
   
   private void setText(Parent root, String nodeId, String text) {
      Node node = getNode(root, nodeId);
      
      if (node instanceof TextField) {
         TextField textField = (TextField)node;
         textField.setText(text);
      }
      else {
         throw new UnsupportedOperationException("Can not find TextField");
      }
   }
   
   private String getTextFromLabel(DrivenKilometersCalculatorController controller) {
      Label kmPerLitreLabel = controller.getKmPerLitreLabel();
      String labelText      = kmPerLitreLabel.getText();
      
      return labelText;
   }
   
   private String getTextFromTextField(DrivenKilometersCalculatorController controller) {
      TextField kilometersTextField = controller.getKilometersTextField();
      String text                   = kilometersTextField.getText();
      
      return text;
   }

   public static void main(String[] args) {
      // create a DrivenKilometersCalculatorTestOK object and call its start method
      launch(args); 
   }
}
