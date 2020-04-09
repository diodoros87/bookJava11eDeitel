/* =====================================================================================
 *       Filename:  CalculateEulerNumber.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.38b - approximately calculate mathematical 
                                constant e number using number of fractions to adding
                                   in mathematical formula of calculate e number
                                   
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;
import factorialPackage.Factorial;

public class CalculateEulerNumber {
   
   public static void main(String[] args) {
   
      final String quitInfo = String.format("To quit enter sequence other than integers in range from %d to %d%n",
                                             Short.MIN_VALUE, Short.MAX_VALUE);
      final String prompt = String.format("Enter integer as number of fractions to adding: ");
      
      System.out.println("*** This program approximately calculate mathematical constant e number using ");
      System.out.println("number of fractions to adding in mathematical formula of calculate e number");

      short fractions;
      double result = 0;
      Factorial factorial = new Factorial();

      while (true) {
         fractions = GettingDataFromStandardInput.getShortInteger(String.format("%s %s", quitInfo, prompt));
         
         result = factorial.getEulerNumber(fractions);
         if (fractions >= 0 && false == factorial.isArithmeticOverflow()) {
            System.out.printf("*** Mathematical constant e for adding %d fractions is %.21f%n", fractions, result);
         }
         factorial.clearArithmeticOverflowError();
      }
      
   }
    
}
