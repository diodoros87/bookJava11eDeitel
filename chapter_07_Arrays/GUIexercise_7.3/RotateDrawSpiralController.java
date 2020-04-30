/* =====================================================================================
 *       Filename:  RotateDrawSpiralController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 7.3 - Drawing spiral using strokeArc and 
                                 spiral's rotation
                  
                             
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

public class RotateDrawSpiralController { 
   @FXML private Canvas canvas;
   
   private double rotationAngle = 0;
   
   // draws spiral
   @FXML
   void rotateSpiralButtonPressed(ActionEvent event) {
      rotationAngle += 30;
      drawSpiralButtonPressed(event);
   }
   
   // draws spiral
   @FXML
   void drawSpiralButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      final double CANVAS_WIDTH = canvas.getWidth();
      final double CANVAS_HEIGHT = canvas.getHeight();
      
      final double WIDTH_CENTER = CANVAS_WIDTH / 2;
      final double HEIGHT_CENTER = CANVAS_HEIGHT / 2;
      
      final double WIDTH_STEP = WIDTH_CENTER / 15;
      final double HEIGHT_STEP = HEIGHT_CENTER / 15;
      
      gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
      
      double drawingWidthCoordinate;
      double drawingHeightCoordinate;
      double drawingWidth;
      double drawingHeight;
      double startAngle;
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
         
         startAngle = rotationAngle;
         arcExtent = (1 == arcCounter % 2) ? 180 : -180;
         
         gc.strokeArc(drawingWidthCoordinate, drawingHeightCoordinate,
                              drawingWidth, drawingHeight, 
                              startAngle, arcExtent , ArcType.OPEN);
         
      } 

   }

}

