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
   // formatters for CURRENCY and percentages
   private static final NumberFormat CURRENCY      = NumberFormat.getCurrencyInstance();
   private static final NumberFormat PERCENT       = NumberFormat.getPercentInstance();
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
         BigDecimal amount  = getValueFromUser(this.amountTextField);
         BigDecimal clients = getValueFromUser(this.numberOfClientsTextField);
         BigDecimal tip     = amount.multiply(tipPercentage);
         BigDecimal total   = calculateTotal(amount, tip, clients);

         tipFieldString     = CURRENCY.format(tip);
         totalFieldString   = CURRENCY.format(total);
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
   
   private BigDecimal getValueFromUser(TextField textField) {
      assert(textField == this.amountTextField || this.numberOfClientsTextField == textField);
      
      BigDecimal value = null;
      
      try {
         if      (this.amountTextField == textField) {
            value = getAmountFromUser();
         }
         else if (this.numberOfClientsTextField == textField) {
            value = getNumberOfClientsFromUser();
         }
         else {
            throw new UnsupportedOperationException(textField + " is unsupported");
         }
      }
      catch (IllegalArgumentException exception) {
         if (false == exception instanceof NumberFormatException) {
            requestToEnterNumber(textField, exception.getMessage());
         }
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
      }
      
      return value; 
   }
   
   private BigDecimal getAmountFromUser() throws NumberFormatException, IllegalArgumentException {
      BigDecimal amount;
      
      try {
         String textFieldString = amountTextField.getText();
         amount                 = new BigDecimal(textFieldString);
         
         validateAmount(amount);
      } 
      catch (NumberFormatException exception) {
         requestToEnterNumber(amountTextField, "Enter amount");
         throw exception;
      }/*
      catch (IllegalArgumentException exception) {
         requestToEnterNumber(amountTextField, exception.getMessage());
         throw exception;
      }*/
      
      return amount; 
   }
   
   private BigDecimal getNumberOfClientsFromUser() throws NumberFormatException, IllegalArgumentException {
      BigDecimal numberOfClients;
      
      try {
         String textFieldString = numberOfClientsTextField.getText();
         
         int clients            = Integer.parseInt(textFieldString);
         validateNumberOfClients(clients);
         
         numberOfClients        = new BigDecimal(clients);
      } 
      catch (NumberFormatException exception) {
         requestToEnterNumber(numberOfClientsTextField, "Enter integer");
         throw exception;
      }/*
      catch (IllegalArgumentException exception) {
         requestToEnterNumber(numberOfClientsTextField, exception.getMessage());
         throw exception;
      }*/
      
      return numberOfClients; 
   }
   
   private void requestToEnterNumber(TextField textField, String message) {
      textField.setText(message);
      textField.selectAll();
      textField.requestFocus();
   }
   
   private void validateAmount(BigDecimal amount) {
      if (-1 == amount.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("amount must be >= 0");
      }
   }
   
   private void validateNumberOfClients(int numberOfClients) {
      if (0 >= numberOfClients) {
         throw new IllegalArgumentException("clients must be > 0");
      }
   }

   // called by FXMLLoader to initialize the controller
   public void initialize() {
      // 0-4 rounds down, 5-9 rounds up 
      CURRENCY.setRoundingMode(ROUNDING_MODE);
      
      // listener for changes to tipPercentageSlider's value
      tipPercentageSlider.valueProperty().addListener(
         new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
               int intValue  = newValue.intValue();
               tipPercentage = BigDecimal.valueOf(intValue / 100.0);
               
               String tipPercentageFormatted = PERCENT.format(tipPercentage);
               tipPercentageLabel.setText(tipPercentageFormatted);
            }
         }
      );
   }
}

