/* =====================================================================================
 *       FileintegerName:  HugeIntegerTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.17 - test of  number class 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class HugeIntegerTest {

   public static void main(String[] args) throws Exception {
      ConstructorTest.testConstructors();
      
      byte[] numbers = {0, 2, 3, 0, 6};
      byte signum = -1;
      HugeInteger first  = new HugeInteger(numbers, (byte)-1);
      System.out.printf("First integer after construct by array and signum's byte of %+d: %s%n", signum, first);
      assert(first.toString().equals("-2306"));
      
      byte[] array = {4, 2, 3, 0, 7, 4};
      first.setIntegerArray(array);
      System.out.printf("First integer after set other array: %s%n", first);
      assert(first.toString().equals("-423074"));
      
      try {
         byte[] minusNumberArray = {4, -2, 3, 0, 7, 4};
         first.setIntegerArray(minusNumberArray);
         System.out.printf("First integer after set minusNumberArray: %s%n", first);
         assert(false);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct integer: %s%n", exception.getMessage());
         exception.printStackTrace();
         System.out.printf("First integer after exception catch: %s%n", first);
      }
      
      byte[] firstIntegerArrayCopy = first.getIntegerArrayCopy(); 
      printArray("Copy of first integer array:", firstIntegerArrayCopy);
      firstIntegerArrayCopy[14] = firstIntegerArrayCopy[11] = firstIntegerArrayCopy[12] = 9;
      printArray("Copy of first integer array after change:", firstIntegerArrayCopy);
      printArray("First integer array:", first.getIntegerArrayCopy());
      
      SettersTest.testSetArray("first", first);
      
      HugeInteger second  = new HugeInteger(numbers, (byte)-1);
      assert(second.toString().equals("-2306"));
      SettersTest.testSetArray("second", second);
      
      ParsingTest.testOfParsing("first", first);
      ParsingTest.testOfParsing("second", second);
      
      ComparingTest.compare(first, second);
      assert(first.isZero() == false);
      assert(second.isZero() == false);
      
      assert(first.isEqualTo(second) == true);
      assert(first.isNotEqualTo(second) == false);
      
      assert(first.isGreaterThan(second) == false);
      assert(first.isLessThanOrEqualTo(second) == true);
      
      assert(first.isLessThan(second) == false);
      assert(first.isGreaterThanOrEqualTo(second) == true);
      
      ComparingTest.assertOfComparingDifferentSignum(first, second);
      ComparingTest.assertOfComparingIdenticalSignum(first, second);
      ComparingTest.assertOfComparingDifferentSignumZero(first, second);
      
      ArithmeticTest.main();
   }

   public static void printArray(String title, byte[] array) {
      System.out.println(title);
      for (byte number : array) {
         System.out.printf("%d ", number);
      }
      System.out.println();
      System.out.println("End of array printing");
   }
} 

class ConstructorTest {
   public static void testConstructors() {
      System.out.printf("%n CORRECT CONSTRUCT OF OBJECTS:%n");
      HugeInteger first  = new HugeInteger();
      System.out.printf("After construct by default constructor: %s%n", first);
      assert(first.toString().equals("0"));
      
      byte[] numbers = {1, 2, 3, 0, 6};
      byte signum = +1;
      HugeInteger second  = new HugeInteger(numbers, signum);
      System.out.printf("After construct by array and signum's byte of %+d: %s%n", signum, second);
      assert(second.toString().equals("+12306"));

      HugeInteger third  = new HugeInteger(second);
      System.out.printf("After construct by other integer: %s%n", third);
      assert(third.toString().equals("+12306"));
      
      System.out.printf("%n INCORRECT ATTEMPTS TO CONSTRUCT OF OBJECTS:%n");
      testIncorrectConstruct("with null array", null, (byte)0);
      
      byte[] tooLargeArraySize = new byte[HugeInteger.MAX_ARRAY_LENGTH + 3];
      testIncorrectConstruct("with too large array's size", tooLargeArraySize, (byte)0);
      
      byte[] nonDigitsArray = {11, 2, 5, 0, 8};
      testIncorrectConstruct("with non digit element in array", nonDigitsArray, (byte)0);
      
      byte[] minusArray = {1, -2, 5, 0, 8};
      testIncorrectConstruct("with minus digit element in array", minusArray, (byte)0);
      
      byte[] zeroArray = {0, 0, 0, 0, 0};
      signum = +1;
      testIncorrectConstruct(String.format("with incorrect signum %+d for zero array", signum), zeroArray, signum);
      
      byte[] nonZeroArray = {0, 0, 0, 0, 1};
      signum = 0;
      testIncorrectConstruct(String.format("with incorrect signum %+d for non-zero array", signum), nonZeroArray, signum);
      
      System.out.printf("%n ------------------------%n");
   }
   
   public static void testIncorrectConstruct(String exceptionInfo, byte[] integerArray, byte signum) {
      try {
         HugeInteger hugeInteger = new HugeInteger(integerArray, signum);
         System.out.printf("???  number after construct %s: %s%n", exceptionInfo, hugeInteger);
         assert(false);
      } catch (NullPointerException exception) {
         System.out.printf("%nException while construct integer \'%s\': %s%n", exceptionInfo, exception.getMessage());
         exception.printStackTrace();
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct integer \'%s\': %s%n", exceptionInfo, exception.getMessage());
         exception.printStackTrace();
      }
   }
}

class SettersTest {
   private static void assertHugeInteger(HugeInteger hugeInteger, String expectedString) {
      byte signum = hugeInteger.getSignum();
      String signumToString = signumToString(signum);
      String wholeExpectedString = signumToString + expectedString;
      String realString = hugeInteger.toString();
      assert(realString.equals(wholeExpectedString));
   }
   
   static String signumToString(byte signum) {
      if (signum > 0) {
         return "+";
      }
      else if (signum == 0) {
         return "";
      }
      else {
         return "-";
      }
   }
   
   public static void testSetArray(String integerName, HugeInteger hugeInteger) {
      System.out.printf("%n TESTS OF SET ARRAY OR SIGNUM :%n");
      
      byte[] array1 = {4, 9};
      hugeInteger.setIntegerArray(array1);
      System.out.printf("%s integer after set other array: %s%n", integerName, hugeInteger);
      assertHugeInteger(hugeInteger, "49");
      
      byte[] array2 = {4, 2, 3};
      hugeInteger.setIntegerArray(array2);
      System.out.printf("%s integer after set other array: %s%n", integerName, hugeInteger);
      assertHugeInteger(hugeInteger, "423");
      
      byte[] array3 = {4};
      hugeInteger.setIntegerArray(array3);
      System.out.printf("%s integer after set other array: %s%n", integerName, hugeInteger);
      assertHugeInteger(hugeInteger, "4");
      
      byte[] array4 = {0};
      hugeInteger.setIntegerArray(array4);
      System.out.printf("%s integer after set other array: %s%n", integerName, hugeInteger);
      assert(hugeInteger.toString().equals("0"));
      
      byte signum = +1;
      try {
         hugeInteger.setSignum(signum);
         System.out.printf("%s integer after set signum to %+d: %s%n", integerName, signum, hugeInteger);
         assert(false);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while set signum to %+d: %s%n", signum, exception.getMessage());
         exception.printStackTrace();
      }
      
      byte[] array5 = {3, 5, 7};
      hugeInteger.setIntegerArray(array5);
      System.out.printf("%s integer after set other array: %s%n", integerName, hugeInteger);
      assert(hugeInteger.toString().equals("+357"));
      
      signum = +1;
      hugeInteger.setSignum(signum);
      System.out.printf("%s integer after set signum to %+d: %s%n", integerName, signum, hugeInteger);
      assert(hugeInteger.toString().equals("+357"));
      
      signum = -1;
      hugeInteger.setSignum(signum);
      System.out.printf("%s integer after set signum to %+d: %s%n", integerName, signum, hugeInteger);
      assert(hugeInteger.toString().equals("-357"));
      
      try {
         signum = 0;
         hugeInteger.setSignum(signum);
         System.out.printf("%s integer after set signum to %+d: %s%n", integerName, signum, hugeInteger);
         assert(false);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while set signum to %+d: %s%n", signum, exception.getMessage());
         exception.printStackTrace();
         assert(hugeInteger.toString().equals("-357"));
      }
      
      hugeInteger.setIntegerArray(array4);
      System.out.printf("%s integer after set other array: %s%n", integerName, hugeInteger);
      assert(hugeInteger.toString().equals("0"));
      signum = 0;
      hugeInteger.setSignum(signum);
      System.out.printf("%s integer after set signum to %+d: %s%n", integerName, signum, hugeInteger);
      assert(hugeInteger.toString().equals("0"));
      
      System.out.printf("%n ------------------------%n");
   }
}

class ComparingTest {
   public static void compare(HugeInteger first, HugeInteger second) throws Exception  {
      System.out.printf("%n COMPARE OF INTEGERS:%n");
      System.out.printf("%s %n", first);
      System.out.printf("%s %n", second);
      
      System.out.printf("%s is zero: %b %n", first, first.isZero());
      System.out.printf("%s is zero: %b %n", second, second.isZero());
      
      System.out.printf("%s is equal to %s: %b %n", first, second, first.isEqualTo(second));
      System.out.printf("%s is not equal to %s: %b %n", first, second, first.isNotEqualTo(second));
      System.out.printf("%s is greater than %s: %b %n", first, second, first.isGreaterThan(second));
      System.out.printf("%s is less than or equal to %s: %b %n", first, second, first.isLessThanOrEqualTo(second));
      System.out.printf("%s is less than %s: %b %n", first, second, first.isLessThan(second));
      System.out.printf("%s is greater than or equal to %s: %b %n", first, second, first.isGreaterThanOrEqualTo(second));
   }
   
   public static void assertOfComparingDifferentSignum(HugeInteger first, HugeInteger second) throws Exception {
      ParsingTest.parseString("first", first, "554");
      assert(first.toString().equals("+554"));
      
      ParsingTest.parseString("second", second, "-8890");
      assert(second.toString().equals("-8890"));
      
      compare(first, second);
      assert(first.isZero() == false);
      assert(second.isZero() == false);
      
      assert(first.isEqualTo(second) == false);
      assert(first.isNotEqualTo(second) == true);
      
      assert(first.isGreaterThan(second) == true);
      assert(first.isLessThanOrEqualTo(second) == false);
      
      assert(first.isLessThan(second) == false);
      assert(first.isGreaterThanOrEqualTo(second) == true);
      
      first.negate();
      assert(first.toString().equals("-554"));
      
      second = HugeInteger.negate(second);
      assert(second.toString().equals("+8890"));
      
      compare(first, second);
      assert(first.isZero() == false);
      assert(second.isZero() == false);
      
      assert(first.isEqualTo(second) == false);
      assert(first.isNotEqualTo(second) == true);
      
      assert(first.isGreaterThan(second) == false);
      assert(first.isLessThanOrEqualTo(second) == true);
      
      assert(first.isLessThan(second) == true);
      assert(first.isGreaterThanOrEqualTo(second) == false);
   }
   
   public static void assertOfComparingDifferentSignumZero(HugeInteger first, HugeInteger second) throws Exception {
      ParsingTest.parseString("first", first, "+7");
      assert(first.toString().equals("+7"));
      
      ParsingTest.parseString("second", second, "0");
      assert(second.toString().equals("0"));
      
      compare(first, second);
      assert(first.isZero() == false);
      assert(second.isZero() == true);
      
      assert(first.isEqualTo(second) == false);
      assert(first.isNotEqualTo(second) == true);
      
      assert(first.isGreaterThan(second) == true);
      assert(first.isLessThanOrEqualTo(second) == false);
      
      assert(first.isLessThan(second) == false);
      assert(first.isGreaterThanOrEqualTo(second) == true);
      
      first.negate();
      assert(first.toString().equals("-7"));
      
      second = HugeInteger.negate(second);
      assert(second.toString().equals("0"));
      
      compare(first, second);
      assert(first.isZero() == false);
      assert(second.isZero() == true);
      
      assert(first.isEqualTo(second) == false);
      assert(first.isNotEqualTo(second) == true);
      
      assert(first.isGreaterThan(second) == false);
      assert(first.isLessThanOrEqualTo(second) == true);
      
      assert(first.isLessThan(second) == true);
      assert(first.isGreaterThanOrEqualTo(second) == false);
   }
   
   public static void assertOfComparingIdenticalSignum(HugeInteger first, HugeInteger second) throws Exception {
      ParsingTest.parseString("first", first, "99787");
      assert(first.toString().equals("+99787"));
      
      ParsingTest.parseString("second", second, "+87");
      assert(second.toString().equals("+87"));
      
      compare(first, second);
      assert(first.isZero() == false);
      assert(second.isZero() == false);
      
      assert(first.isEqualTo(second) == false);
      assert(first.isNotEqualTo(second) == true);
      
      assert(first.isGreaterThan(second) == true);
      assert(first.isLessThanOrEqualTo(second) == false);
      
      assert(first.isLessThan(second) == false);
      assert(first.isGreaterThanOrEqualTo(second) == true);
      
      first.negate();
      assert(first.toString().equals("-99787"));
      
      second = HugeInteger.negate(second);
      assert(second.toString().equals("-87"));
      
      compare(first, second);
      assert(first.isZero() == false);
      assert(second.isZero() == false);
      
      assert(first.isEqualTo(second) == false);
      assert(first.isNotEqualTo(second) == true);
      
      assert(first.isGreaterThan(second) == false);
      assert(first.isLessThanOrEqualTo(second) == true);
      
      assert(first.isLessThan(second) == true);
      assert(first.isGreaterThanOrEqualTo(second) == false);
   }
}
