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
   CardsConfiguration cardsConfiguration;
   PokerHand pokerHand;
   int numberOfCardsToReplace;
   
   public Dealer(Card[] cards) {
      cardsConfiguration = new CardsConfiguration(cards);
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
         throw new IllegalArgumentException("number of cards to replace more than number of received cards");
      }
      
      cardsConfiguration.setDealingCard(receivedCard, numberOfCardsToReplace);
      if (numberOfCardsToReplace == 0) {
         pokerHand = cardsConfiguration.classifyPokerHand();  // after replace all cards, classify new poker hand
      }
   }
   
   private int calculateNumberOfCardsToReplace() throws Exception {
      pokerHand = cardsConfiguration.classifyPokerHand();
      switch (pokerHand) {
         case HIGH_CARD:
         case ONE_PAIR:
            return 3;
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
