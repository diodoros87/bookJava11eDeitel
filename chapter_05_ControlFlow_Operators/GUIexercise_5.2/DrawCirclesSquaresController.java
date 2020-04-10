/* =====================================================================================
 *       Filename:  DrawCirclesSquaresController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 5.2 - Using strokeOval draw 12 concentric
                                circles with:
                                - center on center of canvas,
                                - diameter with length 20 and every next circle with 
                                   doubled length of diameter than previous circle
                                   
                             Using strokeRect draw 12 concentric
                                squares with:
                                - center on center of canvas,
                                - side with length 20 and every next square with 
                                   doubled length of side than previous square
                                 
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawCirclesSquaresController { 
   @FXML private Canvas canvas;

   // when user presses "Draw circles and squares" button, draw circles and squares
   @FXML
   public void drawCirclesSquaresButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      final short  circlesNumber = 12;
      final double widthCenter = canvas.getWidth() / 2;
      final double heightCenter = canvas.getHeight() / 2;
      final double circleDiameter = 20;
      final double circleRadius = circleDiameter / 2;

      for (int counter = 1; counter <= circlesNumber; counter++) {
         // draw circles
         // every next circle with doubled length of diameter than previous circle
         gc.strokeOval(widthCenter - counter * circleRadius, heightCenter - counter * circleRadius, 
            counter * circleDiameter, counter * circleDiameter);
            
         // draw squares
         // every next square with doubled length of side than previous square
         gc.strokeRect(widthCenter - counter * circleRadius, heightCenter - counter * circleRadius, 
            counter * circleDiameter, counter * circleDiameter);
      } 
   }
}
