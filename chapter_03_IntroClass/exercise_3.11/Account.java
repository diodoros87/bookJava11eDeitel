/* =====================================================================================
 *       Filename:  Account.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.11 - Account class with a:
                                double instance variable balance,
                                constructor,
                                deposit method that perform validation,
                                withdraw method that perform validation.
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Account {

   private String name;
   private double balance;
  
   public Account(String name, double balance) {
      this.name = name;

      // validate that the balance is greater than 0.0; if it's not,
      // instance variable balance keeps its default initial value of 0.0
      if (balance > 0.0) { // if the balance is valid
         this.balance = balance; // assign it to instance variable balance
      }
   }

   // method that deposits (adds) only a valid amount to the balance
   public void deposit(double depositAmount) {      
      if (depositAmount > 0.0) { // if the depositAmount is valid
         balance = balance + depositAmount; // add it to the balance 
      }
   }
   
   // method that withdraws (subtracts) only a valid amount from the balance
   public double withdraw(double withdrawAmount) {  
   
      if (withdrawAmount > 0.0) { // if the withdrawAmount is valid

         if (withdrawAmount <= balance) { // if the withdrawAmount is valid
            balance = balance - withdrawAmount; // subtract it from the balance
            return withdrawAmount;
         }
         if (withdrawAmount > balance) { // if the withdrawAmount is not valid
            System.out.printf("The payment would exceed the available balance%n%n");
         }
         
      }
      
      return 0.0;
   }

   // method returns the account balance
   public double getBalance() {
      return balance; 
   } 

   // method that sets the name
   public void setName(String name) {
      this.name = name; 
   } 

   // method that returns the name
   public String getName() {
      return name; 
   } 
}
