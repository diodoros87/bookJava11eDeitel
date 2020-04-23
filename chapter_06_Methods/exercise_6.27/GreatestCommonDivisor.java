/* =====================================================================================
 *       Filename:  GreatestCommonDivisor.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.27 -searching greatest common divisor of
                                2 integers which:
                                 - one must be greater than zero,
                                 - other can not be less than zero
                        
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import pairPackage.LongIntegersPair;
import dataFromStringPackage.GettingDataFromString;
import standardInputDataPackage.GettingDataFromStandardInput;

public class GreatestCommonDivisor {   
   public static final String LONG_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Long.MAX_VALUE);
   public static final String LONG_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Long.MIN_VALUE);
   
   public static void main(String[] args) {
      System.out.printf("*** This program search greatest common divisor of 2 integers which:%n");
      System.out.println(" one must be greater than zero, other can not be less than zero");
      
      final String QUIT_INFO = String.format("%s: %n %s", "To quit enter only End-Of-Transmission (EOT) character",
                                             "Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows");
      LongIntegersPair pair;
      GettingDataFromString stringScanner = getStringScanner(QUIT_INFO);
      
      while (null != stringScanner){
         pair = stringScanner.getOnlyLongIntegersPair();
         if (null != pair) {
            printGCD(pair);
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
   
   public static void printGCD(LongIntegersPair pair) {
      if (null == pair) {
         System.err.printf("%n****** ERROR: Parameter pair can not be null in method printGCD%n");
         return;
      }
      
      if (true == pair.isNullInPair()) {
         System.err.printf("%n****** ERROR: Every long integer in pair can not be null%n");
         return;
      }
      
      long firstNumber = pair.getFirstNumber();
      long secondNumber = pair.getSecondNumber();
      
      if (0 == firstNumber && 0 == secondNumber) {
         System.err.printf("%n*** ERROR: greatest common divisor for 0 and 0 is not calculated.");
         System.err.printf("%nAny dividend divided by zero is undefined.%n");
         return;
      }
      
      if (0 > firstNumber || 0 > secondNumber) {
         System.err.printf("%n^^^ INFO: in this program gcd is not calculated for number less than zero%n");
         return;
      }
      
      printPositiveDivisors(firstNumber);
      printPositiveDivisors(secondNumber);
      
      System.out.printf("%n### RESULT: for %d and %d greatest common divisor is %d%n",
                                       firstNumber, secondNumber, gcd(firstNumber, secondNumber)); 
      
      System.out.printf("%n>>> RESULT : for %d and %d gcd by subtraction version is %d%n",
                           firstNumber, secondNumber, gcdSubtractionVersion(firstNumber, secondNumber));
                           
      System.out.printf("%n$$$ RESULT : for %d and %d gcd by modulo version is %d%n",
                           firstNumber, secondNumber, gcdModuloVersion(firstNumber, secondNumber));
      
    }
    
    public static void printPositiveDivisors(long number) {
      if (0 >= number) {
         return;
      }
      
      System.out.printf("%nDivisors for %d:%n", number); 

      for (long divisor = 1; divisor * divisor <= number; divisor++) {
         if (number % divisor == 0) {
            System.out.printf("%d = %d * %d %n", number, number / divisor, divisor);
         }
      }
      
    }
    
   public static boolean gcdParametesValidation(long firstNumber, long secondNumber) {
      if (0 == firstNumber && 0 == secondNumber) {
         System.err.printf("%n*** ERROR: greatest common divisor for 0 and 0 is not calculated.");
         System.err.printf("%nAny dividend divided by zero is undefined.%n");
         abnormalTermination();
         return false;  
      }
      
      if (0 > firstNumber || 0 > secondNumber) {
         System.err.printf("%n^^^ INFO: in this program gcd is not calculated for number less than zero%n");
         abnormalTermination();
         return false;  
      }
      
      return true;
   }
   
   public static long gcdSubtractionVersion(long firstNumber, long secondNumber) {
      gcdParametesValidation(firstNumber, secondNumber);
      
      if (0 == firstNumber) {
         return secondNumber;
      }
      if (0 == secondNumber) {
         return firstNumber;
      }
      
      while (firstNumber != secondNumber) {
         if (firstNumber > secondNumber) {
            firstNumber = firstNumber - secondNumber;
         }
         else {
            secondNumber = secondNumber - firstNumber;
         }
      }
      
      return firstNumber;
   }
   
   public static long gcdModuloVersion(long firstNumber, long secondNumber) {
      gcdParametesValidation(firstNumber, secondNumber);
      
      long temporary;
      
      while (secondNumber != 0) {
         temporary = firstNumber % secondNumber;
         firstNumber = secondNumber;
         secondNumber = temporary;
      }
      
      return firstNumber;
   }
    
   public static long gcd(long firstNumber, long secondNumber) {
      gcdParametesValidation(firstNumber, secondNumber);
      
      if (0 == firstNumber) {
         return secondNumber;
      }
      
      if (0 == secondNumber) {
         return firstNumber;
      }
      
      long notSmallerNumber;
      long notGreaterNumber;
      
      if (firstNumber > secondNumber) {
         notSmallerNumber = firstNumber;
         notGreaterNumber = secondNumber;
      }
      else {
         notSmallerNumber = secondNumber;
         notGreaterNumber = firstNumber;
      }
      
      long counter = 1;
      long divisor = 1;
      
      while (counter * counter <= notGreaterNumber) {
         if (notGreaterNumber % counter == 0) {
            divisor = notGreaterNumber / counter;
            if (notSmallerNumber % divisor == 0) {
               return divisor;
            }
         }
         counter++;
      }
      
      while (--counter >= 1) {
         if (notGreaterNumber % counter == 0) {
            if (notSmallerNumber % counter == 0) {
               return counter;
            }
         }
      }
      
      return 1;
   }
   
   private static final int abnormalTerminationCode = 1; // status code indicates abnormal termination
   
   public static void abnormalTermination() {
      System.out.println("****** Program is interrupted ");
      System.exit(abnormalTerminationCode);
   }
   
}
