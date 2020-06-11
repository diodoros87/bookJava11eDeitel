/* =====================================================================================
 *       Filename:  RationalNumberTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.16 - test of rational number class 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class RationalNumberTest {

   public static void main(String[] args) {
      testConstructors();
      
      RationalNumber firstRational  = new RationalNumber(76, -24); 
      printRationalNumber("first rational number after construct: ", firstRational, 7);
      
      RationalNumber secondRational = new RationalNumber(-22, -99);
      printRationalNumber("second rational number after construct: ", secondRational, 12);
      
      testArithmetic(firstRational, secondRational);
      
      firstRational.setDenominator(12);
      printRationalNumber("first rational number after change denominator to 12: ", firstRational, 9);
      printRationalNumber("first rational number after change denominator to 12: ", firstRational, 0);
      firstRational.setNumerator(14);
      printRationalNumber("first rational number after change numerator to 14: ", firstRational, 15);
      printRationalNumber("first rational number after change numerator to 14: ", firstRational, 0);
      
      testArithmetic(firstRational, secondRational);
      
      firstRational.negate();
      printRationalNumber("first rational number after negate: ", firstRational, 2);

      firstRational.negate();
      printRationalNumber("first rational number after negate again: ", firstRational, 3);
      
      firstRational.reverse();
      printRationalNumber("first rational number after reverse: ", firstRational, 11);
      
      firstRational.reverse();
      printRationalNumber("first rational number after reverse again: ", firstRational, 5);
      
      firstRational.add(secondRational);
      printRationalNumber("first rational number after add second rational: ", firstRational, 4);
      
      RationalNumber zero  = new RationalNumber(0, -24); 
      printRationalNumber("zero after construct: ", zero, 3);
      
      testDivide(firstRational, zero);
      testDivide(zero, firstRational);
   }

   public static void testConstructors() {
      RationalNumber    defaultRationalNumber  = new RationalNumber();
      printRationalNumber("rational number after construct by default constructor: ", defaultRationalNumber, 5);

      RationalNumber    rationalNumber  = new RationalNumber(777, -999);
      printRationalNumber("rational number after construct: ", rationalNumber, 8);
      
      try {
         RationalNumber zeroDenominator = new RationalNumber(7, 0);
         System.out.printf("??? rational number after construct with 0 = denominator: %15s%n",
                        zeroDenominator);
         assert(false);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct rational number: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
   
   public static void testArithmetic(RationalNumber first, RationalNumber second) {
      System.out.printf(" %s + %s = %s %n", first, second, RationalNumber.add(first, second));
      System.out.printf(" %s + %s = %s %n", second, first, RationalNumber.add(second, first));
      
      System.out.printf(" %s - %s = %s %n", first, second, RationalNumber.subtract(first, second));
      System.out.printf(" %s - %s = %s %n", second, first, RationalNumber.subtract(second, first));
      
      System.out.printf(" %s * %s = %s %n", first, second, RationalNumber.multiply(first, second));
      System.out.printf(" %s * %s = %s %n", second, first, RationalNumber.multiply(second, first));
      
      testDivide(first, second);
      testDivide(second, first);
   }
   
   public static void testDivide(RationalNumber first, RationalNumber second) {
      try {
         System.out.printf(" %s / %s = %s %n", first, second, RationalNumber.divide(first, second));
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while divide:  %s / %s %s%n", first, second, exception.getMessage());
         exception.printStackTrace();
      }
   }
   
   public static void printRationalNumber(String title, RationalNumber rationalNumber, int precision) {
      System.out.printf("%s      precision = %d:%n", title, precision);
      System.out.println(rationalNumber);
      System.out.println(rationalNumber.toFloatingPointFormatString(precision));
      System.out.println(rationalNumber.getFloatingPointString(precision));
      System.out.println("End of number printing");
   }
} 
