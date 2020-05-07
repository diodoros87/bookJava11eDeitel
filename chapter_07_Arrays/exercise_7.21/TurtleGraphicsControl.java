/* =====================================================================================
 *       Filename:  TurtleGraphicsControl.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.21 - class of 2D graphics control, modeled on
                                the Logo programming language with drawing turtle
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class TurtleGraphicsControl {

   public static final String START_INFO = String.format("%s %n %s%n", "Program was written in purpose of drawing 2D graphics.", 
                              "Program was modeled on the Logo programming language with drawing turtle");
   
   public final static int CURRENT_TURTLE_DATA_KEY = 0;  
   public final static int PEN_UP_KEY = 1;                              
   public final static int PEN_DOWN_KEY = 2;  
   public final static int TURN_RIGHT_KEY = 3;  
   public final static int TURN_LEFT_KEY = 4;                              
   public final static int MOVE_FORWARD_KEY = 5;  
   public final static int PRINT_FLOOR_KEY = 6; 
   public final static int RESET_FLOOR_KEY = 7;
   public final static int CHANGE_PEN_KEY = 8;
   public final static int QUIT_KEY = 9;
   
   public static final String QUIT_INFO = String.format("To quit enter End-Of-Transmission (EOT) character or %d ", QUIT_KEY);
   public static final String EOT_INFO = "End-Of-Transmission (EOT) character is Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows";
   
   private String errorMessage = "";
   
   private TurtleGraphics turtleGraphics = new TurtleGraphics();
   private String currentTurtleData = generateCurrentTurtleData();
   
   private boolean quit = false;
   
   public boolean isQuit() {
      return quit;
   }
   
   String getErrorMessage() {
      return errorMessage;
   }
   
   void clearErrorMessage() {
      errorMessage = "";
   }
   
   void control(int command) {
      switch (command) {
         case CURRENT_TURTLE_DATA_KEY:
            System.out.println(generateCurrentTurtleData());
               break;
         case PEN_UP_KEY:
            this.turtleGraphics.setPenUp();
               break;
         case PEN_DOWN_KEY:
            this.turtleGraphics.setPenDown();
               break;
         case TURN_RIGHT_KEY:
            this.turtleGraphics.turnRight();
               break;
         case TURN_LEFT_KEY:
            this.turtleGraphics.turnLeft();
               break;
         case MOVE_FORWARD_KEY:
            this.errorMessage = String.format("After command \'%d \' should be number of moves", command);
               break;
         case PRINT_FLOOR_KEY:
            printDrawing(this.turtleGraphics.generateFloorString(true));
               break;
         case RESET_FLOOR_KEY:
            this.turtleGraphics.resetDrawing();
               break;
         case CHANGE_PEN_KEY:
            this.errorMessage = String.format("After command \'%d \' should be character of printing", command);
               break;
         case QUIT_KEY:
            this.quit = true;
            break;
         default:
            this.errorMessage = String.format("Unrecognized command %d", command);
      }
   }
   
   void control(int command, int moves) {
      if (MOVE_FORWARD_KEY == command) {
         this.turtleGraphics.moveForward(moves);
         
         String errorMessage = turtleGraphics.getErrorMessage();
         if ("" != errorMessage) {
            this.errorMessage += errorMessage;
            this.turtleGraphics.clearErrorMessage();
         }
      }
      else {
         this.errorMessage = String.format("Unrecognized command %d with %d moves", command, moves);
      }
   }
   
   void control(int command, char penCharacter) {
      if (CHANGE_PEN_KEY == command) {
         this.turtleGraphics.setPenCharacter(penCharacter);
      }
      else {
         this.errorMessage = String.format("Unrecognized command %d with %c", command, penCharacter);
      }
   }
   
   void printDrawing(String drawing) {
      System.out.println(drawing);
   }
   
   String generateCurrentTurtleData() {
      this.currentTurtleData = "Localization in row: " + turtleGraphics.getRowLocalization();
      this.currentTurtleData += "\n" + "Localization in column: " + turtleGraphics.getColumnLocalization();
      this.currentTurtleData += "\n" + "Direction: " + turtleGraphics.getCurrentDirectionDescription();
      this.currentTurtleData += "\n" + "Pen's character: " + turtleGraphics.getPenCharacter();
      this.currentTurtleData += "\n" + "Drawing (on/off): " + (turtleGraphics.isPenDrawing() ? "on" : "off");
      
      return this.currentTurtleData;
   }
   
} 
