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
         scanner.nextLine();   // to clear input data - nextDouble() leaves whitespaces in input
         
         writeFloatToMemory(memory, operand, number);

         return true;
      }
      else {
         errorPrintStream.printf("%n???????? ERROR: Value of \'%s\' is not using in this mode%n",
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
   
   public static final int EXPONENT_ORDER_OF_MAGNITUDE = Numbers.integerPower(10, (short)(MANTISSA_DIGITS));
   public static final int MANTISSA_ORDER_OF_MAGNITUDE = Numbers.integerPower(10, (short)(MANTISSA_DIGITS - 1));
   
   private static final int EXPONENT_BIAS = Numbers.integerPower(10, (short)(EXPONENT_DIGITS)) / 2;
   private static final int MAX_EXPONENT_AFTER_BIAS = 2 * EXPONENT_BIAS - 1;
   private static final int MIN_EXPONENT_AFTER_BIAS = 0;
   
   private static final int MAX_EXPONENT = MAX_EXPONENT_AFTER_BIAS - EXPONENT_BIAS;
   private static final int MIN_EXPONENT = MIN_EXPONENT_AFTER_BIAS - EXPONENT_BIAS;
   
   private int calculateFloatToMemory(int exponent, double absoluteMantissa) {
      int mantissaToMemory = calculateAbsMantissaToMemory(absoluteMantissa);
      
      int exponentToMemory = calculateExponentToMemory(exponent);
      
      int valueToMemory = exponentToMemory + mantissaToMemory;
      
      return valueToMemory;
   }
   
   private int calculateAbsMantissaToMemory(double absoluteMantissa) {
      absoluteMantissa = Math.abs(absoluteMantissa);
      
      double mantissaBeforeRounding = absoluteMantissa * MANTISSA_ORDER_OF_MAGNITUDE;
      double mantissaAfterRounding = Math.floor(mantissaBeforeRounding + 0.5);
      
      int mantissaToMemory = (int)mantissaAfterRounding;
      
      return mantissaToMemory;
   }
   
   private void validateExponent(int exponent) {
      if (exponent < MIN_EXPONENT) {
         throw new IllegalArgumentException(String.format("exponent can not be less than %d", MIN_EXPONENT));
      }
      if (exponent > MAX_EXPONENT) {
         throw new IllegalArgumentException(String.format("exponent can not be more than %d", MAX_EXPONENT));
      }
   }
   
   private int calculateExponentToMemory(int exponent) {
      validateExponent(exponent);
      
      int biasedExponent = EXPONENT_BIAS + exponent;
      int exponentToMemory = biasedExponent * EXPONENT_ORDER_OF_MAGNITUDE;
      
      return exponentToMemory;
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
   
   int addSubtract(int accumulator, int element, Arithmetic operation) {
      if (0 == accumulator) {
         return operation == Arithmetic.SUBTRACT ? -element : element;
      }
      if (0 == element) {
         return accumulator;
      }
      
      double accumulatorMantissa = getMantissaFromMemory(accumulator);
      int accumulatorExponent = getExponentFromMemory(accumulator);
      
      double elementMantissa = getMantissaFromMemory(element);
      int elementExponent = getExponentFromMemory(element);
      
      if (operation == Arithmetic.SUBTRACT) {
         elementMantissa = -elementMantissa;
      }
      
      int valueToMemory = add(accumulatorMantissa, accumulatorExponent, elementMantissa, elementExponent, operation);
      
      return valueToMemory;
   }
   
   private int add(double firstMantissa, int firstExponent, double secondMantissa, int secondExponent, Arithmetic operation) {
      int exponent;
      double mantissa;
      double denormalizedMantissa;
      
      if (firstExponent > secondExponent) {
         exponent = firstExponent;
         denormalizedMantissa = secondMantissa;
         mantissa             = firstMantissa;
      }
      else {
         exponent = secondExponent;
         denormalizedMantissa = firstMantissa;
         mantissa             = secondMantissa;
      }
      
      denormalizedMantissa = getDenormalizedMantissa(denormalizedMantissa, firstExponent - secondExponent, operation);
      mantissa += denormalizedMantissa;
      
      int valueToMemory = calculateNormalizedFloat(exponent, mantissa, operation);
      
      return valueToMemory;
   }
   
   private double getDenormalizedMantissa(double mantissa, int exponentDifference, Arithmetic operation) {
      int absExponentDifference = Math.abs(exponentDifference);
      
      if (operation == Arithmetic.ADD || operation == Arithmetic.SUBTRACT) {
         if (absExponentDifference > MANTISSA_DIGITS + 1) {
            return 0;
         }
      }
      
      int roundingPositionAfterDot = MANTISSA_DIGITS - 1;
      
      while (absExponentDifference > 0) {
         mantissa /= 10;
         absExponentDifference--;
      }

      double mantissaAfterRounding = Numbers.round(mantissa, roundingPositionAfterDot);
      
      return mantissaAfterRounding;
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
   
   int modulo(int accumulator, int element) {
      if (true == isMultiplyDivideZero(accumulator, element, Arithmetic.MODULO)) {
         return 0;
      }
      
      double accumulatorFloat = getFloatFromMemory(accumulator);
      double elementFloat = getFloatFromMemory(element);
      
      double doubleResult = accumulatorFloat % elementFloat;
      int result = calculateSignedFloatToMemory(doubleResult);
      
      return result;
   }
   
   int exponentiation(int accumulator, int exponent) {
      if (exponent == 0 && accumulator == 0) {
         throw new ArithmeticException("0 to power of 0 is indeterminate form");
      }
      if (accumulator == 0) {
         return 0;
      }
      if (exponent == 0) {
         return 1;
      }
      
      double accumulatorFloat = getFloatFromMemory(accumulator);
      double exponentFloat = getFloatFromMemory(exponent);
      
      double doubleResult = Math.pow(accumulatorFloat, exponentFloat);
      int result = calculateSignedFloatToMemory(doubleResult);
      
      return result;
   }
   
   public enum Arithmetic {ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO};
   
   private boolean isMultiplyDivideZero(int dividend, int divisor, Arithmetic operation) {
      if (0 == divisor && (Arithmetic.DIVIDE == operation || Arithmetic.MODULO == operation)) {
         throw new ArithmeticException("Divisor (quotient) can not be zero");
      }
      if (0 == dividend || 0 == divisor) {
         return true;
      }
      
      return false;
   }
   
   int multiplyDivide(int accumulator, int element, Arithmetic operation) {
      if (true == isMultiplyDivideZero(accumulator, element, operation)) {
         return 0;
      }
      
      double accumulatorMantissa = getMantissaFromMemory(accumulator);
      int accumulatorExponent = getExponentFromMemory(accumulator);
      
      double elementMantissa = getMantissaFromMemory(element);
      int elementExponent = getExponentFromMemory(element);
      
      double valueToMemoryMantissa = getMantissaMultiplyDivide(accumulatorMantissa, elementMantissa, operation);
      int valueToMemoryExponent = getExponentMultiplyDivide(accumulatorExponent, elementExponent, operation);
      
      int valueToMemory = calculateNormalizedFloat(valueToMemoryExponent, valueToMemoryMantissa, operation);
      
      return valueToMemory;
   }
   
   @SuppressWarnings("fallthrough")
   private int getExponentMultiplyDivide(int firstExponent, int secondExponent, Arithmetic operation) {
      switch (operation) {
         case MODULO:
            throw new IllegalArgumentException("In modulo operation float's value is get direct from memory, without separate on mantissa and exponent");
            
         case DIVIDE:
            secondExponent = -secondExponent;
         case MULTIPLY:
            int exponent = firstExponent + secondExponent;
            
            return exponent;
      
         default:
            throw new IllegalArgumentException("Unrecognized arithmetic operation");
      }
   }
   
   private double getMantissaMultiplyDivide(double firstMantissa, double secondMantissa, Arithmetic operation) {
      switch (operation) {
         case DIVIDE:
            return firstMantissa / secondMantissa; // absolute value of every mantisses are in range: from 1 to less than 10
         case MULTIPLY:
            return firstMantissa * secondMantissa; // absolute value of every mantisses are in range: less than 10 and not less than 1 
                         // so their product is in range: less than 100 and not less than 1 
         case MODULO:
            throw new IllegalArgumentException("In modulo operation float's value is get direct from memory, without separate on mantissa and exponent");
         default:
            throw new IllegalArgumentException("Unrecognized arithmetic operation");
      }
   }
   
   private int calculateNormalizedFloat(int exponent, double mantissa, Arithmetic operation) {
      double absoluteMantissa = Math.abs(mantissa);
      
      int exponentDifference = (int)Math.log10(absoluteMantissa);
      mantissa = getDenormalizedMantissa(mantissa, exponentDifference, operation);
      exponent = exponent + exponentDifference;
      
      return calculateSignedFloatToMemory(exponent, mantissa);
   }
   
}
