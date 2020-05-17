/* =====================================================================================
 *       Filename:  FibonacciSequence.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.29c - calculate number at index (entered by
                              User) in double-precision floating-point format
                                  Fibonacci sequence and calculate maximum number in
                                    Fibonacci sequence for this data type 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
import java.util.ArrayList;
 
public class FibonacciSequence {
                                 
   private static ArrayList<Double> fibonacciNumbers = new ArrayList<Double>();
   
   public static void main(String[] args) throws Exception { 
      System.out.printf("*** This program calculate numbers in Fibonacci sequence%n");
      System.out.printf("*** This program calculate maximum number in Fibonacci sequence for Java double-precision floating-point format %n");
      
      initializeFibonacci();
      
      final String QUIT_INFO = "To quit enter sequence other than number or enter number less than zero";
      final String QUIT_INFO_VIEW_TABLE = "To view maximum number in Fibonacci sequence for Java double type enter number less than zero";
      final String PROMPT = String.format("To calculate number at index in Fibonacci sequence, enter integer as index: ");
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %n %s", QUIT_INFO, QUIT_INFO_VIEW_TABLE, PROMPT);
      
      int index = GettingDataFromStandardInput.getInteger(QUIT_INFO_PROMPT);
      while (index >= 0) {
         System.out.printf("*** Number at index %d in Fibonacci sequence is %e %n%n", index, fibonacci(index));
         index = GettingDataFromStandardInput.getInteger(QUIT_INFO_PROMPT);
      }
      
      fibonacciDouble();
   
   }
   
   public static double fibonacci(int index) throws Exception {
      int size = fibonacciNumbers.size();
      
      if (size > index) {
         return fibonacciNumbers.get(index);
      }
      
      double newLastElement;
         
      do {
         if (true == isArithmeticOverflow()) {
            throw new Exception("Arithmetic overflow");
         }
         
         newLastElement = fibonacciNumbers.get(size - 1) + fibonacciNumbers.get(size - 2);
         fibonacciNumbers.add(newLastElement);
      } while (++size <= index);
      
      return newLastElement;
   }
   
   public static void initializeFibonacci() {
      fibonacciNumbers.add(0.0);
      fibonacciNumbers.add(1.0);
   }
   
   public static boolean isArithmeticOverflow() {
      int size = fibonacciNumbers.size();
      
      if (fibonacciNumbers.get(size - 1) > Double.MAX_VALUE - fibonacciNumbers.get(size - 2)) {
         return true;
      }
      
      return false;
   }
   
   public static void fibonacciDouble() {
      fibonacciNumbers.clear();
      initializeFibonacci();
      
      System.out.printf("%25s    Fibonacci sequence%n", "Index"); 
      System.out.printf("%25d.   %e %n", 0, fibonacciNumbers.get(0));
      System.out.printf("%25d.   %e %n", 1, fibonacciNumbers.get(1));
      
      int size = fibonacciNumbers.size();
      double newLastElement;
      
      do {
         newLastElement = fibonacciNumbers.get(size - 1) + fibonacciNumbers.get(size - 2);
         fibonacciNumbers.add(newLastElement);
         System.out.printf("%25d.   %e %n", size, fibonacciNumbers.get(size));
         size++;
      } while (false == isArithmeticOverflow());
      
      System.out.printf("*** maximum number in Fibonacci sequence for Double type is %e at index %d in Fibonacci sequence%n%n",
                     fibonacciNumbers.get(size - 1), size - 1);
      System.out.printf("*** Double.MAX_VALUE =  %e %n%n", Double.MAX_VALUE);
   }
    
} 
