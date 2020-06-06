/* =====================================================================================
 *       Filename:  SavingAccountTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.6 - test of SavingAccount class 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.math.BigDecimal;

public class SavingAccountTest {

   public static void main(String[] args) {
      BigDecimal    annualInterestRate  = BigDecimal.valueOf(0.04);
      SavingAccount.modifyInterestRate(annualInterestRate);
      SavingAccount saver1 = new SavingAccount(2_000); 
      SavingAccount saver2 = new SavingAccount(3_000);
      
      printYearlyBalance(saver1);
      printYearlyBalance(saver2);
      
      printMonthlyBalance(saver1, 12);
      printMonthlyBalance(saver2, 12);
      
      SavingAccount.modifyInterestRate(0.05);
      
      printMonthlyBalance(saver1, 12);
      printMonthlyBalance(saver2, 12);
      
      testIncorrectSettings(saver1);
   } 
   
   private static void printMonthlyBalance(SavingAccount savingAccount, final int MONTHS) {
      final BigDecimal ANNUAL_INTEREST_RATE = SavingAccount.getAnnualInterestRate().multiply(BigDecimal.valueOf(100));
      
      System.out.printf("%nInitial balance: %15s%n", savingAccount);
      System.out.printf("%6s%25s%25s%n", "Month", "Balance", "Annual interest rate");
      
      for (int month = 1; month <= MONTHS; month++) {
         savingAccount.receiveMonthlyInterest();
         System.out.printf("%6d%25s%25.2f%%%n", month, savingAccount, ANNUAL_INTEREST_RATE);
      }
   }
   
   private static void printYearlyBalance(SavingAccount savingAccount) {
      final BigDecimal ANNUAL_INTEREST_RATE = SavingAccount.getAnnualInterestRate().multiply(BigDecimal.valueOf(100));
      BigDecimal yearlyInterest = savingAccount.calculateYearlyInterest();
      
      System.out.printf("%25s%25s%25s%n", "Initial balance", "After year Balance", "Annual interest rate");
      System.out.printf("%25s%25s%25.2f%%%n", savingAccount, SavingAccount.CURRENCY_INSTANCE.format(yearlyInterest),
                                       ANNUAL_INTEREST_RATE);
   }
   
   private static void testIncorrectSettings(SavingAccount savingAccount) {
      try {
         SavingAccount.modifyInterestRate(-0.05);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while modify interest rate: %s%n",
            exception.getMessage());
      }
      
      try {
         SavingAccount.modifyInterestRate(1.05);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while modify interest rate: %s%n",
            exception.getMessage());
      }
      
      try {
         savingAccount.setSavingsBalance(BigDecimal.ZERO);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while set savings balance: %s%n",
            exception.getMessage());
      }
      
      try {
         savingAccount.setSavingsBalance(-56);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while set savings balance: %s%n",
            exception.getMessage());
      }
   }
} 
