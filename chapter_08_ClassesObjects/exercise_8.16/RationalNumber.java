/* =====================================================================================
 *       Filename:  RationalNumber.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.16 - rational number class 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class RationalNumber {
   
   private long numerator   = 1; 
   private long denominator = 1; 
   
   public RationalNumber() {
      this(1, 1);
   }
   
   public RationalNumber(long numerator, long denominator) {
      if (0 == denominator) {
         throw new IllegalArgumentException("Denominator can not be 0");
      } 
      
      long greatestCommonDivisor = denominator;
      
      if (0 != numerator) {
         greatestCommonDivisor = greatestCommonDivisor(numerator, denominator);
      }
      
      this.numerator   = numerator / greatestCommonDivisor;
      this.denominator = denominator / greatestCommonDivisor;
      
      if (0 > this.denominator) {
         this.numerator   = -this.numerator;
         this.denominator = -this.denominator;
      }
   } 
   
   public RationalNumber(RationalNumber rationalNumber) {
      this(rationalNumber.getNumerator(), rationalNumber.getDenominator());
   }
   
   public void set(RationalNumber rationalNumber) {
      this.numerator = rationalNumber.getNumerator();
      this.denominator = rationalNumber.getDenominator();
   }
   
   public void setNumerator(long numerator) {
      this.numerator = numerator;
      reduceFraction();
   }
   
   public long getNumerator() {
      return numerator;
   }
   
   public void setDenominator(long denominator) {
      if (0 == denominator) {
         throw new IllegalArgumentException("Denominator can not be 0");
      }
      
      this.denominator = denominator;
      reduceFraction();
   }
   
   public long getDenominator() {
      return denominator;
   }
   
   private void reduceFraction() {
      long greatestCommonDivisor = denominator;
      
      if (0 != numerator) {
         greatestCommonDivisor = greatestCommonDivisor(numerator, denominator);
      }
      
      this.numerator   = numerator / greatestCommonDivisor;
      this.denominator = denominator / greatestCommonDivisor;
      
      if (0 > this.denominator) {
         this.numerator   = -this.numerator;
         this.denominator = -this.denominator;
      }
   }
   
   public void add(RationalNumber rationalNumber) {
      set(RationalNumber.add(this, rationalNumber));
   }
   
   public static RationalNumber add(RationalNumber first, RationalNumber second) {
      long firstNumerator       = first.getNumerator();
      long firstDenominator     = first.getDenominator();
      long secondNumerator      = second.getNumerator();
      long secondDenominator    = second.getDenominator();
      
      long resultNumerator      = firstNumerator * secondDenominator + secondNumerator * firstDenominator;
      long resultDenominator    = firstDenominator * secondDenominator;
      
      return new RationalNumber(resultNumerator, resultDenominator);
   }
   
   public void subtract(RationalNumber rationalNumber) {
      set(RationalNumber.subtract(this, rationalNumber));
   }
   
   public static RationalNumber subtract(RationalNumber first, RationalNumber second) {
      RationalNumber negatedSecond = RationalNumber.negate(second);
      
      return RationalNumber.add(first, negatedSecond);
   }
   
   public void multiply(RationalNumber rationalNumber) {
      set(RationalNumber.multiply(this, rationalNumber));
   }
   
   public static RationalNumber multiply(RationalNumber first, RationalNumber second) {
      long firstNumerator       = first.getNumerator();
      long firstDenominator     = first.getDenominator();
      long secondNumerator      = second.getNumerator();
      long secondDenominator    = second.getDenominator();
      
      long resultNumerator      = firstNumerator * secondNumerator;
      long resultDenominator    = firstDenominator * secondDenominator;
      
      return new RationalNumber(resultNumerator, resultDenominator);
   }
   
   public void divide(RationalNumber rationalNumber) {
      set(RationalNumber.divide(this, rationalNumber));
   }
   
   public static RationalNumber divide(RationalNumber dividend, RationalNumber divisor) {
      if (0 == divisor.getNumerator()) {
         throw new ArithmeticException("Divisor can not be 0");
      }
      
      RationalNumber reversedDivisor = RationalNumber.reverse(divisor);
      
      return RationalNumber.multiply(dividend, reversedDivisor);
   }
   
   public static long greatestCommonDivisor(long first, long second) {
      long moduloResult;
      
      while (0 != second) {
         moduloResult = first % second;
         first        = second;
         second       = moduloResult;
      }
      
      return first;
   }
   
   public void negate() {
      set(RationalNumber.negate(this));
   }
   
   public static RationalNumber negate(RationalNumber rationalNumber) {
      long numerator   = -rationalNumber.getNumerator();
      long denominator = rationalNumber.getDenominator();
      
      return new RationalNumber(numerator, denominator);
   }
   
   public void reverse() {
      set(RationalNumber.reverse(this));
   }
   
   public static RationalNumber reverse(RationalNumber rationalNumber) {
      long denominator = rationalNumber.getNumerator();
      long numerator;
      
      if (0 == denominator) {
         numerator = 0;
         denominator = 1;
      }
      else {
         numerator = rationalNumber.getDenominator();
      }
      
      return new RationalNumber(numerator, denominator);
   }
   
   public String toString() {
      return String.format("(%+d / %+d)", getNumerator(), getDenominator());
   } 
   
   public String getFloatingPointString(int precision) {
      FloatingPointString floatingPointString = new FloatingPointString(numerator, denominator, precision);
      
      return floatingPointString.toString();
   }
   
   public String toFloatingPointFormatString(long precision) {
      double result = (double)getNumerator() / getDenominator();

      return String.format("%+." + precision + "f", result);
   } 
} 
