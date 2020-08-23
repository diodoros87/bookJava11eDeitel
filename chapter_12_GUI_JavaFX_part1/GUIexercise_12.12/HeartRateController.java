/* =====================================================================================
 *       Filename:  HeartRateController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.12 - Controller that handles 
                                buttons and slider events
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.math.BigDecimal;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class HeartRateController { 
   private static final NumberFormat PERCENT       = NumberFormat.getPercentInstance();
   private static final BigDecimal   MINUEND       = new BigDecimal(220);
   private static final BigDecimal   MAX_AGE       = new BigDecimal(120);
   
   private BigDecimal maxRatePercentage = BigDecimal.ZERO;
   
   @FXML
   private Label percentageOfMaxLabel;

   @FXML
   private Slider percentageOfMaxSlider;

   @FXML
   private TextField ageTextField;

   @FXML
   private TextField maxHeartRateTextField;

   @FXML
   private TextField targetHeartRateTextField;

   @FXML
   private void calculateButtonPressed(ActionEvent event) {
      String maxHeartRateString    = "";
      String targetHeartRateString = "";
      
      try {
         BigDecimal age             = getValueFromUser(this.ageTextField);
         BigDecimal maxHeartRate    = MINUEND.subtract(age);
         BigDecimal targetHeartRate = maxHeartRate.multiply(maxRatePercentage);

         maxHeartRateString      = String.format("%s", maxHeartRate);
         targetHeartRateString   = String.format("%.0f", targetHeartRate);
      }
      catch (/* NumberFormatException is subclass of */ IllegalArgumentException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
      }
      finally {
         maxHeartRateTextField.setText(maxHeartRateString);
         targetHeartRateTextField.setText(targetHeartRateString);
      }
   }
   
   private BigDecimal getValueFromUser(TextField textField)
                                     throws NumberFormatException, IllegalArgumentException {
      assert(textField == this.ageTextField);
      
      BigDecimal value = null;
      
      try {
         if (this.ageTextField == textField) {
            value = getAgeFromUser();
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
   
   private BigDecimal getAgeFromUser() throws NumberFormatException, IllegalArgumentException {
      BigDecimal age;
      
      try {
         String textFieldString = ageTextField.getText();
         age                    = new BigDecimal(textFieldString);
         
         validateAge(age);
      } 
      catch (NumberFormatException exception) {
         requestToEnterNumber(ageTextField, "Enter age");
         throw exception;
      }
      
      return age; 
   }
   
   private void requestToEnterNumber(TextField textField, String message) {
      textField.setText(message);
      textField.selectAll();
      textField.requestFocus();
   }
   
   private void validateAge(BigDecimal age) {
      if (+1 != age.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("age must be > 0");
      }
      
      if (+1 == age.compareTo(MAX_AGE)) {
         throw new IllegalArgumentException("age must be <= " + MAX_AGE);
      }
   }

   // called by FXMLLoader to initialize the controller
   public void initialize() {
      Number defaultSliderVaue = percentageOfMaxSlider.getValue();
      maxRatePercentage = getSliderPercentageValue(defaultSliderVaue);
      
      DoubleProperty percentageOfMaxProperty = percentageOfMaxSlider.valueProperty();
      
      // listener for changes to percentageOfMaxSlider's value
      percentageOfMaxProperty.addListener(
         new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
               maxRatePercentage = getSliderPercentageValue(newValue);
               
               String percentageOfMaxFormatted = PERCENT.format(maxRatePercentage);
               percentageOfMaxFormatted = String.format("%s of max heart rate", percentageOfMaxFormatted);
               percentageOfMaxLabel.setText(percentageOfMaxFormatted);
            }
         }
      );
   }
   
   private BigDecimal getSliderPercentageValue(Number value) {
      int intValue      = value.intValue();
      double percentage = intValue / 100.0;
      BigDecimal result = BigDecimal.valueOf(percentage);
      
      return result;
   } 
}

