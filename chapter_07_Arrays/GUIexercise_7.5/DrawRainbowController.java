/* =====================================================================================
 *       Filename:  DrawRainbowController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 7.5 - Drawing a rainbow using arcs
                  
                             
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
import javafx.scene.paint.Color;

public class DrawRainbowController { 
   @FXML private Canvas canvas;
   
   // arcColors to use in the rainbow, starting from the inner most
   // The two white entries result in an empty arc in the center
   private final Color[] arcColors = {
      Color.WHITE, Color.WHITE, Color.VIOLET, Color.INDIGO, Color.BLUE,
      Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};
  
   // draws a rainbow using arcs
   @FXML
   void drawRainbowButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      final int ARC_DIAMETER = 20; // diameter of an arc
      final int ARC_DIAMETER_HALF = ARC_DIAMETER / 2;
      
      // draw the rainbow near the bottom-center
      final double WIDTH_CENTER = canvas.getWidth() / 2;
      final double MAX_HEIGHT_COORDINATE = canvas.getHeight() - 10;
      
      double drawingWidthCoordinate;
      double drawingHeightCoordinate;
      double drawingWidth;
      double drawingHeight;
      double startAngle     = 0;
      double arcExtentAngle = 180;
      
      Color fillArcColor = Color.WHITE;

      // draws filled arcs starting with the outermost
      for (int arcCounter = arcColors.length; arcCounter > 0; arcCounter--) {
         fillArcColor = arcColors[arcCounter - 1];
         // set the color for the current arc
         gc.setFill(fillArcColor);
         
         drawingWidthCoordinate  = WIDTH_CENTER - arcCounter * ARC_DIAMETER;
         drawingHeightCoordinate = MAX_HEIGHT_COORDINATE - arcCounter * ARC_DIAMETER;
         drawingWidth  = arcCounter * ARC_DIAMETER * 2;
         drawingHeight = arcCounter * ARC_DIAMETER * 2;
         
         // fill the arc from 0 to 180 degrees
         gc.fillArc(drawingWidthCoordinate, drawingHeightCoordinate, 
                     drawingWidth, drawingHeight,
                     startAngle, arcExtentAngle, ArcType.OPEN);
         
         if (Color.WHITE != fillArcColor) {
            fillArcColor = fillArcColor.darker();
            gc.setFill(fillArcColor);
            drawingWidthCoordinate  += ARC_DIAMETER_HALF;
            drawingHeightCoordinate += ARC_DIAMETER_HALF;
            drawingWidth  -= ARC_DIAMETER;  // next arc has smaller diameter than previous arc
            drawingHeight -= ARC_DIAMETER;  // next arc has smaller diameter than previous arc
            
            // fill the arc from 0 to 180 degrees
            gc.fillArc(drawingWidthCoordinate, drawingHeightCoordinate, 
                        drawingWidth, drawingHeight,
                        startAngle, arcExtentAngle, ArcType.OPEN);
         }
         
      } 
   }

}

