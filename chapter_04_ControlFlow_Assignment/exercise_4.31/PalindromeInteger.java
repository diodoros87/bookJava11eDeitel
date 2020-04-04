/* =====================================================================================
 *       Filename:  PalindromeInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.31 - checking that integer is palindrome                                
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;

public class PalindromeInteger {

   public static void main(String[] args) {
   
      final short numbersOfDigits = 5;
      final String prompt = "To quit enter sequence other than integer. Enter integer: ";
      
      System.out.printf("*** This program checks that integer has exactly %d digits and is palindrome.%n", numbersOfDigits);

      int integer; 

      while (true) {
         integer = GettingDataFromStandardInput.getInteger(prompt);
         if (true == isPalindrome(integer, numbersOfDigits)) {
            System.out.printf("Entered integer is palindrome.%n");
         }
      }
   }
      
   public static boolean isPalindrome(int integer, short numberOfDigitsRequired) {
      assert(numberOfDigitsRequired > 0);
      if (numberOfDigitsRequired < 1){
         System.err.printf("Argument numberOfDigitsRequired %d is improper%n", numberOfDigitsRequired);
         return false;
      }
      
      if (integer < 0) {
         System.err.printf("Entered integer %d is less than zero and is not palindrome.%n", integer);
         return false;
      }
      
      Integer digit = integer % 10;
      String reverseDigitsString = digit.toString();
      short counter  = 1;
      int dividend = integer / 10;
      
      while (dividend > 0) {
         digit = dividend % 10;
         reverseDigitsString += digit.toString();
         dividend /= 10;
         counter++;
      }
      
      if (counter != numberOfDigitsRequired) {
         System.err.printf("Entered integer %d has not exactly %d digits.%n", integer, numberOfDigitsRequired);
         return false;
      }
      
      int beginIterator = 0;
      int endIterator = reverseDigitsString.length() - 1;
      
      while (beginIterator < endIterator) {
         if (reverseDigitsString.charAt(beginIterator) != reverseDigitsString.charAt(endIterator)) {
            System.out.printf("Entered integer %d is not palindrome.%n", integer);
            return false;
         }
         beginIterator++;
         endIterator--;
      }
      
      return true;
   }
    
}
