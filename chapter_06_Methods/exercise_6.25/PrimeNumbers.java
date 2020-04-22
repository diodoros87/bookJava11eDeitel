/* =====================================================================================
 *       Filename:  PrimeNumbers.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.25 - test of search prime numbers for integers
                                                greater than zero
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PrimeNumbers {

   public static void main(String[] args) {
   
      final int begin = 1;   
      final int end = 10_000;
      
      System.out.printf("%n*** Result of search prime numbers from %d to %d is below%n",
                              begin, end - 1);
      
      for (int number = begin, primeNumbersCounter = 0; number < end; number++) {
         if (isPrimeNumber(number, false) == true) {
            System.out.printf("%3d. ******** Prime number is %5d%n", ++primeNumbersCounter, number);
         }
      }
      
      System.out.printf("%n*** Result of search prime numbers from %d to %d is above%n",
                              begin, end - 1);
     
   } 
   
   public static boolean isPrimeNumber(int number, boolean isPrinting) {
      if (number <= 1) {
         return false;
      }
      
      for (int divisor = 2; divisor * divisor <= number; divisor++) {
         if (0 == number % divisor) {
            if (true == isPrinting) {
               System.out.printf("%d = %d * %d %n", number, divisor, number / divisor);
            }
            
            return false;
         }
      }
      
      return true;
      
   }
   
} 
