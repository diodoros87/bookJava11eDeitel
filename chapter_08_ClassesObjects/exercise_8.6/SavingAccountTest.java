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
      
      printBalance(saver1, 12);
      printBalance(saver2, 12);
      
      SavingAccount.modifyInterestRate(0.05);
      
      printBalance(saver1, 12);
      printBalance(saver2, 12);
   } 
   
   private static void printBalance(SavingAccount savingAccount, final int MONTHS) {
      final BigDecimal ANNUAL_INTEREST_RATE = SavingAccount.getAnnualInterestRate();
      final String INTEREST_RATE_STRING = SavingAccount.CURRENCY_INSTANCE.format(ANNUAL_INTEREST_RATE);
      
      BigDecimal balance = savingAccount.getSavingsBalance();
      
      System.out.printf("%nInitial balance: %15s%n", SavingAccount.CURRENCY_INSTANCE.format(balance));
      System.out.printf("%6s%25s%25s%n", "Month", "Balance", "Annual interest rate");
      
      for (int month = 1; month <= MONTHS; month++) {
         savingAccount.receiveMonthlyInterest();
         balance = savingAccount.getSavingsBalance();
         System.out.printf("%6d%25s%25s%n", month, SavingAccount.CURRENCY_INSTANCE.format(balance), INTEREST_RATE_STRING);
      }
   }
} 
