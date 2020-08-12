/* =====================================================================================
 *       Filename:  CarCreditCalculatorController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.9 - Controller that handles 
                                buttons and slider events
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import creditCalculatorPackage.CreditCalculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Collections;
import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class CarCreditCalculatorController { 
   private CreditCalculator creditCalculator = new CreditCalculator();
   
   private BigDecimal[] repaymentYearsArray;
   
   private BigDecimal annualInterestRate = new BigDecimal(0.15); // 15% default
   
   // GUI controls defined in FXML and used by the controller's code
   @FXML
   private Label annualInterestRateLabel;

   @FXML
   private Slider annualInterestRateSlider;

   @FXML
   private TextField carPriceTextField;

   @FXML
   private TextField ownContributionTextField;
   
   @FXML
   private Label firstMonthsLabel;

   @FXML
   private Label secondMonthsLabel;

   @FXML
   private Label thirdMonthsLabel;

   @FXML
   private Label fourthMonthsLabel;

   @FXML
   private TextField firstMonthsTextField;

   @FXML
   private TextField secondMonthsTextField;

   @FXML
   private TextField thirdMonthsTextField;

   @FXML
   private TextField fourthMonthsTextField;
   
   @FXML
   private void calculateButtonPressed(ActionEvent event) {
      ArrayDeque<String> creditPaymentsDeque = null;
      
      try {
         creditPaymentsDeque = getCreditPayments();
      }
      catch (RuntimeException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
         
         Collection<String> creditAmountsList = Collections.nCopies(4, "");
         creditPaymentsDeque = new ArrayDeque<String> (creditAmountsList);
         //creditPaymentsDeque.addAll(creditAmountsList);
      }
      finally {
         firstMonthsTextField.setText(creditPaymentsDeque.poll());
         secondMonthsTextField.setText(creditPaymentsDeque.poll());
         thirdMonthsTextField.setText(creditPaymentsDeque.poll());
         fourthMonthsTextField.setText(creditPaymentsDeque.poll());
      }
   }
   
   private final BigDecimal[] createRepaymentYearsArray() {
      String[] repaymentMonthsStrings  = getRepaymentMonthsStrings();
      BigDecimal[] repaymentYearsArray = createRepaymentYearsArray(repaymentMonthsStrings);
      
      return repaymentYearsArray;
   }
   
   private final BigDecimal[] createRepaymentYearsArray(String[] repaymentMonthsStrings) {
      BigDecimal[] repaymentYearsArray = new BigDecimal[repaymentMonthsStrings.length];
      int index = 0;
      for (String monthsString : repaymentMonthsStrings) {
         try {
            BigDecimal repaymentMonths   = new BigDecimal(monthsString);
            BigDecimal repaymentYears    = CreditCalculator.getRepaymentYears(repaymentMonths);
            repaymentYearsArray[index++] = repaymentYears;
         } 
         catch (NumberFormatException exception) {
            System.err.printf("%n%s%n", exception);
            exception.printStackTrace();
            
            throw exception;
         }
         
      }
      
      return repaymentYearsArray;
   }
   
   private final String[] getRepaymentMonthsStrings() {
      String[] repaymentYearsStrings = { 
         firstMonthsLabel.getText(),
         secondMonthsLabel.getText(),
         thirdMonthsLabel.getText(),
         fourthMonthsLabel.getText()
      }; 
      
      return repaymentYearsStrings;
   }
   
   private ArrayDeque<String> getCreditPayments() {
      BigDecimal carPrice         = getCorrectNumberFromUser(this.carPriceTextField);
      BigDecimal ownContribution  = getCorrectNumberFromUser(this.ownContributionTextField);
      
      try {
         creditCalculator.setLoanAmount(carPrice, ownContribution);
         creditCalculator.setAnnualInterestRate(this.annualInterestRate);
      } 
      catch (IllegalArgumentException exception) {
         assert(false);
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
         
         throw exception;
      }
      
      ArrayDeque<String> creditPaymentsDeque = getCreditPaymentsByRepaymentYears();
      
      return creditPaymentsDeque;
   }
   
   private ArrayDeque<String> getCreditPaymentsByRepaymentYears() {
      ArrayDeque<String> creditPaymentsDeque = new ArrayDeque<>();
      BigDecimal creditPayment;
      String     formattedCreditPayment;
      
      for (int index = repaymentYearsArray.length - 1; index >= 0 ; index--) {
         creditPayment = creditCalculator.calculateMonthlyCreditPayment(repaymentYearsArray[index]);
         formattedCreditPayment = CreditCalculator.getFormattedCurrency(creditPayment);
         creditPaymentsDeque.addFirst(formattedCreditPayment);
      }
      
      return creditPaymentsDeque;
   }
   
   private BigDecimal calculateMonthlyCreditPayment(BigDecimal repaymentYears) {
      try {
         creditCalculator.setRepaymentYears(repaymentYears);
      } 
      catch (IllegalArgumentException exception) {
         assert(false);
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
         
         throw exception;
      }
      
      BigDecimal creditPayment = creditCalculator.calculateMonthlyCreditPayment();
      
      return creditPayment;                                
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
      if (this.carPriceTextField  == textField
      ||  this.ownContributionTextField  == textField) {
         
         CreditCalculator.validateAmount(number);
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
      repaymentYearsArray = createRepaymentYearsArray();
      // listener for changes to repaymentYearsSlider's value
      annualInterestRateSlider.valueProperty().addListener(
         new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
               double doubleValue = newValue.doubleValue();
               annualInterestRate = BigDecimal.valueOf(doubleValue / 100);
               
               String annualInterestRateFormatted = CreditCalculator.getFormattedPercentage(annualInterestRate);
               annualInterestRateLabel.setText(annualInterestRateFormatted);
            }
         }
      );
   }
}

