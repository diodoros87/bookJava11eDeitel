/* =====================================================================================
 *       Filename:  IntegerMultiple.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.16 - checking multiple of integers
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import pairPackage.LongIntegersPair;
import dataFromStringPackage.GettingDataFromString;
import standardInputDataPackage.GettingDataFromStandardInput;

public class IntegerMultiple {   
   public static final String LONG_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Long.MAX_VALUE);
   public static final String LONG_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Long.MIN_VALUE);
   
   public static void main(String[] args) {
      System.out.printf("*** This program checks that first integer is divisor of second integer according to pair of integers entered by User.%n");
      
      final String QUIT_INFO = String.format("%s: %n %s", "To quit enter only End-Of-Transmission (EOT) character",
                                             "Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows");
      LongIntegersPair pair;
      GettingDataFromString stringScanner = getStringScanner(QUIT_INFO);
      
      while (null != stringScanner){
         pair = stringScanner.getOnlyLongIntegersPair();
         if (null != pair) {
            isMultipleLongIntegersPairs(pair);
            System.out.println();
         }
         stringScanner = getStringScanner(QUIT_INFO);
      }
      
      System.out.println();
   } 
   
   public static GettingDataFromString getStringScanner(String quitInfo) {
      final String PROMPT = "Enter only pair of integers separated by whitespaces: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", quitInfo, LONG_MAX_VALUE_STRING, LONG_MIN_VALUE_STRING, PROMPT);
      
      String answerString = GettingDataFromStandardInput.getStringWaitingForInput(QUIT_INFO_PROMPT);
      
      if (null == answerString) {
         return null;
      }
      
      return new GettingDataFromString(answerString);
   }
   
   public static boolean isMultipleLongIntegersPairs(LongIntegersPair pair) {
      if (null == pair) {
         System.err.printf("%n****** ERROR: Parameter pair can not be null in method isMultipleLongIntegersPairs%n");
         return false;
      }
      
      if (true == pair.isNullInPair()) {
         System.err.printf("%n****** ERROR: Every long integer in pair can not be null%n");
         return false;
      }
      
      long firstNumber = pair.getFirstNumber();
      long secondNumber = pair.getSecondNumber();
      
      if (false == isDivisorZero(firstNumber)) {
         if (true == isMultiple(firstNumber, secondNumber)) {
            if (-1 == firstNumber || 1 == firstNumber || firstNumber == secondNumber) {
               System.out.printf("%n$$$ RESULT: %d is trivial divisor of %d %n", firstNumber, secondNumber);  
            }
            else {
               System.out.printf("%n$$$ RESULT: %d is non-trivial divisor of %d %n", firstNumber, secondNumber);  
            }
            
            return true;
         }
         else {
            System.out.printf("%n$$$ RESULT: %d can not be multiplied by some integer to produce %d %n", firstNumber, secondNumber); 
         }
      }
      
      return false;
   }
   
   public static boolean isDivisorZero (long divisor) {
      if (0 == divisor) {
         System.err.printf("%n$$$ RESULT: First number as divisor (quotient) can not be zero. Any dividend divided by zero is undefined.%n");
         return true;
      }
      
      return false;
   }
   
   public static boolean isMultiple(long firstNumber, long secondNumber) {
      if (true == isDivisorZero(firstNumber)) {
         return false;
      }
      
      if (0 == secondNumber % firstNumber)
         return true;
      else 
         return false;
   }
   
}
