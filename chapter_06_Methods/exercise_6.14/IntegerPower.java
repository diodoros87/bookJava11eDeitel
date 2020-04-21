/* =====================================================================================
 *       Filename:  IntegerPower.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.14 - exponentiation of integer
                                                tested by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class IntegerPower {

   public static final String INTEGER_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Integer.MAX_VALUE);
   public static final String INTEGER_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Integer.MIN_VALUE);
   public static final String SHORT_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Short.MAX_VALUE);
   public static final String SHORT_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Short.MIN_VALUE);

   private boolean      arithmeticOverflow = false;

   public long integerPower(int base, short exponent) {
      if (false == parametersValidation(base, exponent)) {
         return 0;
      }
      
      if (0 == base) {
         return 0;
      }
      else {
         long value = 1;
         
         for (short counter = 1; counter <= exponent && false == this.arithmeticOverflow; counter++) {
            if (false == detectMultiplicationOverflow(value, base)) {
               value *= base;
            }
            else {
               System.err.printf(String.format("****** ERROR: Arithmetic overflow error while calculating multiplication for %,d and %d%n",
                                    value, base));
               this.arithmeticOverflow = true;
            }
         }
         
         return value;
      }
   }
   
   public static boolean detectMultiplicationOverflow(long firstNumber, long secondNumber) {
      // Multiplication -- for multiplication identity (neutral) element is 1
      
      // absolute value of Long.MIN_VALUE is greater than absolute value of Long.MAX_VALUE
      if (Long.MIN_VALUE == firstNumber) {
         if (-1 == secondNumber)
            return true;
      }
      if (Long.MIN_VALUE == secondNumber) {
         if (-1 == firstNumber)
            return true;
      }
      
      // to detect integer overflow in multiplication:
      // absolute value of ((Long.MAX_VALUE or Long.MIN_VALUE) divided by secondNumber) must be less than
      // absolute value of firstNumber
      if (firstNumber > 1) 
         if (secondNumber > 1) 
            if (Long.MAX_VALUE / secondNumber < firstNumber)
               return true;
               
      if (firstNumber < -1) 
         if (secondNumber < -1) 
            if (Long.MAX_VALUE / secondNumber > firstNumber)
               return true;
               
      if (firstNumber < -1)  
         if (secondNumber > 1)
            if (Long.MIN_VALUE / secondNumber > firstNumber)
               return true;
               
      if (firstNumber > 1)
         if (secondNumber < -1)
            if (Long.MIN_VALUE / secondNumber < firstNumber)
               return true;
               
      return false;
   }
   
   public static void main(String[] args) {
      System.out.printf("*** This program calculate integer to power according to integers entered by User.%n");
      
      int base;
      short exponent;
      final String QUIT_INFO = "To quit enter sequence other than integer from below range:";
      IntegerPower integerPower = new IntegerPower();
      
      do {
         base     = getBaseFromUser(QUIT_INFO);
         exponent = getExponentFromUser(QUIT_INFO);
         integerPower.printExponentiation(base, exponent);
         integerPower.arithmeticOverflow = false;
      } while (true);

   } 
   
   public static int getBaseFromUser(String quitInfo) {
      final String PROMPT = "Enter base as integer: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", quitInfo, INTEGER_MAX_VALUE_STRING, INTEGER_MIN_VALUE_STRING, PROMPT);
      
      return GettingDataFromStandardInput.getInteger(QUIT_INFO_PROMPT);
   }
   
   public static short getExponentFromUser(String quitInfo) {
      final String PROMPT = "Enter exponent as integer: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", quitInfo, SHORT_MAX_VALUE_STRING, SHORT_MIN_VALUE_STRING, PROMPT);
      
      return GettingDataFromStandardInput.getShortInteger(QUIT_INFO_PROMPT);
   }
   
   public void printExponentiation(int base, short exponent) {
      if (true == parametersValidation(base, exponent)) {
         long result = integerPower(base, exponent);
         
         if (false == this.arithmeticOverflow) {
            System.out.printf("****** RESULT: %,d raised to %d power is %,d%n%n", base, exponent, result);
         }
      }
   }
   
   public static boolean parametersValidation(int base, short exponent) {
      if (0 > exponent) {
         System.err.println("****** ERROR: The case is more complicated. Exponent must be integer not less than zero");
         return false;
      }
      else if (0 == base && 0 == exponent) {
            System.err.println("****** ERROR: The case is more complicated");
            return false;
      }
      
      return true;
   }
   
} 
