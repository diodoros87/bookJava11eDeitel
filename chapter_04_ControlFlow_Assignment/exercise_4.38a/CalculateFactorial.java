/* =====================================================================================
 *       Filename:  CalculateFactorial.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.38a - calculate factorial for entered integer
                                by User
                  
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;
import factorialPackage.Factorial;

public class CalculateFactorial {
   
   public static void main(String[] args) {
   
      final String prompt = String.format("To quit enter sequence other than integers in range from %d to %d. Enter integer: ",
                                             Short.MIN_VALUE, Short.MAX_VALUE);
      
      System.out.printf("*** This program calculate factorial of entered integer%n");

      short integer;
      long  result = 0;
      Factorial factorial = new Factorial();

      while (true) {
         integer = GettingDataFromStandardInput.getShortInteger(prompt);
         factorial.setInteger(integer);
         if (integer >= 0) {
            result = factorial.getFactorial();
            if (false == factorial.isArithmeticOverflow()) {
               System.out.printf("*** Factorial for %d is %d%n", integer, result);
            }
            factorial.clearArithmeticOverflowError();
         }
      }
      
   }
    
}
