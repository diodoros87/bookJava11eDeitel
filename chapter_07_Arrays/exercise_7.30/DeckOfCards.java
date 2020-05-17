/* =====================================================================================
 *       Filename:  DeckOfCards.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.30 - class represents a deck of playing cards
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.security.SecureRandom;

public class DeckOfCards {
   // random number generator
   private static final SecureRandom randomNumbers = new SecureRandom();
   private static final int NUMBER_OF_CARDS = 52; // constant # of Cards
   
   public static final String[] FACES = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
         "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};    
   public static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};  
   
   public static final int DEALING_CARDS = 5; // constant # of Cards
   
   public enum PokerHands {HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_KIND, STRAIGHT,
                              FLUSH, FULL_HOUSE, FOUR_OF_KIND, STRAIGHT_FLUSH};

   private Card[] deck = new Card[NUMBER_OF_CARDS]; // Card references
   private int currentCard = 0; // index of next Card to be dealt (0-51)

   // constructor fills deck of Cards
   public DeckOfCards() {
      // populate deck with Card objects                   
      for (int count = 0; count < deck.length; count++) {  
         deck[count] = new Card(FACES[count % 13], SUITS[count / 13]);                                    
      }                                                    
   } 

   // shuffle deck of Cards with one-pass algorithm
   public void shuffle() {
      // next call to method dealCard should start at deck[0] again
      currentCard = 0; 

      // for each Card, pick another random Card (0-51) and swap them
      for (int first = 0; first < deck.length; first++) {
         // select a random number between 0 and 51 
         int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

         // swap current Card with randomly selected Card
         Card temp = deck[first];   
         deck[first] = deck[second];
         deck[second] = temp;       
      } 
   } 

   // deal one Card
   public Card dealCard() {
      // determine whether Cards remain to be dealt
      if (currentCard < deck.length) {
         return deck[currentCard++]; // return current Card in array
      } 
      else {
         return null; // return null to indicate that all Cards were dealt
      } 
   } 
   
   public static void checkNumberOfCards(Card[] cards) {
      if (cards.length != DEALING_CARDS) {
         throw new IllegalArgumentException("Number of cards must be " + DEALING_CARDS);
      }
   }
   
   public static PokerHands classifyPokerHands (Card[] dealingCards) throws Exception {
      checkNumberOfCards(dealingCards);
      
      int[] facesFrequency = calculateFacesFrequency(dealingCards);
      int maxIdentityFacesNumber = getMax(facesFrequency);
      
      switch (maxIdentityFacesNumber) {
         case 4:
            return PokerHands.FOUR_OF_KIND;
         case 3:
            return classifyMaxIdentityThree(facesFrequency);
         case 2:
            return classifyMaxIdentityTwo(facesFrequency);
         case 1:
            return classifyMaxIdentityOne(dealingCards);
         default:
            throw new IllegalArgumentException("max identity faces number must be in range from 1 to 4");
      }
   }
   
   private static PokerHands classifyMaxIdentityThree (int[] facesFrequency) throws Exception  {
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
   
   private static PokerHands classifyMaxIdentityTwo(int[] facesFrequency) throws Exception  {
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
   
   private static PokerHands classifyMaxIdentityOne(Card[] dealingCards) {
      boolean flush = isFlush(dealingCards);
      boolean straight = isStraight(dealingCards);
      
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
   
   private static boolean isFlush(Card[] dealingCards) {
      String suit = dealingCards[0].getSuit();
      
      for (int index = 1; index < dealingCards.length; index++) {
         if (suit != dealingCards[index].getSuit()) {
            return false;
         }
      } 
      
      return true;
   }
   
   private static boolean isStraight(Card[] dealingCards) {
      int [] facesIndexes = new int[DEALING_CARDS];
      String face;
      for (int index = 0; index < dealingCards.length; index++) {
         face = dealingCards[index].getFace();
         facesIndexes[index] = getFaceIndex(face);
      } 
      
      return isStraight(facesIndexes);
   }
   
   private static boolean isStraight(int [] facesIndexes) {
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
   
   private static int getFaceIndex(String face) {
      for (int index = 0; index < FACES.length; index++) {
         if (face == FACES[index]) {
            return index;
         }
      } 
      
      throw new IllegalArgumentException (String.format("Unrecognized face for %s", face));
   }
   
   public static int[] calculateFacesFrequency(Card[] dealingCards) {
      checkNumberOfCards(dealingCards);
      
      int[] facesFrequency = new int[FACES.length];
      
      for (int cardIndex = 0; cardIndex < dealingCards.length; cardIndex++) {
         for (int faceIndex = 0; faceIndex < FACES.length; faceIndex++) {
            if (dealingCards[cardIndex].getFace() == FACES[faceIndex]) {
               facesFrequency [faceIndex]++;
            }
         }
         
      }
      
      return facesFrequency;
   }
   
   public static String getPokerHandsDescription(PokerHands pokerHands) {
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
            throw new IllegalArgumentException("Unrecognized pocker hands");
      }
         
   }
} 

