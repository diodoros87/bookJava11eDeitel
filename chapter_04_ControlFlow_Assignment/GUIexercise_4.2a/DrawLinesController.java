/* =====================================================================================
 *       Filename:  DrawLinesController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 4.2a - Using strokeLine drawing 20 lines 
                               having START from upper left corner     
                                  (and shifted by 20 steps to lower left corner)
                               and having END from lower left corner  
                                  (and shifted by 20 steps to lower right corner)
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawLinesController { 
   @FXML private Canvas canvas; // used to get the GraphicsContext

   // when user presses Draw Lines button, draw 20 lines from 20 points lying on left edge to 
   // next 20 points lying on lower edge
   @FXML
   void drawLinesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      final int    linesNumber    = 20;
      final double canvasHeight   = canvas.getHeight();
      final double shiftX         = canvas.getWidth() / linesNumber; // shift on axis X in coordinate system
      final double shiftY         = canvasHeight / linesNumber;      // shift on axis Y in coordinate system
      int          linesCounter   = 1;
      
      while (linesCounter < linesNumber) {
         // draw line from point lying on left edge to point lying on lower edge
         gc.strokeLine(0, linesCounter * shiftY, linesCounter * shiftX, canvasHeight);
         
         linesCounter++;               
      }
   }
}
