/* =====================================================================================
 *       Filename:  EvenOrOddInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.17 - even or odd integer
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class EvenOrOddInteger {

   public static final String LONG_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Long.MAX_VALUE);
   public static final String LONG_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Long.MIN_VALUE);
   
   public static void main(String[] args) {
      System.out.printf("*** This program print that integer entered by User is even or odd.%n");
      final String QUIT_INFO = "To quit enter sequence other than integer from below range:";
      
      do {
         printResult(getLongIntegerFromUser(QUIT_INFO));
      } while (true);

   } 
   
   public static void printResult(long integer) {
      String parity = "odd";
             
      if (true == isEven(integer)) {
         parity = "even";
      }
             
      System.out.printf("%n %d is %s number. %n", integer, parity);  
   }
   
   public static long getLongIntegerFromUser(String quitInfo) {
      final String PROMPT = "Enter integer: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", quitInfo, LONG_MAX_VALUE_STRING, LONG_MIN_VALUE_STRING, PROMPT);
      
      return GettingDataFromStandardInput.getLongInteger(QUIT_INFO_PROMPT);
   }
   
   public static boolean isEven(long integer) {
      return (0 == integer % 2) ?  true : false;
   }
   
} 
