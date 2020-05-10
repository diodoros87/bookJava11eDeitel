/* =====================================================================================
 *       Filename:  KnightsTourTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.26 - control test of tracking while finding
                                knight's tour on virtual chessboard by
                                   heuristic accessibility algorithm and checking tour
                                      was closed
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTourTest {
   
   public static void main(String[] args) {
      
      System.out.printf("******** %s %n", KnightsTourControl.START_INFO);
      System.out.printf("*** %s %n%s%n", KnightsTourControl.QUIT_INFO, KnightsTourControl.EOT_INFO);
      
      KnightsTourControl knightsTourControl = new KnightsTourControl();
      
      do {
         knightsTourControl.printMenu();
      } while (true == StandardInput.getKnightsTourControlCommand(knightsTourControl));
      
   } 
   
} 
