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
 
import validateParametersPackage.ValidateParameters;
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

public class Line {
   private static final int PRECISION = 16;
   private static final BigDecimal EPSILON = new BigDecimal(10e-16);
   private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
   static final MathContext  MATH_CONTEXT  = new MathContext(PRECISION, ROUNDING_MODE);
   
   // general line's equation in two-dimensional Cartesian coordinate system: Ax + By + C = 0
   private final BigDecimal A;   // factor A in general line's equation Ax + By + C = 0
   private final BigDecimal B;   // factor B in general line's equation Ax + By + C = 0
   private final BigDecimal C;   // factor C in general line's equation Ax + By + C = 0
   
   public Line(BigDecimal A, BigDecimal B, BigDecimal C) {
      Line.validateLineFactors(A, B, C);
      
      this.A = A;
      this.B = B;
      this.C = C;
   }
   
   public Line(Line line) {
      ValidateParameters.checkNullPointer(line);
      Line.validateLineFactors(line.A, line.B, line.C);
      
      this.A = line.A;
      this.B = line.B;
      this.C = line.C;
   }
   
   // construct line according to two points
   public Line(Point first, Point second) {
      ValidateParameters.checkNullPointer(first, second);
      //Line.validatePoints(first, second);
      BigDecimal firstX  = BigDecimal.valueOf(first.getX());
      BigDecimal firstY  = BigDecimal.valueOf(first.getY());
      BigDecimal secondX = BigDecimal.valueOf(second.getX());
      BigDecimal secondY = BigDecimal.valueOf(second.getY());
      
      this.A = secondY.subtract(firstY, MATH_CONTEXT);
      this.B = firstX.subtract(secondX, MATH_CONTEXT);
      
      BigDecimal firstElement  = secondX.multiply(firstY, MATH_CONTEXT);
      BigDecimal secondElement = firstX.multiply(secondY, MATH_CONTEXT);
      this.C                   = firstElement.subtract(secondElement, MATH_CONTEXT);
      
      Line.validateLineFactors(this.A, this.B, this.C);
   }
   
   public static void validateLineFactors(BigDecimal A, BigDecimal B, BigDecimal C) {
      ValidateParameters.checkNullPointer(A, B, C);
      
      if (true == isLessThanEpsilon(A) && true == isLessThanEpsilon(B)) {
         throw new IllegalArgumentException("A = B = 0");
      }
   }
   /* UNNECESSARY
   public static void validatePoints(Point... pointsArray) {
      Point identicalPoint = Point.getIdenticalPoint(pointsArray);
      if (null != identicalPoint) {
         String message = String.format("Identical points %s was detected. %n", identicalPoint)
                        + " Each points in line must be unique";
                        
         throw new IllegalArgumentException(message);
      }
   }*/
   
   // is point lie on line ?
   public boolean contains(Point point) {
      ValidateParameters.checkNullPointer(point);
      
      double x = point.getX();
      double y = point.getY();
      BigDecimal bigX = BigDecimal.valueOf(x);
      BigDecimal bigY = BigDecimal.valueOf(y);
      
      BigDecimal result = calculateEquationForCoordinations(bigX, bigY);
      
      return Line.isLessThanEpsilon(result);
   }
   
   // line equation Ax + by + C = 0
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
   
   public Point calculateIntersectionPoint(Line other) {
      ValidateParameters.checkNullPointer(other);
      
      BigDecimal determinant_AB = calculateDeterminant(A, B, other.A, other.B);
      BigDecimal determinant_BC = calculateDeterminant(B, C, other.B, other.C);
      BigDecimal determinant_CA = calculateDeterminant(C, A, other.C, other.A);
      
      return getIntersectionPoint(determinant_AB, determinant_BC, determinant_CA);
   }
   
   private Point getIntersectionPoint(BigDecimal determinant_AB, BigDecimal determinant_BC, BigDecimal determinant_CA) {
      if (true == isLessThanEpsilon(determinant_AB)) {
         
         return null;
      }
      
      BigDecimal x = determinant_BC.divide(determinant_AB, MATH_CONTEXT);
      BigDecimal y = determinant_CA.divide(determinant_AB, MATH_CONTEXT);
      
      Point intersectionPoint = new Point("I", x.doubleValue(), y.doubleValue());
      
      return intersectionPoint;
   }
   
   public LinesRelation getRelation(Line other) {
      ValidateParameters.checkNullPointer(other);
      
      BigDecimal determinant_AB = calculateDeterminant(A, B, other.A, other.B);
      BigDecimal determinant_BC = calculateDeterminant(B, C, other.B, other.C);
      BigDecimal determinant_CA = calculateDeterminant(C, A, other.C, other.A);
      
      LinesRelation relation = getRelation(determinant_AB, determinant_BC, determinant_CA);
      if (LinesRelation.INTERSECTING == relation && true == isPerpendicular(other)) {
         
         return LinesRelation.PERPENDICULAR;
      }
      
      return relation;
   }
   
   private LinesRelation getRelation(BigDecimal determinant_AB, BigDecimal determinant_BC, BigDecimal determinant_CA) {
      if (true == isLessThanEpsilon(determinant_AB)) {
         if (true == isLessThanEpsilon(determinant_BC) &&  true == isLessThanEpsilon(determinant_CA)) {
            
            return LinesRelation.IDENTICALLY;
         }
         
         return LinesRelation.PARALLEL;
      }
      
      return LinesRelation.INTERSECTING;
   }
   
   public boolean isPerpendicular(Line other) {
      ValidateParameters.checkNullPointer(other);
      
      BigDecimal firstElement  = A.multiply(other.A, MATH_CONTEXT);
      BigDecimal secondElement = B.multiply(other.B, MATH_CONTEXT);
      BigDecimal result        = firstElement.add(secondElement, MATH_CONTEXT);
      
      return isLessThanEpsilon(result);
   }
   
   public BigDecimal calculateDistance(Point point) {
      ValidateParameters.checkNullPointer(point);
      
      BigDecimal x = BigDecimal.valueOf(point.getX());
      BigDecimal y = BigDecimal.valueOf(point.getY());
      
      BigDecimal resultNumerator = calculateEquationForCoordinations(x, y);
      resultNumerator = resultNumerator.abs(MATH_CONTEXT);
      
      BigDecimal squareA = A.pow(2, MATH_CONTEXT);
      BigDecimal squareB = B.pow(2, MATH_CONTEXT);
      
      BigDecimal resultDenominator = squareA.add(squareB, MATH_CONTEXT);
      resultDenominator            = resultDenominator.sqrt(MATH_CONTEXT);
      
      BigDecimal result = resultNumerator.divide(resultDenominator, MATH_CONTEXT);
      
      return result;
   }
   
   public static BigDecimal calculateDeterminant(BigDecimal upperLeft, BigDecimal upperRight, 
                                                   BigDecimal lowerLeft, BigDecimal lowerRight) {
      ValidateParameters.checkNullPointer(upperLeft, upperRight, lowerLeft, lowerRight);
      
      BigDecimal firstDeterminant  = upperLeft.multiply(lowerRight, MATH_CONTEXT);
      BigDecimal secondDeterminant = upperRight.multiply(lowerLeft, MATH_CONTEXT);
      BigDecimal determinant       = firstDeterminant.subtract(secondDeterminant, MATH_CONTEXT);
      
      return determinant;
   }
   
   public BigDecimal getA() {
      return A;
   }
   
   public BigDecimal getB() {
      return B;
   }
   
   public BigDecimal getC() {
      return C;
   }
   
   @Override 
   public String toString() {
      return String.format(" Line Ax+By+C=0 has: A = %s  B = %s  C = %s", A, B, C);
   } 
}
