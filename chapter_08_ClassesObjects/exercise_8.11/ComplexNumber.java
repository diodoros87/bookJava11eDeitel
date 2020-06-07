/* =====================================================================================
 *       Filename:  ComplexNumber.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.11 - complex number class 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.math.BigDecimal;
import java.math.MathContext;

public class ComplexNumber {
   
   private BigDecimal                real; 
   private BigDecimal                imaginary; 
   
   public ComplexNumber() {
      this(BigDecimal.ZERO, BigDecimal.ZERO);
   }
   
   public ComplexNumber(BigDecimal real, BigDecimal imaginary) {
      this.real      = real;
      this.imaginary = imaginary;
   } 
   
   public ComplexNumber(ComplexNumber complexNumber) {
      this(complexNumber.getReal(), complexNumber.getImaginary());
   }
   
   public void set(ComplexNumber complexNumber) {
      setReal(complexNumber.getReal());
      setImaginary(complexNumber.getImaginary());
   }
   
   public void setReal(BigDecimal real) {
      this.real = real;
   }
   
   public BigDecimal getReal() {
      return real;
   }
   
   public void setImaginary(BigDecimal imaginary) {
      this.imaginary = imaginary;
   }
   
   public BigDecimal getImaginary() {
      return imaginary;
   }
   
   public void add(ComplexNumber complexNumber) {
      set(ComplexNumber.add(this, complexNumber));
   }
   
   public static ComplexNumber add(ComplexNumber first, ComplexNumber second) {
      BigDecimal firstReal          = first.getReal();
      BigDecimal firstImaginary     = first.getImaginary();
      BigDecimal secondReal         = second.getReal();
      BigDecimal secondImaginary    = second.getImaginary();
      
      return new ComplexNumber(firstReal.add(secondReal), firstImaginary.add(secondImaginary));
   }
   
   public void subtract(ComplexNumber complexNumber) {
      set(ComplexNumber.subtract(this, complexNumber));
   }
   
   public static ComplexNumber subtract(ComplexNumber first, ComplexNumber second) {
      BigDecimal firstReal          = first.getReal();
      BigDecimal firstImaginary     = first.getImaginary();
      BigDecimal secondReal         = second.getReal();
      BigDecimal secondImaginary    = second.getImaginary();
      
      return new ComplexNumber(firstReal.subtract(secondReal), firstImaginary.subtract(secondImaginary));
   }
   
   public void multiply(ComplexNumber complexNumber) {
      set(ComplexNumber.multiply(this, complexNumber));
   }
   
   public static ComplexNumber multiply(ComplexNumber first, ComplexNumber second) {
      BigDecimal firstReal          = first.getReal();
      BigDecimal firstImaginary     = first.getImaginary();
      BigDecimal secondReal         = second.getReal();
      BigDecimal secondImaginary    = second.getImaginary();
      
      BigDecimal realResult         = firstReal.multiply(secondReal);
      realResult         = realResult.subtract(firstImaginary.multiply(secondImaginary));
      
      BigDecimal imaginaryResult    = firstImaginary.multiply(secondReal);
      imaginaryResult    = imaginaryResult.add(firstReal.multiply(secondImaginary));
      
      return new ComplexNumber(realResult, imaginaryResult);
   }
   
   public void divide(ComplexNumber complexNumber) {
      set(ComplexNumber.divide(this, complexNumber));
   }
   
   public static ComplexNumber divide(ComplexNumber dividend, ComplexNumber divisor) {
//       BigDecimal dividendReal        = dividend.getReal();
//       BigDecimal dividendImaginary   = dividend.getImaginary();
//       BigDecimal divisorReal         = divisor.getReal();
//       BigDecimal divisorImaginary    = divisor.getImaginary();
//       
//       BigDecimal realResultNumerator = (dividendReal * divisorReal + dividendImaginary * divisorImaginary);
//       BigDecimal resultDenominator   = divisorReal * divisorReal + divisorImaginary * divisorImaginary;
//       BigDecimal imaginaryResultNumerator = firstImaginary * secondReal - firstReal * secondImaginary;
//       
//       return new ComplexNumber(realResult, imaginaryResult);
      ComplexNumber conjugateDivisor = ComplexNumber.conjugate(divisor);
      
      ComplexNumber resultDividend   = ComplexNumber.multiply(dividend, conjugateDivisor);
      ComplexNumber resultDivisor    = ComplexNumber.multiply(divisor, conjugateDivisor);
      
      BigDecimal realResultNumerator      = resultDividend.getReal();
      BigDecimal imaginaryResultNumerator = resultDividend.getImaginary();
      BigDecimal resultDenominator        = resultDivisor.getReal();
      
      BigDecimal realResult       = realResultNumerator.divide(resultDenominator, MathContext.DECIMAL64);
      BigDecimal imaginaryResult  = imaginaryResultNumerator.divide(resultDenominator, MathContext.DECIMAL64);
      
      return new ComplexNumber(realResult, imaginaryResult);
   }
   
   public void conjugate() {
      set(ComplexNumber.conjugate(this));
   }
   
   public static ComplexNumber conjugate(ComplexNumber complexNumber) {
      BigDecimal imaginary = complexNumber.getImaginary();
      
      return new ComplexNumber(complexNumber.getReal(), imaginary.negate());
   }
   
   public void absoluteValue() {
      set(ComplexNumber.absoluteValue(this));
   }
   
   public static ComplexNumber absoluteValue(ComplexNumber complexNumber) {
      BigDecimal real          = complexNumber.getReal();
      BigDecimal imaginary     = complexNumber.getImaginary();
      BigDecimal realSquare      = real.pow(2);
      BigDecimal imaginarySquare = imaginary.pow(2);

      BigDecimal realResult      = realSquare.add(imaginarySquare);
      
      return new ComplexNumber(realResult.sqrt(MathContext.DECIMAL64), BigDecimal.ZERO);
   }
   
   
   
   public String toString() {
      return String.format("(%+f, %+f * i)", getReal(), getImaginary());
   } 
} 
