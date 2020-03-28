/* =====================================================================================
 *       Filename:  DrawLinesController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 4.2b - Using strokeLine for every corner:
                                 drawing 20 lines having START from corner 
                                 (and shifted by 20 steps to 
                                  next corner lying on the same edge) 
                                 and having END from 
                                 previously mentioned corner in brackets 
                                 (and shifted by 20 steps to next corner lying on
                                  the same edge other than first mentioned edge)
                                 
                             
                             
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

   // when user presses Draw Lines button, draw 80 lines from 20 points lying on edge to 
   // next 20 points lying on perpendicular edge
   @FXML
   void drawLinesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      final double canvasWidth    = canvas.getWidth();
      final double canvasHeight   = canvas.getHeight();
      final int    linesNumber    = 20;
      final double shiftX         = canvasWidth / linesNumber;   // shift on axis X in coordinate system
      final double shiftY         = canvasHeight / linesNumber;  // shift on axis Y in coordinate system
      int          linesCounter   = 1;
      
      // auxiliary variables to limit identical calculations:
      double xStep        = 0;              // step right on axis X
      double xReverseStep = canvasWidth;    // step left on axis X
      double yStep        = 0;              // step down on axis Y
      double yReverseStep = canvasHeight;   // step top on axis Y
      
      while (linesCounter < linesNumber) {
         xStep        = linesCounter * shiftX;  // step right on axis X
         xReverseStep = canvasWidth - xStep;    // step left on axis X
         yStep        = linesCounter * shiftY;  // step down on axis Y
         yReverseStep = canvasHeight - yStep;   // step top on axis Y
         
         // draw line from point lying on left edge to point lying on lower edge
         gc.strokeLine(0, yStep, xStep, canvasHeight);
                        
         // draw line from point lying on lower edge to point lying on right edge
         gc.strokeLine(xStep, canvasHeight, canvasWidth, yReverseStep);
                        
         // draw line from point lying on right edge to point lying on upper edge
         gc.strokeLine(canvasWidth, yReverseStep, xReverseStep, 0);
                        
         // draw line from point lying on upper edge to point lying on left edge
         gc.strokeLine(xReverseStep, 0, 0, yStep);
                        
         linesCounter++;    
      }
   }
}
