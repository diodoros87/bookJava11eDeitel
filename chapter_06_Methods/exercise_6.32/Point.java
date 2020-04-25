/* =====================================================================================
 *       Filename:  Point.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.32 - class of point in two-dimensional space
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Point {

   private double x;
   private double y;
   
   public Point(double x, double y) {
      if (Double.isNaN(x) || 
          Double.NEGATIVE_INFINITY == x ||
          Double.POSITIVE_INFINITY == x) {
          
         this.x = 0;
      }
      else {
         this.x = x;
      }
      
      if (Double.isNaN(y) || 
          Double.NEGATIVE_INFINITY == y ||
          Double.POSITIVE_INFINITY == y) {
          
         this.y = 0;
      }
      else {
         this.y = y;
      }
   }

   public void setX(double x) {
      if (Double.isNaN(x) || 
          Double.NEGATIVE_INFINITY == x ||
          Double.POSITIVE_INFINITY == x) {
          
         this.x = 0;
      }
      else {
         this.x = x;
      }
   } 

   public double getX() {
      return x; 
   } 
   
   public void setY(double y) {
      if (Double.isNaN(y) || 
          Double.NEGATIVE_INFINITY == y ||
          Double.POSITIVE_INFINITY == y) {
          
         this.y = 0;
      }
      else {
         this.y = y;
      }
   } 

   public double getY() {
      return y; 
   } 
}
