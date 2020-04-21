/* =====================================================================================
 *       Filename:  GettingDataFromString.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 6 - utility class of string's
                                    scanning
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package dataFromStringPackage;

import java.util.Scanner;

import pairPackage.LongIntegersPair;

public class GettingDataFromString {

   private Scanner scanner;
   
   public GettingDataFromString (String string) {
      if (null == string) {
         System.err.println("ERROR: input string is null");
         string = "";
      }
      scanner = new Scanner(string);
   }
   
   public Scanner getScanner() {
      return scanner;
   }
   
   public LongIntegersPair getOnlyLongIntegersPair() {
      
      Long firstNumber  = null;
      Long secondNumber = null;
      
      while (true == scanner.hasNext()) {    // return true if scanner has another token (word) in its scanner
      
         if (true == scanner.hasNextLong()) {
            if (null == firstNumber) {
               firstNumber = scanner.nextLong();
            }
            else if (null == secondNumber) {
               secondNumber = scanner.nextLong();
            }
            else {
               System.err.printf("%nValue of %d entered by User is integer type. ", scanner.nextLong());
               System.err.printf("This is incorrect. Correct numbers of integer type is 2 %n");
               return null;
            }

         }
         else if (true == scanner.hasNextDouble()) {
            System.err.printf("%nValue of %f entered by User is type double. ", scanner.nextDouble());
            System.err.printf("This is incorrect. Value must be integer type.%n");
            return null;
         }
         else {
            System.err.printf("%nValue of \'%s\' entered by User is not integer type. ", scanner.next());
            System.err.printf("This is incorrect. Value must be integer type.%n");
            return null;
         }
         
      }
      
      if (null == firstNumber) {
         System.err.printf("%nValues entered by User are not integer type. ");
         System.err.printf("This is incorrect. Correct numbers of integer type is 2 %n");
         return null;
      }
      else if (null == secondNumber) {
         System.err.printf("%nOnly 1 value entered by User is integer type. ");
         System.err.printf("This is incorrect. Correct numbers of integer type is 2 %n");
         return null;
      }
      
      return new LongIntegersPair(firstNumber, secondNumber);
   }
   
}
