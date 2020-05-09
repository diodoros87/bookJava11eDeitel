/* =====================================================================================
 *       Filename:  StandardInput.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.22c - utility class using to get
                                data from standard input
                                
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.util.Scanner;

public class StandardInput {
   private static final Scanner input                  = new Scanner(System.in);
   
   static Scanner getScanner() {
      return input;
   }
   
   static boolean getKnightsTourControlCommand(KnightsTourControl knightsTourControl) {
      if (false == input.hasNextLine()) {
         return false;   // return if End-of-transmission character was detected
      }
      else {  
      
         if (true == input.hasNextInt()) {
            knightsTourControl.control(input.nextInt());
            input.nextLine();   // to clear input data - nextInt() leaves whitespaces in input
            
            if (true == knightsTourControl.isQuit()) {
               return false;
            }
         }
         else if (true == input.hasNextDouble()) {
            System.err.printf("%n???????? ERROR: Value of %f entered by User is type double. ", input.nextDouble());
            System.err.printf("This is incorrect. Double values are not using in application.%n");
            input.nextLine();   // to clear input data - NextDouble() leaves whitespaces in input
         }
         else {
            System.err.printf("%n???????? ERROR: Value of \'%s\' entered by User is not using in this program.",
                              input.nextLine());
         }
         
         return true;
      }
   }
   
}
