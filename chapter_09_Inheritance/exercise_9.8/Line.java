/* =====================================================================================
 *       Filename:  Line.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - class about line
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

public class Line {
   private static final int PRECISION = 16;
   private static final BigDecimal EPSILON = new BigDecimal(10e-15);
   private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
   private static final MathContext  MATH_CONTEXT  = new MathContext(PRECISION, ROUNDING_MODE);
   
   public enum LinesRelation {PARALLEL, INTERSECTING, IDENTICALLY};  
   // general line's equation in two-dimensional Cartesian coordinate system: Ax + By + C = 0
   private final BigDecimal A;   // factor A in general line's equation Ax + By + C = 0
   private final BigDecimal B;   // factor B in general line's equation Ax + By + C = 0
   private final BigDecimal C;   // factor C in general line's equation Ax + By + C = 0
   
   public Line(BigDecimal A, BigDecimal B, BigDecimal C) {
      ValidateParameters.checkNullPointer(A, B, C);
      
      this.A = A;
      this.B = B;
      this.C = C;
   }
   
   public Line(Point first, Point second) {
      ValidateParameters.checkNullPointer(first, second);
      
      this.A = A;
      this.B = B;
      this.C = C;
   }
   
   public boolean contains(Point point) {
      ValidateParameters.checkNullPointer(point);
      
      double x = point.getX();
      double y = point.getY();
      BigDecimal result = calculateEquationForCoordinations(bigX, bigY);
      
      return Line.isLessThanEpsilon(result);
   }
   
   private BigDecimal calculateEquationForCoordinations(BigDecimal x, BigDecimal y) {
      BigDecimal firstResult  = A.multiply(x, MATH_CONTEXT);
      BigDecimal secondResult = B.multiply(y, MATH_CONTEXT);
      BigDecimal result       = firstResult.add(secondResult);
      result                  = result.add(C);
      
      return result;
   }
   
   public static boolean isLessThanEpsilon(BigDecimal number) {
      ValidateParameters.checkNullPointer(number);
      
      number = number.abs(MATH_CONTEXT);
      if (-1 == number.compareTo(EPSILON)) {
         return true;
      }
      
      return false;
   }
   
   public LinesRelation getRelation(Line other) {
      ValidateParameters.checkNullPointer(other);
      
      BigDecimal determinant_AB = calculateDeterminant(A, B, other.A, other.B);
      BigDecimal determinant_BC = calculateDeterminant(B, C, other.B, other.C);
      BigDecimal determinant_CA = calculateDeterminant(C, A, other.C, other.A);
   }
   
   public static BigDecimal calculateDeterminant(BigDecimal upperLeft, BigDecimal upperRight, 
                                                   BigDecimal lowerLeft, BigDecimal lowerRight) {
      ValidateParameters.checkNullPointer(upperLeft, upperRight, lowerLeft, lowerRight);
      
      BigDecimal firstDeterminant  = upperLeft.multiply(lowerRight, MATH_CONTEXT);
      BigDecimal secondDeterminant = upperRight.multiply(lowerLeft, MATH_CONTEXT);
      BigDecimal determinant       = firstDeterminant.subtract(secondDeterminant, MATH_CONTEXT);
      
      return determinant;
   }
   
   @Override 
   public String toString() {
      return String.format(" Line Ax+By+C=0 has: A = %s  B = %s  C = %s", A, B, C);
   } 
}
