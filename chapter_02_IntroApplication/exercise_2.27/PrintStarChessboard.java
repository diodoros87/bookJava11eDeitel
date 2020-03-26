/* =====================================================================================
 *       Filename:  PrintStarChessboard.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.27 - print chessboard from stars on screen
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */ 

public class PrintStarChessboard {   

   public static void main(String[] args) {
   
      String template = "* * * * * * * *";
      System.out.printf("%s         %n", template);
      System.out.printf("%c%s        %n", ' ', template);
      System.out.printf("%s        %n", template);
      System.out.printf(" %s        %n", template);
      System.out.printf("%s        %n", template);
      System.out.printf("%s%s        %n", " ", template);
      System.out.printf("%s        %n", template);
      System.out.printf(" %s        %n", template);
		
   }
   
}
