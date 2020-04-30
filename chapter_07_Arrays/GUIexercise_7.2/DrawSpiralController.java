/* =====================================================================================
 *       Filename:  DrawSpiralController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 7.2 - Drawing spiral using strokeArc                                 
                  
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class DrawSpiralController { 
   @FXML private Canvas canvas;
   
   // draws spiral
   @FXML
   void drawSpiralButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      final double WIDTH_CENTER = canvas.getWidth() / 2;
      final double HEIGHT_CENTER = canvas.getHeight() / 2;
      final double WIDTH_STEP = WIDTH_CENTER / 15;
      final double HEIGHT_STEP = HEIGHT_CENTER / 15;
      
      double drawingWidthCoordinate;
      double drawingHeightCoordinate;
      double drawingWidth;
      double drawingHeight;
      double startAngle = 0;
      double arcExtent;

      for (int arcCounter = 1; arcCounter <= 20 ; arcCounter++) {
      
         drawingWidthCoordinate = WIDTH_CENTER;
         drawingWidthCoordinate -= arcCounter * WIDTH_STEP;
         if (1 == arcCounter % 2) {
            drawingWidthCoordinate += WIDTH_STEP;
         }
         
         drawingHeightCoordinate = HEIGHT_CENTER - arcCounter * HEIGHT_STEP;
         drawingWidth = 2 * arcCounter * WIDTH_STEP;
         drawingHeight = 2 * arcCounter * HEIGHT_STEP;
         
         arcExtent = (1 == arcCounter % 2) ? 180 : -180;
         
         gc.strokeArc(drawingWidthCoordinate, drawingHeightCoordinate,
                              drawingWidth, drawingHeight, 
                              startAngle, arcExtent , ArcType.OPEN);
         
      } 

   }

}

