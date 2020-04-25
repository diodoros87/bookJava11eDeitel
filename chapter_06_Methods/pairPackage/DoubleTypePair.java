/* =====================================================================================
 *       Filename:  DoubleTypePair.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 6 - utility class of double-precision 
                                floating-point numbers pair
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package pairPackage;

public class DoubleTypePair {

   private Double firstNumber  = null;
   private Double secondNumber = null;
   
   public DoubleTypePair (double firstNumber, double secondNumber) {
      this.firstNumber = Double.valueOf(firstNumber);
      this.secondNumber = Double.valueOf(secondNumber);
   }
   
   public DoubleTypePair (Double firstNumber, Double secondNumber) {
      this.firstNumber = firstNumber;
      this.secondNumber = secondNumber;
   }
   
   public boolean isNullInPair() {
      if (null == firstNumber || null == secondNumber) {
         return true;
      }
      
      return false;
   }
   
   public double getFirstNumber() {
      return firstNumber.doubleValue();
   }
   
   public Double getFirstNumberObject() {
      return firstNumber;
   }
   
   public void setFirstNumber(double firstNumber) {
      this.firstNumber = Double.valueOf(firstNumber);
   }
   
   public void setFirstNumber(Double firstNumber) {
      this.firstNumber = firstNumber;
   }
   
   public double getSecondNumber() {
      return secondNumber.doubleValue();
   }
   
   public Double getSecondNumberObject() {
      return secondNumber;
   }
   
   public void setSecondNumber(double secondNumber) {
      this.secondNumber = Double.valueOf(secondNumber);
   }
   
   public void setSecondNumber(Double secondNumber) {
      this.secondNumber = secondNumber;
   }
   
}
