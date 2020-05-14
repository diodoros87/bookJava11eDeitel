/* =====================================================================================
 *       Filename:  EightQueensTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.25 - test of solving
                                 eight queens problem on virtual chessboard by
                                    brute force algorithm
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class EightQueensTest {
   
   public static void main(String[] args) {
      
      System.out.printf("******** %s %n", EightQueensDescription.INFO);
      
      EightQueens eightQueens = new EightQueens();
      eightQueens.place8Queens();
      
      System.out.printf("All possible solutions of 8 queens problem on virtual chessboard by brute force algorithm are above%n");
      System.out.printf("******** %s %n", EightQueensDescription.INFO);
   } 
   
} 
