/* =====================================================================================
 *       Filename:  MyRectangle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.1 - class represents a rectangle
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class MyRectangle extends MyShape {
   private boolean filled;
   private Color   filledColor; 

   // constructor with input values
   public MyRectangle(
      double x1, double y1, double x2, double y2, Color strokeColor, Color filledColor, boolean filled) {
      super(x1, y1, x2, y2, strokeColor);
      
      this.filledColor = Objects.requireNonNull(filledColor, "filledColor must not be null");
      this.filled      = filled;
   } 
   
   public MyRectangle() {
      this(0, 0, 0, 0, Color.BLACK, Color.BLACK, false);
   } 
   
   public void setFilled (boolean filled) {
      this.filled = filled;
   }
   
   public boolean isFilled () {
      return filled;
   }
   
   public void setFilledColor (Color filledColor) {
      this.filledColor = Objects.requireNonNull(filledColor, "filledColor must not be null");
   }
   
   public Color getFilledColor () {
      return filledColor;
   }
   
   public double getUpperLeftX() {
      return Math.min(getX1(), getX2());
   }
   
   public double getUpperLeftY() {
      return Math.min(getY1(), getY2());
   }
   
   public double getWidth() {
      return Math.abs(getX1() - getX2());
   }
   
   public double getHeigth() {
      return Math.abs(getY1() - getY2());
   }
   
   public void draw(GraphicsContext gc) {
      Objects.requireNonNull(gc, "GraphicsContext must not be null");
      
      if (true == isFilled()) {
         drawFilledRectangle(gc);
      }
      else {
         drawStrokeRectangle(gc);
      }
   } 
   
   private void drawStrokeRectangle(GraphicsContext gc) {
      gc.setStroke(getStrokeColor());
      gc.strokeRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeigth());
   }
   
   private void drawFilledRectangle(GraphicsContext gc) {
      gc.setFill(getFilledColor());
      gc.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeigth());
   }
} 
