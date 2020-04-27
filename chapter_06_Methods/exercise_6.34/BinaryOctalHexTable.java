/* =====================================================================================
 *       Filename:  BinaryOctalHexTable.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.34 - printing decimal values from -256 to 256
                                in binary, octal and hex formats 
                                
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class BinaryOctalHexTable {

   public static final short BEGIN = -256;
   public static final short LAST = 256;
   
   public static void main(String[] args) {
      System.out.printf("*** This program prints decimal values from %d to %d in binary, octal and hex formats%n", BEGIN, LAST);
      
      System.out.printf("%10s%40s%20s%20s%n", "decimal", "binary", "octal", "hex");
      
      for (int counter = BEGIN; counter <= LAST; counter++) {
         printIntegers(counter); 
         System.out.println();
      }
      
      System.out.printf("%10s%40s%20s%20s%n", "decimal", "binary", "octal", "hex");
      System.out.printf("%n*** This program prints decimal values from %d to %d in binary, octal and hex formats%n", BEGIN, LAST);

   } 
   
   public static void printIntegers(int integer) {
      System.out.printf("%10s", integer);
      
      String binaryString = getIntegerString(integer, 2);
      String octalString  = getIntegerString(integer, 8);
      String hexString    = getIntegerString(integer, 16);
      
      System.out.printf("%40s", 
         true == Integer.toBinaryString(integer).equals(binaryString) ? binaryString : "binary???");
         
      System.out.printf("%20s", 
         true == Integer.toOctalString(integer).equals(octalString) ? octalString : "octal???");
         
      System.out.printf("%20s", 
         true == Integer.toHexString(integer).toUpperCase().equals(hexString) ? hexString : "hex???");
   }
   
   public static String getIntegerString (int integer, int base) {
      if (false == validateBase(base)) {
         return "";
      }
      
      String result = "";
      int value;
      String valueString;
      long longInteger = integer;
      
      if (longInteger < 0) {
         longInteger = (long)Math.pow(2, 32) + longInteger;  // int in Java is size of 32 bits 
         // above is formula of presentation int values in system of two's complement
      }
      
      do {
         value = (int)(longInteger % base);
         
         if (16 == base) {
            valueString = getHexValue(value);
         }
         else {
            valueString = ((Integer)value).toString();
         }
         
         result = valueString + result;
         longInteger /= base;
      } while (longInteger > 0);
      
      return result;
   }
   
   public static boolean validateBase (int base) {
      if (base < 2) {
         System.err.println("ERROR: In method getIntegerString parameter base can not be less than 2");
         return false;
      }
      
      if (base > 10 && base != 16) {
         System.err.println("ERROR: In method getIntegerString parameter base can not be greater than 10 and not equals 16");
         return false;
      }
      
      return true;
   }
   
   public static String getHexValue (int value) {
      if (value >= 0 && value <= 9) {
         return ((Integer)value).toString();
      }
      
      switch (value) {
         case 10:
            return "A";
         case 11:
            return "B";
         case 12:
            return "C";
         case 13:
            return "D";
         case 14:
            return "E";
         case 15:
            return "F";
         default:
            System.err.println("ERROR: In method getHexValue parameter value must be in range from 0 to 15");
            return "";
      }
   }
   
} 
