/* =====================================================================================
 *       Filename:  MyLine.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 8.1 - class represents a line
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyLine {
   private double x1; // x-coordinate of first endpoint
   private double y1; // y-coordinate of first endpoint
   private double x2; // x-coordinate of second endpoint
   private double y2; // y-coordinate of second endpoint
   private Color strokeColor; // color of this line
   private int lineWidth;

   // constructor with input values
   public MyLine(
      double x1, double y1, double x2, double y2, Color strokeColor, int lineWidth) {

      this.x1 = x1; 
      this.y1 = y1; 
      this.x2 = x2; 
      this.y2 = y2; 
      this.strokeColor = strokeColor;
      this.lineWidth = lineWidth;
   } 
   
   // draw the line in the specified color
   public void draw(GraphicsContext gc) {
      gc.setStroke(strokeColor);
      gc.setLineWidth(lineWidth);
      gc.strokeLine(x1, y1, x2, y2);
   } 
} 
