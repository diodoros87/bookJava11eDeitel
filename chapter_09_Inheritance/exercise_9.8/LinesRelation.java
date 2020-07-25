/* =====================================================================================
 *       Filename:  LinesRelation.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - lines relation enum type
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum LinesRelation {
   IDENTICALLY("identical"),
   PARALLEL("parallel"),
   INTERSECTING("intersecting"),
   PERPENDICULAR("perpendicular");
   
   private String name;
   
   private LinesRelation(String name) {
      this.name = name;
   }
   
   public String toString() {
      return name;
   }
} 

