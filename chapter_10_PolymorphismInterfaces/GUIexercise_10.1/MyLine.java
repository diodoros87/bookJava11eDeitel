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

public class MyLine extends MyShape {
   private double lineWidth;

   // constructor with input values
   public MyLine(double x1, double y1, double x2, double y2, Color strokeColor, double lineWidth) {
      super(x1, y1, x2, y2, strokeColor);
      this.lineWidth = Math.max(lineWidth, 0);
   } 
   
   public MyLine() {
      this(0, 0, 0, 0, Color.BLACK, 0);
   } 
   
   public void setLineWidth (double lineWidth) {
      this.lineWidth = Math.max(lineWidth, 0);
   }
   
   public double getLineWidth () {
      return lineWidth;
   }
   
   // draw the line in the specified color
   public void draw(GraphicsContext gc) {
      gc.setStroke(getStrokeColor());
      gc.setLineWidth(getLineWidth());
      gc.strokeLine(getX1(), getY1(), getX2(), getY2());
   } 
} 
