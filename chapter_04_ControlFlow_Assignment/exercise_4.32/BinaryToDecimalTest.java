/* =====================================================================================
 *       Filename:  BinaryToDecimalTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.32 - calculate decimal value of 
                                binary integer
                                (binary integer as integer entered by User)
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;

public class BinaryToDecimalTest {

   public static void main(String[] args) {
   
      final String prompt = "To quit enter sequence other than digits. Enter binary integer: ";
      final String maxValueMessage = "Maximum value of entered number can not be more than ";
      final String minValueMessage = "Minimum value of entered number can not be less than ";
      
      System.out.printf("*** This program calculate decimal value of %d-bits ", BinaryToDecimal.maxBitsNumber);
      System.out.println("binary integer (in system of two's complement) entered by User");
      System.out.println(BinaryToDecimal.nonBinaryFormatErrorString);
      System.out.printf ("%s %s (as decimal: %d) %n", maxValueMessage, 
                                    BinaryToDecimal.maxBinaryValue, BinaryToDecimal.maxBinaryAsDecimalValue);
      System.out.printf ("%s %s (as decimal: %d) %n", minValueMessage, 
                                    BinaryToDecimal.minBinaryValue, BinaryToDecimal.minBinaryAsDecimalValue);
      
      int integer; 

      while (true) {
         integer = GettingDataFromStandardInput.getInteger(prompt);

         BinaryToDecimal.setInteger(integer);
         if (true == BinaryToDecimal.isCorrectBitsNumber() && true == BinaryToDecimal.isCorrectBinaryFormat()) {
            System.out.printf ("In system of two's complement decimal value of %d is %d %n", 
                                    BinaryToDecimal.getInteger(), BinaryToDecimal.getDecimalValue());
         }
         BinaryToDecimal.errorsInfo();
         BinaryToDecimal.resetDataForNextInteger();
      }
   }
      
}
