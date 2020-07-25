/* =====================================================================================
 *       Filename:  Rectangle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - Rectangle class inherits from Quadrilateral
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import java.math.BigDecimal;

public class Rectangle extends Parallelogram {
   private static final int NUMBER_OF_PERPENDICULAR_PAIRS_OF_SIDES = 4;
   
   public Rectangle(Point... pointsArray) {
      super(pointsArray);
      //validatePoints(pointsArray);
      checkSides(LinesRelation.PERPENDICULAR, NUMBER_OF_PERPENDICULAR_PAIRS_OF_SIDES, true);
      //checkSidesPerpendicularity();
   }
   /*
   private final void checkSidesPerpendicularity() {
      int perpendicularSidesPairs = calculateSidesPairRelations(LinesRelation.PERPENDICULAR);
      if (perpendicularSidesPairs != 2) {
         throw new IllegalArgumentException(this + "\n must have 2 pairs of perpendicular sides");
      }
   }*/
   
   @Override
   public BigDecimal calculateArea() {
      LineSegment side         = getSide(0);
      LineSegment nextSide     = getSide(1);
      
      final BigDecimal SIDE_LENGTH       = side.calculateLength();
      final BigDecimal NEXT_SIDE_LENGTH  = nextSide.calculateLength();
      
      BigDecimal result = SIDE_LENGTH.multiply(NEXT_SIDE_LENGTH, Line.MATH_CONTEXT);
      
      return result;
   }
} 
