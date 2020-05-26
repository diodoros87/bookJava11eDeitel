/* =====================================================================================
 *       Filename:  Player.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - class of player with option replace some
                                cards
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

//package poker;

public class Player {
   public static final int MAX_OF_REPLACED_CARDS = 3;
   
   private Card[] returnedCards = null;
   private boolean cardsToReturn = false;
   
   private CardsConfiguration cardsConfiguration = new CardsConfiguration();
   private PokerHand pokerHand;
   private int numberOfCardsToReplace;
   
   public Player() { }
   
   public Player(Card[] cards) {
      cardsConfiguration = new CardsConfiguration(cards);
   }
   
   public Player(CardsConfiguration cardsConfiguration) {
      this.cardsConfiguration = cardsConfiguration;
   }
   
   public CardsConfiguration getCardsConfiguration() {
      return cardsConfiguration;
   }
   
   public void setCardsToConfiguration(Card[] cards) throws Exception {
      cardsConfiguration.setDealingCards(cards);
      pokerHand = cardsConfiguration.classifyPokerHand();
   }
   
   public PokerHand getPokerHand() {
      return pokerHand;
   }
   
   public int getNumberOfCardsToReplace() throws Exception {
      numberOfCardsToReplace = calculateNumberOfCardsToReplace();
      
      if (numberOfCardsToReplace > 0) {
         changeCardsOrder();
         returnedCards = new Card[numberOfCardsToReplace];
         cardsToReturn = true;
      }
      
      return numberOfCardsToReplace;
   }
   
   public void receiveCard(Card receivedCard) throws Exception {
      if (--numberOfCardsToReplace < 0) {
         throw new IllegalArgumentException("number of cards to replace less than number of received cards");
      }
      
      Card cardToReturn = cardsConfiguration.returnDealingCard(numberOfCardsToReplace);
      returnedCards[numberOfCardsToReplace] = cardToReturn;
      
      cardsConfiguration.setDealingCard(receivedCard, numberOfCardsToReplace);
      if (numberOfCardsToReplace == 0) {
         pokerHand = cardsConfiguration.classifyPokerHand();  // after replace all cards, classify new poker hand
      }
   }
   
   public boolean areCardsToReturn() {
      return cardsToReturn;
   }
   
   public Card[] getReturnedCards() throws Exception {
      if (false == cardsToReturn) {
         return null;
      }
      if (null == returnedCards) {
         throw new Exception("no cards to return");
      }
      
      cardsToReturn = false;
      
      return returnedCards;
   }
   
   public void receiveCards(Card[] receivedCards, int[] indexes) throws Exception {
      if (null == receivedCards) {
         throw new NullPointerException("received cards can not be null");
      }
      if (CardsConfiguration.POKER_CARDS < receivedCards.length) {
         throw new IllegalArgumentException("maximum number of cards less than number of received cards");
      }
      if (receivedCards.length > indexes.length) {
         throw new IllegalArgumentException("number of received cards more than number of cards indexes");
      }
      if (receivedCards.length > 0) {
         returnedCards = new Card[receivedCards.length];
         cardsToReturn = true;
      }
      
      for(int counter = 0; receivedCards.length > counter; counter++) {
         Card cardToReturn = cardsConfiguration.returnDealingCard(indexes[counter]);
         returnedCards[counter] = cardToReturn;
      
         cardsConfiguration.setDealingCard(receivedCards[counter], indexes[counter]);
      }
      
      pokerHand = cardsConfiguration.classifyPokerHand();  // after replace all cards, classify new poker hand
   }
   
   private int calculateNumberOfCardsToReplace() throws Exception {
      pokerHand = cardsConfiguration.classifyPokerHand();
      switch (pokerHand) {
         case HIGH_CARD:
         case ONE_PAIR:
            return MAX_OF_REPLACED_CARDS;
         case TWO_PAIRS:
            return 1;
         case THREE_OF_KIND:
            return 2;
         default:
            return 0;
      }
   }
   
   private void changeCardsOrder() {
      switch (pokerHand) {
         case HIGH_CARD:
            cardsConfiguration.sortAsc();
            break;
         case ONE_PAIR:
            cardsConfiguration.changeCardsOrderOnePair();
            break;
         case TWO_PAIRS:
            cardsConfiguration.changeCardsOrderTwoPairs();
            break;
         case THREE_OF_KIND:
            cardsConfiguration.changeCardsOrderThreeOfKind();
            break;
         default:
            return;
      }
   }
   
}
