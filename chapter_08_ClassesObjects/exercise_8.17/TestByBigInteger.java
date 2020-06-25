/* =====================================================================================
 *       Filename:  TestByBigInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.17 - arithmetic test of number class HugeInteger 
                                by Java BigInteger


 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.math.BigInteger;
import java.util.Random;  

enum InsertDigitMode {IDENTICAL, RANDOM};

public class TestByBigInteger {
   public static final Random randomNumbers = new Random();
   private static final HugeInteger firstHugeStatic  = new HugeInteger();
   private static final HugeInteger secondHugeStatic = new HugeInteger();

   public static void main(String... args) throws Exception {
      testIntegers("+1", "0");
      testIntegers("+1098", "+56");
      testIntegers("-765441", "0");
      testIntegers("0", "+567");
      testIntegers("+99", "+56797654");
      testIntegers("-99", "+56797654");
      testIntegers("+99", "-56797654");
      testIntegers("-99", "-56797654");
      testIntegers("+235667889776554443", "-45678889");
      testIntegers("+354544364565676511909", "+554433");
      testIntegers("+1000000007777000000000", "-999990099");
      testIntegers("+1000000007777000000000", "+999990099");
      testIntegers("-1000000007777000000000", "+999990099");
      testIntegers("-1000000007777000000000", "-999990099");
      testIntegers("-100999000000", "+9009900999");
      testIntegers("+100999000000", "+9009900999");
      testIntegers("-100999000000", "-9009900999");
      testIntegers("+100999000000", "-9009900999");
      testIntegers("+40454500777700676767435604", "-99099454569857");
      testIntegers("+40454500777700676767435604", "+99099454569857");
      testIntegers("-40454500777700676767435604", "-99099454569857");
      testIntegers("-40454500777700676767435604", "+99099454569857");
      
      InsertDigitMode mode = InsertDigitMode.IDENTICAL;
      testIntegersByStringLength(HugeInteger.MAX_ARRAY_LENGTH / 2 + 7, HugeInteger.MAX_ARRAY_LENGTH / 2 - 8, mode);
      testIntegersByStringLength(HugeInteger.MAX_ARRAY_LENGTH - 1, 1, mode);
      mode = InsertDigitMode.RANDOM;
      testIntegersByStringLength(HugeInteger.MAX_ARRAY_LENGTH / 2 + 10, HugeInteger.MAX_ARRAY_LENGTH / 2 - 10, mode);
      testIntegersByStringLength(HugeInteger.MAX_ARRAY_LENGTH - 15, 14, mode);
      testIntegersByStringLength(HugeInteger.MAX_ARRAY_LENGTH / 2 + 2, HugeInteger.MAX_ARRAY_LENGTH / 2 - 5, mode);
      testIntegersByStringLength(HugeInteger.MAX_ARRAY_LENGTH / 2, 19, mode);
      testIntegersByStringLength(7, 7, mode);
      testIntegersByStringLength(12, 6, mode);
      testIntegersByStringLength(27, 7, mode);
      testIntegersByStringLength(10, 26, mode);
   }
   
   public static void testIntegersByStringLength(int firstIntegerLength, int secondIntegerLength,
                                       InsertDigitMode insertDigitMode) throws Exception {
      String firstIntegerString  = "";
      String secondIntegerString = "";
      char digitCharacter;
      String signumString;
      
      for (int signumChanging = 0; signumChanging < 4; signumChanging++) {
         
         for (int largeNumberCounter = 1; largeNumberCounter <= 9; largeNumberCounter++) {
            
            digitCharacter = ((char)('0' + largeNumberCounter));
            firstIntegerString = getDigitsString(insertDigitMode, digitCharacter, firstIntegerLength);
            
            if (signumChanging > 1) {
               signumString = "+";
            }
            else {
               signumString = "-";
            }
            
            firstIntegerString = signumString + firstIntegerString;
            
            for (int smallNumberCounter = 9; smallNumberCounter > 0; smallNumberCounter--) {
               if (signumChanging % 2 == 0) {
                  signumString = "+";
               }
               else {
                  signumString = "-";
               }
               
               digitCharacter = ((char)('0' + smallNumberCounter));
               
               secondIntegerString = getDigitsString(insertDigitMode, digitCharacter, secondIntegerLength);
               secondIntegerString = signumString + secondIntegerString;
               
               testIntegers(firstIntegerString, secondIntegerString);
               secondIntegerString = "";
            }
            
            firstIntegerString = "";
         }
      }
      
   }
   
   public static String getRandomDigitsString(int integerLength) {
      Integer randomDigit;
      String digitsString = "";
      
      for (int counter = 0; counter < integerLength; counter++) {
         randomDigit = randomNumbers.nextInt(10);
         digitsString += randomDigit.toString();
      }

      return digitsString;
   }
   
   public static String getDigitsString(InsertDigitMode insertDigitMode, char digitCharacter, int integerLength) {
      switch (insertDigitMode) {
         case IDENTICAL:
            return ArithmeticTest.getNumberString(digitCharacter, integerLength);
         case RANDOM:
            return getRandomDigitsString(integerLength);
         default:
            throw new IllegalArgumentException("Unexpected InsertDigitMode");
      }
   }

   public static void testIntegers(String firstString, String secondString) throws Exception {
      ParsingTest.parseString("firstStatic", firstHugeStatic, firstString);
      ParsingTest.parseString("secondStatic", secondHugeStatic, secondString);
      
      BigInteger firstBig  = new BigInteger(firstString);
      BigInteger secondBig = new BigInteger(secondString);
      
      testArithmetic(firstHugeStatic, secondHugeStatic, firstBig, secondBig);
      testArithmetic(secondHugeStatic, firstHugeStatic, secondBig, firstBig);
   }
   
   public static void testArithmetic(HugeInteger firstHuge, HugeInteger secondHuge, 
                                     BigInteger firstBig, BigInteger secondBig) throws Exception {
      String resultHugeIntegerString = String.format("%s", HugeInteger.add(firstHuge, secondHuge));
      System.out.printf("                   %s + %s = %s %n", firstHuge, secondHuge, resultHugeIntegerString);
      String resultBigIntegerString = formatBigInteger(firstBig.add(secondBig));
      System.out.printf("TEST by BigInteger: %+d + %+d = %s %n", firstBig, secondBig, resultBigIntegerString);
      assert(true == resultHugeIntegerString.equals(resultBigIntegerString));
      
      
      resultHugeIntegerString = new String(HugeInteger.subtract(firstHuge, secondHuge).toString());
      System.out.printf("                    %s - %s = %s %n", firstHuge, secondHuge, resultHugeIntegerString);
      resultBigIntegerString = formatBigInteger(firstBig.subtract(secondBig));
      System.out.printf("TEST by BigInteger: %+d - %+d = %s %n", firstBig, secondBig, resultBigIntegerString);
      assert(true == resultHugeIntegerString.equals(resultBigIntegerString));
      
      resultHugeIntegerString = HugeInteger.multiply(firstHuge, secondHuge).toString();
      System.out.printf("                    %s * %s = %s %n", firstHuge, secondHuge, resultHugeIntegerString);
      resultBigIntegerString = formatBigInteger(firstBig.multiply(secondBig));
      System.out.printf("TEST by BigInteger: %+d * %+d = %s %n", firstBig, secondBig, resultBigIntegerString);
      assert(true == resultHugeIntegerString.equals(resultBigIntegerString));

      testDivide(firstHuge, secondHuge, firstBig, secondBig);
      testRemainder(firstHuge, secondHuge, firstBig, secondBig);
   }
   
   public static void testDivide(HugeInteger firstHuge, HugeInteger secondHuge, 
                                 BigInteger firstBig, BigInteger secondBig) throws Exception {
      try {
         String resultHugeIntegerString = HugeInteger.divide(firstHuge, secondHuge).toString();
         System.out.printf("                   %s / %s = %s %n", firstHuge, secondHuge, resultHugeIntegerString);
         String resultBigIntegerString = formatBigInteger(firstBig.divide(secondBig));
         System.out.printf("TEST by BigInteger: %+d / %+d = %s %n", firstBig, secondBig, resultBigIntegerString);
         assert(true == resultHugeIntegerString.equals(resultBigIntegerString));
         
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while divide: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
   
   public static void testRemainder(HugeInteger firstHuge, HugeInteger secondHuge, 
                                    BigInteger firstBig, BigInteger secondBig) throws Exception {
      try {
         String resultHugeIntegerString = HugeInteger.remainder(firstHuge, secondHuge).toString();
         System.out.printf("                   %s %% %s = %s %n", firstHuge, secondHuge, resultHugeIntegerString);
         String resultBigIntegerString = formatBigInteger(firstBig.remainder(secondBig));
         System.out.printf("TEST by BigInteger: %+d %% %+d = %s %n", firstBig, secondBig, resultBigIntegerString);
         assert(true == resultHugeIntegerString.equals(resultBigIntegerString));
         
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while divide: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
   
   public static String formatBigInteger(BigInteger bigInteger) {
      if(bigInteger.equals(BigInteger.ZERO)) {
         return String.format("%d", bigInteger);
      }
      else {
         return String.format("%+d", bigInteger);
      }
   }
}
