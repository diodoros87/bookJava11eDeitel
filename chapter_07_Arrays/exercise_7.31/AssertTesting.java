
/* =====================================================================================
 *       Filename:  AssertTesting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.31 - class to testing by asserts program of 
                                cards shuffling, dealing for 2 players and
                                   deciding about game's result
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class AssertTesting {

   public static void assertPokerHands(CardsConfiguration.PokerHands pokerHands, CardsConfiguration.PokerHands expectedPokerHands) {
      assert(pokerHands == expectedPokerHands) : 
                     String.format("%s != %s%n", CardsConfiguration.getPokerHandsDescription(pokerHands),
                                                CardsConfiguration.getPokerHandsDescription(expectedPokerHands));
   } 
   
   public static void assertScore(GameResult.Score score, GameResult.Score expectedScore) {
      assert(score == expectedScore) : 
                     String.format("%s != %s%n", GameResult.getScoreDescription(score),
                                                GameResult.getScoreDescription(expectedScore));
   }
   
} 
