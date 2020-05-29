/* =====================================================================================
 *       Filename:  IntegerNumbers.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.38 - utility class to numbers calculations,
                                number formats conversions
                                
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package simpletron;

public class IntegerNumbers {
   
   public static int integerPower(int base, short exponent) {
      parametersValidation(base, exponent);
      
      if (0 == base) {
         return 0;
      }
      else {
         int value = 1;
         
         for (short counter = 1; counter <= exponent; counter++) {
            value *= base;
         }
         
         return value;
      }
   }
   
   public static void parametersValidation(int base, short exponent) {
      if (0 > exponent) {
         throw new IllegalArgumentException("****** ERROR: The case is more complicated. Exponent must be integer not less than zero");
      }
      else if (0 == base && 0 == exponent) {
         throw new IllegalArgumentException("****** ERROR: The case is more complicated");
      }
   }
   
} 

class HexToInt {

   public static void validateHexToInt(String inputHexString, int integer) {
      int correctInteger = Integer.parseUnsignedInt(inputHexString, 16);
      
      if (correctInteger != integer) {
         throw new IllegalArgumentException (String.format("Incorrect conversion from hex string %s to integer %d, should be: %d",
                  inputHexString, integer, correctInteger));
      }
   }
   
   public static boolean isHexFormat(String string) {
      char[] charsArray = string.toCharArray();
      
      for (char character : charsArray) {
         if (false == isHexValue(character)) {
            return false;
         }
      }
      
      return true;
   }
   
   public static boolean isHexValue(char character) {
      if ('0' <= character && character <= '9') {
         return true;
      }
      if ('A' <= character && character <= 'F') {
         return true;
      }
      if ('a' <= character && character <= 'f') {
         return true;
      }
      
      return false;
   }
   
   public static int calculateDecimalFromHex(String hexString) {
      final int hexStringLength = hexString.length();
      int result = 0;
      int exponent = 0;
      
      for (int index = hexStringLength - 1; index >= 0; index--, exponent++) {
         char character = hexString.charAt(index);
         int value = getDecimalFromHex(character);
         
         result += Math.pow(16, exponent) * value; 
      }
      
      validateHexToInt(hexString, result);
      
      return result;
   }
   
   public static int getDecimalFromHex(char character) {
      int characterCodeValue = (int) character;
      
      if ('0' <= character && character <= '9') {
         int characterZeroValue = (int) ('0');
         
         return (int)character - characterZeroValue;
      }
      
      if ('A' <= character && character <= 'F') {
         return 10 + (int)character - (int) ('A');
      }
      
      if ('a' <= character && character <= 'f') {
         return 10 + (int)character - (int) ('a');
      }
      
      throw new IllegalArgumentException(String.format("\'%c\' is not included to hex format representation", character));
   }
}

class IntToHex {

   public static String getHexString (int integer) {
      String result = "";
      int value;
      String valueString;
      long longInteger = integer;
      
      if (longInteger < 0) {
         longInteger = (long)Math.pow(2, 32) + longInteger;  // int in Java is size of 32 bits 
         // above is formula of presentation int values in system of two's complement
      }
      
      do {
         value = (int)(longInteger % 16);
         
         valueString = getHexValue(value);
         
         result = valueString + result;
         longInteger /= 16;
      } while (longInteger > 0);
      
      validateIntToHex(integer, result);
      
      return result;
   }
   
   public static void validateIntToHex(int inputInteger, String hexString) {
      String correctHexString = Integer.toHexString(inputInteger).toLowerCase();
      
      if (false == correctHexString.equals(hexString)) {
         throw new IllegalArgumentException (String.format("Incorrect conversion from integer %d to hex string %s, should be: %s",
                  inputInteger, hexString, correctHexString));
      }
   }
   
   public static String getHexValue (int value) {
      if (value >= 0 && value <= 9) {
         return ((Integer)value).toString();
      }
      
      switch (value) {
         case 10:
            return "a";
         case 11:
            return "b";
         case 12:
            return "c";
         case 13:
            return "d";
         case 14:
            return "e";
         case 15:
            return "f";
         default:
            throw new IllegalArgumentException ("In method getHexValue parameter value must be in range from 0 to 15 ");
      }
   }
   
}
