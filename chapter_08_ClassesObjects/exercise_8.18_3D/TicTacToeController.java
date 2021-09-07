/* =====================================================================================
 *       Filename:  TicTacToeController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - controller's' class of tic tac toe game
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import java.io.PrintStream;

public class TicTacToeController {

   private TicTacToe     model;
   private TicTacToeView view;
   
   public TicTacToeController(TicTacToe model, TicTacToeView view) {
      if (null == model || null == view) {
         String nullReferences = 
            ((model == null) ? "model " : "") +
            ((view == null)  ? "view "  : "");
            
         throw new NullPointerException("Null reference to " + nullReferences);
      }
      
      this.model = model;
      this.view = view;
   }
   
   public TicTacToe getModel() {
      return model;
   }
   
   public TicTacToeView getView() {
      return view;
   }
   
   public void setPrintStream(PrintStream printStream) {
      view.setPrintStream(printStream);
   }
   
   public PrintStream getPrintStream() {
      return view.getPrintStream();
   }
   
   public GameStatus getGameStatus() {
      return model.getGameStatus();
   }
   
   public boolean isGameOver() {
      return model.isGameOver();
   }
   
   public void restart() {
      model.restart();
   }
   
   public static byte getSQUARE_SIZE() {
      return TicTacToe.getSQUARE_SIZE();
   }
   
   public static void throwWrongLevelOrRowOrColumnException(String levelOrRowOrColumn) {
      final byte SQUARE_SIZE = getSQUARE_SIZE();
      String levelRowColumnRequirement = String.format("Requirement: %s > %d and %s <= %d",
                           levelOrRowOrColumn, 0, levelOrRowOrColumn, SQUARE_SIZE);
                           
      throw new IllegalArgumentException(levelRowColumnRequirement);
   }
   
   public static void validateLevelOrRowOrColumn(byte number, String levelOrRowOrColumn) {
      if (false == TicTacToe.validateLevelOrRowOrColumn(number)) {
         throwWrongLevelOrRowOrColumnException(levelOrRowOrColumn);
      }
   }
   
   public boolean validatePosition(byte level, byte row, byte column) {
      MoveStatus moveStatus = model.validatePosition(level, row, column);
      boolean result = validateMoveStatus(level, row, column, moveStatus);
    
      return result;
   }
   
   private boolean validateMoveStatus(byte level, byte row, byte column, MoveStatus moveStatus) {
      boolean result = false;
      
      switch (moveStatus) {
         case AFTER_GAME_OVER:
            view.printGameOver();
            break;
         case OUT_OF_BOARD_LEVEL:
            throwWrongLevelOrRowOrColumnException("level");
            break;
         case OUT_OF_BOARD_ROW:
            throwWrongLevelOrRowOrColumnException("row");
            break;
         case OUT_OF_BOARD_COLUMN:
            throwWrongLevelOrRowOrColumnException("column");
            break;
         case OCCUPIED_POSITION:
            view.printOccupiedPositionInfo(level, row, column);
            break;
         case CORRECT:
            result = true;
      }
    
      return result;
   }
   
   public boolean move(byte level, byte row, byte column) {
      MoveStatus moveStatus = model.move(level, row, column);
      boolean result = validateMoveStatus(level, row, column, moveStatus);
    
      return result;
   }
   
   public void printStartInfo() {
      view.printStartInfo();
   }
   public void printMoveCoordinationsInfo(String playerName, byte level, byte row, byte column) {
      view.printMoveCoordinationsInfo(playerName, level, row, column);
   }
   
   public void printGameStatus() {
      GameStatus gameStatus = getGameStatus();
      view.printGameStatus(gameStatus);
   }
   
   public void printBoard() {
      view.printBoard(model);
   } 
 
} 
