/* =====================================================================================
 *       Filename:  DeckOfCardsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.30 - Card shuffling and dealing
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DeckOfCardsTest {

   public static void main(String[] args) throws Exception {
      DeckOfCards myDeckOfCards = new DeckOfCards();
      myDeckOfCards.shuffle(); // place Cards in random order
      
      // print 5 Cards in the order in which they are dealt
      for (int counter = 1; counter <= DeckOfCards.DEALING_CARDS; counter++) {
         // deal and display a Card
         System.out.printf("%s ", myDeckOfCards.dealCard());
      } 
      System.out.printf("%n %n ");
      
      test();
   }
   
   public static void printPockerHands(Card[] playerCards) throws Exception {
      System.out.printf("%s %n", "*******  Dealing cards:");
      for (int counter = 0; counter < playerCards.length; counter++) {
         System.out.printf("%s %n", playerCards[counter]);
      } 
      
      DeckOfCards.PokerHands pokerHands = DeckOfCards.classifyPokerHands(playerCards);
      System.out.printf("   --- Result: %s %n", DeckOfCards.getPokerHandsDescription(pokerHands));
   }
   
   public static void test() throws Exception {
      Card[] playerCards = new Card[DeckOfCards.DEALING_CARDS];
      
      playerCards[0] = new Card("Ace", "Hearts");
      playerCards[1] = new Card("Ace", "Diamonds");
      playerCards[2] = new Card("Ace", "Clubs");
      playerCards[3] = new Card("Ace", "Spades");
      playerCards[4] = new Card("Deuce", "Spades");
      printPockerHands(playerCards);
      
      playerCards[1] = new Card("King", "Diamonds");
      printPockerHands(playerCards);
      
      playerCards[1] = new Card("Deuce", "Diamonds");
      printPockerHands(playerCards);
      
      playerCards[0] = new Card("Four", "Clubs");
      printPockerHands(playerCards);
      
      playerCards[1] = new Card("Queen", "Diamonds");
      printPockerHands(playerCards);
      
      playerCards[2] = new Card("Five", "Clubs");
      printPockerHands(playerCards);
      
      playerCards[2] = new Card("Five", "Spades");
      playerCards[1] = new Card("Three", "Spades");
      playerCards[0] = new Card("Four", "Spades");
      printPockerHands(playerCards);
      
      playerCards[0] = new Card("Jack", "Spades");
      printPockerHands(playerCards);
      
      playerCards[0] = new Card("Six", "Spades");
      playerCards[3] = new Card("Four", "Spades");
      printPockerHands(playerCards);
      
      playerCards[0] = new Card("Six", "Clubs");
      printPockerHands(playerCards);
      
      playerCards[0] = new Card("Ace", "Spades");
      playerCards[2] = new Card("King", "Spades");
      printPockerHands(playerCards);
      
      playerCards[0] = new Card("King", "Spades");
      playerCards[1] = new Card("Queen", "Spades");
      playerCards[2] = new Card("Jack", "Spades");
      playerCards[3] = new Card("Ten", "Spades");
      playerCards[4] = new Card("Ace", "Spades");
      printPockerHands(playerCards);
      
      playerCards[0] = new Card("King", "Spades");
      playerCards[1] = new Card("Queen", "Spades");
      playerCards[2] = new Card("Jack", "Spades");
      playerCards[3] = new Card("Ten", "Spades");
      playerCards[4] = new Card("Nine", "Spades");
      printPockerHands(playerCards);
      
      playerCards[2] = new Card("Exception", "Spades");
      printPockerHands(playerCards);
   }
} 
