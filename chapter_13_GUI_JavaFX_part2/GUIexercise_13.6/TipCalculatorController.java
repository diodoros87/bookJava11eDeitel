/* =====================================================================================
 *       Filename:  TipCalculatorController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.6 - Controller that handles 
                                buttons and slider events
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController { 
   // formatters for CURRENCY and percentages
   private static final NumberFormat CURRENCY      = NumberFormat.getCurrencyInstance();
   private static final NumberFormat PERCENT       = NumberFormat.getPercentInstance();
   private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
   private static final int PRECISION              = 30;
   private static final MathContext MATH_CONTEXT   = new MathContext(PRECISION, ROUNDING_MODE);
   
   private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default
   
   private Input input = new Input();
   
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
   private void calculate() {
      String tipFieldString   = "";
      String totalFieldString = "";
      
      try {
         BigDecimal amount  = input.getValueFromUser(this.amountTextField);
         BigDecimal clients = input.getValueFromUser(this.numberOfClientsTextField);
         BigDecimal tip     = amount.multiply(tipPercentage, MATH_CONTEXT);
         BigDecimal total   = calculateTotal(amount, tip, clients);

         tipFieldString     = CURRENCY.format(tip);
         totalFieldString   = CURRENCY.format(total);
      }
      catch (/* NumberFormatException is subclass of */ IllegalArgumentException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
      }
      finally {
         tipTextField.setText(tipFieldString);
         totalTextField.setText(totalFieldString);
      }
   }
   
   private BigDecimal calculateTotal(BigDecimal amount, BigDecimal tip, BigDecimal clients) {
      BigDecimal total = amount.add(tip, MATH_CONTEXT);
      total            = total.divide(clients, MATH_CONTEXT);
      
      return total;
   }
   
   private class Input {
      BigDecimal getValueFromUser(TextField textField)
                                        throws NumberFormatException, IllegalArgumentException {
         assert(textField == TipCalculatorController.this.amountTextField 
         || TipCalculatorController.this.numberOfClientsTextField == textField);
         
         BigDecimal value = null;
         
         try {
            if      (TipCalculatorController.this.amountTextField == textField) {
               value = getAmountFromUser();
            }
            else if (TipCalculatorController.this.numberOfClientsTextField == textField) {
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
            
            throw exception;
         }
         
         return value; 
      }
      
      BigDecimal getAmountFromUser() throws NumberFormatException, IllegalArgumentException {
         BigDecimal amount;
         
         try {
            String textFieldString = amountTextField.getText();
            amount                 = new BigDecimal(textFieldString);
            
            validateAmount(amount);
         } 
         catch (NumberFormatException exception) {
            requestToEnterNumber(amountTextField, "Enter amount");
            throw exception;
         }
         
         return amount; 
      }
      
      BigDecimal getNumberOfClientsFromUser() throws NumberFormatException, IllegalArgumentException {
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
         }
         
         return numberOfClients; 
      }
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
      CURRENCY.setRoundingMode(ROUNDING_MODE);
      initializePercentValueBinding();

      DoubleProperty sliderValue = tipPercentageSlider.valueProperty();
      initializeDoublePropertyListener(sliderValue);
      
      StringProperty textFieldProperty = amountTextField.textProperty();
      initializeStringPropertyListener(textFieldProperty);
      
      textFieldProperty = numberOfClientsTextField.textProperty();
      initializeStringPropertyListener(textFieldProperty);
   }
   
   private void initializeDoublePropertyListener(DoubleProperty property) {
      property.addListener(
         new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
               calculate();
            }
         }
      );
   }
   
   private void initializeStringPropertyListener(StringProperty property) {
      property.addListener(
         new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
               calculate();
            }
         }
      );
   }
   
   private void initializePercentValueBinding() {
      DoubleProperty sliderValue = tipPercentageSlider.valueProperty();
      
      StringBinding sliderValueStringBinding = new StringBinding() {
         {
            super.bind(sliderValue);
         }

         @Override
         protected String computeValue() {
            int intValue  = sliderValue.intValue();
            tipPercentage = BigDecimal.valueOf(intValue / 100.0);
            
            String tipPercentageFormatted = PERCENT.format(tipPercentage);
            String stringToDisplay = String.format("Tip percentage: %s", tipPercentageFormatted);
            
            return stringToDisplay;
         }
      };
      
      StringProperty stringValue = tipPercentageLabel.textProperty();
      stringValue.bind(sliderValueStringBinding);
   }
}

