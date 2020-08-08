/* =====================================================================================
 *       Filename:  TipCalculatorController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.6 - Controller that handles 
                                buttons and slider events
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController { 
   // formatters for currency and percentages
   private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
   private static final NumberFormat percent  = NumberFormat.getPercentInstance();
   private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
   
   private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default
   
   // GUI controls defined in FXML and used by the controller's code
   @FXML 
   private TextField amountTextField; 
   
   @FXML 
   private TextField numberOfClientsTextField; 

   @FXML
   private Label tipPercentageLabel; 

   @FXML
   private Slider tipPercentageSlider;

   @FXML
   private TextField tipTextField;

   @FXML
   private TextField totalTextField;

   // calculates and displays the tip and total amounts
   @FXML
   private void calculateButtonPressed(ActionEvent event) {
      String tipFieldString   = "";
      String totalFieldString = "";
      
      try {
         BigDecimal amount  = getValueFromUser(amountTextField, "amount");
         BigDecimal clients = getValueFromUser(numberOfClientsTextField, "clients");
         BigDecimal tip     = amount.multiply(tipPercentage);
         BigDecimal total   = calculateTotal(amount, tip, clients);

         tipFieldString     = currency.format(tip);
         totalFieldString   = currency.format(total);
      }
      catch (/*NumberFormatException is subclass of */IllegalArgumentException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
      }
      finally {
         tipTextField.setText(tipFieldString);
         totalTextField.setText(totalFieldString);
      }
   }
   
   private BigDecimal calculateTotal(BigDecimal amount, BigDecimal tip, BigDecimal clients) {
      BigDecimal total = amount.add(tip);
      int scale        = total.scale() - clients.scale();
      total            = total.divide(clients, scale, ROUNDING_MODE);
      
      return total;
   }
   
   private BigDecimal getValueFromUser(TextField textField, String valueName) 
                              throws NumberFormatException, IllegalArgumentException {
      BigDecimal value;
      
      try {
         String valueString = textField.getText();
         value              = new BigDecimal(valueString);
         validateValue(value, valueName);
      } 
      catch (NumberFormatException exception) {
         requestToEnterNumber(textField, "Enter amount");
         throw exception;
      }
      catch (IllegalArgumentException exception) {
         requestToEnterNumber(textField, exception.getMessage());
         throw exception;
      }
      
      return value; 
   }
   
   private void requestToEnterNumber(TextField textField, String message) {
      textField.setText(message);
      textField.selectAll();
      textField.requestFocus();
   }
   
   private void validateValue(BigDecimal value, String valueName) {
      switch (valueName) {
         case "amount":
            if (-1 == value.compareTo(BigDecimal.ZERO)) {
               String message = String.format("%s must be >= 0", valueName);
               
               throw new IllegalArgumentException(message);
            }
            
            break;
         case "clients":
            if (+1 != value.compareTo(BigDecimal.ZERO)) {
               String message = String.format("%s must be > 0", valueName);
               
               throw new IllegalArgumentException(message);
            }
            
            break;
         default:
            throw new IllegalArgumentException("Illegal value name");
      }
   }

   // called by FXMLLoader to initialize the controller
   public void initialize() {
      // 0-4 rounds down, 5-9 rounds up 
      currency.setRoundingMode(ROUNDING_MODE);
      
      // listener for changes to tipPercentageSlider's value
      tipPercentageSlider.valueProperty().addListener(
         new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, 
               Number oldValue, Number newValue) {
               tipPercentage = 
                  BigDecimal.valueOf(newValue.intValue() / 100.0);
               tipPercentageLabel.setText(percent.format(tipPercentage));
            }
         }
      );
   }
}

