/* =====================================================================================
 *       Filename:  CardsConfiguration.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.32 - class represents cards configurations
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class CardsConfiguration {
   
   public static final int POKER_CARDS = 5; // constant # of Cards
                              
   private Card[] dealingCards = new Card[POKER_CARDS];
   
   public CardsConfiguration(Card[] cards) {
      validateNumberOfCards(cards);
      
      if (false == areCardsUnique(cards)) {
         throw new IllegalArgumentException("Cards must be unique");
      }
      
      assignCards(cards);
   }
   
   public CardsConfiguration() { }
   
   public Card[] getDealingCards() {
      return dealingCards;
   }
   
   public void setDealingCards(Card[] cards) {
      validateNumberOfCards(cards);
      
      if (false == areCardsUnique(cards)) {
         throw new IllegalArgumentException("Cards must be unique");
      }
      
      assignCards(cards);
   }
   
   public void setDealingCard(Card card, int index) {
      if (index < 0 || index >= POKER_CARDS) {
         throw new IllegalArgumentException("card can not be set on index" + index);
      }
      
      if (false == areCardsUnique(card)) {
         throw new IllegalArgumentException("Identical card has already existed and can not be accepted");
      }
      
      dealingCards[index] = card;
   }
   
   private void assignCards(Card[] cards) {
      for (int index = 0; index < cards.length; index++) {
         dealingCards[index] = cards[index];
      } 
   }
   
   public static void validateNumberOfCards(Card[] cards) {
      if (cards.length != POKER_CARDS) {
         throw new IllegalArgumentException("Number of cards must be " + POKER_CARDS);
      }
   }
   
   public static void checkNullCard(Card card) {
      if (null == card) {
         throw new IllegalArgumentException("Card can not be null");
      }
   }
   
   public static void checkNullCard(Card[] cards) {
      for (int index = 0; index < cards.length; index++) {
         checkNullCard(cards[index]);
      } 
   }
   
   public boolean areCardsUnique(Card card) {
      checkNullCard(card);
      
      for (int index = 0; index < POKER_CARDS; index++) {
         if (card.getFace() == dealingCards[index].getFace() &&
             card.getSuit() == dealingCards[index].getSuit()) {
               
            return false;
         }
      }
      
      return true;
   }
   
   public static boolean areCardsUnique(Card[] cards) {
      validateNumberOfCards(cards);
      checkNullCard(cards);
      
      for (int startIndex = 0; startIndex < POKER_CARDS - 1; startIndex++) {
         for (int nextIndex = startIndex + 1; nextIndex < POKER_CARDS; nextIndex++) {
            Card first = cards[startIndex];
            Card second = cards[nextIndex];
            
            if (first.getFace() == second.getFace() &&
                first.getSuit() == second.getSuit()) {
                
                return false;
            }
         }
      }
      
      return true;
   }
   
   public PokerHand classifyPokerHand () throws Exception {
      int[] facesFrequency = DeckOfCards.calculateFacesFrequency(dealingCards);
      int maxIdentityFacesNumber = getMax(facesFrequency);
      
      switch (maxIdentityFacesNumber) {
         case 4:
            return PokerHand.FOUR_OF_KIND;
         case 3:
            return classifyMaxIdentityThree(facesFrequency);
         case 2:
            return classifyMaxIdentityTwo(facesFrequency);
         case 1:
            return classifyMaxIdentityOne();
         default:
            throw new IllegalArgumentException("max identity faces number must be in range from 1 to 4");
      }
   }
   
   private PokerHand classifyMaxIdentityThree (int[] facesFrequency) throws Exception  {
      for (int frequency : facesFrequency) {
         if (1 == frequency) {
            return PokerHand.THREE_OF_KIND;
         }
         else if (2 == frequency) {
            return PokerHand.FULL_HOUSE;
         }
      }
      
      throw new Exception("Error for max identity faces number 3 calculation");
   }
   
   private PokerHand classifyMaxIdentityTwo(int[] facesFrequency) throws Exception  {
      int pairCounter = 0;
      
      for (int frequency : facesFrequency) {
         if (2 == frequency) {
            pairCounter++;
         }
      }
      
      if (1 == pairCounter) {
         return PokerHand.ONE_PAIR;
      }
      else if (2 == pairCounter) {
         return PokerHand.TWO_PAIRS;
      }
      else {
         throw new Exception("Error in pair calculation");
      }
   }
   
   private PokerHand classifyMaxIdentityOne() {
      boolean flush = isFlush();
      boolean straight = isStraight();
      
      if (true == flush && true == straight) {
         return PokerHand.STRAIGHT_FLUSH;
      }
      if (true == flush) {
         return PokerHand.FLUSH;
      }
      if (true == straight) {
         return PokerHand.STRAIGHT;
      }
      
      return PokerHand.HIGH_CARD;
   }
   
   private boolean isFlush() {
      String suit = dealingCards[0].getSuit();
      
      for (int index = 1; index < dealingCards.length; index++) {
         if (suit != dealingCards[index].getSuit()) {
            return false;
         }
      } 
      
      return true;
   }
   
   private boolean isStraight() { 
      int[] facesIndexes = getFacesIndexes(dealingCards);
      
      return isStraight(facesIndexes);
   }
   
   public void sortAsc() {
      for (int iteration = 1; iteration < dealingCards.length; iteration++) {
         for (int index = 0; index < dealingCards.length - iteration; index++) {
            String face = dealingCards[index].getFace();
            int faceIndex = DeckOfCards.getFaceIndex(face);
            
            String nextFace = dealingCards[index + 1].getFace();
            int nextFaceIndex = DeckOfCards.getFaceIndex(nextFace);
            
            if (faceIndex > nextFaceIndex) {
               Card temp = dealingCards[index + 1];
               dealingCards[index + 1] = dealingCards[index];
               dealingCards[index] = temp;
            }
         }
      } 
   }
   
   public void changeCardsOrderOnePair() {  
      // assume that in frequency array are only values: one time 2, three times 1 and nine times 0
      String cardFace = DeckOfCards.getFaceByFrequency(dealingCards, 2);
      
      // insert faces with two frequency at 2 last indexes (to remain card with this face)
      insertCardsOnLastIndexes(cardFace, 2);
   }
   
   private boolean isCardOnIndex(String cardFace, int index) {
      if (cardFace == dealingCards[index].getFace()) {
         return true;
      }
      
      return false;
   }
   
   public void changeCardsOrderThreeOfKind() {
      // assume that in frequency array are only values: one time 3, two times 1 and nine times 0
      String cardFace = DeckOfCards.getFaceByFrequency(dealingCards, 3);
      insertCardsOnLastIndexes(cardFace, 3); // insert face with three frequency at last index (to remain card with this face)
   }
      
   public void changeCardsOrderTwoPairs() {
      // assume that in frequency array are only values: two times 2, one time 1 and ten times 0
      String cardFace = DeckOfCards.getFaceByFrequency(dealingCards, 1);
      insertCardOnIndex(0, cardFace); // insert face with one frequency at 0 index (to replace card with this face)
   }
   
   private void insertCardOnIndex(int swapIndex, String cardFace) {
      Card temp;
      String dealingCardFace;
      for (int index = 0; index < dealingCards.length; index++) {
         dealingCardFace = dealingCards[index].getFace();
         if (cardFace == dealingCardFace) {
            temp = dealingCards[index];
            dealingCards[index] = dealingCards[swapIndex];
            dealingCards[swapIndex] = temp;   // insert card with one frequency at start index (to replace card with this face)
            break;
         }
      }
   }
   
   private void insertCardsOnLastIndexes(String cardFace, int numberOfCards) {
      int changes = numberOfCards;  // number of changes

      // insert card with face with at last indexes
      for (int index = 0; changes > 0 && index < dealingCards.length - numberOfCards; index++) {
         if (true == isCardOnIndex(cardFace, index)) {
         
            int lastIndex = dealingCards.length - 1;
            while (lastIndex >= 0 && true == isCardOnIndex(cardFace, lastIndex)) { // to avoid swapping with card with identical face
               lastIndex--;
            }
            
            if (lastIndex >= dealingCards.length - numberOfCards) {
               Card temp = dealingCards[index];
               dealingCards[index] = dealingCards[lastIndex];
               dealingCards[lastIndex] = temp;
               changes--;
            }
            else {
               break;   // all cards with cardFace are on the last indexes
            }
         }
      }
   }
   
   public static int[] getFacesIndexes(Card[] cards) {
      int [] facesIndexes = new int[cards.length];
      String face;
      for (int index = 0; index < cards.length; index++) {
         face = cards[index].getFace();
         facesIndexes[index] = DeckOfCards.getFaceIndex(face);
      } 
      
      return facesIndexes;
   }
   
   static boolean isStraight(int [] facesIndexes) {
      sortAsc(facesIndexes);
         
      for (int index = 1, difference; index < facesIndexes.length; index++) {
         difference = facesIndexes[index] - facesIndexes[index - 1];
         if (difference != 1 && difference != 9) {
            return false;
         }
      }
      
      return true;
   }
   
   public static void sortAsc(int[] array) {
      for (int iteration = 1, temp; iteration < array.length; iteration++) {
         for (int index = 0; index < array.length - iteration; index++) {
            if (array[index] > array[index + 1]) {
               temp = array[index + 1];
               array[index + 1] = array[index];
               array[index] = temp;
            }
         }
      } 
   }

   public static int getMax(int[] array) {
      int max = Integer.MIN_VALUE;
      
      for (int index = 0; index < array.length; index++) {
         if (max < array[index]) {
            max = array[index];
         }
      } 
      
      return max;
   }
} 

