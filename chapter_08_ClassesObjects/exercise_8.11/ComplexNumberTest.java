/* =====================================================================================
 *       Filename:  ComplexNumberTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.11 - test of complex number class 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.math.BigDecimal;

public class ComplexNumberTest {

   public static void main(String[] args) {
      testConstructors();
      
      ComplexNumber firstComplex  = new ComplexNumber(); 
      ComplexNumber secondComplex = new ComplexNumber(new BigDecimal("2.5"), new BigDecimal(-4.5));
      
      testArithmetic(firstComplex, secondComplex);
      
      BigDecimal firstBigDecimal  = new BigDecimal(2.5); 
      BigDecimal secondBigDecimal = new BigDecimal(-4.5);
      
      firstComplex.setImaginary(firstBigDecimal);
      firstComplex.setReal(secondBigDecimal);
      
      testArithmetic(firstComplex, secondComplex);
      
      System.out.print(firstComplex);
      firstComplex.conjugate();
      System.out.println(" after conjugate: " + firstComplex);
      System.out.println(" after conjugate: " + firstComplex);
      
      System.out.printf(" %n absolute value: %s %n", ComplexNumber.absoluteValue(firstComplex));
   }

   public static void testConstructors() {
      ComplexNumber    defaultComplexNumber  = new ComplexNumber();
      System.out.printf("complex number after construct by default constructor: %15s%n", defaultComplexNumber);
      
      BigDecimal real      = new BigDecimal(2.5); 
      BigDecimal imaginary = new BigDecimal(-4.5); 
      ComplexNumber    complexNumber  = new ComplexNumber(real, imaginary);
      System.out.printf("complex number after construct by constructor with 2 parameters: %15s%n", complexNumber);
   }
   
   public static void testArithmetic(ComplexNumber first, ComplexNumber second) {
      System.out.printf(" %s + %s = %s %n", first, second, ComplexNumber.add(first, second));
      System.out.printf(" %s + %s = %s %n", second, first, ComplexNumber.add(second, first));
      
      System.out.printf(" %s - %s = %s %n", first, second, ComplexNumber.subtract(first, second));
      System.out.printf(" %s - %s = %s %n", second, first, ComplexNumber.subtract(second, first));
      
      System.out.printf(" %s * %s = %s %n", first, second, ComplexNumber.multiply(first, second));
      System.out.printf(" %s * %s = %s %n", second, first, ComplexNumber.multiply(second, first));

      testDivide(first, second);
      testDivide(second, first);
   }
   
   public static void testDivide(ComplexNumber first, ComplexNumber second) {
      try {
         System.out.printf(" %s / %s = %s %n", first, second, ComplexNumber.divide(first, second));
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while divide: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
} 
