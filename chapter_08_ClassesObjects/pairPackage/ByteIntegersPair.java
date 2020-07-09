/* =====================================================================================
 *       Filename:  ByteIntegersPair.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 8 - utility class of byte size of
                                integer   
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package pairPackage;

public class ByteIntegersPair {

   private Byte firstNumber  = null;
   private Byte secondNumber = null;
   
   public ByteIntegersPair (byte firstNumber, byte secondNumber) {
      this.firstNumber  = Byte.valueOf(firstNumber);
      this.secondNumber = Byte.valueOf(secondNumber);
   }
   
   public ByteIntegersPair (Byte firstNumber, Byte secondNumber) {
      this.firstNumber  = firstNumber;
      this.secondNumber = secondNumber;
   }
   
   public boolean isNullInPair() {
      if (null == firstNumber || null == secondNumber) {
         return true;
      }
      
      return false;
   }
   
   public byte getFirstNumber() {
      return firstNumber.byteValue();
   }
   
   public Byte getFirstNumberObject() {
      return firstNumber;
   }
   
   public void setFirstNumber(byte firstNumber) {
      this.firstNumber = Byte.valueOf(firstNumber);
   }
   
   public void setFirstNumber(Byte firstNumber) {
      this.firstNumber = firstNumber;
   }
   
   public byte getSecondNumber() {
      return secondNumber.byteValue();
   }
   
   public Byte getSecondNumberObject() {
      return secondNumber;
   }
   
   public void setSecondNumber(byte secondNumber) {
      this.secondNumber = Byte.valueOf(secondNumber);
   }
   
   public void setSecondNumber(Byte secondNumber) {
      this.secondNumber = secondNumber;
   }
   
}
