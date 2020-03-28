/* =====================================================================================
 *       Filename:  DrawLinesController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 4.1b - Using strokeLine drawing lines from
                                every corner to diagonal of a canvas
                             
                             
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

   // when user presses Draw Lines button, draw lines from every corner to 
   // diagonal of a canvas 
   @FXML
   void drawLinesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      final double canvasWidth    = canvas.getWidth();
      final double canvasHeight   = canvas.getHeight();
      final double xUpperLeft     = 0;
      final double yUpperLeft     = 0;
      final double xLowerLeft     = 0;
      final double yLowerLeft     = canvasHeight;
      final double xUpperRight    = canvasWidth;
      final double yUpperRight    = 0;
      final double xLowerRight    = canvasWidth;
      final double yLowerRight    = canvasHeight;
      final int    linesNumber    = 20;
      final double shiftX         = canvasWidth / linesNumber;   // shift on axis X in coordinate system
      final double shiftY         = canvasHeight / linesNumber;  // shift on axis Y in coordinate system
      int          linesCounter   = 1;
      
      // auxiliary variables to limit identical calculations:
      double yStep        = 0;                      // step down on axis Y
      double xStep        = 0;                      //step right on axis X
      double xReverseStep = canvasWidth;            // step left on axis X
      
      while (linesCounter < linesNumber) {
         yStep        = linesCounter * shiftY;   // step down on axis Y
         xStep        = linesCounter * shiftX;   //step right on axis X
         xReverseStep = canvasWidth - xStep;     // step left on axis X
         
         // draw line from upper-left corner to diagonal of a canvas
         gc.strokeLine(xUpperLeft, yUpperLeft, xReverseStep, yStep);
                        
         // draw line from lower-left corner to diagonal of a canvas
         gc.strokeLine(xLowerLeft, yLowerLeft, xStep, yStep);
                        
         // draw line from upper-right corner to diagonal of a canvas
         gc.strokeLine(xUpperRight, yUpperRight, xStep, yStep);
                        
         // draw line from lower-right corner to diagonal of a canvas
         gc.strokeLine(xLowerRight, yLowerRight, xReverseStep, yStep);
                        
         linesCounter++;               
      }
   }
}
