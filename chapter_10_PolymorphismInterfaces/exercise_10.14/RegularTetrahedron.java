/* =====================================================================================
 *       Filename:  RegularTetrahedron.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - RegularTetrahedron concrete class 
                                inherits from ThreeDimensionalShape
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public final class RegularTetrahedron extends ThreeDimensionalShape {
   private double edge;
   
   public RegularTetrahedron(double edge) {
      super("edge");
      validateLength(edge);
      
      this.edge = edge;
   }
   
   public void setEdge(double edge) {
      validateLength(edge);
      
      this.edge = edge;
   }
   
   public double getEdge() {
      return edge;
   }
   
   @Override
   public double calculateArea() {
      return Math.sqrt(3) * getEdge() * getEdge();
   }
   
   @Override
   public double calculateVolume() {
      return Math.sqrt(2) * getEdge() * getEdge() * getEdge() / 12;
   }
   
   @Override
   public String toString() {
      return String.format("%40s edge = %e", super.toString(), getEdge());
   }
} 
