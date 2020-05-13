/* =====================================================================================
 *       Filename:  EightQueensTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.24 - control test of tracking while solving
                                 eight queens problem on virtual chessboard by
                                    eliminate positions by
                                    heuristic algorithm
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class EightQueensTest {
   
   public static void main(String[] args) {
      
      System.out.printf("******** %s %n", EightQueensControl.START_INFO);
      System.out.printf("*** %s %n%s%n", EightQueensControl.QUIT_INFO, EightQueensControl.EOT_INFO);
      
      EightQueensControl eightQueensControl = new EightQueensControl();
      
      do {
         eightQueensControl.printMenu();
      } while (true == StandardInput.getCommand(eightQueensControl));
      
   } 
   
} 
