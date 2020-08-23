/* =====================================================================================
 *       Filename:  BodyMassIndexCalculatorController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.11 - Controller that handles 
                                buttons and toggle-buttons events
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.math.BigDecimal;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BodyMassIndexCalculatorController { 
   private MeasurementSystem unitsSystem = null; 
   private BodyMassIndex     calculator = new BodyMassIndex();
   
   @FXML
   private Label weightLabel;

   @FXML
   private TextField weightTextField;

   @FXML
   private Label heightLabel;

   @FXML
   private TextField heightTextField;

   @FXML
   private ToggleButton metricToggleButton;
   
   @FXML
   private ToggleButton imperialToggleButton;

   @FXML
   private ToggleGroup unitsSystemToggleGroup;
   
   @FXML
   private TextField bmiTextField;
   
   @FXML
   private void unitsToggleButtonSelected(ActionEvent event) {
      Toggle selectedToggle = unitsSystemToggleGroup.getSelectedToggle();
      
      this.unitsSystem = (MeasurementSystem) selectedToggle.getUserData();
      
      updateLabelText();
   }
   
   private void updateLabelText() {
      String lengthUnit = unitsSystem.getLengthUnit();
      String weightUnit = unitsSystem.getMassUnit();
      
      String heightDescription = String.format("Height in %s", lengthUnit);
      String weightDescription = String.format("Weight in %s", weightUnit);
      
      heightLabel.setText(heightDescription);
      weightLabel.setText(weightDescription);
   }
   
   @FXML
   private void calculateButtonPressed(ActionEvent event) {
      String formattedBMI = "";
      
      try {
         BigDecimal bmi = calculateBMI();
         formattedBMI   = String.format(" %.1f ", bmi);
      }
      catch (RuntimeException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
      }
      finally {
         bmiTextField.setText(formattedBMI);
      }
   }
   
   private BigDecimal calculateBMI() {
      getCorrectNumberFromUser(this.weightTextField);
      getCorrectNumberFromUser(this.heightTextField);
      
      calculator.setMeasurementSystem(this.unitsSystem);
      BigDecimal bmi   = calculator.calculateBMI();
      
      return bmi;
   }
   
   private BigDecimal getCorrectNumberFromUser(TextField textField)
            throws NumberFormatException, IllegalArgumentException, UnsupportedOperationException {
               
      BigDecimal value = getNumberFromUser(textField);
      
      try {
         validateNumber(value, textField);
      } 
      catch (IllegalArgumentException exception) {
         requestToEnterNumber(textField, exception.getMessage());
         
         throw exception;
      }
      
      return value; 
   }
   
   private void validateNumber(BigDecimal number, TextField textField) throws IllegalArgumentException {
      if (this.weightTextField  == textField ) {
         calculator.setWeight(number);
      }
      else if (this.heightTextField == textField) {
         calculator.setHeight(number);
      }
      else {
         throw new UnsupportedOperationException(textField + " is unsupported");
      }
   }
   
   private BigDecimal getNumberFromUser(TextField textField) throws NumberFormatException {
      BigDecimal number;
      
      try {
         String textFieldString = textField.getText();
         number                 = new BigDecimal(textFieldString);
      } 
      catch (NumberFormatException exception) {
         requestToEnterNumber(textField, "Enter number");
         
         throw exception;
      }
      
      return number; 
   }
   
   private void requestToEnterNumber(TextField textField, String message) {
      textField.setText(message);
      textField.selectAll();
      textField.requestFocus();
   }

   // called by FXMLLoader to initialize the controller
   public void initialize() {
      metricToggleButton.setUserData(MeasurementSystem.METRIC);
      imperialToggleButton.setUserData(MeasurementSystem.IMPERIAL);
      
      ActionEvent event = null;
      unitsToggleButtonSelected(event);
   }
}
