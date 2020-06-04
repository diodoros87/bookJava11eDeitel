/* =====================================================================================
 *       Filename:  Rectangle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.4 - class represents a rectangle
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Rectangle {

   private static final int EXCLUDE_MIN_SIZE = 0;
   private static final int EXCLUDE_MAX_SIZE = 20;
   
   private static final String INCORRECT_WIDTH = String.format("Width must be more than %d and less than %d",
                                 EXCLUDE_MIN_SIZE, EXCLUDE_MAX_SIZE);
   private static final String INCORRECT_LENGTH = String.format("Length must be more than %d and less than %d",
                                 EXCLUDE_MIN_SIZE, EXCLUDE_MAX_SIZE);
   
   private double width  = 1; 
   private double length = 1; 
   
   public Rectangle() {
      this.width  = 1; 
      this.length = 1;
   }

   public Rectangle(double width, double length) {
      if (EXCLUDE_MIN_SIZE < width && width < EXCLUDE_MAX_SIZE) {
         this.width = width; 
      }
      else {
         throw new IllegalArgumentException(INCORRECT_WIDTH);
      }
      
      if (EXCLUDE_MIN_SIZE < length && length < EXCLUDE_MAX_SIZE) {
         this.length = length; 
      }
      else {
         throw new IllegalArgumentException(INCORRECT_LENGTH);
      }
   }  
   
   public void setWidth (double width) {
      if (false == isCorrectSize(width)) {
         throw new IllegalArgumentException(INCORRECT_WIDTH);
      }
      
      this.width = width;
   }
   
   public void setLength (double length) {
      if (false == isCorrectSize(length)) {
         throw new IllegalArgumentException(INCORRECT_LENGTH);
      }
      
      this.length = length;
   }
   
   public double getWidth() {
      return width;
   }
   
   public double getLength() {
      return length;
   }
   
   public double area() {
      return width * length;
   }
   
   public double perimeter() {
      return 2 * (width + length);
   }
   
   private boolean isCorrectSize(double size) {
      if (EXCLUDE_MIN_SIZE < size && size < EXCLUDE_MAX_SIZE) {
         return true;
      }
      
      return false;
   }
   
   public String toString() {
      return String.format("Rectangle's data:%n width: %f, length: %f, area: %f, perimeter: %f %n", 
         getWidth(), getLength(), area(), perimeter());
   } 
} 
