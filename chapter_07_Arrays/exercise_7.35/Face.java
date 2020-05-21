/* =====================================================================================
 *       Filename:  Face.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.35 - enum represents a face of
                                 playing card
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum Face {
   DEUCE("Deuce"),
   THREE("Three"),
   FOUR("Four"),
   FIVE("Five"),
   SIX("Six"),
   SEVEN("Seven"),
   EIGHT("Eight"),
   NINE("Nine"),
   TEN("Ten"),
   JACK("Jack"),
   QUEEN("Queen"),
   KING("King"),
   ACE("Ace"); 
   
   private String name;
   
   private Face(String name) {
      this.name = name;
   }
   
   public String toString() {
      return name;
   }
} 
