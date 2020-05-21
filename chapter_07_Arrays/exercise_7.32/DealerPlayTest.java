/* =====================================================================================
 *       Filename:  DealerPlayTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.32 - Card shuffling, dealing for 2 players (one 
                             of them is dealer with option replace some cards) and
                                deciding about game's result 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DealerPlayTest {

   public static void main(String[] args) throws Exception {
      Card[] dealerCards = new Card[CardsConfiguration.POKER_CARDS];
      Card[] secondPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      DeckOfCards deckOfCards = new DeckOfCards();
      
      deckOfCards.shuffle(); // place Cards in random order
      
      dealCards(dealerCards, deckOfCards);
      Dealer dealer = new Dealer(dealerCards);
      CardsConfiguration dealerConfiguration = dealer.getCardsConfiguration();
      displayPockerHands(dealerConfiguration, "*******  Dealer cards before replace:");
      
      dealCards(secondPlayerCards, deckOfCards);
      CardsConfiguration secondConfiguration = new CardsConfiguration(secondPlayerCards);
      
      replaceCards(dealer, deckOfCards);
      
      displayPockerHands(dealerConfiguration, "*******  Dealer cards after replace:");
      displayPockerHands(secondConfiguration, "*******  Second player cards:");
      displayGameResult(dealerConfiguration, secondConfiguration);
   }
   
   public static void replaceCards(Dealer dealer, DeckOfCards deckOfCards) throws Exception {
      int counter = dealer.getNumberOfCardsToReplace();
      Card card;
      while (counter > 0) {
         card = deckOfCards.dealCard();
         dealer.receiveCard(card);
         counter--;
      } 
   }
   
   public static void displayCards(Card[] cards, String title) {
      System.out.printf("%n%s%n", title);
      for (int counter = 0; counter < cards.length; counter++) {
         System.out.printf("%s %n", cards[counter]);
      } 
   }
   
   public static void displayPockerHands(CardsConfiguration configuration, String title) throws Exception {
      displayCards(configuration.getDealingCards(), title);
      PokerHand pokerHand = configuration.classifyPokerHand();
      System.out.printf("%s %s %n", " --- Poker hand of dealing cards:", pokerHand);
   }
   
   public static void displayGameResult(CardsConfiguration firstConfiguration, CardsConfiguration secondConfiguration) throws Exception  {
      GameResult gameResult = new GameResult(firstConfiguration, secondConfiguration);
      
      Score score  = gameResult.getScore();
      System.out.printf("   --- Score: %s %n", score);
   }
   
   public static void dealCards(Card[] cards, DeckOfCards deckOfCards) {
      for (int index = 0; index < CardsConfiguration.POKER_CARDS; index++) {
         // deal and display a Card
         cards[index] = deckOfCards.dealCard();
      } 
   }
}
