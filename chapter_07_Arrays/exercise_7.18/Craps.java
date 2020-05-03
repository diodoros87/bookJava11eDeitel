/* =====================================================================================
 *       Filename:  Craps.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.18 - class simulates the dice game craps
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;

public class Craps {
   // create secure random number generator for use in method rollDice
   public static final SecureRandom randomNumbers = new SecureRandom();

   // enum type with constants that represent the game status
   public enum Status {START, CONTINUE, WON, LOST};                

   // constants that represent common rolls of the dice
   private static final int SNAKE_EYES = 2;
   private static final int TREY = 3;      
   private static final int SEVEN = 7;     
   private static final int YO_LEVEN = 11; 
   private static final int BOX_CARS = 12; 
   
   private Status gameStatus = Status.START;
   private int endGameTurn = 0;
   
   public Status getGameStatus() {
      return gameStatus;
   }
   
   public int getEndGameTurn() {
      return endGameTurn;
   }
   
   private void setFirstRollGameStatus(int sumOfDice) {
      // determine game status and point based on first roll 
      switch (sumOfDice) {
         case SEVEN: // win with 7 on first roll    
         case YO_LEVEN: // win with 11 on first roll
            gameStatus = Status.WON;
            endGameTurn = 1;
            break;
         case SNAKE_EYES: // lose with 2 on first roll
         case TREY: // lose with 3 on first roll      
         case BOX_CARS: // lose with 12 on first roll 
            gameStatus = Status.LOST;
            endGameTurn = 1;
            break;
         default: // did not win or lose, so remember point  
            gameStatus = Status.CONTINUE; // game is not over
      }
   }
   
   private void setEventuallyNextRollsGameStatus(int point) {
      int sumOfDice = 0;
      int turn = 2;
      
      // while game is not complete
      for (; gameStatus == Status.CONTINUE; turn++) { // not WON or LOST
         
         sumOfDice = rollDice(); // roll dice again
         
         // determine game status
         if (sumOfDice == point) { // win by making point
            gameStatus = Status.WON;
         } 
         else if (sumOfDice == SEVEN) { // lose by rolling 7 before point
            gameStatus = Status.LOST;
         } 
          
      } 
      
      endGameTurn = turn - 1;
   }

   // plays one game of craps
   public void runGame() {
      gameStatus = Status.CONTINUE;
      
      int point = 0; // point if no win or loss on first roll
      int sumOfDice = rollDice(); // first roll of the dice
      setFirstRollGameStatus(sumOfDice); // can contain CONTINUE, WON or LOST
      
      if (gameStatus == Status.CONTINUE) {
         point = sumOfDice; // remember the point       
         //System.out.printf("%nPoint is %d%n", point);
         setEventuallyNextRollsGameStatus(point);
      }

      //printGameResult();
   } 

   // roll dice, calculate sum and display results
   public static int rollDice() {
      // pick random die values
      int die1 = 1 + randomNumbers.nextInt(6); // first die roll
      int die2 = 1 + randomNumbers.nextInt(6); // second die roll

      int sum = die1 + die2; // sum of die values

      //System.out.printf("Player rolled %d + %d = %d%n", die1, die2, sum);

      return sum; 
   } 
   
   public void printGameResult() {
      // display won or lost message
      if (gameStatus == Status.WON) {
         System.out.println("$$$ Player wins after " + endGameTurn + " turns");
      } 
      else if (gameStatus == Status.LOST) {
         System.out.println("### Player loses after " + endGameTurn + " turns");
      } 
      else if (gameStatus == Status.CONTINUE) {
         System.err.println("******* ERROR: This instruction should NOT be executed in method printGameResult");
      }
   }
   
} 
