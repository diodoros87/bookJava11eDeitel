/* =====================================================================================
 *       Filename:  DrawShapesController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.4 - Controller that handles buttons to:
                                adding, subtracting, multiplying and dividing numbers
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {
   private double firstNumber;
   private double secondNumber;


   @FXML // ResourceBundle that was given to the FXMLLoader
   private ResourceBundle resources;

   @FXML // URL location of the FXML file that was given to the FXMLLoader
   private URL location;

   @FXML // fx:id="firstNumberTextField"
   private TextField firstNumberTextField; // Value injected by FXMLLoader

   @FXML // fx:id="secondNumberTextField"
   private TextField secondNumberTextField; // Value injected by FXMLLoader

   @FXML // fx:id="resultLabel"
   private Label resultLabel; // Value injected by FXMLLoader

   @FXML
   void onAsteriskButtonPressed(ActionEvent event) {
      try {
          obtainNumbersFromUser();
          double result = firstNumber * secondNumber;
          setResultLabel(result);
      }
      catch (NumberFormatException exception) {
         resultLabel.setText("");
      }
   }
   
   private void setResultLabel(double number) {
      double absoluteNumber = Math.abs(number);
      String numberString;
      
      if (absoluteNumber > 1.0e20 || absoluteNumber < 1.0e-20) {
         numberString = String.format("   %,e", number);
      }
      else {
         numberString = String.format("   %,f", number);
      }
      
      resultLabel.setText(numberString);
   }
   
   private void obtainNumbersFromUser() {
      firstNumber  = getNumberFromTextField(firstNumberTextField);
      secondNumber = getNumberFromTextField(secondNumberTextField);
   }
   
   private double getNumberFromTextField(TextField textField) {
      double number = 0;

      try {
         String text   = textField.getText();
         number        = Double.parseDouble(text);
      }
      catch (NumberFormatException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
         requestToEnterData(textField);
         
         throw exception;
      }
      
      return number;
   }

   private void requestToEnterData(TextField textField) {
      textField.setText("Enter number");
      textField.selectAll();
      textField.requestFocus();
   }

   @FXML
   void onMinusButtonPressed(ActionEvent event) {
      try {
          obtainNumbersFromUser();
          double result = firstNumber - secondNumber;
          setResultLabel(result);
      }
      catch (NumberFormatException exception) {
         resultLabel.setText("");
      }
   }

   @FXML
   void onPlusButtonPressed(ActionEvent event) {
      try {
          obtainNumbersFromUser();
          double result = firstNumber + secondNumber;
          setResultLabel(result);
      }
      catch (NumberFormatException exception) {
         resultLabel.setText("");
      }
   }

   @FXML
   void onSlashButtonPressed(ActionEvent event) {
      try {
          obtainNumbersFromUser();
          if (secondNumber == 0) {
             throw new ArithmeticException("can not divide by 0");
          }
          double result = firstNumber / secondNumber;
          setResultLabel(result);
      }
      catch (NumberFormatException exception) {
         resultLabel.setText("");
      }
      catch (ArithmeticException exception) {
         resultLabel.setText(exception.getMessage());
      }
   }

   @FXML // This method is called by the FXMLLoader when initialization is complete
   void initialize() {
      assert firstNumberTextField != null : "fx:id=\"firstNumberTextField\" was not injected: check your FXML file 'Calculator.fxml'.";
      assert secondNumberTextField != null : "fx:id=\"secondNumberTextField\" was not injected: check your FXML file 'Calculator.fxml'.";
      assert resultLabel != null : "fx:id=\"resultLabel\" was not injected: check your FXML file 'Calculator.fxml'.";

   }
}

