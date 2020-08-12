/* =====================================================================================
 *       Filename:  CreditCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 12 - utility class using in 
                                programs in exercises to chapter 12 to calculate credit
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package creditCalculatorPackage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.Objects;

public class CreditCalculator { 
   private static final NumberFormat CURRENCY               = NumberFormat.getCurrencyInstance();
   private static final NumberFormat PERCENT                = NumberFormat.getPercentInstance();
   private static final RoundingMode CURRENCY_ROUNDING_MODE = RoundingMode.HALF_EVEN;
   private static final RoundingMode PERCENT_ROUNDING_MODE  = RoundingMode.HALF_UP;

   private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;
   
   private static final BigDecimal MONTHS_IN_YEAR  = BigDecimal.valueOf(12);
   private static final BigDecimal HUNDRED         = BigDecimal.valueOf(100);
   private static final BigDecimal MAX_CREDIT_RATE = BigDecimal.valueOf(40);
   private static final BigDecimal MIN_CREDIT_RATE = BigDecimal.ZERO;
   
   private BigDecimal repaymentYears   = BigDecimal.TEN; 
   private BigDecimal loanAmount       = BigDecimal.ZERO; 
   private BigDecimal annualIntestRate = BigDecimal.TEN; 
   
   public CreditCalculator() { }
   
   public CreditCalculator(BigDecimal loanAmount, BigDecimal annualIntestRate, BigDecimal repaymentYears) {
      Objects.requireNonNull(loanAmount);
      Objects.requireNonNull(annualIntestRate);
      Objects.requireNonNull(repaymentYears);
      
      validateAmount(loanAmount);
      validateAnnualInterestRate(annualIntestRate);
      validateRepaymentYears(repaymentYears);
      
      setValues(loanAmount, annualIntestRate, repaymentYears);
   }
   
   public CreditCalculator(BigDecimal ownContribution, BigDecimal price, BigDecimal annualIntestRate,
                                 BigDecimal repaymentYears) {
      Objects.requireNonNull(annualIntestRate);
      Objects.requireNonNull(repaymentYears);
      Objects.requireNonNull(ownContribution);
      Objects.requireNonNull(price);
      
      validateAnnualInterestRate(annualIntestRate);
      validateRepaymentYears(repaymentYears);
      validateAmount(ownContribution);
      validateAmount(price);
      
      BigDecimal creditAmount = price.subtract(ownContribution, MATH_CONTEXT);
      creditAmount            = creditAmount.max(BigDecimal.ZERO);
      
      setValues(creditAmount, annualIntestRate, repaymentYears);
   }
   
   private final void setValues(BigDecimal loanAmount, BigDecimal annualIntestRate, BigDecimal repaymentYears) {
      this.loanAmount       = loanAmount;
      this.annualIntestRate = annualIntestRate;
      this.repaymentYears   = repaymentYears;
      
      setRoundingMode();
   }
   
   public BigDecimal getLoanAmount() {
      return loanAmount;
   }
   
   public void setLoanAmount(BigDecimal ownContribution, BigDecimal price) {
      Objects.requireNonNull(ownContribution);
      Objects.requireNonNull(price);
      
      validateAmount(ownContribution);
      validateAmount(price);
      
      BigDecimal creditAmount = price.subtract(ownContribution, MATH_CONTEXT);
      this.loanAmount         = creditAmount.max(BigDecimal.ZERO);
   }
   
   public void setLoanAmount(BigDecimal loanAmount) {
      Objects.requireNonNull(loanAmount);
      validateAmount(loanAmount);
      this.loanAmount = loanAmount;
   }
   
   public BigDecimal getAnnualIntestRate() {
      return annualIntestRate;
   }
   
   public void setAnnualInterestRate(BigDecimal annualIntestRate) {
      Objects.requireNonNull(annualIntestRate);
      validateAnnualInterestRate(annualIntestRate);
      this.annualIntestRate = annualIntestRate;
   }
   
   public static BigDecimal getRepaymentYears(BigDecimal repaymentMonths) {
      Objects.requireNonNull(repaymentMonths);
      
      return repaymentMonths.divide(MONTHS_IN_YEAR, MATH_CONTEXT);
   }
   
   public BigDecimal getRepaymentMonths() {
      return repaymentYears.multiply(MONTHS_IN_YEAR, MATH_CONTEXT);
   }
   
   public BigDecimal getRepaymentYears() {
      return repaymentYears;
   }
   
   public void setRepaymentYears(BigDecimal repaymentYears) {
      Objects.requireNonNull(repaymentYears);
      validateRepaymentYears(repaymentYears);
      this.repaymentYears = repaymentYears;
   }
   
   private final void setRoundingMode() {
      setRoundingMode(CURRENCY, CURRENCY_ROUNDING_MODE);
      setRoundingMode(PERCENT, PERCENT_ROUNDING_MODE);
   }
   
   public static void setRoundingMode(NumberFormat numberFormat, RoundingMode roundingMode) {
      Objects.requireNonNull(numberFormat);
      Objects.requireNonNull(roundingMode);
      
      numberFormat.setRoundingMode(roundingMode);
   }
   
   public static String getFormattedCurrency(BigDecimal amount) 
                           throws NullPointerException, IllegalArgumentException {
      Objects.requireNonNull(amount);
      
      String     formattedAmount = CURRENCY.format(amount);
      
      return formattedAmount;
   }
   
   public static String getFormattedPercentage(BigDecimal percentage) 
                           throws NullPointerException, IllegalArgumentException {
      Objects.requireNonNull(percentage);
      
      String     formattedPercentage = PERCENT.format(percentage);
      
      return formattedPercentage;
   }
   
   public BigDecimal calculateMonthlyCreditPayment() {
      BigDecimal numberOfPayments = this.repaymentYears.multiply(MONTHS_IN_YEAR, MATH_CONTEXT);
      
      if (0 == loanAmount.compareTo(BigDecimal.ZERO)) {
         return BigDecimal.ZERO;
      }
      
      if (0 == annualIntestRate.compareTo(BigDecimal.ZERO)) {
         return loanAmount.divide(numberOfPayments, MATH_CONTEXT);
      }
      else {
         return calculateMonthlyCreditPayment(numberOfPayments);
      }
   }
   
   public BigDecimal calculateMonthlyCreditPayment(BigDecimal numberOfPayments) {
      Objects.requireNonNull(numberOfPayments);
      
      BigDecimal monthlyIntestRate = annualIntestRate.divide(MONTHS_IN_YEAR, MATH_CONTEXT);
      monthlyIntestRate            = monthlyIntestRate.divide(HUNDRED, MATH_CONTEXT);
      
      BigDecimal component = calculateRepeatedFormulaComponent(numberOfPayments, monthlyIntestRate);
      
      BigDecimal numerator = loanAmount.multiply(monthlyIntestRate, MATH_CONTEXT);
      numerator            = numerator.multiply(component, MATH_CONTEXT);
      
      BigDecimal denominator = component.subtract(BigDecimal.ONE, MATH_CONTEXT);
      
      BigDecimal result = numerator.divide(denominator, MATH_CONTEXT);
      
      return result;
   }
   
   private BigDecimal calculateRepeatedFormulaComponent(BigDecimal numberOfPayments, BigDecimal interestRate) {
      BigDecimal component = interestRate.add(BigDecimal.ONE, MATH_CONTEXT);
      int exponent         = numberOfPayments.intValue();
      
      try {
         component         = component.pow(exponent, MATH_CONTEXT);
      }
      catch (ArithmeticException exception) {
         throw exception;
      }
      
      return component;
   }
   
   public static void validateAmount(BigDecimal amount) {
      Objects.requireNonNull(amount);
      if (-1 == amount.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("amount must be >= 0");
      }
   }
   
   public static void validateRepaymentYears(BigDecimal years) {
      Objects.requireNonNull(years);
      if (+1 != years.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("repayment years must be > 0");
      }
   }
   
   public void validateAnnualInterestRate(BigDecimal ratePercent) {
      Objects.requireNonNull(ratePercent);
      
      if (-1 == ratePercent.compareTo(MIN_CREDIT_RATE)) {
         throw new IllegalArgumentException(
               String.format("rate must be >= %d", MIN_CREDIT_RATE.intValue()));
      }
      
      if (+1 == ratePercent.compareTo(MAX_CREDIT_RATE)) {
         throw new IllegalArgumentException(
               String.format("rate must be <= %d", MAX_CREDIT_RATE.intValue()));
      }
   }

}

