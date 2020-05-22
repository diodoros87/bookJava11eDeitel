/* =====================================================================================
 *       Filename:  Card.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.32 - Card class represents a playing card
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Card {
   private final String face; // face of card ("Ace", "Deuce", ...)
   private final String suit; // suit of card ("Hearts", "Diamonds", ...)

   // two-argument constructor initializes card's face and suit
   public Card(String cardFace, String cardSuit) {
      if (null == cardFace || null == cardSuit) {
         throw new NullPointerException("Card can not accept null field");
      }
      if (false == contains(DeckOfCards.FACES, cardFace)) {
         throw new IllegalArgumentException (String.format("Unrecognized face for %s", cardFace));
      }
      if (false == contains(DeckOfCards.SUITS, cardSuit)) {
         throw new IllegalArgumentException (String.format("Unrecognized suit for %s", cardSuit));
      }
      
      this.face = cardFace; // initialize face of card
      this.suit = cardSuit; // initialize suit of card
   } 

   // return String representation of Card
   public String toString() {             
      return face + " of " + suit;        
   }   
   
   public String getFace() {             
      return face;        
   } 
   
   public String getSuit() {             
      return suit;        
   } 
   
   public static boolean contains(String [] array, String value) {
      for (String element : array) {
         if (element == value) {
            return true;
         }
      }
      
      return false;
   }
} 
