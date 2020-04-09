/* =====================================================================================
 *       Filename:  Triangle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 4 - utility class using in 
                                programs in exercises to chapter 4 to describe
                                triangle's properties
                                
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

package trianglePackage;

public class Triangle {

   private static final short numberOfTriangleSides      = 3;
   private static final String nonPositiveValuesOfTriangleSidesError = "Sides of triangle's length must be greater than zero";
   
   private long        firstTriangleSideLength    = 0;
   private long        secondTriangleSideLength   = 0;
   private long        thirdTriangleSideLength    = 0;
   private boolean     positiveValuesOfTriangleSides = true;
   
   public static short getNumberOfTriangleSides () {
      return numberOfTriangleSides;
   }
   
   public long getFirstTriangleSideLength () {
      return firstTriangleSideLength;
   }
   
   public void setFirstTriangleSideLength (long sideLength) {
      if (sideLength > 0) {
         firstTriangleSideLength = sideLength;
      }
      else {
         firstTriangleSideLength = 0;
      }
   }
   
   public long getSecondTriangleSideLength () {
      return secondTriangleSideLength;
   }
   
   public void setSecondTriangleSideLength (long sideLength) {
      if (sideLength > 0) {
         secondTriangleSideLength = sideLength;
      }
      else {
         secondTriangleSideLength = 0;
      }
   }
   
   public long getThirdTriangleSideLength () {
      return thirdTriangleSideLength;
   }
   
   public void setThirdTriangleSideLength (long sideLength) {
      if (sideLength > 0) {
         thirdTriangleSideLength = sideLength;
      }
      else {
         thirdTriangleSideLength = 0;
      }
   }
   
   public boolean isTriangleSidesLengths() {
      if (firstTriangleSideLength <= 0 || secondTriangleSideLength <= 0 || thirdTriangleSideLength <= 0) {
         positiveValuesOfTriangleSides = false;
         return false;
      }
      
      // exclude degenerated triangle with collinear vertices and zero area (angles: two 0 degree and one 180 degree)
      if (firstTriangleSideLength + secondTriangleSideLength <= thirdTriangleSideLength) {
         return false;
      }
      if (thirdTriangleSideLength + secondTriangleSideLength <= firstTriangleSideLength) {
         return false;
      }
      if (thirdTriangleSideLength + firstTriangleSideLength <= secondTriangleSideLength) {
         return false;
      }
      
      return true;
   }
   
   public boolean isRightTriangleSidesLengths() {
      if (false == isTriangleSidesLengths()) {
         return false;
      }
      
      short triangleSideDesignation = getDesignationOfTheLongestOfTriangleSides();
      
      if (0 == triangleSideDesignation) {   // no (only one) the longest of triangle's side
         return false;
      }
      else if (1 == triangleSideDesignation) {
         return isPythagoreanTheoremTrue(firstTriangleSideLength, secondTriangleSideLength,
                                          thirdTriangleSideLength);
      }
      else if (2 == triangleSideDesignation) {
         return isPythagoreanTheoremTrue(secondTriangleSideLength, firstTriangleSideLength,
                                          thirdTriangleSideLength);
      }
      else {
         return isPythagoreanTheoremTrue(thirdTriangleSideLength, secondTriangleSideLength,
                                          firstTriangleSideLength);
      }
   }
   
   public static boolean isPythagoreanTheoremTrue(long theLongestOfTriangleSidesLength, long secondSide, long thirdSide) {
      if (theLongestOfTriangleSidesLength <= 0 || secondSide <= 0 || thirdSide <= 0) {
         return false;
      }
      
      long theBiggestSquare = theLongestOfTriangleSidesLength * theLongestOfTriangleSidesLength;
      long secondSquare = secondSide * secondSide;
      long thirdSquare = thirdSide * thirdSide;
      
      if (theBiggestSquare == secondSquare + thirdSquare) {
         return true;
      }
      
      return false;
   }
   
   public short getDesignationOfTheLongestOfTriangleSides () {
      short triangleSideDesignation = 0;
      
      if (firstTriangleSideLength > secondTriangleSideLength) {
         if (firstTriangleSideLength > thirdTriangleSideLength) {
            triangleSideDesignation = 1;
         }
      }
      
      if (0 == triangleSideDesignation) {
         if (secondTriangleSideLength > firstTriangleSideLength) {
            if (secondTriangleSideLength > thirdTriangleSideLength) {
               triangleSideDesignation = 2;
            }
         }
      }
      
      if (0 == triangleSideDesignation) {
         if (thirdTriangleSideLength > firstTriangleSideLength) {
            if (thirdTriangleSideLength > secondTriangleSideLength) {
               triangleSideDesignation = 3;
            }
         }
      }
      
      return triangleSideDesignation;
   }
   
   public void printErrorsInfo() {
      if (false == positiveValuesOfTriangleSides) {
         System.err.println(nonPositiveValuesOfTriangleSidesError);
      }
   }
   
   public void clearErrorsFlag() {
      positiveValuesOfTriangleSides = true;
   }
   
}
