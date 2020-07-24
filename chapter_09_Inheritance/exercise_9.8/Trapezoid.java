/* =====================================================================================
 *       Filename:  Trapezoid.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - Trapezoid class inherits from Quadrilateral
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.math.BigDecimal;

public class Trapezoid extends Quadrilateral {
   //private final BigDecimal HEIGHT;

   public Trapezoid(Point... pointsArray) {
      super(pointsArray);
      //validatePoints(pointsArray);
      int parallelSides = calculateSidesRelations(Line.LinesRelation.PARALLEL);
      if (1 > parallelSides) {
         throw new IllegalArgumentException("Trapezoid must have at least 1 pair of parallel sides");
      }
   }
   
   
   /*
   public static void validatePoints(Point... pointsArray) {
      Quadrilateral.validatePoints(pointsArray);
      
      String message = String.format("Identical vertices %s was detected. %n", pointsArray[index]);
                        + " Each of vertices in quadrilateral must be unique";
                        
      throw new IllegalArgumentException(message);
   }*/
} 
