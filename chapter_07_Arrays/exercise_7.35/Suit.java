/* =====================================================================================
 *       Filename:  Suit.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.35 - enum represents a suit of
                                 playing card
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum Suit {
   HEARTS("Hearts"),
   DIAMONDS("Diamonds"),
   CLUBS("Clubs"),
   SPADES("Spades");
   
   private String name;
   
   private Suit(String name) {
      this.name = name;
   }
   
   public String toString() {
      return name;
   }
} 
