/* =====================================================================================
 *       Filename:  Encryption.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.39 - class encryption
                                of integer greater than zero
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Encryption {
   public static final short digitsNumber = 4;
   public static final String digitsNumberInfo = String.format("Integer must contains exactly %d digits ", digitsNumber);
   public static final String nonPositiveNumberInfo = "Integer must be greater than zero";
   
   private short integer = 0; // improper data
   
   private boolean correctDigitsNumber = false;
   private boolean nonPositiveNumber = true;
         
   public short getInteger() {
      return integer;
   }
   
   public void setInteger(short integer) {
      if (true == validateInteger(integer)) {
         this.integer = integer;
      }
   }
   
   private short encrypt() {
      short encryptedValue = 0;
      
      short counter  = 0;
      short dividend = integer;
      short exponent = 0;
      
      while (dividend > 0) {
         if (2 > counter) {    // change place for 1st and 2nd digit
            exponent = (short)(counter + (short)2);
         }
         else {               // change place for 3rd and 4th digit
            exponent = (short)(counter - (short)2);
         }
         encryptedValue += ((dividend % 10 + 7) % 10) * Cipher.exponentiation((short)10, exponent);
         
         dividend /= 10;   // (dividend % 10) as last digit of dividend, after that operation last digit will be removed
         counter++;
      }
      
      return encryptedValue;
   }
   
   private boolean validateInteger(short integer) {
      
      if (0 >= integer) {
         nonPositiveNumber = true;
      }
      else {
         nonPositiveNumber = false;
      }
      
      if (1000 <= integer && 9999 >= integer) {
         correctDigitsNumber = true;
         return true;
      }
      
      if (-1000 >= integer && -9999 <= integer) {
         correctDigitsNumber = true;
         return false;
      }
      
      correctDigitsNumber = false;
      
      return false;
   }
   
   public void printEncryptionResult() {
   
      if (true == correctDigitsNumber && false == nonPositiveNumber) {
         System.out.printf ("After encryption value of %d is %s %n", integer, Cipher.toString(encrypt()));
         return;
      }
   
      if (false == correctDigitsNumber) {
         System.err.println(digitsNumberInfo);
      }
      if (true == nonPositiveNumber) {
         System.err.println(nonPositiveNumberInfo);
      }
      
   }
}
