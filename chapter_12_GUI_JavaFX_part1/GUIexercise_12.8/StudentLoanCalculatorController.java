/* =====================================================================================
 *       Filename:  StudentLoanCalculatorController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.8 - Controller that handles 
                                buttons and slider events
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import creditCalculatorPackage.CreditCalculator;

import java.math.BigDecimal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class StudentLoanCalculatorController { 
   private CreditCalculator creditCalculator = new CreditCalculator();
   
   private BigDecimal repaymentYears = BigDecimal.TEN; 
   
   // GUI controls defined in FXML and used by the controller's code
   @FXML
   private Slider repaymentYearsSlider;

   @FXML
   private TextField annualIntestRateTextField;

   @FXML
   private TextField loanAmountTextField;

   @FXML
   private TextField monthlyCreditPaymentTextField;
   
   @FXML
   private void calculateButtonPressed(ActionEvent event) {
      String formattedCreditPayment = "";
      
      try {
         BigDecimal monthlyCreditPayment = calculateMonthlyCreditPayment();
         formattedCreditPayment          = CreditCalculator.getFormattedCurrency(monthlyCreditPayment);
      }
      catch (RuntimeException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
      }
      finally {
         monthlyCreditPaymentTextField.setText(formattedCreditPayment);
      }
   }
   
   private BigDecimal calculateMonthlyCreditPayment() {
      BigDecimal loanAmount       = getCorrectNumberFromUser(this.loanAmountTextField);
      BigDecimal creditIntestRate = getCorrectNumberFromUser(this.annualIntestRateTextField);
      
      creditCalculator.setRepaymentYears(this.repaymentYears);

      BigDecimal monthlyCreditPayment   = creditCalculator.calculateMonthlyCreditPayment();
      
      return monthlyCreditPayment;
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
      if (this.loanAmountTextField  == textField ) {
         creditCalculator.setLoanAmount(number);
      }
      else if (this.annualIntestRateTextField == textField) {
         creditCalculator.setAnnualInterestRate(number);
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
      // listener for changes to repaymentYearsSlider's value
      repaymentYearsSlider.valueProperty().addListener(
         new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
               int intValue   = newValue.intValue();
               repaymentYears = BigDecimal.valueOf(intValue);
            }
         }
      );
   }
}

