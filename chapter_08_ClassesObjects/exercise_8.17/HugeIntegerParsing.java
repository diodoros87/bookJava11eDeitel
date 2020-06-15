/* =====================================================================================
 *       Filename:  HugeIntegerParsing.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.17 - utility class to help creating HugeInteger
                                object from parsed string  
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class HugeIntegerParsing {

   public static void validateString(String validatedString) {
      if (null == validatedString) {
         throw new NullPointerException("Requirement: reference to string can not be null");
      }
      final int MAX_STRING_LENGTH       = HugeInteger.MAX_ARRAY_LENGTH + 1;
      final int VALIDATED_STRING_LENGTH = validatedString.length();
      
      if (VALIDATED_STRING_LENGTH > MAX_STRING_LENGTH) {
         throw new IllegalArgumentException(String.format("Requirement: integer's string.length <= %d", MAX_STRING_LENGTH));
      }
      if (VALIDATED_STRING_LENGTH == 0) {
         throw new IllegalArgumentException("Requirement: integer's string can not be empty");
      }
      
      char firstCharacter   = validatedString.charAt(0);
      if (VALIDATED_STRING_LENGTH == 1) {
         if (false == Character.isDigit(firstCharacter)) {
            throw new IllegalArgumentException("Requirement: string must contains only integer number without other characters");
         }
      }
      else {
         String remainedString = validatedString.substring(1);  // 
         
         if (false == isIntegerCharacter(firstCharacter) || false == isStringContainsOnlyDigits(remainedString)) {
            
            throw new IllegalArgumentException("Requirement: string must contains only integer number without other characters");
         }
         
         validateSignum(remainedString, firstCharacter);
      }
   }
   
   public static boolean isIntegerCharacter(char character) {
      if (Character.isDigit(character) || character == '+' || character == '-') {
         return true;
      }
      
      return false;
   }
   
   public static boolean isStringContainsOnlyDigits(String string) {
      if (null == string) {
         throw new NullPointerException("Requirement: reference to string can not be null");
      }
      
      final int STRING_LENGTH = string.length();
      char      character;
      for (int index = 0; index < STRING_LENGTH; index++) {
         character = string.charAt(index);
         if (false == Character.isDigit(character)) {
            return false;
         }
      }
      
      return 0 == STRING_LENGTH ? false : true;
   }
   
   private static void validateSignum(String string, char firstCharacter) {
      boolean stringContainsOnlyZeros = isStringContainsOnlyValue(string, '0');
      
      if (true == stringContainsOnlyZeros && ('+' == firstCharacter ||  '-' == firstCharacter)) {
         throw new IllegalArgumentException("Requirement: string can not contain signum for zero integer");
      }
   }
   
   public static boolean isStringContainsOnlyValue(String string, char value) {
      if (null == string) {
         throw new NullPointerException("Requirement: reference to string can not be null");
      }
      
      final int STRING_LENGTH = string.length();
      char      character;
      for (int index = 0; index < STRING_LENGTH; index++) {
         character = string.charAt(index);
         if (value != character) {
         
            return false;
         }
      }
      
      return 0 == STRING_LENGTH ? false : true;
   }
   
   public static byte skipLeadingIntegersWithValue(byte[] integerArray, byte skippedValue) {
      if (null == integerArray) {
         throw new NullPointerException("Requirement: reference to array can not be null");
      }
      
      byte index = 0;
      while (index < integerArray.length - 1) {
         if (skippedValue == integerArray[index]) {
            index++;
         }
         else {
            break;
         }
      }
      
      return index;
   }
   
   
} 
