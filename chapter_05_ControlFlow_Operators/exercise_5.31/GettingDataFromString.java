/* =====================================================================================
 *       Filename:  GettingDataFromString.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.31 - class of string's scanning
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.util.Scanner;

public class GettingDataFromString {

   public  static final short endOfDataValueOfShort   = Short.MIN_VALUE;

   private static final int abnormalTerminationCode = 1; // status code indicates abnormal termination
   private Scanner scanner;
   
   public GettingDataFromString (String string) {
      if (null == string) {
         System.err.println("ERROR: input string is null");
         string = "";
      }
      scanner = new Scanner(string);
   }
   
   public Scanner getScanner() {
      return scanner;
   }

   public short getShortIntegerRejectOthersData(boolean isInfoDisplaying) {
      
      short value = endOfDataValueOfShort;
      
      while (true == scanner.hasNext()) {    // return true if scanner has another token (word) in its scanner
         if (true == scanner.hasNextShort()) {
            value = scanner.nextShort();
            
            if (true == isInfoDisplaying) {
               System.out.printf("%nValue of %d entered by User was accepted. %n", value);
            }
            break;
         }
         else if (true == scanner.hasNextDouble()) {
            System.err.printf("%nValue of %f entered by User is type double. ", scanner.nextDouble());
            System.err.printf("This is incorrect. Value must be integer type.%n");
         }
         else {
            System.err.printf("%nValue of \'%s\' entered by User is not integer type. ", scanner.next());
            System.err.printf("This is incorrect. Value must be integer type.%n");
         }
      }
      
      return value;
   }
   
}
