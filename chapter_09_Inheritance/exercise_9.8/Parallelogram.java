/* =====================================================================================
 *       Filename:  Parallelogram.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - Parallelogram class inherits from Trapezoid
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import java.math.BigDecimal;

public class Parallelogram extends Trapezoid {
   private static final int NUMBER_OF_PARALLEL_PAIRS_OF_SIDES = 2;
   
   public Parallelogram(Point... pointsArray) {
      super(pointsArray);
      checkSides(LinesRelation.PARALLEL, NUMBER_OF_PARALLEL_PAIRS_OF_SIDES, true);
   }
   
   @Override
   public BigDecimal calculateArea() {
      LineSegment side         = getSide(0);
      LineSegment parallelSide = getSide(2);
      
      final BigDecimal HEIGHT       = super.calculateHeight(side, parallelSide);
      final BigDecimal SIDE_LENGTH  = side.calculateLength();
      
      BigDecimal result = SIDE_LENGTH.multiply(HEIGHT, Line.MATH_CONTEXT);
      
      return result;
   }
} 
