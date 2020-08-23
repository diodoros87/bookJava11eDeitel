/* =====================================================================================
 *       Filename:  DrivenKilometersCalculatorController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.10 - Controller that handles 
                                button events
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.math.BigDecimal;
import java.math.MathContext;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DrivenKilometersCalculatorController { 
   @FXML
   private TextField kilometersTextField;

   @FXML
   private TextField litresTextField;

   @FXML
   private Label     kmPerLitreLabel;
   
   @FXML
   void calculateButtonPressed(ActionEvent event) {
      String kilometersPerLitreString = "";
      
      try {
         BigDecimal kilometersPerLitre = calculateKilometersPerLitre();
         kilometersPerLitreString      = String.format("%.2f", kilometersPerLitre);
      }
      catch (ArithmeticException exception) {
         kilometersPerLitreString = exception.getMessage();
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
      }
      catch (RuntimeException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
      }
      finally {
         kmPerLitreLabel.setText(kilometersPerLitreString);
      }
   }
   
   Label getKmPerLitreLabel() {
      return kmPerLitreLabel;
   }
   
   TextField getKilometersTextField() {
      return kilometersTextField;
   }
   
   private BigDecimal calculateKilometersPerLitre() {
      BigDecimal litres       = getCorrectNumberFromUser(this.litresTextField, "litres");
      BigDecimal kilometers   = getCorrectNumberFromUser(this.kilometersTextField, "kilometers");

      BigDecimal kilometersPerLitre   = calculateKilometersPerLitre(litres, kilometers);
      
      return kilometersPerLitre;
   }
   
   private BigDecimal calculateKilometersPerLitre(BigDecimal litres, BigDecimal kilometers) {
      boolean isZeroLitres     = 0 == litres.compareTo(BigDecimal.ZERO);
      boolean isZeroKilometers = 0 == kilometers.compareTo(BigDecimal.ZERO);
      
      if (isZeroLitres) {
         if (isZeroKilometers) {
            return BigDecimal.ZERO;
         }
         else {
            throw new ArithmeticException("Can not divide by 0");
         }
      }
      
      BigDecimal result = kilometers.divide(litres, MathContext.DECIMAL128);
      
      return result;
   }
   
   private BigDecimal getCorrectNumberFromUser(TextField textField, String designation) throws NumberFormatException {
               
      BigDecimal value = getNumberFromUser(textField, designation);
      
      try {
         validateUnsignedNumber(value, designation);
      } 
      catch (IllegalArgumentException exception) {
         requestToEnterNumber(textField, exception.getMessage());
         
         throw exception;
      }
      
      return value; 
   }
   
   private BigDecimal getNumberFromUser(TextField textField, String designation) throws NumberFormatException {
      BigDecimal number;
      
      try {
         String textFieldString = textField.getText();
         number                 = new BigDecimal(textFieldString);
      } 
      catch (NumberFormatException exception) {
         requestToEnterNumber(textField, "Enter " + designation);
         
         throw exception;
      }
      
      return number; 
   }
   
   private void validateUnsignedNumber(BigDecimal number, String designation) {
      if (-1 == number.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException(designation + " must be >= 0");
      }
   }
   
   private void requestToEnterNumber(TextField textField, String message) {
      textField.setText(message);
      textField.selectAll();
      textField.requestFocus();
   }
}

