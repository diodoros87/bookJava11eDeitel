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
      System.out.printf("%nTESTS OF ADDING NON-negative INTEGERS : %n");
      byte signum = +1;
      AddingSubtractingTest.parseAssertOfAddingIdenticalSignum(signum);
      
      System.out.printf("%nTESTS OF ADDING NON-positive INTEGERS : %n");
      signum = -1;
      AddingSubtractingTest.parseAssertOfAddingIdenticalSignum(signum);
      
      System.out.printf("%nTESTS OF ADDING INTEGERS : %n");
      boolean adding = true;
      AddingSubtractingTest.parseAssertOfAddingSubtracting(adding);
      
      System.out.printf("%nTESTS OF SUBTRACTING INTEGERS : %n");
      adding = false;
      AddingSubtractingTest.parseAssertOfAddingSubtracting(adding);
   }
}

class AddingSubtractingTest {
      private static final HugeInteger firstStatic  = new HugeInteger();
      private static final HugeInteger secondStatic = new HugeInteger();
   
   public static void parseAssertOfAddingIdenticalSignum(byte signum) {
      String signumString = SettersTest.signumToString(signum);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "4");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "2");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "6", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "7");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "8");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "15", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, "0");
      ParsingTest.parseString("secondStatic", secondStatic, "0");
      assertOfAddingSubtracting(firstStatic, secondStatic, "0", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "45");
      ParsingTest.parseString("secondStatic", secondStatic, "0");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "45", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, "0");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "485");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "485", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "145");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "197");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "342", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "79");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "5");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "84", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "59");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "35");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "94", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "89");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "45");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "134", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "139");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "60");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "199", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "189");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "234");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "423", true);
      
      ParsingTest.parseString("firstStatic", firstStatic, signumString + "4567767189");
      ParsingTest.parseString("secondStatic", secondStatic, signumString + "34599876456");
      assertOfAddingSubtracting(firstStatic, secondStatic, signumString + "39167643645", true);
   }
   
   public static void parseAssertOfAddingSubtracting(boolean adding) {
      
      ParsingTest.parseString("firstStatic", firstStatic, "+4");
      ParsingTest.parseString("secondStatic", secondStatic, "-2");
      String result = adding == true ? "+2" : "+6";
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
}
