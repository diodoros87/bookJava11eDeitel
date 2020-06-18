/* =====================================================================================
 *       FileintegerName:  ArithmeticTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.17 - arithmetic test of number class 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ArithmeticTest {
   
   public static void main(String... args) {
      AddingSubtractingTest.run();
      MultiplyingTest.run();
      
   }
   
   public static String getNumberString(char character, int length) {
      if (character < '1' || character > '9') {
         throw new IllegalArgumentException("Only digit from 1 to 9 is allowed");
      }
      if (length < 0) {
         throw new IllegalArgumentException("requirement: length >= 1");
      }
      
      String digitString = String.valueOf(character);
      String largeNumberString = "";
      for (int counter = 1; counter <= length; counter++) {
         largeNumberString += digitString;
      }
      
      return largeNumberString;
   }
}

class AddingSubtractingTest {
      private static final HugeInteger firstStatic  = new HugeInteger();
      private static final HugeInteger secondStatic = new HugeInteger();
      
   public static void run() {
      System.out.printf("%nTESTS OF ADDING NON-negative INTEGERS : %n");
      byte signum = +1;
      parseAssertOfAddingIdenticalSignum(signum);
      
      System.out.printf("%nTESTS OF ADDING NON-positive INTEGERS : %n");
      signum = -1;
      parseAssertOfAddingIdenticalSignum(signum);
      
      System.out.printf("%nTESTS OF ADDING INTEGERS : %n");
      boolean adding = true;
      parseAssertOfAddingSubtracting(adding);
      
      System.out.printf("%nTESTS OF SUBTRACTING INTEGERS : %n");
      adding = false;
      parseAssertOfAddingSubtracting(adding);
      
      testArithmeticOverflow();
   }
   
   public static void parseAssertOfAddingIdenticalSignum(byte signum) {
      String signumString = SettersTest.signumToString(signum);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "4");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "2");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "6", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "6", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "7");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "8");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "15", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "15", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, "0");
      ParsingTest.parseString("secondStatic", secondStatic, "0");
      assertOfAddingSubtracting(firstStatic, secondStatic, "0", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, "0", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "45");
      ParsingTest.parseString("secondStatic", secondStatic, "0");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "45", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "45", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, "0");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "485");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "485", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "485", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "145");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "197");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "342", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "342", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "79");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "5");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "84", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "84", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "59");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "35");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "94", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "94", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "8900");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "4500");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "13400", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "13400", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "139");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "60");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "199", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "199", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "189");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "234");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "423", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "423", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "1895678");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "234");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "1895912", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "1895912", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "4567767189");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "34599876456");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "39167643645", true);
      assertOfAddingSubtracting(secondStatic, firstStatic, signumString + "39167643645", true);
   }
   
   public static void parseAssertOfAddingSubtracting(boolean adding) {
      ParsingTest.parseString("firstStatic", firstStatic, "+4");
      ParsingTest.parseString("secondStatic", secondStatic, "0");
      String result = adding == true ? "+4" : "+4";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "+4" : "-4";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "0");
      ParsingTest.parseString("secondStatic", secondStatic, "-123");
      result = adding == true ? "-123" : "+123";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "-123" : "-123";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "+4");
      ParsingTest.parseString("secondStatic", secondStatic, "-2");
      result = adding == true ? "+2" : "+6";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "+2" : "-6";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "-7");
      ParsingTest.parseString("secondStatic", secondStatic, "+8");
      result = adding == true ? "+1" : "-15";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "+1" : "+15";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "+79");
      ParsingTest.parseString("secondStatic", secondStatic, "-5");
      result = adding == true ? "+74" : "+84";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "+74" : "-84";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "-59");
      ParsingTest.parseString("secondStatic", secondStatic, "-35");
      result = adding == true ? "-94" : "-24";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "-94" : "+24";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "+89");
      ParsingTest.parseString("secondStatic", secondStatic, "+45");
      result = adding == true ? "+134" : "+44";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "+134" : "-44";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "-139");
      ParsingTest.parseString("secondStatic", secondStatic, "+60");
      result = adding == true ? "-79" : "-199";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "-79" : "+199";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "-189");
      ParsingTest.parseString("secondStatic", secondStatic, "+234");
      result = adding == true ? "+45" : "-423";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "+45" : "+423";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "-18900");
      ParsingTest.parseString("secondStatic", secondStatic, "+234000");
      result = adding == true ? "+215100" : "-252900";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "+215100" : "+252900";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "-1894567323");
      ParsingTest.parseString("secondStatic", secondStatic, "+234123455");
      result = adding == true ? "-1660443868" : "-2128690778";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "-1660443868" : "+2128690778";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
      
      ParsingTest.parseString("firstStatic", firstStatic, "+4567767");
      ParsingTest.parseString("secondStatic", secondStatic, "-34599876");
      result = adding == true ? "-30032109" : "+39167643";
      assertOfAddingSubtracting(firstStatic, secondStatic, result, adding);
      result = adding == true ? "-30032109" : "-39167643";
      assertOfAddingSubtracting(secondStatic, firstStatic, result, adding);
   }
   
   public static void assertOfAddingSubtracting(HugeInteger first, HugeInteger second, String expectedResult, boolean adding) {
      HugeInteger result = (adding == true) ? 
                     addAndPrint(first, second) : subtractAndPrint(first, second);
      assert(result.toString().equals(expectedResult));
   }
   
   public static HugeInteger addAndPrint(HugeInteger first, HugeInteger second) {
      HugeInteger sum = HugeInteger.add(first, second);
      System.out.printf(" %s + %s = %s %n", first, second, sum);
      
      return sum;
   }
   
   public static HugeInteger subtractAndPrint(HugeInteger first, HugeInteger second) {
      HugeInteger difference = HugeInteger.subtract(first, second);
      System.out.printf(" %s - %s = %s %n", first, second, difference);
      
      return difference;
   }
   
   public static void testArithmeticOverflow() {
      String largeNumberString = ArithmeticTest.getNumberString('5', HugeInteger.MAX_ARRAY_LENGTH);
      ParsingTest.parseString("firstStatic", firstStatic, largeNumberString);
      
      largeNumberString = ArithmeticTest.getNumberString('8', HugeInteger.MAX_ARRAY_LENGTH);
      ParsingTest.parseString("secondStatic", secondStatic, largeNumberString);
      
      try {
         addAndPrint(firstStatic, secondStatic);
         assert(false);
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while adding: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
      
      byte signum = -1;
      secondStatic.setSignum(signum);
      
      try {
         subtractAndPrint(firstStatic, secondStatic);
         assert(false);
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while subtracting: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
}

class MultiplyingTest {
   private static final HugeInteger firstStatic  = new HugeInteger();
   private static final HugeInteger secondStatic = new HugeInteger();
      
   public static void run() {
      System.out.printf("%nTESTS OF MULTIPLYING NON-negative INTEGERS : %n");
      byte signum = +1;
      parseAssertOfMultiplyingIdenticalSignum(signum);
      
      System.out.printf("%nTESTS OF MULTIPLYING NON-positive INTEGERS : %n");
      signum = -1;
      parseAssertOfMultiplyingIdenticalSignum(signum);
      
      System.out.printf("%nTESTS OF MULTIPLYING INTEGERS : %n");
      parseAssertOfMultiplying();
      
      testArithmeticOverflow();
   }
   
   public static void parseAssertOfMultiplyingIdenticalSignum(byte signum) {
      String signumString = SettersTest.signumToString(signum);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "4");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "2");
      assertOfMultiplying(firstStatic, secondStatic, "+8");
      assertOfMultiplying(secondStatic, firstStatic, "+8");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "7");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "8");
      assertOfMultiplying(firstStatic, secondStatic, "+56");
      assertOfMultiplying(secondStatic, firstStatic, "+56");
      
      ParsingTest.parseString("firstStatic", firstStatic, "0");
      ParsingTest.parseString("secondStatic", secondStatic, "0");
      assertOfMultiplying(firstStatic, secondStatic, "0");
      assertOfMultiplying(secondStatic, firstStatic, "0");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "145");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "197");
      assertOfMultiplying(firstStatic, secondStatic, "+28565");
      assertOfMultiplying(secondStatic, firstStatic, "+28565");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "79");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "5");
      assertOfMultiplying(firstStatic, secondStatic, "+395");
      assertOfMultiplying(secondStatic, firstStatic, "+395");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "59");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "35");
      assertOfMultiplying(firstStatic, secondStatic, "+2065");
      assertOfMultiplying(secondStatic, firstStatic, "+2065");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "89");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "45");
      assertOfMultiplying(firstStatic, secondStatic, "+4005");
      assertOfMultiplying(secondStatic, firstStatic, "+4005");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "139");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "60");
      assertOfMultiplying(firstStatic, secondStatic, "+8340");
      assertOfMultiplying(secondStatic, firstStatic, "+8340");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "189");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "234");
      assertOfMultiplying(firstStatic, secondStatic, "+44226");
      assertOfMultiplying(secondStatic, firstStatic, "+44226");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "8900");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "45000");
      assertOfMultiplying(firstStatic, secondStatic, "+400500000");
      assertOfMultiplying(secondStatic, firstStatic, "+400500000");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "13999");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "6556077");
      assertOfMultiplying(firstStatic, secondStatic, "+91778521923");
      assertOfMultiplying(secondStatic, firstStatic, "+91778521923");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "76189");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "4234");
      assertOfMultiplying(firstStatic, secondStatic, "+322584226");
      assertOfMultiplying(secondStatic, firstStatic, "+322584226");
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "45671000");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "34590");
      assertOfMultiplying(firstStatic, secondStatic, "+1579759890000");
      assertOfMultiplying(secondStatic, firstStatic, "+1579759890000");
   }
   
   public static void parseAssertOfMultiplying() {
      ParsingTest.parseString("firstStatic", firstStatic, "+4");
      ParsingTest.parseString("secondStatic", secondStatic, "0");
      assertOfMultiplying(firstStatic, secondStatic, "0");
      assertOfMultiplying(secondStatic, firstStatic, "0");
      
      ParsingTest.parseString("firstStatic", firstStatic, "+45");
      ParsingTest.parseString("secondStatic", secondStatic, "0");
      assertOfMultiplying(firstStatic, secondStatic, "0");
      assertOfMultiplying(secondStatic, firstStatic, "0");
      
      ParsingTest.parseString("firstStatic", firstStatic, "0");
      ParsingTest.parseString("secondStatic", secondStatic, "+485");
      assertOfMultiplying(firstStatic, secondStatic, "0");
      assertOfMultiplying(secondStatic, firstStatic, "0");
      
      ParsingTest.parseString("firstStatic", firstStatic, "0");
      ParsingTest.parseString("secondStatic", secondStatic, "-123");
      assertOfMultiplying(firstStatic, secondStatic, "0");
      assertOfMultiplying(secondStatic, firstStatic, "0");
      
      ParsingTest.parseString("firstStatic", firstStatic, "+4");
      ParsingTest.parseString("secondStatic", secondStatic, "-2");
      assertOfMultiplying(firstStatic, secondStatic, "-8");
      assertOfMultiplying(secondStatic, firstStatic, "-8");
      
      ParsingTest.parseString("firstStatic", firstStatic, "-7");
      ParsingTest.parseString("secondStatic", secondStatic, "+8");
      assertOfMultiplying(firstStatic, secondStatic, "-56");
      assertOfMultiplying(secondStatic, firstStatic, "-56");
      
      ParsingTest.parseString("firstStatic", firstStatic, "+189");
      ParsingTest.parseString("secondStatic", secondStatic, "-234");
      assertOfMultiplying(firstStatic, secondStatic, "-44226");
      assertOfMultiplying(secondStatic, firstStatic, "-44226");
      
      ParsingTest.parseString("firstStatic", firstStatic, "+79678");
      ParsingTest.parseString("secondStatic", secondStatic, "-554356");
      assertOfMultiplying(firstStatic, secondStatic, "-44169977368");
      assertOfMultiplying(secondStatic, firstStatic, "-44169977368");
      
      ParsingTest.parseString("firstStatic", firstStatic, "-59897324");
      ParsingTest.parseString("secondStatic", secondStatic, "+35");
      assertOfMultiplying(firstStatic, secondStatic, "-2096406340");
      assertOfMultiplying(secondStatic, firstStatic, "-2096406340");
      
      ParsingTest.parseString("firstStatic", firstStatic, "+8900");
      ParsingTest.parseString("secondStatic", secondStatic, "-45000");
      assertOfMultiplying(firstStatic, secondStatic, "-400500000");
      assertOfMultiplying(secondStatic, firstStatic, "-400500000");
      
      ParsingTest.parseString("firstStatic", firstStatic, "-13999");
      ParsingTest.parseString("secondStatic", secondStatic, "+6556077");
      assertOfMultiplying(firstStatic, secondStatic, "-91778521923");
      assertOfMultiplying(secondStatic, firstStatic, "-91778521923");
      
      ParsingTest.parseString("firstStatic", firstStatic, "-76189");
      ParsingTest.parseString("secondStatic", secondStatic, "+4234");
      assertOfMultiplying(firstStatic, secondStatic, "-322584226");
      assertOfMultiplying(secondStatic, firstStatic, "-322584226");
      
      ParsingTest.parseString("firstStatic", firstStatic, "+45671000");
      ParsingTest.parseString("secondStatic", secondStatic, "-34590");
      assertOfMultiplying(firstStatic, secondStatic, "-1579759890000");
      assertOfMultiplying(secondStatic, firstStatic, "-1579759890000");
   }
   
   public static void assertOfMultiplying(HugeInteger first, HugeInteger second, String expectedResult) {
      HugeInteger result = multiplyAndPrint(first, second);
      assert(result.toString().equals(expectedResult));
   }
   
   public static HugeInteger multiplyAndPrint(HugeInteger first, HugeInteger second) {
      HugeInteger product = HugeInteger.multiply(first, second);
      System.out.printf(" %s * %s = %s %n", first, second, product);
      
      return product;
   }
   
   public static void testArithmeticOverflow() {
      String largeNumberString = ArithmeticTest.getNumberString('8', HugeInteger.MAX_ARRAY_LENGTH);
      ParsingTest.parseString("firstStatic", firstStatic, largeNumberString);
   
      ParsingTest.parseString("secondStatic", secondStatic, "1234");
      
      try {
         multiplyAndPrint(firstStatic, secondStatic);
         assert(false);
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while multiplying: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
      
      byte signum = -1;
      secondStatic.setSignum(signum);
      
      try {
         multiplyAndPrint(secondStatic, firstStatic);
         assert(false);
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while multiplying: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
      
      largeNumberString = ArithmeticTest.getNumberString('1', HugeInteger.MAX_ARRAY_LENGTH / 2);
      ParsingTest.parseString("firstStatic", firstStatic, largeNumberString);
      
      String otherNumberString = ArithmeticTest.getNumberString('1', HugeInteger.MAX_ARRAY_LENGTH / 2);
      ParsingTest.parseString("secondStatic", secondStatic, otherNumberString);
      signum = -1;
      secondStatic.setSignum(signum);
      
      assertOfMultiplying(firstStatic, secondStatic, "-123456790123456790120987654320987654321");
      assertOfMultiplying(secondStatic, firstStatic, "-123456790123456790120987654320987654321");
      
      otherNumberString = ArithmeticTest.getNumberString('1', HugeInteger.MAX_ARRAY_LENGTH / 2 + 1);
      ParsingTest.parseString("secondStatic", secondStatic, otherNumberString);
      
      assertOfMultiplying(firstStatic, secondStatic, "+1234567901234567901220987654320987654321");
      assertOfMultiplying(secondStatic, firstStatic, "+1234567901234567901220987654320987654321");
      
      largeNumberString = ArithmeticTest.getNumberString('1', HugeInteger.MAX_ARRAY_LENGTH / 2 + 1);
      ParsingTest.parseString("firstStatic", firstStatic, largeNumberString);
      
      try {
         multiplyAndPrint(firstStatic, secondStatic);
         assert(false);
      } catch (ArithmeticException exception) {
         System.out.printf("%nException while multiplying: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
}
