/* =====================================================================================
 *       Filename:  GettingDataFromStandardInput.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 8 - utility class using in 
                                programs in exercises to chapter 8 to get
                                data from standard input
                                
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

package standardInputDataPackage;

import java.util.Scanner;
import java.io.PrintStream;

public class GettingDataFromStandardInput {
   private static final Scanner input               = new Scanner(System.in);
   
   private static final PrintStream printStream      = System.out;
   private static final PrintStream errorPrintStream = System.err;
   
   public static Scanner getScanner() {
      return input;
   }
   
   public static PrintStream getPrintStream() {
      return printStream;
   }
   
   public static PrintStream getErrorPrintStream() {
      return errorPrintStream;
   }
   
   public static String getString(String prompt) {
      printStream.print(prompt);
      
      if (true == input.hasNextLine()) {    // return true if scanner has another line in its input
         return input.nextLine();
      }
      
      return null;
   }
   
   public static short getShortInteger(String prompt) {
      printStream.print(prompt);
      
      short     value = 0;
      
      if (false == input.hasNextShort()) {
         throw new IllegalArgumentException("Value entered by User is not type short");
      }
      else {
         value = input.nextShort();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static int getInteger(String prompt) {
      printStream.print(prompt);
      
      int     value = 0;
      
      if (false == input.hasNextInt()) {
         throw new IllegalArgumentException("Value entered by User is not type int");
      }
      else {
         value = input.nextInt();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public void clearNextLine() {
      if (true == input.hasNextLine()) {    // return true if scanner has another line in its input
         input.nextLine();
      }
   }
   
   public static Long getLongIntegerRejectOthersData(String prompt, boolean isInfoDisplaying) {
      printStream.print(prompt);
      
      Long value = null;
      
      while (true == input.hasNext()) {    // return true if scanner has another token (word) in its input
      
         if (true == input.hasNextLong()) {
            value = input.nextLong();
            
            if (isInfoDisplaying) {
               printStream.printf("%nValue of %d entered by User was accepted. %n", value);
            }
            
            return value;
         }
         else if (true == input.hasNextDouble()) {
            errorPrintStream.printf("%nValue of %f entered by User is type double. ", input.nextDouble());
            errorPrintStream.printf("This is incorrect. Value must be long integer type.%n");
         }
         else {
            errorPrintStream.printf("%nValue of \'%s\' entered by User is not long integer type. ", input.next());
            errorPrintStream.printf("This is incorrect. Value must be long integer type.%n");
         }
      }
      
      return value;   // return if End-of-transmission character was detected
   }
   
   public static Integer getIntegerRejectOthersData(String prompt, boolean isInfoDisplaying) {
      printStream.print(prompt);
      
      Integer value = null;
      
      while (true == input.hasNext()) {    // return true if scanner has another token (word) in its input
      
         if (true == input.hasNextInt()) {
            value = input.nextInt();
            
            if (isInfoDisplaying) {
               printStream.printf("%nValue of %d entered by User was accepted. %n", value);
            }
            
            return value;
         }
         else if (true == input.hasNextDouble()) {
            errorPrintStream.printf("%nValue of %f entered by User is type double. ", input.nextDouble());
            errorPrintStream.printf("This is incorrect. Value must be integer type.%n");
         }
         else {
            errorPrintStream.printf("%nValue of \'%s\' entered by User is not integer type. ", input.next());
            errorPrintStream.printf("This is incorrect. Value must be integer type.%n");
         }
      }
      
      return value;   // return if End-of-transmission character was detected
   }
   
   public static Short getShortIntegerRejectOthersData(String prompt, boolean isInfoDisplaying) {
      if (true == isInfoDisplaying) {
         printStream.print(prompt);
      }
      
      Short value = null;
      
      while (true == input.hasNext()) {    // return true if scanner has another token (word) in its input
      
         if (true == input.hasNextShort()) {
            value = input.nextShort();
            
            if (true == isInfoDisplaying) {
               printStream.printf("%nValue of %d entered by User was accepted. %n", value);
            }
            
            return value;
         }
         else if (true == input.hasNextDouble()) {
            errorPrintStream.printf("%nValue of %f entered by User is type double. ", input.nextDouble());
            errorPrintStream.printf("This is incorrect. Value must be short integer type.%n");
         }
         else {
            errorPrintStream.printf("%nValue of \'%s\' entered by User is not short integer type. ", input.next());
            errorPrintStream.printf("This is incorrect. Value must be short integer type.%n");
         }
      }
      
      return value;    // return if End-of-transmission character was detected
   }
   
   public static long getLongInteger(String prompt) {
      printStream.print(prompt);
      
      long     value = 0;
      
      if (false == input.hasNextLong()) {
         throw new IllegalArgumentException("Value entered by User is not type long int");
      }
      else {
         value = input.nextLong();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static double getDouble(String prompt) {
      printStream.print(prompt);
      
      double  value = 0;
      
      if (false == input.hasNextDouble()) {
         throw new IllegalArgumentException("Value entered by User is not type double");
      }
      else {
         value = input.nextDouble();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static Double getDoubleRejectOthersData(String prompt, boolean isTextDisplaying) {
      if (isTextDisplaying) {
         printStream.print(prompt);
      }
      
      Double value = null;
      
      while (true == input.hasNext()) {    // return true if scanner has another token (word) in its input
      
         if (true == input.hasNextDouble()) {
            value = input.nextDouble();
            if (isTextDisplaying) {
               printStream.printf("%nValue of %f entered by User was accepted. %n", value);
            }
            
            return value;
         }
         else {
            errorPrintStream.printf("%nValue of \'%s\' entered by User is not double type. ", input.next());
            errorPrintStream.printf("This is incorrect. Value must be double type.%n");
         }
      }
      
      return value;   // return if End-of-transmission character was detected
   }
   
   public static float getFloat(String prompt) {
      printStream.print(prompt);
      
      float  value = 0;
      
      if (false == input.hasNextFloat()) {
         throw new IllegalArgumentException("Value entered by User is not type float");
      }
      else {
         value = input.nextFloat();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
}
