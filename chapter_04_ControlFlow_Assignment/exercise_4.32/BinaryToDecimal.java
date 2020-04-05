/* =====================================================================================
 *       Filename:  BinaryToDecimal.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.32 - class of calculate decimal value of 
                                binary integer
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class BinaryToDecimal {

   public static final String maxBinaryValue = "01111111";   // as decimal 127
   public static final String minBinaryValue = "10000000";   // as decimal -128

   public static final short maxBinaryAsDecimalValue = 127;
   public static final short minBinaryAsDecimalValue = -128;
   public static final short maxBitsNumber = 8;
   
   public static final String bitsNumberErrorString = String.format("Entered integer can not contains more than %d digits %n", maxBitsNumber);
   public static final String nonBinaryFormatErrorString = "Entered integer must contains only digits 1 or 0 ";
   
   private static int binaryInteger= 54329786; // improper data
   
   private static boolean correctBitsNumber = true;
   private static boolean correctBinaryFormat = true;
   
   public static void resetDataForNextInteger() {
      binaryInteger = 97865432; // improper data
      correctBitsNumber = true;
      correctBinaryFormat = true;
   }
   
   public static void errorsInfo() {
      if (false == correctBitsNumber) {
         System.err.printf(bitsNumberErrorString);
      }
      if (false == correctBinaryFormat) {
         System.err.println(nonBinaryFormatErrorString);
      }
   }
   
   public static boolean isCorrectBitsNumber() {
      return correctBitsNumber;
   }
   
   public static boolean isCorrectBinaryFormat() {
      return correctBinaryFormat;
   }
   
   public static int getInteger() {
      return binaryInteger;
   }
   
   public static int getDecimalValue() {
      int decimalValue = 0;
      short counter  = 0;
      int dividend = binaryInteger;
      
      while (dividend > 0) {
         if (7 == counter) {
            decimalValue -= (dividend % 10) * exponentiation((short)2, counter);
         }
         else {
            decimalValue += (dividend % 10) * exponentiation((short)2, counter);
         }
         dividend /= 10;   // (dividend % 10) as last digit of dividend, after operation (dividend /= 10) will be removed
         counter++;
      }
      
      return decimalValue;
   }
   
   public static int exponentiation(short base, short exponent) {
      if (0 > exponent) {
         System.err.println("The case is more complicated. Exponent must be integer greater than zero");
         return 0;
      }
      else if (0 == base) {
         if (0 == exponent) 
            System.err.println("The case is more complicated");
            
         return 0;
      }
      else {
         short counter  = 1;
         int value = 1;
         
         while (counter <= exponent) {
            value *= base;
            counter++;
         }
         
         return value;
      }
   }
   
   public static void setInteger(int integer) {
      if (true == validateInteger(integer)) {
         binaryInteger = integer;
      }
   }
   
   public static boolean validateInteger(int integer) {
      if (0 > integer) {
         correctBinaryFormat = false;
         integer = -integer;   // no return, to search that integer has number of incorrect bits 
      }                        // and display other error
      
      short digitsCounter = 1;
      int digit = integer % 10;
      int dividend = integer / 10;
      
      if (digit != 0 && digit != 1) {
         correctBinaryFormat = false;
      }
      
      while (dividend > 0) {
         digit = dividend % 10;
         if (digit != 0 && digit != 1) {
            correctBinaryFormat = false;
            // no break, to search that integer has number of incorrect bits
         }
         dividend /= 10;
         digitsCounter++;
      }
      
      if (digitsCounter > maxBitsNumber) { // search that integer has number of incorrect bits
         correctBitsNumber = false;
      }
      
      return correctBinaryFormat && correctBitsNumber;
   }
}
