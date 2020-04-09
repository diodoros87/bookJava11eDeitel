/* =====================================================================================
 *       Filename:  MultiplyingEbyEManyTimes.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.38c - approximately calculate of multiplying 
                                many times mathematical constant e number
                                using number of fractions to adding
                                   in mathematical formula of calculate 
                                    exponentiation of e number
                                   
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;
import factorialPackage.Factorial;

public class MultiplyingEbyEManyTimes {
   
   public static void main(String[] args) {
   
      final String quitInfo = String.format("To quit enter sequence other than integers in range from %d to %d.%n",
                                             Short.MIN_VALUE, Short.MAX_VALUE);
      final String prompt = "Enter integer as number of";
      
      System.out.println("*** This program approximately calculate of multiplying many times mathematical constant e number ");
      System.out.println("using of number of fractions to adding in mathematical formula of calculate exponentiation of e number");

      short fractions;
      short exponent;
      double result = 0;
      Factorial factorial = new Factorial();

      while (true) {
         fractions = GettingDataFromStandardInput.getShortInteger(String.format("%s %s %s", quitInfo, prompt, "fractions to adding: "));
         exponent = GettingDataFromStandardInput.getShortInteger(String.format("%s %s", prompt, "exponent for e: "));
         
         result = factorial.getEulerNumberToPower(fractions, exponent);
         if (fractions >= 0 && false == factorial.isArithmeticOverflow()) {
            System.out.printf("*** Mathematical constant e to power of %d for adding %d fractions is %.21f%n",
                              exponent, fractions, result);
         }
         factorial.clearArithmeticOverflowError();
      }
      
   }
    
}
