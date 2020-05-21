/* =====================================================================================
 *       Filename:  Card.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.34 - Card class represents a playing card
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Card {
   private final Face face; // face of card ("Ace", "Deuce", ...)
   private final Suit suit; // suit of card ("Hearts", "Diamonds", ...)

   // two-argument constructor initializes card's face and suit
   public Card(Face cardFace, Suit cardSuit) {
      this.face = cardFace; // initialize face of card
      this.suit = cardSuit; // initialize suit of card
   } 

   // return String representation of Card
   public String toString() {             
      return face + " of " + suit;        
   }                
} 
