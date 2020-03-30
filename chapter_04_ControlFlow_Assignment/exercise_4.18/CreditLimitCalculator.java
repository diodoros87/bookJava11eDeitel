/* =====================================================================================
 *       Filename:  CreditLimitCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.18 - class of calculate shop's customer 
                                credit limit
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class CreditLimitCalculator {

   private static long accountNumberCounter = Long.MAX_VALUE;
   
   private long   accountNumber;
   private double initialStateBalance;
   private double purchaseAmount;
   private double depositAmount;
   private double acceptableCreditLimit;
   
   public CreditLimitCalculator() {
      accountNumber         = accountNumberCounter--;
   }
   
   public void resetDataForNextCustomer() {
      accountNumber         = accountNumberCounter--;
      initialStateBalance   = 0;
      purchaseAmount        = 0;
      depositAmount         = 0;
      acceptableCreditLimit = 0;
   }
   
   public void setDataForNextCustomer(double initialStateBalance, double purchaseAmount,
                                    double depositAmount, double acceptableCreditLimit) {
      
      setInitialStateBalance(initialStateBalance);
      setPurchaseAmount(purchaseAmount);
      setDepositAmount(depositAmount);
      setAcceptableCreditLimit(acceptableCreditLimit);
   }
   
   public long getAccountNumber() {
      return accountNumber;
   }
   
   public double getNewBalance() {
      return initialStateBalance + depositAmount - purchaseAmount;
   }
   
   public boolean isCreditLimitExceeded() {
      if (getNewBalance() + acceptableCreditLimit < 0) {
         return true;
      }
      else {
         return false;
      }
   }

   public void setInitialStateBalance(double initialStateBalance) {
      this.initialStateBalance = initialStateBalance;
   } 

   public double getInitialStateBalance() {
      return initialStateBalance; 
   } 
   
   public void setPurchaseAmount(double purchaseAmount) {
      if (purchaseAmount >= 0) {
         this.purchaseAmount = purchaseAmount;
      }
   } 

   public double getPurchaseAmount() {
      return purchaseAmount; 
   } 
   
   public void setDepositAmount(double depositAmount) {
      if (depositAmount > 0) {
         this.depositAmount = depositAmount;
      }
   } 

   public double getDepositAmount() {
      return depositAmount; 
   } 
   
   public void setAcceptableCreditLimit(double acceptableCreditLimit) {
      if (acceptableCreditLimit >= 0) {
         this.acceptableCreditLimit = acceptableCreditLimit;
      }
   } 

   public double getAcceptableCreditLimit() {
      return acceptableCreditLimit; 
   } 
   
}
