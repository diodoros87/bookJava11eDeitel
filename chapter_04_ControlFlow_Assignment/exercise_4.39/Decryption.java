/* =====================================================================================
 *       Filename:  Decryption.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.39 - class of
                                decryption of integer not less than zero
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Decryption {

   public static final short min = 0;
   public static final short max = 9999;
   public static final String correctNumberInfo = String.format("Integer must be in range between %d and %d ", min, max);
   
   private short integer = -1; // improper data
   
   private boolean correctNumber = false;
         
   public short getInteger() {
      return integer;
   }
   
   public void setInteger(short integer) {
      if (true == validateInteger(integer)) {
         this.integer = integer;
      }
   }
   
   private short decrypt() {
      short decryptedValue = 0;
      
      short counter  = 0;
      short dividend = integer;
      short exponent = 0;
      short digit = 0;
      
      while (counter < Encryption.digitsNumber) {
         if (2 > counter) {   // change place for 1st and 2nd digit
            exponent = (short)(counter + (short)2);
         }
         else {              // change place for 3rd and 4th digit
            exponent = (short)(counter - (short)2);
         }
         
         digit = (short)(dividend % 10 - 7);
         if (digit < 0) {
            digit += 10;
         }
         decryptedValue += digit * Cipher.exponentiation((short)10, exponent);
         
         dividend /= 10;   // (dividend % 10) as last digit of dividend, after that operation last digit will be removed
         counter++;
      }
      
      return decryptedValue;
   }
   
   private boolean validateInteger(short integer) {
      
      if (min <= integer && max >= integer) {
         correctNumber = true;
      }
      else {
         correctNumber = false;
      }
      
      return correctNumber;
   }
   
   public void printDecryptionResult() {
      if (true == correctNumber) {
         System.out.printf ("After decryption value of %d is %s %n", integer, Cipher.toString(decrypt()));
      }
      else {
         System.err.println(correctNumberInfo);
      }
   }
}
