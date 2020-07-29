/* =====================================================================================
 *       Filename:  MyOval.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.1 - class represents an oval
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyOval {
   private double x1; 
   private double y1; 
   private double x2; 
   private double y2; 
   private Color strokeColor;
   
   private boolean filled;
   private Color filledColor; 

   // constructor with input values
   public MyOval(
      double x1, double y1, double x2, double y2, Color strokeColor, Color filledColor, boolean filled) {

      setX1(x1);
      setX2(x2);
      setY1(y1);
      setY2(y2);
      setStrokeColor(strokeColor);
      setFilledColor(filledColor);
      setFilled(filled);
   } 
   
   public MyOval() {
      this.x1 = 0; 
      this.y1 = 0; 
      this.x2 = 0; 
      this.y2 = 0; 
      this.strokeColor = Color.BLACK;
      this.filledColor = Color.BLACK;
      this.filled = false;
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
   
   public void setFilled (boolean filled) {
      this.filled = filled;
   }
   
   public void setFilledColor (Color filledColor) {
      this.filledColor = filledColor;
   }
   
   public void setStrokeColor (Color strokeColor) {
      this.strokeColor = strokeColor;
   }
   
   public double getUpperLeftX() {
      return Math.min(x1, x2);
   }
   
   public double getUpperLeftY() {
      return Math.min(y1, y2);
   }
   
   public double getWidth() {
      return Math.abs(x1 - x2);
   }
   
   public double getHeigth() {
      return Math.abs(y1 - y2);
   }
   
   public void draw(GraphicsContext gc) {
      if (true == filled) {
         drawFilledOval(gc);
      }
      else {
         drawStrokeOval(gc);
      }
   } 
   
   private void drawStrokeOval(GraphicsContext gc) {
      gc.setStroke(strokeColor);
      gc.strokeOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeigth());
   }
   
   private void drawFilledOval(GraphicsContext gc) {
      gc.setFill(filledColor);
      gc.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeigth());
   }
} 
