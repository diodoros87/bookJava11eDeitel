/* =====================================================================================
 *       Filename:  PerfectNumbers.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.24 - test of search perfect numbers for integers
                                                greater than zero
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PerfectNumbers {

   public static void main(String[] args) {
   
      final int begin = 1;   
      final int last = 150_337;
      
      System.out.printf("%n*** Result of search perfect numbers from %d to %d is below%n",
                              begin, last);
      
      for (int number = begin; number <= last; number++) {
         if (isPerfectNumber(number) == true) {
            System.out.printf("%n******** Perfect number is %5d%n", number);
            System.out.printf("%d = %d * %d %n", number, number, 1);
            sumOfDivisorsForNumber(number, true);
         }
      }
      
      System.out.printf("%n*** Result of search perfect numbers from %d to %d is above%n",
                              begin, last);
     
   } 
   
   public static boolean isPerfectNumber(int number) {
      if (number <= 1) {
         return false;
      }
      
      if (number == sumOfDivisorsForNumber(number, false)) {
         return true;
      }
      
      return false;
      
   }
   
   public static int sumOfDivisorsForNumber(int number, boolean isPrinting) {
      if (number <= 0) {
         return 0;
      }
      
      int sum = 1;
      
      for (int divisor = 2, otherDivisor = 0; divisor * divisor <= number; divisor++) {
         if (0 == number % divisor) {
            sum += divisor;
            otherDivisor = number / divisor;
            if (divisor != otherDivisor) {
               sum += otherDivisor;
            }
            if (true == isPrinting) {
               System.out.printf("%d = %d * %d %n", number, divisor, otherDivisor);
            }
         }
      }
      
      return sum;
   }
   
} 
