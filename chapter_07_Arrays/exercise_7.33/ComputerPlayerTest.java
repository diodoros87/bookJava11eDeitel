/* =====================================================================================
 *       Filename:  ComputerPlayerTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - Card shuffling, dealing for 2 players (one 
                             of them is firstPlayer with option replace some cards) and
                                deciding about game's result 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ComputerPlayerTest {

   public static void main(String[] args) throws Exception {
      randomTest();
      testUntilPokerHand();
   }
   
   public static void randomTest() throws Exception {
      Card[] firstPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      Card[] secondPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      DeckOfCards deckOfCards = new DeckOfCards();
      
      deckOfCards.shuffle(); // place Cards in random order
      
      dealCards(firstPlayerCards, deckOfCards);
      
      Player firstPlayer = new Player(firstPlayerCards);
      CardsConfiguration firstPlayerConfiguration = firstPlayer.getCardsConfiguration();
      displayPockerHands(firstPlayerConfiguration, "*******  First player cards before replace:");
      
      dealCards(secondPlayerCards, deckOfCards);
      CardsConfiguration secondConfiguration = new CardsConfiguration(secondPlayerCards);
      
      replaceCards(firstPlayer, deckOfCards);
      
      displayPockerHands(firstPlayerConfiguration, "*******  First player cards after replace:");
      displayPockerHands(secondConfiguration, "*******  Second player cards:");
      displayGameResult(firstPlayerConfiguration, secondConfiguration);
   }
   
   public static void testUntilPokerHand() throws Exception {
      DeckOfCards deckOfCards = new DeckOfCards();
      CardsConfiguration firstPlayerConfiguration = getConfigurationUntilPockerHand(deckOfCards, PokerHand.THREE_OF_KIND);
      Player firstPlayer = new Player(firstPlayerConfiguration);
      
      displayPockerHands(firstPlayerConfiguration, "*******  First player cards before replace:");
      
      Card[] secondPlayerCards = new Card[CardsConfiguration.POKER_CARDS];
      dealCards(secondPlayerCards, deckOfCards);
      CardsConfiguration secondConfiguration = new CardsConfiguration(secondPlayerCards);
      
      replaceCards(firstPlayer, deckOfCards);
      
      displayPockerHands(firstPlayerConfiguration, "*******  First player cards after replace:");
      displayPockerHands(secondConfiguration, "*******  Second player cards:");
      displayGameResult(firstPlayerConfiguration, secondConfiguration);
      
      displayRemainedCards(deckOfCards, "---------- Remained cards in deck:", firstPlayerConfiguration, secondConfiguration);
   }
   
   public static void replaceCards(Player firstPlayer, DeckOfCards deckOfCards) throws Exception {
      int counter = firstPlayer.getNumberOfCardsToReplace();
      Card card;
      while (counter > 0) {
         card = deckOfCards.dealCard();
         firstPlayer.receiveCard(card);
         counter--;
      } 
   }
   
   public static void displayCards(Card[] cards, String title) {
      System.out.printf("%n%s%n", title);
      for (int counter = 0; counter < cards.length; counter++) {
         System.out.printf("%s %n", cards[counter]);
      } 
   }
   
   public static void displayRemainedCards(DeckOfCards deckOfCards, String title,
                             CardsConfiguration configuration, CardsConfiguration secondConfiguration) {
      System.out.printf("%n%s%n", title);
      Card[] cards = configuration.getDealingCards();
      Card[] secondCards = secondConfiguration.getDealingCards();
      
      Card card = deckOfCards.dealCard();
      for (int counter = 0; card != null; counter++) {
      
         for (int index = 0; index < CardsConfiguration.POKER_CARDS; index++) {
            deckOfCards.checkCardInDeck(cards[index]);
            deckOfCards.checkCardInDeck(secondCards[index]);
         }
         
         System.out.printf("%2d. %s %n", counter, card);
         card = deckOfCards.dealCard();
      } 
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
      for (int index = 0; index < CardsConfiguration.POKER_CARDS; index++) {
         deckOfCards.acceptCard(cards[index]);
      }
   }
}
