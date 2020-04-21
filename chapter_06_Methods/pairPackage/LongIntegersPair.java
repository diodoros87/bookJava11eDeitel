/* =====================================================================================
 *       Filename:  LongIntegersPair.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 6 - utility class of long integers   
                                pair
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package pairPackage;

public class LongIntegersPair {

   private Long firstNumber  = null;
   private Long secondNumber = null;
   
   public LongIntegersPair (long firstNumber, long secondNumber) {
      this.firstNumber = Long.valueOf(firstNumber);
      this.secondNumber = Long.valueOf(secondNumber);
   }
   
   public LongIntegersPair (Long firstNumber, Long secondNumber) {
      this.firstNumber = firstNumber;
      this.secondNumber = secondNumber;
   }
   
   public boolean isNullInPair() {
      if (null == firstNumber || null == secondNumber) {
         return true;
      }
      
      return false;
   }
   
   public long getFirstNumber() {
      return firstNumber.longValue();
   }
   
   public Long getFirstNumberObject() {
      return firstNumber;
   }
   
   public void setFirstNumber(long firstNumber) {
      this.firstNumber = Long.valueOf(firstNumber);
   }
   
   public void setFirstNumber(Long firstNumber) {
      this.firstNumber = firstNumber;
   }
   
   public long getSecondNumber() {
      return secondNumber.longValue();
   }
   
   public Long getSecondNumberObject() {
      return secondNumber;
   }
   
   public void setSecondNumber(long secondNumber) {
      this.secondNumber = Long.valueOf(secondNumber);
   }
   
   public void setSecondNumber(Long secondNumber) {
      this.secondNumber = secondNumber;
   }
   
}
