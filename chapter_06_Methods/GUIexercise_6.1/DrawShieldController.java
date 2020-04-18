/* =====================================================================================
 *       Filename:  DrawShieldController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 6.1 - Drawing shield using 2 random colors                                 
                  
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.security.SecureRandom;

public class DrawShieldController { 
   @FXML private Canvas canvas;
   
   // create secure random number generator for use in method getRandomColorComponent
   private static final SecureRandom randomNumbers = new SecureRandom();
   
   // draws shield
   @FXML
   void drawShieldButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      final Color firstRandomColor = Color.rgb(getRandomColorComponent(),
                                          getRandomColorComponent(), getRandomColorComponent());
      final Color secondRandomColor = Color.rgb(getRandomColorComponent(),
                                          getRandomColorComponent(), getRandomColorComponent());
      
      final short  circlesNumber = 5;
      final double widthCenter = canvas.getWidth() / 2;
      final double heightCenter = canvas.getHeight() / 2;
      final double circleDiameter = widthCenter / 3;
      final double circleRadius = circleDiameter / 2;

      for (int counter = circlesNumber; counter >= 1; counter--) {
         if (0 == counter % 2) {
            gc.setFill(firstRandomColor);
         }
         else {
            gc.setFill(secondRandomColor);
         }
         // draw circles
         // each next circle has half length of diameter's previous circle
         gc.fillOval(widthCenter - counter * circleRadius, heightCenter - counter * circleRadius, 
            counter * circleDiameter, counter * circleDiameter);
      } 

   }
   
   // method returns values in range from 0 (inclusive) to 256 (exclusive)
   public static int getRandomColorComponent() {
      return randomNumbers.nextInt(256);
   } 
}

