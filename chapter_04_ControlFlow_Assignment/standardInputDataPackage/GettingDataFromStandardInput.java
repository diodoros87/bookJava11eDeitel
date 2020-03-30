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
