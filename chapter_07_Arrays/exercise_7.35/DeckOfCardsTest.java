/* =====================================================================================
 *       Filename:  DeckOfCardsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.35 - Card shuffling by Fisher-Yates algorithm 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DeckOfCardsTest {
   // execute application
   public static void main(String[] args) {
      DeckOfCards myDeckOfCards = new DeckOfCards();
      myDeckOfCards.shuffle(); // place Cards in random order
      
      // print all 52 Cards in the order in which they are dealt
      for (int i = 1; i <= DeckOfCards.NUMBER_OF_CARDS; i++) {
         // deal and display a Card
         System.out.printf("%-19s", myDeckOfCards.dealCard());

         if (i % 4 == 0) { // output a newline after every fourth card
            System.out.println();
         } 
      } 
   } 
} 
