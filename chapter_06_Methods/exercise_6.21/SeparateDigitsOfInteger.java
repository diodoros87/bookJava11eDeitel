/* =====================================================================================
 *       Filename:  SeparateDigitsOfInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.21 - separate digits of integer
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class SeparateDigitsOfInteger {

   public static final String INTEGER_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Integer.MAX_VALUE);
   public static final String INTEGER_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Integer.MIN_VALUE);
   
   public static boolean isDivisorZero (long divisor) {
      if (0 == divisor) {
         System.err.printf("%n$$$ RESULT: Number as divisor (quotient) can not be zero. Any dividend divided by zero is undefined.%n");
         return true;
      }
      
      return false;
   }
   
   public static long integerDivision(long dividend, long divisor) {
      if (true == isDivisorZero(divisor)) {
         abnormalTermination();
         return -1;  // return incorrect result
      }
      
      return dividend / divisor;
   }
   
   public static long integerDivisionRemainder(long dividend, long divisor) {
      if (true == isDivisorZero(divisor)) {
         abnormalTermination();
         return -1;  // return incorrect result
      }
      
      return dividend % divisor;
   }

   public static long integerPower(int base, short exponent) {
      if (false == exponentiationParametersValidation(base, exponent)) {
         return 0;
      }
      
      if (0 == base) {
         return 0;
      }
      else {
         long value = 1;
         
         for (short counter = 1; counter <= exponent; counter++) {
            value *= base;
         }
         
         return value;
      }
   }
   
   public static void main(String[] args) {
      System.out.printf("*** This program separate digits of integer according to integer entered by User.%n");
      
      final String QUIT_INFO = "To quit enter sequence other than integer from below range:";
      final String PROMPT = "Only first entered integer will be accepted. Enter integer: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", QUIT_INFO, INTEGER_MAX_VALUE_STRING, INTEGER_MIN_VALUE_STRING, PROMPT);
      
      do {
         printSeparateDigits(GettingDataFromStandardInput.getInteger(QUIT_INFO_PROMPT));
      } while (true);

   } 
   
   public static void printSeparateDigits(int integer) {
      long longInteger = integer;
      String spaces = "   ";
            
      if (integer < 0) {
         System.out.printf("%c%s", '-', spaces);
         
         longInteger = -longInteger;    // using long type: Math.abs(Integer.MAX_VALUE) < Math.abs(Integer.MIN_VALUE) 
      }
      
      long divisor = 10;
      short exponent = 1;
            
      while (divisor <= longInteger) {
         divisor = integerPower(10, ++exponent);
      }
      
      do {
         divisor = integerPower(10, --exponent);
         System.out.printf("%d%s", integerDivision(longInteger, divisor), spaces);
         longInteger = integerDivisionRemainder(longInteger, divisor);
      } while (exponent > 0);
      
      System.out.printf("%n");

   }
   
   public static boolean exponentiationParametersValidation(int base, short exponent) {
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
   
   private static final int abnormalTerminationCode = 1; // status code indicates abnormal termination
   
   public static void abnormalTermination() {
      System.out.println("****** Program is interrupted ");
      System.exit(abnormalTerminationCode);
   }
   
} 
