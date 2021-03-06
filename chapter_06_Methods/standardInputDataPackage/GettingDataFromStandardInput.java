/* =====================================================================================
 *       Filename:  GettingDataFromStandardInput.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 6 - utility class using in 
                                programs in exercises to chapter 6 to get
                                data from standard input
                                
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

package standardInputDataPackage;

import java.util.Scanner;

public class GettingDataFromStandardInput {

   public  static final short endOfDataValueOfShort    = Short.MIN_VALUE;
   public  static final long  endOfDataValueOfLong     = Long.MIN_VALUE;
   public  static final double  endOfDataValueOfDouble = Double.NEGATIVE_INFINITY;

   private static final int abnormalTerminationCode = 1; // status code indicates abnormal termination
   private static final Scanner input               = new Scanner(System.in);
   
   public static Scanner getScanner() {
      return input;
   }
   
   public static String getString(String prompt) {
      System.out.print(prompt);
      
      return input.nextLine();
   }
   
   public static String getStringWaitingForInput(String prompt) {
      System.out.print(prompt);
      
      while (true == input.hasNextLine()) {    // return true if scanner has another line in its input
         return input.nextLine();
      }
      
      return null;
   }
   
   public static short getShortInteger(String prompt) {
      System.out.print(prompt);
      
      short     value = 0;
      
      if (false == input.hasNextShort()) {
         System.err.println("Value entered by User is incorrect");
         input.close();
         System.exit(abnormalTerminationCode);
      }
      else {
         value = input.nextShort();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static int getInteger(String prompt) {
      System.out.print(prompt);
      
      int     value = 0;
      
      if (false == input.hasNextInt()) {
         System.err.println("Value entered by User is incorrect");
         input.close();
         System.exit(abnormalTerminationCode);
      }
      else {
         value = input.nextInt();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static void clearNextLineOfInputData() {
      input.nextLine(); // clear input data
   }
   
   public static String clearNextLineWaitingForInput() {
      while (true == input.hasNextLine()) {    // return true if scanner has another line in its input
         return input.nextLine();
      }
      
      return "";
   }
   
   public static long getLongIntegerRejectOthersData(String prompt, boolean isInfoDisplaying) {
      System.out.print(prompt);
      
      long value = endOfDataValueOfLong;
      
      while (true == input.hasNext()) {    // return true if scanner has another token (word) in its input
         if (true == input.hasNextLong()) {
            value = input.nextLong();
            
            if (isInfoDisplaying) {
               System.out.printf("%nValue of %d entered by User was accepted. %n", value);
            }
            return value;
         }
         else if (true == input.hasNextDouble()) {
            System.err.printf("%nValue of %f entered by User is type double. ", input.nextDouble());
            System.err.printf("This is incorrect. Value must be integer type.%n");
         }
         else {
            System.err.printf("%nValue of \'%s\' entered by User is not integer type. ", input.next());
            System.err.printf("This is incorrect. Value must be integer type.%n");
         }
      }
      
      return value;   // return if End-of-transmission character was detected
   }
   
   public static Integer getIntegerRejectOthersData(String prompt, boolean isInfoDisplaying) {
      System.out.print(prompt);
      
      Integer value = null;
      
      while (true == input.hasNext()) {    // return true if scanner has another token (word) in its input
         if (true == input.hasNextInt()) {
            value = input.nextInt();
            
            if (isInfoDisplaying) {
               System.out.printf("%nValue of %d entered by User was accepted. %n", value);
            }
            return value;
         }
         else if (true == input.hasNextDouble()) {
            System.err.printf("%nValue of %f entered by User is type double. ", input.nextDouble());
            System.err.printf("This is incorrect. Value must be integer type.%n");
         }
         else {
            System.err.printf("%nValue of \'%s\' entered by User is not integer type. ", input.next());
            System.err.printf("This is incorrect. Value must be integer type.%n");
         }
      }
      
      return value;   // return if End-of-transmission character was detected
   }
   
   public static short getShortIntegerRejectOthersData(String prompt, boolean isInfoDisplaying) {
      if (true == isInfoDisplaying) {
         System.out.print(prompt);
      }
      
      short value;
      
      while (true == input.hasNext()) {    // return true if scanner has another token (word) in its input
         if (true == input.hasNextShort()) {
            value = input.nextShort();
            
            if (true == isInfoDisplaying) {
               System.out.printf("%nValue of %d entered by User was accepted. %n", value);
            }
            return value;
         }
         else if (true == input.hasNextDouble()) {
            System.err.printf("%nValue of %f entered by User is type double. ", input.nextDouble());
            System.err.printf("This is incorrect. Value must be integer type.%n");
         }
         else {
            System.err.printf("%nValue of \'%s\' entered by User is not integer type. ", input.next());
            System.err.printf("This is incorrect. Value must be integer type.%n");
         }
      }
      
      return endOfDataValueOfShort;    // return if End-of-transmission character was detected
   }
   
   public static long getLongInteger(String prompt) {
      System.out.print(prompt);
      
      long     value = 0;
      
      if (false == input.hasNextLong()) {
         System.err.println("Value entered by User is incorrect");
         input.close();
         System.exit(abnormalTerminationCode);
      }
      else {
         value = input.nextLong();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static double getDouble(String prompt) {
      System.out.print(prompt);
      
      double  value = 0;
      
      if (false == input.hasNextDouble()) {
         System.err.println("Value entered by User is incorrect");
         input.close();
         System.exit(abnormalTerminationCode);
      }
      else {
         value = input.nextDouble();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static double getDoubleRejectOthersData(String prompt, boolean isTextDisplaying) {
      if (isTextDisplaying) {
         System.out.print(prompt);
      }
      
      double value = endOfDataValueOfDouble;
      
      while (true == input.hasNext()) {    // return true if scanner has another token (word) in its input
         if (true == input.hasNextDouble()) {
            value = input.nextDouble();
            if (isTextDisplaying) {
               System.out.printf("%nValue of %f entered by User was accepted. %n", value);
            }
            
            return value;
         }
         else {
            System.err.printf("%nValue of \'%s\' entered by User is not double type. ", input.next());
            System.err.printf("This is incorrect. Value must be double type.%n");
         }
      }
      
      return value;   // return if End-of-transmission character was detected
   }
   
}
