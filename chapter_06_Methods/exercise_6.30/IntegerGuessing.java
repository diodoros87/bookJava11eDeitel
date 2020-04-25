/* =====================================================================================
 *       Filename:  IntegerGuessing.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.30 - game of guessing the number
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;

import standardInputDataPackage.GettingDataFromStandardInput;
 
public class IntegerGuessing {
   public static final short MAX_INTEGER = 1000;
   public static final short MIN_INTEGER = 1;
   
   public static final String SHORT_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Short.MAX_VALUE);
   public static final String SHORT_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Short.MIN_VALUE);
                                       
   private final short INTEGER_TO_GUESS = (short)(MIN_INTEGER + new SecureRandom().nextInt((short)(MAX_INTEGER - MIN_INTEGER + 1)));
   
   public static boolean menu() {
      
      System.out.printf("%n### Do you play again? %n");
      System.out.printf("*** Enter only 1 for select play again, for quit enter other key/keys%n");
      
      String menuSelection = GettingDataFromStandardInput.getStringWaitingForInput("");
      
      if (null != menuSelection && menuSelection.equals("1")) {
         return true;
      }
      
      return false;
   }
   
   public static void main(String[] args) {
      System.out.println("********* This program is game of integer's guessing");
      
      final String QUIT_INFO = "To quit enter sequence other than integer from below range:";
      final String PROMPT = "Only first entered integer will be accepted. Enter integer as number to guessing: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", QUIT_INFO, SHORT_MAX_VALUE_STRING, SHORT_MIN_VALUE_STRING, PROMPT);
      
      do {
         IntegerGuessing integerGuessing = new IntegerGuessing();
         
         do {
            System.out.printf("********* integer to guessing is in range from %d to %d%n", MIN_INTEGER, MAX_INTEGER);
         } while (false == integerGuessing.isNumberGuessing(GettingDataFromStandardInput.getShortInteger(QUIT_INFO_PROMPT)));
         
      } while (true == menu());
      
   } 
   
   public boolean isNumberGuessing(short integer) {

      if (integer < MIN_INTEGER) {
         System.out.printf("%n***ERROR: %d is less than minimum in range: %d%n", integer, MIN_INTEGER);
      }
      else if (integer > MAX_INTEGER) {
         System.out.printf("%n***ERROR: %d is greater than maximum in range: %d%n", integer, MAX_INTEGER);
      }
      else if (integer == INTEGER_TO_GUESS) {
         System.out.printf("%n$$$ Congratulations - guessing the number %d%n", INTEGER_TO_GUESS);
         return true;
      }
      else if (integer > INTEGER_TO_GUESS) {
         System.out.printf("%n$$$ integer to guessing is smaller than %d%n", integer);
      }
      else if (integer < INTEGER_TO_GUESS) {
         System.out.printf("%n$$$ integer to guessing is greater than %d%n", integer);
      }
      
      System.out.println(">>> Try again");
      return false;
   }
}
