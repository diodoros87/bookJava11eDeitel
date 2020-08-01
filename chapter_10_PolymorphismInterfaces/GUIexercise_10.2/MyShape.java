/* =====================================================================================
 *       Filename:  MyShape.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.2 - abstract class of shape
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collections;

import java.util.Objects;

public abstract class MyShape {
   private double x1; // x-coordinate of first endpoint
   private double y1; // y-coordinate of first endpoint
   private double x2; // x-coordinate of second endpoint
   private double y2; // y-coordinate of second endpoint
   private Color strokeColor; // color of this line

   // constructor with input values
   public MyShape(double x1, double y1, double x2, double y2, Color strokeColor) {
      this.x1 = Math.abs(x1);
      this.x2 = Math.abs(x2);
      this.y1 = Math.abs(y1);
      this.y2 = Math.abs(y2);
      this.strokeColor = Objects.requireNonNull(strokeColor, "strokeColor must not be null");
   } 
   
   public MyShape() {
      this(0, 0, 0, 0, Color.BLACK);
   } 
   
   public void setX1 (double x1) {
      this.x1 = Math.abs(x1);
   }
   
   public double getX1 () {
      return x1;
   }
   
   public void setX2 (double x2) {
      this.x2 = Math.abs(x2);
   }
   
   public double getX2 () {
      return x2;
   }
   
   public void setY1 (double y1) {
      this.y1 = Math.abs(y1);
   }
   
   public double getY1 () {
      return y1;
   }
   
   public void setY2 (double y2) {
      this.y2 = Math.abs(y2);
   }
   
   public double getY2 () {
      return y2;
   }
   
   public void setStrokeColor (Color strokeColor) {
      this.strokeColor = Objects.requireNonNull(strokeColor, "strokeColor must not be null");
   }
   
   public Color getStrokeColor () {
      return strokeColor;
   }
   
   public abstract void draw(GraphicsContext gc);
} 
