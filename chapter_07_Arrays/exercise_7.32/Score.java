/* =====================================================================================
 *       Filename:  Score.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.32 - enum represents a score(result) of game
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum Score {
   
   FIRST_PLAYER_WIN("first player wins"),
   SECOND_PLAYER_WIN("second player wins"),
   DRAW("draw"); 
   
   private String name;
   
   Score(String name) {
      this.name = name;
   }
   
   public String toString() {
      return name;
   }
} 
