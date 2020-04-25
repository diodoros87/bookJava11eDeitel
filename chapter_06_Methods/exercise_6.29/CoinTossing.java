/* =====================================================================================
 *       Filename:  CoinTossing.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.29 - coin tossing simulation
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;

import standardInputDataPackage.GettingDataFromStandardInput;
 
public class CoinTossing {
   // create secure random number generator for use in method flip
   private static final SecureRandom secureRandomNumbers = new SecureRandom();

   // enum type with constants that represent simulation's result of coin tossing
   private enum Coin {HEADS, TAILS}; 
   
   private int headsFrequency = 0;
   private int tailsFrequency = 0;
   
   public static Coin flip() {
      int result = secureRandomNumbers.nextInt(2); // get number from 0 to 1
      
      switch (result) {
         case 0:
            return Coin.HEADS;
         case 1:
            return Coin.TAILS;
         default:
            System.err.println("******* ERROR: This instruction should NOT be executed in method flip()");
            return Coin.TAILS;
      }
   }
   
   public boolean menu() {
      System.out.printf("********* This program simulate of coin tossing %n");
      System.out.printf("### 1. coin toss %n");
      System.out.printf("*** Enter only 1 for select simulation of coin tossing, for quit enter other key/keys%n");
      
      String menuSelection = GettingDataFromStandardInput.getStringWaitingForInput("");
      
      if (null != menuSelection && menuSelection.equals("1")) {
         return calculateCoinTossingResult();
      }
      
      return false;
   }
   
   public static void main(String[] args) {
      CoinTossing coinTossing = new CoinTossing();
      
      do {
      } while (true == coinTossing.menu());
      
      coinTossing.printResults();
      coinTossing.headsFrequency = 0;
      coinTossing.tailsFrequency = 0;
      
      for (int tossingCounter = 0; tossingCounter < 99_999; tossingCounter++) {
         coinTossing.calculateCoinTossingResult();
      }
      
      coinTossing.printResults();
   } 
   
   public void printResults() {
      System.out.printf("%n### RESULT of coin tossing:%n");
      System.out.printf(" %9s    %8s  %n", "Heads", "Tails");
      System.out.printf(" %9d    %8d  %n", headsFrequency, tailsFrequency);
      System.out.printf(" %9f%%  %9f%%  %n", 100.0 * headsFrequency / (headsFrequency + tailsFrequency),
                                             100.0 * tailsFrequency / (headsFrequency + tailsFrequency));
   }
   
   public boolean calculateCoinTossingResult() {
      Coin result = flip();
      
      switch (result) {
         case HEADS:
            headsFrequency++;
            return true;
         case TAILS:
            tailsFrequency++;
            return true;
         default:
            System.err.println("******* ERROR: This instruction should NOT be executed in method menu()");
      }
      
      return false;
   } 
}
