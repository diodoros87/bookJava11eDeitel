/* =====================================================================================
 *       Filename:  DeckOfCards.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.31 - class represents a deck of playing cards
                           
                             
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
   
   public static final String[] FACES = {"Deuce", "Three", "Four", "Five", "Six",
         "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};    
   public static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};  

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
   
   static int getFaceIndex(String face) {
      for (int index = 0; index < FACES.length; index++) {
         if (face == FACES[index]) {
            return index;
         }
      } 
      
      throw new IllegalArgumentException (String.format("Unrecognized face for %s", face));
   }
   
   public static int[] calculateFacesFrequency(Card[] cards) {
      int[] facesFrequency = new int[FACES.length];
      
      for (int cardIndex = 0; cardIndex < cards.length; cardIndex++) {
         for (int faceIndex = 0; faceIndex < FACES.length; faceIndex++) {
            if (cards[cardIndex].getFace() == FACES[faceIndex]) {
               facesFrequency [faceIndex]++;
            }
         }
         
      }
      
      return facesFrequency;
   }
} 

