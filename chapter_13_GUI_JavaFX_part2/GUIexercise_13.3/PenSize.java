/* =====================================================================================
 *       Filename:  PenSize.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 13.3 - Declaring an enum type describing
                                various pen size
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum PenSize {
   SMALL(2), 
   MEDIUM(4), 
   LARGE(6);
   
   private final int radius;
   
   PenSize(int radius) {this.radius = radius;}
   
   public int getRadius() {return radius;}
}
