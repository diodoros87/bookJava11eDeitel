/* =====================================================================================
 *       Filename:  CrapsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.33 - test of dice game craps
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;
 
public class CrapsTest {
   
   public static double getWagerFromUser() {
      final String QUIT_INFO = "To quit enter sequence other than number";
      final String PROMPT = "Enter wager's value: ";
      final String QUIT_INFO_PROMPT = String.format("%s%n%s", QUIT_INFO, PROMPT);
      
      return GettingDataFromStandardInput.getDouble(QUIT_INFO_PROMPT);
   }
      
   public static boolean menu() {
      System.out.printf("%n********* This program simulates the dice game craps %n");
      System.out.printf("### 1. play again %n");
      System.out.printf("*** Enter only 1 for playing game, for quit enter other key/keys%n");
      
      String menuSelection = GettingDataFromStandardInput.getStringWaitingForInput("");
      
      if (null != menuSelection && menuSelection.equals("1")) {
         return true;
      }
      
      return false;
   }
   
   public static void main(String[] args) {
      Craps craps = new Craps();
      
      do {
         singleGame(craps);
         craps.printMessage();
      } while (Craps.Status.END != craps.getGameStatus() && true == menu());
      
   } 
   
   public static void singleGame(Craps craps) {
      if (null == craps) {
         System.err.printf("*********ERROR:  NullPointer Exception craps == null %n");
         return;
      }
      
      System.out.printf("********* This program simulates the dice game craps %n");
      double wager = 0;
      
      do {
         wager = getWagerFromUser();
      } while (false == craps.setWager(wager));
      
      craps.runGame(wager);
   }
   
}
