/* =====================================================================================
 *       Filename:  PokerAgainstComputerTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - test of poker game human vs computer 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 


public class PokerAgainstComputerTest {

   public static void main(String[] args) throws Exception {
      Score gameScore;
      java.io.PrintStream printStream = System.out;
      int draws = 0;
      int humanPlayerWins = 0;
      int computerPlayerWins = 0;
      
      for (int gamesCounter = 0; gamesCounter < 10; gamesCounter++) {
         PokerAgainstComputer pokerAgainstComputer = new PokerAgainstComputer(printStream, false);
         pokerAgainstComputer.run();
         gameScore = pokerAgainstComputer.getGameScore();
         
         switch (gameScore) {
            case FIRST_PLAYER_WIN:
               computerPlayerWins++;
               break;
            case SECOND_PLAYER_WIN:
               humanPlayerWins++;
               break;
            case DRAW:
               draws++;
         }
      }
      
      printStream.printf("%20s %15s %15s %n", "Computer's wins", "Human's wins", "Draws");
      printStream.printf("%20d %15d %15d %n", computerPlayerWins, humanPlayerWins, draws);
   }
}
