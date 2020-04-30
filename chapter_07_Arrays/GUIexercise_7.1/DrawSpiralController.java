/* =====================================================================================
 *       Filename:  DrawSpiralController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 7.1 - Drawing spiral using strokeLine                                 
                  
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawSpiralController { 
   @FXML private Canvas canvas;
   
   // draws spiral
   @FXML
   void drawSpiralButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      final double CANVAS_WIDTH = canvas.getWidth();
      final double CANVAS_HEIGHT = canvas.getHeight();
      final double WIDTH_CENTER = CANVAS_WIDTH / 2;
      final double HEIGHT_CENTER = CANVAS_HEIGHT / 2;
      final double WIDTH_STEP = CANVAS_WIDTH / 10;
      final double HEIGHT_STEP = CANVAS_HEIGHT / 10;
      
      double horizontalLineLength = WIDTH_STEP;
      double verticalLineLength = HEIGHT_STEP;
      
      double drawBeginX = WIDTH_CENTER;
      double drawBeginY = HEIGHT_CENTER;
      double drawEndX = WIDTH_CENTER;
      double drawEndY = HEIGHT_CENTER;

      for (int linesCounter = 0; verticalLineLength <= CANVAS_WIDTH && horizontalLineLength <= CANVAS_HEIGHT; linesCounter+= 2) {

         drawEndY += (0 == linesCounter % 4) ? verticalLineLength : -verticalLineLength;
         gc.strokeLine(drawBeginX, drawBeginY, drawEndX, drawEndY);
         drawBeginY = drawEndY;
         
         drawEndX += (0 == linesCounter % 4) ? -horizontalLineLength : horizontalLineLength;
         gc.strokeLine(drawBeginX, drawBeginY, drawEndX, drawEndY);
         drawBeginX = drawEndX;
         
         verticalLineLength += HEIGHT_STEP;
         horizontalLineLength += WIDTH_STEP;
         
      } 

   }

}

