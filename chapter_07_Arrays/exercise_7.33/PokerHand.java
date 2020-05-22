/* =====================================================================================
 *       Filename:  PokerHand.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.33 - enum represents a poker hand of
                                 playing card
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum PokerHand {
   HIGH_CARD("high card"),
   ONE_PAIR("one pair"),
   TWO_PAIRS("two pairs"),
   THREE_OF_KIND("three of kind"),
   STRAIGHT("straight"),
   FLUSH("flush"),
   FULL_HOUSE("full house"),
   FOUR_OF_KIND("four of kind"),
   STRAIGHT_FLUSH("straight flush");
   
   private String name;
   
   PokerHand(String name) {
      this.name = name;
   }
   
   public String toString() {
      return name;
   }
} 
