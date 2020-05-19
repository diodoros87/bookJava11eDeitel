
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
   
//    public static void expectedDay(Date date, int day) {
//       assert(date.getDay() == day) : 
//                      String.format("%d != %d%n", date.getDay(), day);
//    } 
//    
//    public static void expectedMonth(Date date, int month) {
//       assert(date.getMonth() == month) : 
//                      String.format("%d != %d%n", date.getMonth(), month);
//    } 
//    
//    public static void expectedYear(Date date, int year) {
//       assert(date.getYear() == year) : 
//                      String.format("%d != %d%n", date.getYear(), year);
//    } 
   
} 
