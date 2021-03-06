/* =====================================================================================
 *       Filename:  GettingDataFromStandardInput.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 4 - utility class using in 
                                programs in exercises to chapter 4 to get
                                data from standard input
                                
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

package standardInputDataPackage;

import java.util.Scanner;

public class GettingDataFromStandardInput {

   private static final int abnormalTerminationCode = 1; // status code indicates abnormal termination
   private static final Scanner input               = new Scanner(System.in);
   
   public static Scanner getScanner() {
      return input;
   }
   
   public static String getString(String prompt) {
      System.out.print(prompt);
      
      String value = input.nextLine();
      
      return value;
   }
   
   public static short getShortInteger(String prompt) {
      System.out.print(prompt);
      
      short     value = 0;
      boolean isCorrectInputData = input.hasNextShort();   // to check input data
      
      if (false == isCorrectInputData) {
         System.err.println("Value entered by User is incorrect");
         input.close();
         System.exit(abnormalTerminationCode);
      }
      if (true == isCorrectInputData) {
         value = input.nextShort();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static int getInteger(String prompt) {
      System.out.print(prompt);
      
      int     value = 0;
      boolean isCorrectInputData = input.hasNextInt();   // to check input data
      
      if (false == isCorrectInputData) {
         System.err.println("Value entered by User is incorrect");
         input.close();
         System.exit(abnormalTerminationCode);
      }
      if (true == isCorrectInputData) {
         value = input.nextInt();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static void clearNextLineOfInputData() {
      input.nextLine(); // clear input data
   }
   
   public static long getLongIntegerRejectOthersData(String prompt, boolean isInfoDisplaying) {
      System.out.print(prompt);
      
      long value = 0;
      
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
      
      return value;
   }
   
   public static long getLongInteger(String prompt) {
      System.out.print(prompt);
      
      long     value = 0;
      boolean isCorrectInputData = input.hasNextLong();   // to check input data
      
      if (false == isCorrectInputData) {
         System.err.println("Value entered by User is incorrect");
         input.close();
         System.exit(abnormalTerminationCode);
      }
      if (true == isCorrectInputData) {
         value = input.nextLong();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
   public static double getDouble(String prompt) {
      System.out.print(prompt);
      
      double  value = 0;
      boolean isCorrectInputData = input.hasNextDouble();   // to check input data
      
      if (false == isCorrectInputData) {
         System.err.println("Value entered by User is incorrect");
         input.close();
         System.exit(abnormalTerminationCode);
      }
      if (true == isCorrectInputData) {
         value = input.nextDouble();
      }
      
      input.nextLine(); // clear input data
      return value;
   }
   
}
