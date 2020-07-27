/* =====================================================================================
 *       Filename:  Square.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - Square class inherits from Rectangle
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import java.math.BigDecimal;

public final class Square extends Rectangle {
   private  static final int NUMBER_OF_EQUAL_LENGTH_OF_SIDES = 4;
   
   public Square(Point... pointsArray) {
      super(pointsArray);
      checkEquality();
   }
   
   private final void checkEquality() {
      LineSegment side       = getSide(0);
      BigDecimal  sideLength = side.calculateLength();
      for (int index = 1; index < NUMBER_OF_EQUAL_LENGTH_OF_SIDES; index++) {
         LineSegment otherSide       = getSide(index);
         BigDecimal  otherSideLength = otherSide.calculateLength();
         
         if (0 != sideLength.compareTo(otherSideLength)) {
            
            String message = String.format("%s %n and %s %n have different lengths: %n %s and %s",
                                    side, otherSide, sideLength, otherSideLength)
                        + String.format("%n%s %n must have all sides with identical length", this);
            throw new IllegalArgumentException(message);
         }
      }
   }
   
   @Override
   public BigDecimal calculateArea() {
      LineSegment side             = getSide(0);
      final BigDecimal SIDE_LENGTH = side.calculateLength();
      
      BigDecimal result = SIDE_LENGTH.multiply(SIDE_LENGTH, Line.MATH_CONTEXT);
      
      return result;
   }
} 
