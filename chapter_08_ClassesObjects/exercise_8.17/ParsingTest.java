/* =====================================================================================
 *       Filename:  ParsingTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.17 - test of parsing number class


 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ParsingTest {
   public static void parseIncorrectString(String integerName, HugeInteger hugeInteger, String incorrectString) {
      try {
         hugeInteger.parse(incorrectString);
         System.out.printf("???? %s integer after parsing of \'%s\': %s%n", integerName, incorrectString, hugeInteger);
         assert(false);
      } catch (IllegalArgumentException exception) {
         System.out.printf("%nException while parsing of \'%s\': %s%n", incorrectString, exception.getMessage());
         exception.printStackTrace();
      }
   }

   public static void parseString(String integerName, HugeInteger hugeInteger, String string) {
      hugeInteger.parse(string);
      System.out.printf("%s integer after parsing of \'%s\': %s%n", integerName, string, hugeInteger);
   }

   public static void testOfParsing(String integerName, HugeInteger hugeInteger) {
      System.out.printf("%n TESTS OF PARSING STRING :%n");

      parseString(integerName, hugeInteger, "12345");
      assert(hugeInteger.toString().equals("+12345"));

      parseString(integerName, hugeInteger, "-1234565");
      assert(hugeInteger.toString().equals("-1234565"));

      parseString(integerName, hugeInteger, "+45");
      assert(hugeInteger.toString().equals("+45"));

      parseString(integerName, hugeInteger, "0");
      assert(hugeInteger.toString().equals("0"));

      parseIncorrectString(integerName, hugeInteger, "E123");
      assert(hugeInteger.toString().equals("0"));

      parseIncorrectString(integerName, hugeInteger, "p");
      assert(hugeInteger.toString().equals("0"));

      parseIncorrectString(integerName, hugeInteger, "6p");
      assert(hugeInteger.toString().equals("0"));

      parseString(integerName, hugeInteger, "10");
      assert(hugeInteger.toString().equals("+10"));

      parseString(integerName, hugeInteger, "345");
      assert(hugeInteger.toString().equals("+345"));

      parseIncorrectString(integerName, hugeInteger, "0p");
      assert(hugeInteger.toString().equals("+345"));

      parseIncorrectString(integerName, hugeInteger, "++7");
      assert(hugeInteger.toString().equals("+345"));

      parseIncorrectString(integerName, hugeInteger, "");
      assert(hugeInteger.toString().equals("+345"));

      parseIncorrectString(integerName, hugeInteger, " ");
      assert(hugeInteger.toString().equals("+345"));

      parseIncorrectString(integerName, hugeInteger, "8 ");
      assert(hugeInteger.toString().equals("+345"));

      parseIncorrectString(integerName, hugeInteger, "-0");
      assert(hugeInteger.toString().equals("+345"));

      parseString(integerName, hugeInteger, "000");
      assert(hugeInteger.toString().equals("0"));

      parseString(integerName, hugeInteger, "088");
      assert(hugeInteger.toString().equals("+88"));

      parseString(integerName, hugeInteger, "-02");
      assert(hugeInteger.toString().equals("-2"));

      parseIncorrectString(integerName, hugeInteger, "844444444444444444444444444444444444444444444444444444444444444444444444444");
      assert(hugeInteger.toString().equals("-2"));

      System.out.printf("%n ------------------------%n");
   }
}
