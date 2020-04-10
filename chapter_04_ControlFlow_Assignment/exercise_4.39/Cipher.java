/* =====================================================================================
 *       Filename:  Cipher.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.39 - class of cipher with common elements for
                                encryption and decryption
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Cipher {
           
   public static String toString(short number) {
      if (0 > number || 10_000 <= number) { // incorrect data
         return null;
      }
      
      String digitsString = "";
      
      if (number < 10) {
         digitsString = "000";
      }
      else if (number < 100) {
         digitsString = "00";
      }
      else if (number < 1000) {
         digitsString = "0";
      }
      
      digitsString += number;
      
      
      return digitsString;
   }
   
   public static short exponentiation(short base, short exponent) {
      if (0 > exponent) {
         System.err.println("The case is more complicated. Exponent must be integer not less than zero");
         return 0;
      }
      else if (0 == base) {
         if (0 == exponent) 
            System.err.println("The case is more complicated");
            
         return 0;
      }
      else {
         short counter  = 1;
         short value = 1;
         
         while (counter <= exponent) {
            value *= base;
            counter++;
         }
         
         return value;
      }
   }
}
