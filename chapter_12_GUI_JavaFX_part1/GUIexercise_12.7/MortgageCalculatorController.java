/* =====================================================================================
 *       Filename:  MortgageCalculatorController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 12.7 - Controller that handles 
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
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MortgageCalculatorController { 
   // formatters for CURRENCY and percentages
   private static final NumberFormat CURRENCY               = NumberFormat.getCurrencyInstance();
   private static final RoundingMode CURRENCY_ROUNDING_MODE = RoundingMode.HALF_EVEN;
   private static final BigDecimal MONTHS_IN_YEAR  = new BigDecimal(12);
   private static final BigDecimal HUNDRED         = new BigDecimal(100);
   private static final BigDecimal MAX_CREDIT_RATE = HUNDRED;
   
   private BigDecimal repaymentYears = BigDecimal.TEN; 
   
   // GUI controls defined in FXML and used by the controller's code
   @FXML
   private Slider repaymentYearsSlider;

   @FXML
   private TextField ownContributionTextField;

   @FXML
   private TextField creditIntestRateTextField;

   @FXML
   private TextField creditAmountTextField;

   @FXML
   private TextField monthlyCreditPaymentTextField;

   @FXML
   private TextField apartmentPriceTextField;
   
   @FXML
   private void calculateButtonPressed(ActionEvent event) {
      List<String> creditAmountsList = null;
      
      try {
         creditAmountsList = getCreditAmountsList();
      }
      catch (RuntimeException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
         creditAmountsList = List.of("", "");
      }
      finally {
         creditAmountTextField.setText(creditAmountsList.get(0));
         monthlyCreditPaymentTextField.setText(creditAmountsList.get(1));
      }
   }
   
   private List<String> getCreditAmountsList() {
      BigDecimal apartmentPrice   = getCorrectNumberFromUser(this.apartmentPriceTextField);
      BigDecimal ownContribution  = getCorrectNumberFromUser(this.ownContributionTextField);
      BigDecimal creditIntestRate = getCorrectNumberFromUser(this.creditIntestRateTextField);

      BigDecimal creditAmount         = calculateCreditAmount(apartmentPrice, ownContribution);
      BigDecimal monthlyCreditPayment = calculateMonthlyCreditPayment(creditAmount, creditIntestRate);
      System.err.printf("%nmonthlyCreditPayment = %s%n", monthlyCreditPayment);
      List<String> creditAmountsList = getCreditAmountsList(creditAmount, monthlyCreditPayment);
      
      return creditAmountsList;
   }
   
   private ArrayList<String> getCreditAmountsList(BigDecimal creditAmount, BigDecimal monthlyCreditPayment) {
      ArrayList<String> creditAmountsList = new ArrayList<>();

      String creditAmountString         = CURRENCY.format(creditAmount);
      String monthlyCreditPaymentString = CURRENCY.format(monthlyCreditPayment);
      
      creditAmountsList.add(creditAmountString);
      creditAmountsList.add(monthlyCreditPaymentString);
      
      return creditAmountsList;
   }
   
   private BigDecimal calculateCreditAmount(BigDecimal apartmentPrice, BigDecimal ownContribution) {
      BigDecimal creditAmount = apartmentPrice.subtract(ownContribution);
      creditAmount            = creditAmount.max(BigDecimal.ZERO);
      
      return creditAmount;
   }
   
   private BigDecimal calculateMonthlyCreditPayment(BigDecimal creditAmount, BigDecimal creditIntestRate) {
      BigDecimal numberOfPayments = this.repaymentYears.multiply(MONTHS_IN_YEAR);
      
      if (0 == creditAmount.compareTo(BigDecimal.ZERO)) {
         return BigDecimal.ZERO;
      }
      
      if (0 == creditIntestRate.compareTo(BigDecimal.ZERO)) {
         return creditAmount.divide(numberOfPayments, MathContext.DECIMAL128);
      }
      else {
         return calculateMonthlyCreditPayment(creditAmount, creditIntestRate, numberOfPayments);
      }
   }
   
   private BigDecimal calculateMonthlyCreditPayment(BigDecimal creditAmount, BigDecimal creditIntestRate,
                                                   BigDecimal numberOfPayments) {
      BigDecimal monthlyCreditIntestRate = creditIntestRate.divide(MONTHS_IN_YEAR, MathContext.DECIMAL128);
      //System.err.printf("%n monthlyCreditIntestRate = %s%n", monthlyCreditIntestRate);
      monthlyCreditIntestRate = monthlyCreditIntestRate.divide(HUNDRED, MathContext.DECIMAL128);
      
      //System.err.printf("%n monthlyCreditIntestRate = %s%n", monthlyCreditIntestRate);
      
      BigDecimal component = calculateRepeatedFormulaComponent(numberOfPayments, monthlyCreditIntestRate);
      
      BigDecimal numerator = creditAmount.multiply(monthlyCreditIntestRate, MathContext.DECIMAL128);
      numerator            = numerator.multiply(component, MathContext.DECIMAL128);
      
      BigDecimal denominator = component.subtract(BigDecimal.ONE, MathContext.DECIMAL128);
      
      BigDecimal result = numerator.divide(denominator, MathContext.DECIMAL128);
      
      return result;
   }
   
   private BigDecimal calculateRepeatedFormulaComponent(BigDecimal numberOfPayments, BigDecimal creditIntestRate) {
      BigDecimal component = creditIntestRate.add(BigDecimal.ONE, MathContext.DECIMAL128);
      int exponent         = numberOfPayments.intValue();
      
      try {
         component         = component.pow(exponent, MathContext.DECIMAL128);
      }
      catch (ArithmeticException exception) {
         throw exception;
      }
      
      return component;
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
      if (this.apartmentPriceTextField  == textField 
       || this.ownContributionTextField == textField) {
          
         validateAmount(number);
      }
      else if (this.creditIntestRateTextField == textField) {
         validateCreditIntestRate(number);
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
   
   private void validateAmount(BigDecimal amount) {
      if (-1 == amount.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("amount must be >= 0");
      }
   }
   
   private void validateCreditIntestRate(BigDecimal ratePercent) {
      if (-1 == ratePercent.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("rate must be >= 0");
      }
      
      if (+1 == ratePercent.compareTo(MAX_CREDIT_RATE)) {
         throw new IllegalArgumentException(
               String.format("rate must be <= %d", MAX_CREDIT_RATE.intValue()));
      }
   }

   // called by FXMLLoader to initialize the controller
   public void initialize() {
      CURRENCY.setRoundingMode(CURRENCY_ROUNDING_MODE);
      
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

