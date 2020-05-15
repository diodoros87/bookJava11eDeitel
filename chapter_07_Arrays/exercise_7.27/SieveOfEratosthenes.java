/* =====================================================================================
 *       Filename:  SieveOfEratosthenes.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.27 - sieve of Eratosthenes, ancient algorithm
                                to finding prime numbers (in this test for integers
                                    from 2 to 999)
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Arrays;
 
public class SieveOfEratosthenes {
   public static final int LAST  = 999;
   
   public static void main(String[] args) {
      System.out.printf("Sieve of Eratosthenes, ancient algorithm to finding prime numbers%n");
      
      boolean[] sieve = sieveOfEratosthenes(LAST);
      printPrimeNumbers(sieve, LAST);
   } 
   
   public static boolean[] sieveOfEratosthenes(final int LAST) {
      boolean[] sieve = new boolean[LAST + 1];
      Arrays.fill(sieve, true);
      int limit = (int)Math.sqrt(LAST);
      
      for (int divisor = 2; divisor <= limit; divisor++) {
         if (true == sieve[divisor]) {
            for (int dividend = divisor * divisor; dividend < sieve.length; dividend += divisor) {
               sieve[dividend] = false;
            }
         }
      }
      
      return sieve;
   }
   
   private static void printPrimeNumbers(boolean[] sieve, final int LAST) {
      System.out.printf("Prime numbers for integers from %d to %d have printed below:%n", 2, LAST);
      
      for (int number = 2, counter = 1; number < sieve.length; number++) {
         if (true == sieve[number]) {
            System.out.printf("%3d. %3d %n", counter++, number);
         }
      }
      
      System.out.printf("Prime numbers for integers from %d to %d have printed above%n", 2, LAST);
   }
   
} 
