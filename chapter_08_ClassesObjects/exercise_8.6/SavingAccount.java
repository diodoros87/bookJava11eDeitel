/* =====================================================================================
 *       Filename:  SavingAccount.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.6 - saving account class 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class SavingAccount {
   public static final BigDecimal    MONTHS_PER_YEAR  = BigDecimal.valueOf(12);
   private static final int          SCALE            = 2;
   
   private static final BigDecimal   MAX_INTEREST_RATE = BigDecimal.valueOf(0.1);
   private static final BigDecimal   MIN_INTEREST_RATE = BigDecimal.valueOf(0.01);
   private static BigDecimal         annualInterestRate;
   
   public static final NumberFormat  CURRENCY_INSTANCE = NumberFormat.getCurrencyInstance();
   
   public static final String INVALID_INTEREST_RATE = String.format("Interest rate must be from %25s to %25s", 
                                 CURRENCY_INSTANCE.format(MIN_INTEREST_RATE), CURRENCY_INSTANCE.format(MAX_INTEREST_RATE));
   
   private BigDecimal savingsBalance; 
   
   public static void modifyInterestRate(BigDecimal rate) {
   
      if (-1 == rate.compareTo(MIN_INTEREST_RATE) || 1 == rate.compareTo(MAX_INTEREST_RATE)) {
         throw new IllegalArgumentException(INVALID_INTEREST_RATE);
      }
      
      annualInterestRate = rate;
   }
   
   public static void modifyInterestRate(double rate) {
      modifyInterestRate(BigDecimal.valueOf(rate));
   }
   
   public static BigDecimal getAnnualInterestRate() {
      return annualInterestRate;
   }
   
   public SavingAccount(double savingsBalance) {
      this(BigDecimal.valueOf(savingsBalance));
   } 
   
   public SavingAccount(BigDecimal savingsBalance) {
      if (BigDecimal.ZERO == savingsBalance.max(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("Saving balance must be more than zero");
      }
      
      this.savingsBalance = savingsBalance;
   } 
   
   public void receiveMonthlyInterest() {
      BigDecimal monthlyInterest = calculateMonthlyInterest();
      
      setSavingsBalance(monthlyInterest);
   }
   
   public BigDecimal calculateMonthlyInterest() {
      BigDecimal monthlyInterestGrowth = calculateMonthlyInterestGrowth();
      BigDecimal monthlyInterest = savingsBalance.add(monthlyInterestGrowth);
   
      return monthlyInterest.setScale(SCALE, RoundingMode.HALF_EVEN);
   }
   
   public BigDecimal calculateMonthlyInterestGrowth() {
      BigDecimal yearlyInterestGrowth = calculateYearlyInterestGrowth();
      BigDecimal monthlyInterestGrowth = yearlyInterestGrowth.divide(MONTHS_PER_YEAR, SCALE, RoundingMode.HALF_EVEN);
      
      return monthlyInterestGrowth.setScale(SCALE, RoundingMode.HALF_EVEN);
   }
   
   public BigDecimal calculateYearlyInterest() {
      BigDecimal yearlyInterestGrowth = calculateYearlyInterestGrowth();
      BigDecimal yearlyInterest = savingsBalance.add(yearlyInterestGrowth);
      
      return yearlyInterest.setScale(SCALE, RoundingMode.HALF_EVEN);
   }
   
   public BigDecimal calculateYearlyInterestGrowth() {
      BigDecimal yearlyInterestGrowth = savingsBalance.multiply(annualInterestRate);
      
      return yearlyInterestGrowth.setScale(SCALE, RoundingMode.HALF_EVEN);
   }
   
   public BigDecimal getSavingsBalance() {
      return savingsBalance.setScale(SCALE, RoundingMode.HALF_EVEN);
   }
   
   public void setSavingsBalance(BigDecimal savingsBalance) {
      if (BigDecimal.ZERO == savingsBalance.max(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("Saving balance must be more than zero");
      }
      
      this.savingsBalance = savingsBalance;
   }
   
   public String toString() {
      return String.format("%20s", CURRENCY_INSTANCE.format(savingsBalance));
   } 
} 
