/* =====================================================================================
 *       Filename:  TurtleGraphicsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.21 - 2D graphics control test
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class TurtleGraphicsTest {

   static void printMenu() {
      System.out.printf("%n------------- MENU %n");
      
      System.out.printf("### enter %d for print drawing's data  %n", TurtleGraphicsControl.CURRENT_TURTLE_DATA_KEY);
      System.out.printf("### enter %d for pen up (drawing is off)  %n", TurtleGraphicsControl.PEN_UP_KEY);
      System.out.printf("### enter %d for pen down (drawing is on)  %n", TurtleGraphicsControl.PEN_DOWN_KEY);
      System.out.printf("### enter %d for turn right  %n", TurtleGraphicsControl.TURN_RIGHT_KEY);
      System.out.printf("### enter %d for turn left  %n", TurtleGraphicsControl.TURN_LEFT_KEY);
      System.out.printf("### enter \'%d, %d\' for move forward %d times or another number to move another times%n",
                                                         TurtleGraphicsControl.MOVE_FORWARD_KEY, 3, 3);
      System.out.printf("### enter %d for print drawing  %n", TurtleGraphicsControl.PRINT_FLOOR_KEY);
      System.out.printf("### enter %d for reset all drawing  %n", TurtleGraphicsControl.RESET_FLOOR_KEY);
      System.out.printf("### enter \'%d, %c\' for change pen's character to %c (or another character to another change)%n",
                                                         TurtleGraphicsControl.CHANGE_PEN_KEY, '#', '#');
      System.out.printf("### enter %d for QUIT  %n", TurtleGraphicsControl.QUIT_KEY);
   }
   
   static void drawExample() {
      TurtleGraphicsControl turtleGraphicsControl = new TurtleGraphicsControl();
      
      turtleGraphicsControl.control(TurtleGraphicsControl.PEN_DOWN_KEY);
      for (int counter = 0, movement = 12; counter < 4; counter++) {
         turtleGraphicsControl.control(TurtleGraphicsControl.MOVE_FORWARD_KEY, movement);
         turtleGraphicsControl.control(TurtleGraphicsControl.TURN_RIGHT_KEY);
      }
      
      turtleGraphicsControl.control(TurtleGraphicsControl.PEN_UP_KEY);
      turtleGraphicsControl.control(TurtleGraphicsControl.PRINT_FLOOR_KEY);
      turtleGraphicsControl.control(TurtleGraphicsControl.QUIT_KEY);
      
      System.out.printf("Drawing example was printed above %n");
   }
   
   public static void main(String[] args) {
      drawExample();
      
      System.out.printf("******** %s %n", TurtleGraphicsControl.START_INFO);
      System.out.printf("*** %s %n%s%n", TurtleGraphicsControl.QUIT_INFO, TurtleGraphicsControl.EOT_INFO);
      
      StandardInput standardInput = new StandardInput();
      
      do {
         printMenu();
      } while (true == standardInput.getTurtleGraphicsControlCommand());
      
      System.out.printf("Last result of drawing:%n");
      standardInput.turtleGraphicsControl.control(TurtleGraphicsControl.PRINT_FLOOR_KEY);
      
   } 
   
} 
