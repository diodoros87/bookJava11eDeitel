/* =====================================================================================
 *       Filename:  CalculateFactorial.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.13 - calculate factorial for entered integer
                                by User
                  
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;

public class CalculateFactorial {
   
   public static void main(String[] args) {
      Factorial factorial = new Factorial();
      factorial.printFactorialResults((short)-2, (short)26);
   
      final String prompt = String.format("To quit enter sequence other than integers in range from %d to %d. Enter integer: ",
                                             Short.MIN_VALUE, Short.MAX_VALUE);
      
      System.out.printf("*** This program calculate factorial of entered integer%n");

      short integer;

      while (true) {
         integer = GettingDataFromStandardInput.getShortInteger(prompt);
         factorial.setInteger(integer);
         factorial.printFactorialResult();
      }
      
      
   }
    
}
