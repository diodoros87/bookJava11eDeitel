/* =====================================================================================
 *       Filename:  FibonacciSequence.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.29a - calculate number at index (entered by
                              User) in Fibonacci sequence 
                                 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
import java.util.ArrayList;
 
public class FibonacciSequence {

   public static final String INTEGER_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Integer.MAX_VALUE);
   public static final String MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", 0);
                                       
   private static ArrayList<Long> fibonacciNumbers = new ArrayList<Long>();
   
   public static void main(String[] args) throws Exception { 
      System.out.printf("*** This program calculate numbers in Fibonacci sequence%n");
      
      initializeFibonacci();
      
      final String PROMPT = String.format("To calculate number at index in Fibonacci sequence, enter integer as index: ");
      final String QUIT_INFO = "To quit enter sequence other than integer from below range:";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", QUIT_INFO, INTEGER_MAX_VALUE_STRING, MIN_VALUE_STRING, PROMPT);
      
      int index = GettingDataFromStandardInput.getInteger(QUIT_INFO_PROMPT);
      while (index >= 0) {
         System.out.printf("*** Number at index %d in Fibonacci sequence is %d %n%n", index, fibonacci(index));
         index = GettingDataFromStandardInput.getInteger(QUIT_INFO_PROMPT);
      }
   
   }

   
   public static long fibonacci(int index) throws Exception {
      int size = fibonacciNumbers.size();
      
      if (size > index) {
         return fibonacciNumbers.get(index);
      }
      else {
         long newLastElement;
         
         do {
            if (true == isArithmeticOverflow()) {
               throw new Exception("Arithmetic overflow");
            }
            
            newLastElement = fibonacciNumbers.get(size - 1) + fibonacciNumbers.get(size - 2);
            fibonacciNumbers.add(newLastElement);
         } while (++size <= index);
         
         return newLastElement;
      }
   }
   
   public static void initializeFibonacci() {
      fibonacciNumbers.add(0L);
      fibonacciNumbers.add(1L);
   }
   
   public static boolean isArithmeticOverflow() {
      int size = fibonacciNumbers.size();
      
      if (fibonacciNumbers.get(size - 1) > Long.MAX_VALUE - fibonacciNumbers.get(size - 2)) {
         return true;
      }
      
      return false;
   }
    
} 
