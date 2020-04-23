/* =====================================================================================
 *       Filename:  ReverseDigitsOfInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.26 - print integer with reverse digits than 
                                integer entered by User
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class ReverseDigitsOfInteger {

   public static final String INTEGER_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Integer.MAX_VALUE);
   public static final String INTEGER_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Integer.MIN_VALUE);
   
   public static void main(String[] args) {
      System.out.printf("*** This program print integer with reverse digits than integer entered by User.%n");
      
      final String QUIT_INFO = "To quit enter sequence other than integer from below range:";
      final String PROMPT = "Only first entered integer will be accepted. Enter integer: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", QUIT_INFO, INTEGER_MAX_VALUE_STRING, INTEGER_MIN_VALUE_STRING, PROMPT);
      
      do {
         System.out.printf("%n$$$ RESULT: Integer with reverse digits is %d%n", 
                                          getReverseDigitsInteger(GettingDataFromStandardInput.getInteger(QUIT_INFO_PROMPT)));
      } while (true);

   } 
   
   public static long getReverseDigitsInteger(int integer) {
      long longInteger = integer;
      long result = 0;
            
      if (integer < 0) {
         longInteger = -longInteger;    // using long type: Math.abs(Integer.MAX_VALUE) < Math.abs(Integer.MIN_VALUE) 
      }
      
      long factor= 1;
      
      while (factor <= longInteger) {
         factor *= 10;
      }
      
      while (longInteger > 0) {
         factor /= 10;
         result += longInteger % 10 * factor;
         longInteger /= 10;
      }
      
      if (integer < 0) {
         result = -result;
      }
      
      return result;
   }
   
} 
