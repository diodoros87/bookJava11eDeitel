
/* =====================================================================================
 *       Filename:  AssertTesting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - class to testing by asserts program of 
                                cards shuffling, dealing for 2 players and
                                   deciding about game's result
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class AssertTesting {

   public static void assertPokerHand(PokerHand pokerHand, PokerHand expectedPokerHand) {
      assert(pokerHand == expectedPokerHand) : 
                     String.format("%s != %s%n", pokerHand, expectedPokerHand);
   } 
   
   public static void assertScore(Score score, Score expectedScore) {
      assert(score == expectedScore) : 
                     String.format("%s != %s%n", score, expectedScore);
   }
   
} 
