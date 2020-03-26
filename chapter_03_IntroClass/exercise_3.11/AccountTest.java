/* =====================================================================================
 *       Filename:  AccountTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.11 - Inputting and outputting 
                                floating-point numbers with Account objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class AccountTest {

   public static void main(String[] args) {
   
      Account accountMarcus = new Account("Marcus Aurelius", 50.00);
      Account accountLucius = new Account("Lucius Seneca", -7.53); 

      // display initial balance of each object
      System.out.printf("%s balance: $%.2f%n",
         accountMarcus.getName(), accountMarcus.getBalance()); 
      System.out.printf("%s balance: $%.2f%n%n",
         accountLucius.getName(), accountLucius.getBalance()); 

      double depositAmount = GettingDataFromStandardInput.getDouble("Enter deposit amount for Marcus's account: ");
      System.out.printf("%ntry to add %.2f to Marcus's account balance%n%n", 
         depositAmount);
      accountMarcus.deposit(depositAmount); // try to add to accountMarcus balance

      // display balances
      System.out.printf("%s balance: $%.2f%n",
         accountMarcus.getName(), accountMarcus.getBalance()); 
      System.out.printf("%s balance: $%.2f%n%n",
         accountLucius.getName(), accountLucius.getBalance()); 

      depositAmount = GettingDataFromStandardInput.getDouble("Enter deposit amount for Lucius's account: ");
      System.out.printf("%ntry to add %.2f to Lucius's account balance%n%n", 
         depositAmount);
      accountLucius.deposit(depositAmount); // try to add to accountLucius balance

      // display balances
      System.out.printf("%s balance: $%.2f%n", 
         accountMarcus.getName(), accountMarcus.getBalance()); 
      System.out.printf("%s balance: $%.2f%n%n",
         accountLucius.getName(), accountLucius.getBalance()); 
         
      double withdrawAmount = GettingDataFromStandardInput.getDouble("Enter withdraw amount for Marcus's account: ");
      System.out.printf("%ntry to subtract %.2f from Marcus's account balance%n%n", 
         withdrawAmount);
      accountMarcus.withdraw(withdrawAmount); // try to subtract from accountMarcus balance

      // display balances
      System.out.printf("%s balance: $%.2f%n",
         accountMarcus.getName(), accountMarcus.getBalance()); 
      System.out.printf("%s balance: $%.2f%n%n",
         accountLucius.getName(), accountLucius.getBalance()); 

      withdrawAmount = GettingDataFromStandardInput.getDouble("Enter withdraw amount for Lucius's account: ");
      System.out.printf("%ntry to subtract %.2f to Lucius's account balance%n%n", 
         withdrawAmount);
      accountLucius.withdraw(withdrawAmount); // try to subtract from accountLucius balance

      // display balances
      System.out.printf("%s balance: $%.2f%n", 
         accountMarcus.getName(), accountMarcus.getBalance()); 
      System.out.printf("%s balance: $%.2f%n%n",
         accountLucius.getName(), accountLucius.getBalance()); 
   } 
   
} 
