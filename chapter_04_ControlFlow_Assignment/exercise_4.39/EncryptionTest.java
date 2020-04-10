/* =====================================================================================
 *       Filename:  EncryptionTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.39 - encryption
                                value of integer entered by User
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;

public class EncryptionTest {

   public static void main(String[] args) {
      System.out.println("*** This program encrypt of integer.");
      System.out.println(Encryption.digitsNumberInfo);
      System.out.println(Encryption.nonPositiveNumberInfo);
   
      final String prompt = String.format("To quit enter sequence other than integers in range from %d to %d Enter integer: ",
                                             Short.MIN_VALUE, Short.MAX_VALUE);
      
      short integer; 
      Encryption encryption = new Encryption();

      while (true) {
         integer = GettingDataFromStandardInput.getShortInteger(prompt);

         encryption.setInteger(integer);
         encryption.printEncryptionResult();
      }
   }
      
}
