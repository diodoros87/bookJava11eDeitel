/* =====================================================================================
 *       Filename:  Craps.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.33 - class simulates the dice game craps
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;

public class Craps {
   // create secure random number generator for use in method rollDice
   private static final SecureRandom randomNumbers = new SecureRandom();

   // enum type with constants that represent the game status
   public enum Status {START, CONTINUE, WON, LOST, END};                

   // constants that represent common rolls of the dice
   private static final int SNAKE_EYES = 2;
   private static final int TREY = 3;      
   private static final int SEVEN = 7;     
   private static final int YO_LEVEN = 11; 
   private static final int BOX_CARS = 12; 
   
   private double bankBalance = 1_000;
   private double wager = 0;
   private Status gameStatus = Status.START; 
   
   public double getBankBalance() {
      return bankBalance;
   }
   
   public double getWager() {
      return wager;
   }
   
   public Status getGameStatus() {
      return gameStatus;
   }
   
   public boolean setWager(double wager) {
      if (Double.isNaN(wager) || 
          Double.POSITIVE_INFINITY == wager) {
          System.err.printf("%n****** ERROR: wager can not set to %f %n", wager);
          
          return false;
      }
      
      if (0 > wager) {
         System.out.println("Wager can not be less than 0 - can not accept wager");
         return false;
      }
      
      if (0 > this.bankBalance) {
         System.out.println("Balance is less than 0 - can not accept wager");
         System.out.println("### Game over");
         return false;
      }
      else if (0 == this.bankBalance) {
         System.out.println("Balance is 0 - can not accept wager");
         System.out.println("### Game over");
         return false;
      }
      else if (wager > this.bankBalance) {
         System.out.println("Balance is less than wager - can not accept wager");
         return false;
      }
      else {
         this.wager = wager;
         return true;
      }
   }
   
   public void setFirstRollGameStatus(int sumOfDice) {
      validateSumOfDice(sumOfDice);

      // determine game status and point based on first roll 
      switch (sumOfDice) {
         case SEVEN: // win with 7 on first roll    
         case YO_LEVEN: // win with 11 on first roll
            gameStatus = Status.WON;
            break;
         case SNAKE_EYES: // lose with 2 on first roll
         case TREY: // lose with 3 on first roll      
         case BOX_CARS: // lose with 12 on first roll 
            gameStatus = Status.LOST;
            break;
         default: // did not win or lose, so remember point  
            gameStatus = Status.CONTINUE; // game is not over
      }
   }
   
   public void setEventuallyNextRollsGameStatus(int point) {
      validateSumOfDice(point);
      
      int sumOfDice = 0;
      
      // while game is not complete
      while (gameStatus == Status.CONTINUE) { // not WON or LOST
         printMessage();
         
         sumOfDice = rollDice(true); // roll dice again
         
         // determine game status
         if (sumOfDice == point) { // win by making point
            gameStatus = Status.WON;
         } 
         else if (sumOfDice == SEVEN) { // lose by rolling 7 before point
            gameStatus = Status.LOST;
         } 
          
      } 
   }

   // plays one game of craps
   public void runGame(double wager) {
      if (false == setWager(wager)) {
         return;
      }
      
      gameStatus = Status.CONTINUE;
      printMessage();
      
      int point = 0; // point if no win or loss on first roll
      int sumOfDice = rollDice(true); // first roll of the dice
      setFirstRollGameStatus(sumOfDice); // can contain CONTINUE, WON or LOST
      
      if (gameStatus == Status.CONTINUE) {
         point = sumOfDice; // remember the point       
         System.out.printf("%nPoint is %d%n", point);
         setEventuallyNextRollsGameStatus(point);
      }

      printGameResult();
   } 

   // roll dice, calculate sum and display results
   public static int rollDice(boolean isDisplaying) {
      // pick random die values
      int die1 = 1 + randomNumbers.nextInt(6); // first die roll
      int die2 = 1 + randomNumbers.nextInt(6); // second die roll

      int sum = die1 + die2; // sum of die values

      if (true == isDisplaying) {
         System.out.printf("Player rolled %d + %d = %d%n", die1, die2, sum);
      }

      return sum; 
   } 
   
   public void printGameResult() {
      // display won or lost message
      if (gameStatus == Status.WON) {
         bankBalance += wager;
         System.out.println("$$$ Player wins");
      } 
      else if (gameStatus == Status.LOST) {
         System.out.println("### Player loses");
         bankBalance -= wager;
         
         if (bankBalance <= 0) {
            System.out.println("### Sorry, everything were lost");
            gameStatus = Status.END;
         }
      } 
      else if (gameStatus == Status.CONTINUE) {
         System.err.println("******* ERROR: This instruction should NOT be executed in method printGameResult");
      }
      
      System.out.println("$$$ Balance is " + bankBalance);
   }
   
   public void printMessage() {
      if (gameStatus == Status.END) {
         System.out.println("### Game over");
         return;
      } 
      
      int value = 0;
      
      if (gameStatus == Status.WON) {
         value += 6;
      } 
      else if (gameStatus == Status.LOST) {
         value -= 6;
      } 
      
      if (bankBalance > 1_000) {
         value += 6;
      }
      else if (bankBalance < 10) {
         value -= 6;
      }
      
      value += rollDice(false);
      
      if (value > 12) {
         System.out.println("$$$ Maybe this is good time to take the money?");
      } 
      else if (value < 1) {
         System.out.println("$$$ Maybe You will lose everything soon?");
      } 
      else {
         System.out.println("$$$ Be valiant! You must have determination to win!");
      }

   }
   
   public static boolean validateSumOfDice(int sumOfDice) {
      if (sumOfDice <= 0 || sumOfDice > 12) {
         System.err.println("***** ERROR: Sum of dice can not has value " + sumOfDice);
         abnormalTermination();
         return false;
      }
      
      return true;
   }
   
   private static final int abnormalTerminationCode = 1; // status code indicates abnormal termination
   
   public static void abnormalTermination() {
      System.out.println("****** Program is interrupted ");
      System.exit(abnormalTerminationCode);
   }
} 
