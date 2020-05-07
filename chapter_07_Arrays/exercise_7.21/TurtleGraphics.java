/* =====================================================================================
 *       Filename:  TurtleGraphics.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.21 - class of 2D graphics, modeled on the Logo
                                programming language with drawing turtle
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class TurtleGraphics {
   
   private enum Direction {MINUS_HORIZONTAL, MINUS_VERTICAL, PLUS_HORIZONTAL, PLUS_VERTICAL};
   
   public static final int ROWS = 20;
   public static final int COLUMNS = 20;
   private byte [][] floor = new byte[ROWS][COLUMNS];
   
   private static final byte DRAWING = 1;  // value means that position in floor table has been drawing currently
   private static final byte EMPTY = 0;    // value means that position in floor table has not been drawing currently
   
   private int rowLocalization    = 0;    // row number on the floor table where turtle is currently  
   private int columnLocalization = 0; // column number on the floor table where turtle is currently  
   
   private Direction direction = Direction.PLUS_HORIZONTAL;  // direction currently set 
   private boolean penDrawing = false;    // penDrawing is false == (when turtle change position in floor table, turtle does not draw)
                                          // otherwise penDrawing is true
   private char penCharacter = '*';
   
   static final String SPACE_STRING = " ";
   static final String NEW_LINE_STRING = "\n";
   
   private String errorMessage = "";
   
   TurtleGraphics() {
      setValuesInArray(this.floor, EMPTY);
      this.rowLocalization = 0;
      this.columnLocalization = 0;
      this.direction = Direction.PLUS_HORIZONTAL;
      this.penDrawing = false;
      this.penCharacter = '*';
   }
   
   void setPenCharacter(char penCharacter) {
      if (false == Character.isWhitespace(penCharacter)) {
         this.penCharacter = penCharacter;
      }
   }
   
   void setPenUp() {
      this.penDrawing = false;
   }
   
   void setPenDown() {
      this.penDrawing = true;
      this.floor[rowLocalization][columnLocalization] = DRAWING;
   }
   
   public String getCurrentDirectionDescription() {
      switch (this.direction) {
         case MINUS_HORIZONTAL:
            return "left";
         case MINUS_VERTICAL:
            return "up";
         case PLUS_HORIZONTAL:
            return "right";
         case PLUS_VERTICAL:
            return "down";
         default:
            return null;
      }
   }
   
   public int getRowLocalization() {
      return rowLocalization;
   }
   
   public int getColumnLocalization() {
      return columnLocalization;
   }
   
   public char getPenCharacter() {
      return penCharacter;
   }
   
   public boolean isPenDrawing() {
      return penDrawing;
   }
   
   public String getErrorMessage() {
      return errorMessage;
   }
   
   void clearErrorMessage() {
      errorMessage = "";
   }
   
   void turnRight() {
      switch (this.direction) {
         case MINUS_HORIZONTAL:
            this.direction = Direction.MINUS_VERTICAL;
            break;
         case MINUS_VERTICAL:
            this.direction = Direction.PLUS_HORIZONTAL;
            break;
         case PLUS_HORIZONTAL:
            this.direction = Direction.PLUS_VERTICAL;
            break;
         case PLUS_VERTICAL:
            this.direction = Direction.MINUS_HORIZONTAL;
      }
   }
   
   void turnLeft() {
      switch (this.direction) {
         case MINUS_HORIZONTAL:
            this.direction = Direction.PLUS_VERTICAL;
            break;
         case PLUS_VERTICAL:
            this.direction = Direction.PLUS_HORIZONTAL;
            break;
         case PLUS_HORIZONTAL:
            this.direction = Direction.MINUS_VERTICAL;
            break;
         case MINUS_VERTICAL:
            this.direction = Direction.MINUS_HORIZONTAL;
      }
   }
   
   void resetDrawing() {
      setValuesInArray(this.floor, EMPTY);
   }
   
   public static void setValuesInArray(byte[][] array, byte value) {
      for (int row = 0; row < array.length; row++) {
         for (int column = 0; column < array[row].length; column++) {
            array[row][column] = value;
         }
      }
   }
   
   public String generateFloorString(boolean NumbersAppending) {
      String floorString = "";
      
      if (true == NumbersAppending) {
         floorString = appendNumbersRow();
      }
      
      for (int row = 0; row < floor.length; row++) {
         if (true == NumbersAppending) {
            floorString += row % 10;
         }
            
         for (int column = 0; column < floor[row].length; column++) {
            if (this.floor[row][column] == DRAWING) {
               floorString += String.valueOf(this.penCharacter); 
            }
            else {
               floorString += SPACE_STRING; 
            }
            
         }
         
         if (true == NumbersAppending) {
               floorString += row % 10;
         }
         floorString = floorString.concat(NEW_LINE_STRING); 
      }
      
      if (true == NumbersAppending) {
         floorString = floorString.concat(appendNumbersRow());
      }
      
      return floorString;
   }
   
   private String appendNumbersRow() {
      String numbersRowString = "";
      
      numbersRowString += SPACE_STRING; 
      
      for (int column = 0; column < floor[0].length; column++) {
         numbersRowString += column % 10; 
      }
      
      numbersRowString += NEW_LINE_STRING; 
      
      return numbersRowString;
   }
   
   private boolean validateMoves(int moves) {
      if (moves <= 0) {
         errorMessage = String.format("Number of moves must be greater than zero");
         return false;
      }
      
      switch (this.direction) {
         case MINUS_HORIZONTAL:
            if (0 > this.columnLocalization - moves) {
               errorMessage = String.format("out of bounds move: 0 > %d - %d",
                                    this.columnLocalization, moves);
               return false;
            }
            
            break;
         case PLUS_VERTICAL:
            if (ROWS - 1 < this.rowLocalization + moves) {
               errorMessage = String.format("out of bounds move: %d < %d + %d",
                                    ROWS - 1, this.rowLocalization, moves);
               return false;
            }
            
            break;
         case PLUS_HORIZONTAL:
            if (COLUMNS - 1 < this.columnLocalization + moves) {
               errorMessage = String.format("out of bounds move: %d < %d + %d",
                                    COLUMNS - 1, this.columnLocalization, moves);
               return false;
            }
            
            break;
         case MINUS_VERTICAL:
            if (0 > this.rowLocalization - moves) {
               errorMessage = String.format("out of bounds move: 0 > %d - %d",
                                    this.rowLocalization, moves);
               return false;
            }
      }
      
      return true;
   }
   
   void moveForward(int moves) {
      if (false == validateMoves(moves)) {
         return;
      }
      
      switch (this.direction) {
         case MINUS_HORIZONTAL:
            if (true == this.penDrawing) {
               drawMinusHorizontal(moves);
            }
            
            this.columnLocalization = Math.max(0, this.columnLocalization - moves);
            break;
            
         case PLUS_VERTICAL:
            if (true == this.penDrawing) {
               drawPlusVertical(moves);
            }
            
            this.rowLocalization = Math.min(ROWS - 1, this.rowLocalization + moves);
            break;
            
         case PLUS_HORIZONTAL:
            if (true == this.penDrawing) {
               drawPlusHorizontal(moves);
            }
            
            this.columnLocalization = Math.min(COLUMNS - 1, this.columnLocalization + moves);
            break;
         case MINUS_VERTICAL:
            if (true == this.penDrawing) {
               drawMinusVertical(moves);
            }
            
            this.rowLocalization = Math.max(0, this.rowLocalization - moves);
      }
      
   }
   
   private void drawMinusHorizontal(int moves) {
      for (int column = this.columnLocalization; moves > 0; moves--) {
         floor[this.rowLocalization][--column] = DRAWING;
      }
   }
   
   private void drawPlusVertical(int moves) {
      for (int row = this.rowLocalization; moves > 0; moves--) {
         floor[++row][this.columnLocalization] = DRAWING;
      }
   }
   
   private void drawPlusHorizontal(int moves) {
      for (int column = this.columnLocalization; moves > 0; moves--) {
         floor[this.rowLocalization][++column] = DRAWING;
      }
   }
   
   private void drawMinusVertical(int moves) {
      for (int row = this.rowLocalization; moves > 0; moves--) {
         floor[--row][this.columnLocalization] = DRAWING;
      }
   }
   
} 
