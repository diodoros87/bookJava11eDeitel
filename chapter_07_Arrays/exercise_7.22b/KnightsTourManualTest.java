/* =====================================================================================
 *       Filename:  KnightsTourManualTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.22b - manual control test on keyboard for User
                                to trying knight's tour on virtual chessboard
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTourManualTest {
   
   public static void main(String[] args) {
      
      System.out.printf("******** %s %n", KnightsTourControl.START_INFO);
      System.out.printf("*** %s %n%s%n", KnightsTourControl.QUIT_INFO, KnightsTourControl.EOT_INFO);
      
      StandardInput standardInput = new StandardInput();
      
      do {
         standardInput.knightsTourControl.updateData();
         standardInput.knightsTourControl.printMenu();
      } while (true == standardInput.getKnightsTourControlCommand());
      
   } 
   
} 
