/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.18 - class of user inferface in
                                program with CreditLimitCalculator objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {

   private static final String initialStateBalancePrompt    = "Enter number of initial state balance: ";
   private static final String purchaseAmountPrompt         = "Enter number of purchase amount: ";
   private static final String depositAmountPrompt          = "Enter number of deposit amount: ";
   private static final String acceptableCreditLimitPrompt  = "Enter number of acceptable credit limit: "; 
   
   public static boolean modifyCustomerDataByUser(CreditLimitCalculator customerData) {
      boolean isProcessContinue = true;
      final   short positionAfterDot = 3;
      
      double initialStateBalance;
      double purchaseAmount;
      double depositAmount;
      double acceptableCreditLimit; 
      
      System.out.printf("%n****** To quit enter floating-point number has digit other than zero%n");
      System.out.printf(" on position number %d after dot (decimal separator).%n", positionAfterDot);
      System.out.println("****** To quit enter sequence other than number.");
      System.out.printf("%n****** Enter data of customer to display this data and check credit limit is exceeded.%n");
   
      initialStateBalance = GettingDataFromStandardInput.getDouble(initialStateBalancePrompt);
      isProcessContinue = getProcessContinuation(getDigitOnPositionAfterDot(initialStateBalance, positionAfterDot));
      if (true == isProcessContinue) {
         purchaseAmount = GettingDataFromStandardInput.getDouble(purchaseAmountPrompt);
         isProcessContinue = getProcessContinuation(getDigitOnPositionAfterDot(purchaseAmount, positionAfterDot));
         if (true == isProcessContinue) {
            depositAmount = GettingDataFromStandardInput.getDouble(depositAmountPrompt);
            isProcessContinue = getProcessContinuation(getDigitOnPositionAfterDot(depositAmount, positionAfterDot));
            if (true == isProcessContinue) {
               acceptableCreditLimit = GettingDataFromStandardInput.getDouble(acceptableCreditLimitPrompt);
               isProcessContinue = getProcessContinuation(getDigitOnPositionAfterDot(acceptableCreditLimit, positionAfterDot));
               if (true == isProcessContinue) {
                  customerData.setDataForNextCustomer(initialStateBalance, purchaseAmount,
                                    depositAmount, acceptableCreditLimit);
               }
            }  
         }
      }
      
      return isProcessContinue;
   }
      
   public static void displayCustomerData(CreditLimitCalculator customerData) {
      System.out.printf("-------Customer's data: %n"); 
      System.out.printf("account number %d %n", customerData.getAccountNumber());
      System.out.printf("initial state balance: %.2f\t purchase amount: %.2f%n", 
                        customerData.getInitialStateBalance(), customerData.getPurchaseAmount());
      System.out.printf("deposit amount: %.2f\t acceptable credit limit: %.2f%n", 
                        customerData.getDepositAmount(), customerData.getAcceptableCreditLimit());
      System.out.printf("New state balance is %.2f %n", customerData.getNewBalance());
      System.out.printf("Acceptable credit limit is %sexceeded %n", customerData.isCreditLimitExceeded() ? "" : "not ");
   }
   
   public static short getDigitOnPositionAfterDot(double number, short positionAfterDot) { 
      if (positionAfterDot > 0) {
         short counter = 0;
         short factor  = 1;
         while (counter < positionAfterDot) {
            factor *= 10;
            counter++;
         }
         
         long integer = (long)(number * factor); /* in result of casting to long remains
                                                   only number of 'factor' digits after dot (decimal separator),
                                                   rest of digits after dot were deleted */
         return (short) (integer % 10);           // return last digit of number 
      }
      else {
         System.err.printf("%nERROR class:UserInterface function:getDigitOnPosition argument:positionAfterDot %d <= 0%n",
                              positionAfterDot);
         return 0;
      }
   }
   
   public static boolean getProcessContinuation(short digit) { 
      if (digit == 0) {
         return true;
      }
      else {
         return false;
      }
   }
}
