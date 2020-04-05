/* =====================================================================================
 *       Filename:  StarChessboard.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.33 - print chessboard using asterisks and spaces
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */ 

public class StarChessboard {   

   public static void main(String[] args) {
   
      final short printAsterisk  = 8; 
      final short rows           = 8;
      short printAsteriskCounter = 1;
      short rowsCounter          = 1;
      
      while (rowsCounter <= rows) {
         if (0 == rowsCounter % 2) {
            System.out.print(" ");
         }
         
         printAsteriskCounter = 1;
         while (printAsteriskCounter <= printAsterisk) {
            System.out.print("* ");
            printAsteriskCounter++;
         }
         
         System.out.println("");
         rowsCounter++;
      }
		
   }
   
}
