/* =====================================================================================
 *       Filename:  PokerAgainstComputerTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - Card shuffling, dealing for 2 players (one 
                             of them is computerPlayer with option replace some cards) and
                                deciding about game's result 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PokerAgainstComputerTest {

   public static void main(String[] args) throws Exception {
      PokerAgainstComputer pokerAgainstComputer = new PokerAgainstComputer(System.out);
      pokerAgainstComputer.run();
   }
}
