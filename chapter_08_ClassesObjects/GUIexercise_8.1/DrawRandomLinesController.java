/* =====================================================================================
 *       Filename:  DrawRandomLinesController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 8.1 - Drawing random lines using MyLine
                                 objects
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.security.SecureRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawRandomLinesController { 
   private static final SecureRandom randomNumbers = new SecureRandom();
   @FXML private Canvas canvas;

   // draws random lines
   @FXML
   void drawRandomLinesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      MyLine[] lines = new MyLine[100]; // stores the MyLine objects
      
      final int width  = (int) canvas.getWidth();
      final int height = (int) canvas.getHeight();

      // create lines
      for (int count = 0; count < lines.length; count++) {
         // generate random coordinates
         int x1 = randomNumbers.nextInt(width);
         int y1 = randomNumbers.nextInt(height);
         int x2 = randomNumbers.nextInt(width);
         int y2 = randomNumbers.nextInt(height);
         
         // generate a random color
         Color color = Color.rgb(randomNumbers.nextInt(256), randomNumbers.nextInt(256), randomNumbers.nextInt(256));
         
         int lineWidth = randomNumbers.nextInt(25);
         
         // add a new MyLine to the array
         lines[count] = new MyLine(x1, y1, x2, y2, color, lineWidth);
      } 

      // clear the Canvas then draw the lines
      gc.clearRect(0, 0, width, height); 

      for (MyLine line : lines) {
         line.draw(gc);
      } 
   }
}

