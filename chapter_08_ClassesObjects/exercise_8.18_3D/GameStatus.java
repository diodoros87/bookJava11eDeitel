/* =====================================================================================
 *       Filename:  GameStatus.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - game status enum type
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum GameStatus {
   CONTINUE("indecisive"),
   FIRST_PLAYER_WIN("first player win"),
   SECOND_PLAYER_WIN("second player win"),
   DRAW("draw");
   
   private String name;
   
   private GameStatus(String name) {
      this.name = name;
   }
   
   public String toString() {
      return name;
   }
} 

