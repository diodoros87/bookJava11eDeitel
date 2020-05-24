/* =====================================================================================
 *       Filename:  Dealer.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.32 - class of dealer with option replace some
                                cards
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Dealer {
   public static final int MAX_OF_REPLACED_CARDS = 3;
   
   private Card[] returnedCards = null;
   private boolean cardsToReturn = false;
   
   private CardsConfiguration cardsConfiguration;
   private PokerHand pokerHand;
   private int numberOfCardsToReplace;
   
   public Dealer(Card[] cards) {
      cardsConfiguration = new CardsConfiguration(cards);
   }
   
   public Dealer(CardsConfiguration cardsConfiguration) {
      this.cardsConfiguration = cardsConfiguration;
   }
   
   public CardsConfiguration getCardsConfiguration() {
      return cardsConfiguration;
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
   
//    public Card getCardToReplace() {
//       numberOfCardsToReplace = getNumberOfCardsToReplace();
//       
//       if (numberOfCardsToReplace == 0) {
//          return null;
//       }
//       else {
//          return new Card[numberOfCardsToReplace];
//       }
//    }
   
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
      if (null == returnedCards) {
         throw new Exception("no cards to return");
      }
      
      return returnedCards;
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
