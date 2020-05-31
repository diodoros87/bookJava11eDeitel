/* =====================================================================================
 *       Filename:  FloatingPoint.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.38 - simulation of machine language programming
                                 - stage program execution, operations with floating-
                                    point numbers
                                
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

package simpletron;

import java.io.PrintStream;
import java.util.Scanner;

import static simpletron.ComputerSimulator.*;
import static simpletron.ProgramExecution.*;

public class FloatingPoint {
   private static final int EXPONENT_DIGITS = 2;
   private static final int MANTISSA_DIGITS = INSTRUCTION_LENGTH - EXPONENT_DIGITS;

   private final PrintStream printStream;
   private final PrintStream errorPrintStream;
   private final Scanner scanner;
   
   public FloatingPoint(PrintStream printStream, Scanner scanner, PrintStream errorPrintStream) {
      if (null == printStream) {
         throw new NullPointerException("print stream can not be null.");
      }
      if (null == scanner) {
         throw new NullPointerException("Input's scanner can not be null.");
      }
      if (null == errorPrintStream) {
         throw new NullPointerException("Error print stream can not be null.");
      }
      
      this.printStream = printStream;
      this.scanner = scanner;
      this.errorPrintStream = errorPrintStream;
   }
   
   void enterFloat(int[] memory, int operand) {
      
      printStream.printf("Enter floating-point number: ");
      while (true == scanner.hasNextLine()
             && false == isCorrectFloat(memory, operand)) {
         
         printStream.printf("Enter floating-point number: ");
      }
         
   }
   
   private boolean isCorrectFloat(int[] memory, int operand) {
      
      if (true == scanner.hasNextDouble()) {
         double number = scanner.nextDouble();
         scanner.nextLine();   // to clear input data - nextInt() leaves whitespaces in input
         
         writeFloatToMemory(memory, operand, number);

         return true;
      }
      else {
         errorPrintStream.printf("%n???????? ERROR: Value of \'%s\' is not using in this version of Simpletron%n",
                                             scanner.nextLine());
      }
      
      return false;
   }
   
   private void writeFloatToMemory(int[] memory, int operand, double number) {
      if (0 == number) {
         memory[operand] = 0;
      }
      else {
         memory[operand] = calculateSignedFloatToMemory(number);
      }
   }
   
   public static final int EXPONENT_ORDER_OF_MAGNITUDE = IntegerNumbers.integerPower(10, (short)(MANTISSA_DIGITS));
   public static final int MANTISSA_ORDER_OF_MAGNITUDE = IntegerNumbers.integerPower(10, (short)(MANTISSA_DIGITS - 1));
   
   private static final int EXPONENT_BIAS = IntegerNumbers.integerPower(10, (short)(EXPONENT_DIGITS)) / 2;
   private static final int MAX_EXPONENT_AFTER_BIAS = 2 * EXPONENT_BIAS - 1;
   private static final int MIN_EXPONENT_AFTER_BIAS = 0;
   
   private int calculateFloatToMemory(int exponent, double absoluteMantissa) {
      int mantissaToMemoryStorage = calculateAbsMantissaToMemory(absoluteMantissa);
      
      int exponentToMemoryStorage = calculateExponentToMemory(exponent);
      
      int valueToMemoryStorage = exponentToMemoryStorage + mantissaToMemoryStorage;
      
      return valueToMemoryStorage;
   }
   
   private int calculateAbsMantissaToMemory(double absoluteMantissa) {
      double mantissaBeforeRounding = absoluteMantissa * MANTISSA_ORDER_OF_MAGNITUDE;
      double mantissaAfterRounding = Math.floor(mantissaBeforeRounding + 0.5);
      
      int mantissaToMemoryStorage = (int)mantissaAfterRounding;
      
      return mantissaToMemoryStorage;
   }
   
   private int calculateExponentToMemory(int exponent) {
      int biasedExponent = EXPONENT_BIAS + exponent;
      
      if (biasedExponent < MIN_EXPONENT_AFTER_BIAS) {
         biasedExponent = MIN_EXPONENT_AFTER_BIAS;
      }
      else if (biasedExponent > MAX_EXPONENT_AFTER_BIAS) {
         biasedExponent = MAX_EXPONENT_AFTER_BIAS;
      }
      
      int exponentToMemoryStorage = biasedExponent * EXPONENT_ORDER_OF_MAGNITUDE;
      
      return exponentToMemoryStorage;
   }
   
   private int calculateSignedFloatToMemory(double number) {
      double absMantissa = Math.abs(number);
      int exponent = 0;
      
      while (absMantissa >= 10) {
         absMantissa /= 10;
         exponent++;
      }
      
      while (absMantissa < 1) {
         absMantissa *= 10;
         exponent--;
      }
      
      int valueToMemory = calculateFloatToMemory(exponent, absMantissa);
      valueToMemory = number < 0 ? -valueToMemory : valueToMemory;
      
      return valueToMemory;
   }
   
   void printFloat(int[] memory, int operand) {
      double floatFromMemory = getFloatFromMemory(memory[operand]);
      
      printStream.printf(" in memory's location %03x is %+e %n", operand, floatFromMemory);
   }
   
   private double getFloatFromMemory(int integerFromMemory) {
      double mantissa = getMantissaFromMemory(integerFromMemory);
      int exponent = getExponentFromMemory(integerFromMemory);

      double floatFromMemory = mantissa * Math.pow(10, exponent);
      
      return floatFromMemory;
   }
   
   private double getMantissaFromMemory(int integerFromMemory) {
      double mantissa = integerFromMemory % EXPONENT_ORDER_OF_MAGNITUDE;
      mantissa /= MANTISSA_ORDER_OF_MAGNITUDE;
      
      return mantissa;
   }
   
   private int getExponentFromMemory(int integerFromMemory) {
      int biasedExponent = Math.abs(integerFromMemory) / EXPONENT_ORDER_OF_MAGNITUDE;
      int exponent = biasedExponent - EXPONENT_BIAS;
      
      return exponent;
   }
   
   int add(int accumulator, int element) {
      double accumulatorMantissa = getMantissaFromMemory(accumulator);
      int accumulatorExponent = getExponentFromMemory(accumulator);
      
      double elementMantissa = getMantissaFromMemory(element);
      int elementExponent = getExponentFromMemory(element);
      
      int valueToMemory = add(accumulatorMantissa, accumulatorExponent, elementMantissa, elementExponent);
      
      return valueToMemory;
   }
   
   private int add(double firstMantissa, int firstExponent, double secondMantissa, int secondExponent) {
      int exponent;
      double mantissa;
      double denormalizedMantissa;
      
      if (firstExponent > secondExponent) {
         exponent = firstExponent;
         denormalizedMantissa = secondMantissa;
         mantissa = firstMantissa;
      }
      else {
         exponent = secondExponent;
         denormalizedMantissa = firstMantissa;
         mantissa = secondMantissa;
      }
      
      denormalizedMantissa = getDenormalizedMantissa(denormalizedMantissa, firstExponent - secondExponent);
      mantissa += denormalizedMantissa;
      
      double absoluteMantissa = Math.abs(mantissa);
      if (absoluteMantissa >= 10) {
         mantissa = getDenormalizedMantissa(mantissa, 1);
         exponent = mantissa > 0 ? ++exponent : --exponent;
      }
      
      return calculateSignedFloatToMemory(exponent, mantissa);
   }
   
   private double getDenormalizedMantissa(double mantissa, int exponentDifference) {
      int absExponentDifference = Math.abs(exponentDifference);
      
      if (absExponentDifference > MANTISSA_DIGITS + 1) {
         return 0;
      }
      
      int roundingPositionAfterDot = MANTISSA_DIGITS;
      
      while (absExponentDifference > 0) {
         mantissa /= 10;
         roundingPositionAfterDot++;
         absExponentDifference--;
      }
      
      if (roundingPositionAfterDot <= 0) {
         return mantissa;
      }
      else {
         return round(mantissa, roundingPositionAfterDot);
      }
   }
   
   private int calculateSignedFloatToMemory(int exponent, double mantissa) {
      int valueToMemory = calculateFloatToMemory(exponent, Math.abs(mantissa));
      
      if (0 > mantissa) {
         return -valueToMemory;
      }
      
      if (0 < mantissa) {
         return valueToMemory;
      }
      
      return 0;
   }
   
   private double round(double number, int positionAfterDot) {
      int orderOfMagnitude = IntegerNumbers.integerPower(10, (short)(positionAfterDot));
      
      return Math.floor(orderOfMagnitude * number + 0.5) / orderOfMagnitude;
   }
   
//    private int calculateExponentDifference(int firstFromMemory, int secondFromMemory) {
//       int firstExponent = getExponentFromMemory(firstFromMemory);
//       int secondExponent = getExponentFromMemory(secondFromMemory);
//       
//       return firstExponent - secondExponent;
//       
//       if (firstExponent > secondExponent) {
//          return 1;
//       }
//       if (secondExponent > firstExponent) {
//          return 2;
//       }
//       else {
//          return 0;
//       }
//    }
   
}
