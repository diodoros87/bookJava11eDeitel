/* =====================================================================================
 *       Filename:  CardsConfiguration.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.31 - class represents cards configurations
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class CardsConfiguration {
   
   public static final int POKER_CARDS = 5; // constant # of Cards
   
   public enum PokerHands {HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_KIND, STRAIGHT,
                              FLUSH, FULL_HOUSE, FOUR_OF_KIND, STRAIGHT_FLUSH};
                              
   private Card[] dealingCards = new Card[POKER_CARDS];
   
   public CardsConfiguration(Card[] cards) {
      validateNumberOfCards(cards);
      
      if (false == areCardsUnique(cards)) {
         throw new IllegalArgumentException("Cards must be unique");
      }
      
      assignCards(cards);
   }
   
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
   
   public static boolean areCardsUnique(Card[] cards) {
      validateNumberOfCards(cards);
      
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
   
   public PokerHands classifyPokerHands () throws Exception {
      int[] facesFrequency = DeckOfCards.calculateFacesFrequency(dealingCards);
      int maxIdentityFacesNumber = getMax(facesFrequency);
      
      switch (maxIdentityFacesNumber) {
         case 4:
            return PokerHands.FOUR_OF_KIND;
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
   
   private PokerHands classifyMaxIdentityThree (int[] facesFrequency) throws Exception  {
      for (int frequency : facesFrequency) {
         if (1 == frequency) {
            return PokerHands.THREE_OF_KIND;
         }
         else if (2 == frequency) {
            return PokerHands.FULL_HOUSE;
         }
      }
      
      throw new Exception("Error for max identity faces number 3 calculation");
   }
   
   private PokerHands classifyMaxIdentityTwo(int[] facesFrequency) throws Exception  {
      int pairCounter = 0;
      
      for (int frequency : facesFrequency) {
         if (2 == frequency) {
            pairCounter++;
         }
      }
      
      if (1 == pairCounter) {
         return PokerHands.ONE_PAIR;
      }
      else if (2 == pairCounter) {
         return PokerHands.TWO_PAIRS;
      }
      else {
         throw new Exception("Error in pair calculation");
      }
   }
   
   private PokerHands classifyMaxIdentityOne() {
      boolean flush = isFlush();
      boolean straight = isStraight();
      
      if (true == flush && true == straight) {
         return PokerHands.STRAIGHT_FLUSH;
      }
      if (true == flush) {
         return PokerHands.FLUSH;
      }
      if (true == straight) {
         return PokerHands.STRAIGHT;
      }
      
      return PokerHands.HIGH_CARD;
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
   
   public static String getPokerHandsDescription(CardsConfiguration.PokerHands pokerHands) {
      switch (pokerHands) {
         case HIGH_CARD:
            return "high card";
         case ONE_PAIR:
            return "one pair";
         case TWO_PAIRS:
            return "two pairs";
         case THREE_OF_KIND:
            return "three of kind";
         case STRAIGHT:
            return "straight";
         case FLUSH:
            return "flush";
         case FULL_HOUSE:
            return "full house";
         case FOUR_OF_KIND:
            return "four of kind";
         case STRAIGHT_FLUSH:
            return "straight flush";
         default:
            return "unrecognized poker hands";
      }
         
   }
} 

