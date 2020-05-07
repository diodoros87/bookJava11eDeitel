/* =====================================================================================
 *       Filename:  StandardInput.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.21 - utility class using to get
                                data from standard input
                                
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.util.Scanner;

public class StandardInput {
   private static final Scanner input                  = new Scanner(System.in);
   
   TurtleGraphicsControl turtleGraphicsControl         = new TurtleGraphicsControl();
   
   static Scanner getScanner() {
      return input;
   }
   
   boolean getTurtleGraphicsControlCommand() {
      if (false == input.hasNextLine()) {
         return false;   // return if End-of-transmission character was detected
      }
      else {  
      
         if (true == input.hasNextInt()) {
            examineCommand(input.nextInt());
            input.nextLine();   // to clear input data - nextInt() leaves whitespaces in input
            
            if (true == turtleGraphicsControl.isQuit()) {
               return false;
            }
         }
         else if (true == input.hasNextDouble()) {
            System.err.printf("%n???????? ERROR: Value of %f entered by User is type double. ", input.nextDouble());
            System.err.printf("This is incorrect. Double values are not using in application.%n");
            input.nextLine();   // to clear input data - NextDouble() leaves whitespaces in input
         }
         else {
            examineCommand(input.nextLine());
         }
         
         return true;
      }
   }
   
   private void examineCommand(int command) {
      turtleGraphicsControl.control(command);
      printErrorMessage();
   }
   
   private void printErrorMessage() {
      String errorMessage = turtleGraphicsControl.getErrorMessage();
      if ("" != errorMessage) {
         System.err.println("???????? ERROR: " + errorMessage);
         turtleGraphicsControl.clearErrorMessage();
      }
   }
   
   public static String getString (int integer) {
      return ((Integer)integer).toString();
   }
   
   private void examineCommand(String inputString) {
   
      if (inputString.matches(String.format("\\s*%d, \\d+\\s*", TurtleGraphicsControl.MOVE_FORWARD_KEY))) {
      
         String commandKeyString = getString(TurtleGraphicsControl.MOVE_FORWARD_KEY);
         int commandKeyIndex = inputString.indexOf(commandKeyString);
         String onlyNumberString = inputString.substring(commandKeyIndex + 3).trim();
         int moves = Integer.valueOf(onlyNumberString);
         turtleGraphicsControl.control(TurtleGraphicsControl.MOVE_FORWARD_KEY, moves);
         printErrorMessage();
      }
      else if (inputString.matches(String.format("\\s*%d, \\p{Graph}\\s*", TurtleGraphicsControl.CHANGE_PEN_KEY))) {
      
         String commandKeyString = getString(TurtleGraphicsControl.CHANGE_PEN_KEY);
         int commandKeyIndex = inputString.indexOf(commandKeyString);
         char penCharacter = inputString.charAt(commandKeyIndex + 3);
         turtleGraphicsControl.control(TurtleGraphicsControl.CHANGE_PEN_KEY, penCharacter);
         printErrorMessage();
      }
      else {
         System.err.printf("%n???????? ERROR: Value of \'%s\' entered by User is not using in this program.", inputString);
      }
   }
   
}
