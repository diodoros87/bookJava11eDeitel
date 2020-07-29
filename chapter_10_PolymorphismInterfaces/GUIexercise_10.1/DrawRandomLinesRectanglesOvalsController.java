/* =====================================================================================
 *       Filename:  DrawRandomLinesRectanglesOvalsController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.1 - Drawing random lines, rectangles and 
                                 ovals
                                 
                                                  
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

public class DrawRandomLinesRectanglesOvalsController { 
   private static final SecureRandom randomNumbers = new SecureRandom();
   @FXML private Canvas canvas;
   
   private static final int SHAPES_NUMBER = 100;

   @FXML
   void drawRandomLinesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      MyLine[] lines = new MyLine[SHAPES_NUMBER]; // stores the MyLine objects
      
      final int WIDTH  = (int) canvas.getWidth();
      final int HEIGHT = (int) canvas.getHeight();

      // create lines
      for (int count = 0; count < lines.length; count++) {
         // generate random coordinates
         int x1 = randomNumbers.nextInt(WIDTH);
         int y1 = randomNumbers.nextInt(HEIGHT);
         int x2 = randomNumbers.nextInt(WIDTH);
         int y2 = randomNumbers.nextInt(HEIGHT);
         
         // generate a random color
         Color strokeColor = getRandomColor();
         
         int lineWidth = randomNumbers.nextInt(5);
         
         // add a new MyLine to the array
         lines[count] = new MyLine(x1, y1, x2, y2, strokeColor, lineWidth);
      } 

      // clear the Canvas then draw the lines
      gc.clearRect(0, 0, WIDTH, HEIGHT); 

      for (MyLine line : lines) {
         line.draw(gc);
      } 
   }
   
   @FXML
   void drawRandomRectanglesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      MyRectangle[] rectangles = new MyRectangle[SHAPES_NUMBER]; // stores the MyRectangle objects
      
      final int WIDTH  = (int) canvas.getWidth();
      final int HEIGHT = (int) canvas.getHeight();

      // create rectangles
      for (int count = 0; count < rectangles.length; count++) {
         // generate random coordinates
         int x1 = randomNumbers.nextInt(WIDTH);
         int y1 = randomNumbers.nextInt(HEIGHT);
         int x2 = randomNumbers.nextInt(WIDTH);
         int y2 = randomNumbers.nextInt(HEIGHT);
         
         // generate a random colors
         Color strokeColor = getRandomColor();
         Color filledColor = getRandomColor();
         
         boolean filled = isFilled();
         
         // add a new MyRectangle to the array
         rectangles[count] = new MyRectangle(x1, y1, x2, y2, strokeColor, filledColor, filled);
      } 

      // clear the Canvas then draw the rectangles
      gc.clearRect(0, 0, WIDTH, HEIGHT); 

      for (MyRectangle rectangle : rectangles) {
         rectangle.draw(gc);
      } 
   }
   
   @FXML
   void drawRandomOvalsButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      MyOval[] ovals = new MyOval[SHAPES_NUMBER]; // stores the MyOval objects
      
      final int WIDTH  = (int) canvas.getWidth();
      final int HEIGHT = (int) canvas.getHeight();

      // create ovals
      for (int count = 0; count < ovals.length; count++) {
         // generate random coordinates
         int x1 = randomNumbers.nextInt(WIDTH);
         int y1 = randomNumbers.nextInt(HEIGHT);
         int x2 = randomNumbers.nextInt(WIDTH);
         int y2 = randomNumbers.nextInt(HEIGHT);
         
         // generate a random color
         Color strokeColor = getRandomColor();
         Color filledColor = getRandomColor();
         
         boolean filled = isFilled();
         
         // add a new MyOval to the array
         ovals[count] = new MyOval(x1, y1, x2, y2, strokeColor, filledColor, filled);
      } 

      // clear the Canvas then draw the ovals
      gc.clearRect(0, 0, WIDTH, HEIGHT); 

      for (MyOval oval : ovals) {
         oval.draw(gc);
      } 
   }
   
   public static Color getRandomColor() {
      return Color.rgb(randomNumbers.nextInt(256), randomNumbers.nextInt(256), randomNumbers.nextInt(256));
   }
   
   public static boolean isFilled() {
      int filled = randomNumbers.nextInt(2);
 
      return (filled == 0) ? false : true;
   }
}

