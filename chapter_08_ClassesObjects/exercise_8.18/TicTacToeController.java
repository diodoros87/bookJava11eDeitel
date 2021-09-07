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
   
   public static void throwWrongRowOrColumnException(String rowOrColumn) {
      final byte SQUARE_SIZE = getSQUARE_SIZE();
      String rowColumnRequirement = String.format("Requirement: %s > %d and %s <= %d",
                           rowOrColumn, 0, rowOrColumn, SQUARE_SIZE);
                           
      throw new IllegalArgumentException(rowColumnRequirement);
   }
   
   public static void validateRowOrColumn(byte number, String rowOrColumn) {
      if (false == TicTacToe.validateRowOrColumn(number)) {
         throwWrongRowOrColumnException(rowOrColumn);
      }
   }
   
   public boolean validatePosition(byte row, byte column) {
      MoveStatus moveStatus = model.validatePosition(row, column);
      boolean result = validateMoveStatus(row, column, moveStatus);
    
      return result;
   }
   
   private boolean validateMoveStatus(byte row, byte column, MoveStatus moveStatus) {
      boolean result = false;
      
      switch (moveStatus) {
         case AFTER_GAME_OVER:
            view.printGameOver();
            break;
         case OUT_OF_BOARD_ROW:
            throwWrongRowOrColumnException("row");
            break;
         case OUT_OF_BOARD_COLUMN:
            throwWrongRowOrColumnException("column");
            break;
         case OCCUPIED_POSITION:
            view.printOccupiedPositionInfo(row, column);
            break;
         case CORRECT:
            result = true;
      }
    
      return result;
   }
   
   public boolean move(byte row, byte column) {
      MoveStatus moveStatus = model.move(row, column);
      boolean result = validateMoveStatus(row, column, moveStatus);
    
      return result;
   }
   
   public void printStartInfo() {
      view.printStartInfo();
   }
   public void printMoveCoordinationsInfo(String playerName, byte row, byte column) {
      view.printMoveCoordinationsInfo(playerName, row, column);
   }
   
   public void printGameStatus() {
      GameStatus gameStatus = getGameStatus();
      view.printGameStatus(gameStatus);
   }
   
   public void printBoard() {
      view.printBoard(model);
   } 
 
} 
