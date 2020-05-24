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
      Card[] exampleCards = null;
      exampleCards = new Card[3];
      exampleCards[0] = new Card("Ace", "Hearts");
      exampleCards[1] = new Card("King", "Diamonds");
      exampleCards[2] = new Card("Ace", "Clubs");
      displayCards(exampleCards, "Example of cards: ");
      
      DeckOfCards deckOfCards = new DeckOfCards();
      randomTest(deckOfCards);
      testUntilPokerHand(deckOfCards);
   }
   
   public static void randomTest(DeckOfCards deckOfCards) throws Exception {
      Card[] dealerCards = new Card[CardsConfiguration.POKER_CARDS];
      Card[] secondPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      //DeckOfCards deckOfCards = new DeckOfCards();
      
      deckOfCards.shuffle(); // place Cards in random order
      
      dealCards(dealerCards, deckOfCards);
      
      Dealer dealer = new Dealer(dealerCards);
      CardsConfiguration dealerConfiguration = dealer.getCardsConfiguration();
      displayPockerHands(dealerConfiguration, "*******  Dealer cards before replace:");
      
      dealCards(secondPlayerCards, deckOfCards);
      CardsConfiguration secondConfiguration = new CardsConfiguration(secondPlayerCards);
      
      replaceCards(dealer, deckOfCards);
      displayCards(dealer.getReturnedCards(), "---------- Dealer's cards to return:");
      
      displayPockerHands(dealerConfiguration, "*******  Dealer cards after replace:");
      displayPockerHands(secondConfiguration, "*******  Second player cards:");
      displayGameResult(dealerConfiguration, secondConfiguration);
      
      returnCardsToDeck(dealer.getReturnedCards(), deckOfCards);
      displayRemainedCards(deckOfCards, "---------- Remained cards in deck:", dealerConfiguration, secondConfiguration);
      returnCardsToDeck(dealerConfiguration.getDealingCards(), deckOfCards);
      returnCardsToDeck(secondPlayerCards, deckOfCards);
      displayRemainedCards(deckOfCards, "---------- Remained cards in deck:");
   }
   
   public static void testUntilPokerHand(DeckOfCards deckOfCards) throws Exception {
      //DeckOfCards deckOfCards = new DeckOfCards();
      displayRemainedCards(deckOfCards, "---------- test until poker hand = THREE_OF_KIND   Remained cards in deck:");
      CardsConfiguration dealerConfiguration = getConfigurationUntilPockerHand(deckOfCards, PokerHand.THREE_OF_KIND);
      Dealer dealer = new Dealer(dealerConfiguration);
      
      displayPockerHands(dealerConfiguration, "*******  Dealer cards before replace:");
      
      Card[] secondPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      dealCards(secondPlayerCards, deckOfCards);
      CardsConfiguration secondConfiguration = new CardsConfiguration(secondPlayerCards);
      
      replaceCards(dealer, deckOfCards);
      displayCards(dealer.getReturnedCards(), "---------- Dealer's cards to return:");
      
      displayPockerHands(dealerConfiguration, "*******  Dealer cards after replace:");
      displayPockerHands(secondConfiguration, "*******  Second player cards:");
      displayGameResult(dealerConfiguration, secondConfiguration);
      
      returnCardsToDeck(dealer.getReturnedCards(), deckOfCards);
      returnCardsToDeck(dealerConfiguration.getDealingCards(), deckOfCards);
      returnCardsToDeck(secondPlayerCards, deckOfCards);
      displayRemainedCards(deckOfCards, "---------- Remained cards in deck:");
      
      Card[] otherCards = new Card[CardsConfiguration.POKER_CARDS];
      dealCards(otherCards, deckOfCards);
      displayCards(otherCards, "*******  Other cards dealing from deck:");
      displayRemainedCards(deckOfCards, "---------- Remained cards in deck:");
      //displayRemainedCards(deckOfCards, "---------- Remained cards in deck:", dealerConfiguration, secondConfiguration);
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
   
   public static void displayRemainedCards(DeckOfCards deckOfCards, String title) {
      System.out.printf("%n%s%n", title);
      
      int remainedCardsInDeck = deckOfCards.getCurrentNumberOfCards();
      Card[] dealingCards = new Card[remainedCardsInDeck];
      
      Card card = deckOfCards.dealCard();
      for (int counter = 0; card != null; counter++) {
         dealingCards[counter] = card;
         System.out.printf("%2d. %s %n", counter, dealingCards[counter]);
         card = deckOfCards.dealCard();
      } 
      
      returnCardsToDeck(dealingCards, deckOfCards);
   }
   
   public static void displayRemainedCards(DeckOfCards deckOfCards, String title,
                             CardsConfiguration configuration, CardsConfiguration secondConfiguration) {
      System.out.printf("%n%s%n", title);
      
      int remainedCardsInDeck = deckOfCards.getCurrentNumberOfCards();
      Card[] dealingCards = new Card[remainedCardsInDeck];
      
      Card[] cards = configuration.getDealingCards();
      Card[] secondCards = secondConfiguration.getDealingCards();
      
      Card card = deckOfCards.dealCard();
      for (int counter = 0; card != null; counter++) {
         dealingCards[counter] = card;
         
         for (int index = 0; index < CardsConfiguration.POKER_CARDS; index++) {
            deckOfCards.checkCardInDeck(cards[index]);
            deckOfCards.checkCardInDeck(secondCards[index]);
         }
         
         System.out.printf("%2d. %s %n", counter, card);
         card = deckOfCards.dealCard();
      } 
      
      returnCardsToDeck(dealingCards, deckOfCards);
   }
   
   public static void displayPockerHands(CardsConfiguration configuration, String title) throws Exception {
      displayCards(configuration.getDealingCards(), title);
      PokerHand pokerHand = configuration.classifyPokerHand();
      System.out.printf("%s %s %n", " --- Poker hand of dealing cards:", pokerHand);
   }
   
   public static void displayGameResult(CardsConfiguration firstConfiguration, CardsConfiguration secondConfiguration) throws Exception {
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
   
   public static CardsConfiguration getConfigurationUntilPockerHand(DeckOfCards deckOfCards,
                                                PokerHand expectedPokerHand) throws Exception { 
      Card dealedCard;
      PokerHand pokerHand;
      CardsConfiguration configuration = new CardsConfiguration();
      Card[] cards = new Card[CardsConfiguration.POKER_CARDS];
      
      do {
         deckOfCards.shuffle(); // place Cards in random order
         dealCards(cards, deckOfCards); 
         
         configuration.setDealingCards(cards);
         pokerHand = configuration.classifyPokerHand();
         
         if (pokerHand != expectedPokerHand) {
            returnCardsToDeck(cards, deckOfCards);
         }
      } while (pokerHand != expectedPokerHand);
      
      return configuration;
   }
   
   public static void returnCardsToDeck(Card[] cards, DeckOfCards deckOfCards) {
      for (int index = 0; index < cards.length; index++) {
         deckOfCards.acceptCard(cards[index]);
      }
   }
}
