/* =====================================================================================
 *       Filename:  Factorial.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 4 - utility class using in 
                                programs in exercises to chapter 4 to calculate
                                   factorial
                                
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

package factorialPackage;

public class Factorial {

   private static final String factorialError = "Factorial can not be calculate for integers less than zero";
   private static final String fractionsError = "Number of fractions can not be less than zero";
   private static final String arithmeticOverflowError = "Factorial can not be proper calculate due to arithmetic overflow";
   
   private short        integer    = -1;
   private boolean      arithmeticOverflow = false;
      
   public short getInteger () {
      return integer;
   }
   
   public void setInteger (short integer) {
      if (integer < 0) {
         System.err.println(factorialError);
      }
      this.integer = integer;
   }
   
   public boolean isArithmeticOverflow () {
      return arithmeticOverflow;
   }
   
   public long getFactorial () {
      if (0 > integer) {
         System.err.println(factorialError);
         return -1;  // return incorrect value
      }
      
      long factorial = 1;
      short counter = 2;
      
      while (counter <= integer) {
         if (factorial <= Long.MAX_VALUE / counter) {  // to detect arithmetic overflow
            factorial *= counter;
            counter++;
         }
         else {
            System.err.printf(String.format("%s: %s%d%n", arithmeticOverflowError,
                              "while calculating factorial for ", counter));
            arithmeticOverflow = true;
            break;
         }
      }
      
      return factorial;
   }
   
   public double getEulerNumber (short fractions) {
      if (fractions < 0) {
         System.err.println(fractionsError);
         return -1;  // return incorrect value
      }
      double eulerNumber = 1;
      long factorial = 1;
      
      integer = 1;
      
      while (integer <= fractions && false == arithmeticOverflow) {
         factorial = getFactorial ();
         if (false == arithmeticOverflow) {
            eulerNumber += 1.0 / factorial;
            integer++;
         }
      }
      
      return eulerNumber;
   }
   
   public double getEulerNumberToPower (short fractions, short exponent) {
      if (fractions < 0) {
         System.err.println(fractionsError);
         return -1;  // return incorrect value
      }
      double result = 1;
      long factorial = 1;
      long product = 1;
      
      integer = 1;
      
      while (integer <= fractions && false == arithmeticOverflow) {
         factorial = getFactorial ();
         if (false == arithmeticOverflow) {
            if (false == detectMultiplicationOverflow(product, exponent)) {
               product *= exponent;
               result += (double)product / factorial;
               integer++;
            }
            else {
               System.err.printf(String.format("Arithmetic overflow error while calculating multiplication for %,d and %d%n",
                                    product, exponent));
               arithmeticOverflow = true;
            }
         }
      }
      
      return result;
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
   
   public void clearArithmeticOverflowError() {
      arithmeticOverflow = false;
   }
   
}
