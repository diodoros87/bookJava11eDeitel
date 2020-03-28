/* =====================================================================================
 *       Filename:  DrawLinesController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 4.1a - Using strokeLine drawing lines from
                                upper left corner to diagonal of a canvas
                             
                             
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

   // when user presses Draw Lines button, draw lines from upper-left corner to 
   // diagonal of a canvas 
   @FXML
   void drawLinesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      final int    linesNumber    = 20;
      final double canvasWidth    = canvas.getWidth();
      final double shiftX         = canvasWidth / linesNumber;        // shift on axis X in coordinate system
      final double shiftY         = canvas.getHeight() / linesNumber; // shift on axis Y in coordinate system
      int          linesCounter   = 1;
      
      while (linesCounter < linesNumber) {
         // draw line from upper-left corner to diagonal of a canvas
         gc.strokeLine(0, 0, canvasWidth - linesCounter * shiftX, linesCounter * shiftY);
         
         linesCounter++;               
      }
   }
}
