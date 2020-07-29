/* =====================================================================================
 *       Filename:  MyLine.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.1 - class represents a line
                                 
                                                  
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
   public MyLine(double x1, double y1, double x2, double y2, Color strokeColor, int lineWidth) {
      setX1(x1);
      setX2(x2);
      setY1(y1);
      setY2(y2);
      setStrokeColor(strokeColor);
      setLineWidth(lineWidth);
   } 
   
   public MyLine() {
      this.x1 = 0; 
      this.y1 = 0; 
      this.x2 = 0; 
      this.y2 = 0; 
      this.strokeColor = Color.BLACK;
      this.lineWidth = 0;
   } 
   
   public void setX1 (double x1) {
      if (0 > x1) {
         this.x1 = 0;
      }
      else {
         this.x1 = x1;
      }
   }
   
   public void setX2 (double x2) {
      if (0 > x2) {
         this.x2 = 0;
      }
      else {
         this.x2 = x2;
      }
   }
   
   public void setY1 (double y1) {
      this.y1 = (0 > y1) ? 0 : y1;
   }
   
   public void setY2 (double y2) {
      this.y2 = (0 > y2) ? 0 : y2;
   }
   
   public void setLineWidth (int lineWidth) {
      this.lineWidth = Math.max(lineWidth, 0);
   }
   
   public void setStrokeColor (Color strokeColor) {
      this.strokeColor = strokeColor;
   }
   
   // draw the line in the specified color
   public void draw(GraphicsContext gc) {
      gc.setStroke(strokeColor);
      gc.setLineWidth(lineWidth);
      gc.strokeLine(x1, y1, x2, y2);
   } 
} 
