/* =====================================================================================
 *       Filename:  DrawShapesController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 6.2 - Drawing 10 shapes using random:
                                 colors, localizations, dimensions
                                 
                                                  
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

public class DrawShapesController { 
   @FXML private Canvas canvas;
   
   // create secure random number generator for use in method getRandomNumber
   private static final SecureRandom randomNumbers = new SecureRandom();
   
   // draws shapes
   @FXML
   void drawShapesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      final short  shapesNumber = 10;
      final short  shapeTypesNumber = 2;
      final short  colorComponentRange = 256;
      final double canvasWidth = canvas.getWidth();
      final double canvasHeight = canvas.getHeight();
      final int widthCenter = (int)canvasWidth / 2;   // center of width localization (half of width's length)
      final int heightCenter = (int)canvasHeight / 2; // center of height localization (half of height's length)
      
      // after "Draw shapes" button was pressed, clear the canvas for next set of shapes
      gc.clearRect(0, 0, canvasWidth, canvasHeight);

      for (int counter = shapesNumber; counter >= 1; counter--) {
         // set random color with 3 random color's components with range from 0 to 255
         gc.setFill(Color.rgb(getRandomNumber(colorComponentRange), 
                           getRandomNumber(colorComponentRange), getRandomNumber(colorComponentRange)));
         
         
         if (0 == getRandomNumber(shapeTypesNumber)) {  // get random number from 0 to 1 // for 0 call draw filled oval
            gc.fillOval(getRandomNumber(widthCenter), getRandomNumber(heightCenter),
                        getRandomNumber(widthCenter), getRandomNumber(heightCenter));  
         } 
         else {   // for 1 call draw filled rect
            gc.fillRect(getRandomNumber(widthCenter), getRandomNumber(heightCenter),
                        getRandomNumber(widthCenter), getRandomNumber(heightCenter));  
         }
         
      } 

   }
   
   // method returns values in range from 0 (inclusive) to bound (exclusive)
   public static int getRandomNumber(int bound) {
      if (bound <= 0) {
         System.err.println("bound must be greater than zero");
         return 0;
      }
      
      return randomNumbers.nextInt(bound);
   } 
}

