/* =====================================================================================
 *       Filename:  AccountTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.19 - test of Account class 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;

public class AccountTest {

   public static void main(String[] args) {
      Account accountMarcus = new Account("Marcus Aurelius", "50.000");
      Account accountLucius = new Account("Lucius Seneca", "+7.53"); 

      displayManyAccounts(accountMarcus, accountLucius);
      
      depositAccount(accountMarcus);
      displayManyAccounts(accountMarcus, accountLucius);

      depositAccount(accountLucius);
      displayManyAccounts(accountMarcus, accountLucius);
         
      withdrawAccount(accountMarcus);
      displayManyAccounts(accountMarcus, accountLucius);

      withdrawAccount(accountLucius);
      displayManyAccounts(accountMarcus, accountLucius);
      
      testIncorrectSettings(accountLucius);
   } 
   
   public static void depositAccount(Account account) {
      ExceptionChecker.checkNullPointerException(account, " account " + account);
      
      String name = account.getName();
      String depositAmount = GettingDataFromStandardInput.getString(String.format(
                           "Enter deposit amount for %s's account: ", name));
                           
      System.out.printf("%n try to add %s to %s's account balance%n%n", depositAmount, name);
      account.deposit(depositAmount); 
   }
   
   public static void withdrawAccount(Account account) {
      ExceptionChecker.checkNullPointerException(account, " account " + account);
      
      String name = account.getName();
      String withdrawAmount = GettingDataFromStandardInput.getString(String.format(
                           "Enter withdraw amount from %s's account: : ", name));
                           
      System.out.printf("%n try to subtract %s from %s's account balance%n%n", withdrawAmount, name);
      
      WithdrawStatus withdrawStatus = account.withdraw(withdrawAmount); 
      if (WithdrawStatus.EXCEEDED == withdrawStatus) {
         System.out.println(Account.PAYMENT_EXCEEDED);
      }
   }
   
   public static void displayManyAccounts(Account... accountsToDisplay) {
      for (Account account : accountsToDisplay) {
         displayAccount(account);
      }
   }
   
   public static void displayAccount(Account accountToDisplay) {
      ExceptionChecker.checkNullPointerException(accountToDisplay, " account " + accountToDisplay);
      
      System.out.printf(" %s %n", accountToDisplay.toString()); 
   }
   
   private static void testIncorrectSettings(Account account) {
      try {
         account.setName("  "); 
         assert(false) : "Name set to blank";
      }
      catch (IllegalArgumentException exception) {
         System.err.printf("%nException while set name: %s%n", exception.getMessage());
      }
      
      try {
         account.deposit("7ABC.53"); 
         assert(false) : "deposit: 7ABC.53";
      }
      catch (NumberFormatException exception) {
         System.err.printf("%nException while deposit: %s%n", exception.getMessage());
      }
      
      try {
         account.deposit("-7.53"); 
         assert(false) : "deposit: -7.53";
      }
      catch (IllegalArgumentException exception) {
         System.err.printf("%nException while deposit: %s%n", exception.getMessage());
      }
   }
   
} 

class ExceptionChecker {
   static void checkNullPointerException(Object object, String message) {
      if (null == object) { 
         throw new NullPointerException(message);
      }
   }
}
