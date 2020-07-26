/* =====================================================================================
 *       Filename:  Quadrilateral.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - Quadrilateral class 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import validateParametersPackage.ValidateParameters;

import java.util.ArrayList;
import java.math.BigDecimal;

public abstract class Quadrilateral {
   public  static final int NUMBER_OF_VERTICES = 4;
   
   private final String CLASS_NAME = this.getClass().getName();
   private final Point[] VERTICES  = new Point[NUMBER_OF_VERTICES];  
   
   /*
    *  SIDES[0] create from VERTICES[0] to VERTICES[1]
    *  SIDES[1] create from VERTICES[1] to VERTICES[2]
    *  SIDES[2] create from VERTICES[2] to VERTICES[3]
    *  SIDES[3] create from VERTICES[3] to VERTICES[0]
   */
   private final LineSegment[] SIDES = new LineSegment[NUMBER_OF_VERTICES];  
   
   protected Quadrilateral(Point... pointsArray) {
      //CLASS_NAME = this.getClass().getName();
      validatePoints(pointsArray);
      createSides(pointsArray);
      validateSides();
      Point.assignPoints(VERTICES, pointsArray, NUMBER_OF_VERTICES);
   }

   @Override 
   public String toString() {
      return String.format(" %s %s %s %s %s", CLASS_NAME,
         VERTICES[0], VERTICES[1], VERTICES[2], VERTICES[3]);
   } 
   
   public abstract BigDecimal calculateArea();
   
   public LineSegment getSide(int index) {
      try {
         return SIDES[index];
      }
      catch (ArrayIndexOutOfBoundsException exception) {
         throw exception;
      }
   }
   
   public final void validatePoints(Point... pointsArray) {
      Point identicalPoint = Point.getIdenticalPoint(pointsArray);
      if (null != identicalPoint) {
         String message = String.format("Identical vertices %s was detected. %n", identicalPoint)
                        + String.format(" Each vertices in %s must be unique", CLASS_NAME);
                        
         throw new IllegalArgumentException(message);
      }
      
      if (NUMBER_OF_VERTICES != pointsArray.length) {
         String message = String.format("%s must contain %d vertices %n", CLASS_NAME, NUMBER_OF_VERTICES)
                        + String.format(" Number of vertices: %d is incorrect", pointsArray.length);
                        
         throw new IllegalArgumentException(message);
      }
   }
   
   private final void createSides(Point... pointsArray) {
      for (int index = 0; index < NUMBER_OF_VERTICES - 1; index++) {
         SIDES[index] = new LineSegment(pointsArray[index], pointsArray[index + 1]);
      }
      
      SIDES[NUMBER_OF_VERTICES - 1] = new LineSegment(pointsArray[NUMBER_OF_VERTICES - 1], pointsArray[0]);
   }
   
   private final void validateSides() {
      detectCollinearSides();
      detectForbiddenSidesIntersections();
   }
   
   private final void detectCollinearSides() {
      for (int index = 0; index < NUMBER_OF_VERTICES - 1; index++) {
         for (int nextIndex = index + 1; nextIndex < NUMBER_OF_VERTICES; nextIndex++) {
            Line sideLine      = SIDES[index].getLine();
            Line otherSideLine = SIDES[nextIndex].getLine();
            
            if (LinesRelation.IDENTICALLY == sideLine.getRelation(otherSideLine)) {
               String message = Message.getErrorMessage(SIDES[index], SIDES[nextIndex]);
                        
               throw new IllegalArgumentException(message);
            }
         }
      }
   }
   
   // detect intersections between two segments which can not intersect
   private final void detectForbiddenSidesIntersections() {
      detectForbiddenSidesIntersections(SIDES[0], SIDES[2]);  
      detectForbiddenSidesIntersections(SIDES[1], SIDES[3]);
   }
   
   private final void detectForbiddenSidesIntersections(LineSegment first, LineSegment second) {
      Point intersectionPoint = first.getIntersectionPoint(second);
      if (null != intersectionPoint) {
         throw new IllegalArgumentException(String.format("Intersection between %s and %s in %s ", 
                                                first, second, intersectionPoint));
      }
   }
   
   protected int calculateSidesPairRelations(LinesRelation linesRelation) {
      int sidesRelationsCounter = 0;
      for (int index = 0; index < NUMBER_OF_VERTICES - 1; index++) {
         for (int nextIndex = index + 1; nextIndex < NUMBER_OF_VERTICES; nextIndex++) {
            Line sideLine      = SIDES[index].getLine();
            Line otherSideLine = SIDES[nextIndex].getLine();
            
            if (linesRelation == sideLine.getRelation(otherSideLine)) {
               sidesRelationsCounter++;
            }
         }
      }
      
      return sidesRelationsCounter;
   }
   
   protected ArrayList<Integer> getSidesWithRelations(LinesRelation linesRelation, int sidesNumber) {
      if (sidesNumber < 2 || sidesNumber > NUMBER_OF_VERTICES) {
         throw new IllegalArgumentException("Requirement: 2 <= sides number <= " + NUMBER_OF_VERTICES);
                                                
      }
      
      ArrayList<Integer> sidesList = new ArrayList<>();
      for (int index = 0; index < NUMBER_OF_VERTICES - 1 && sidesList.size() < sidesNumber; index++) {
         for (int nextIndex = index + 1; nextIndex < NUMBER_OF_VERTICES && sidesList.size() < sidesNumber; nextIndex++) {
            Line sideLine      = SIDES[index].getLine();
            Line otherSideLine = SIDES[nextIndex].getLine();
            
            if (linesRelation == sideLine.getRelation(otherSideLine)) {
               if (false == sidesList.contains(index))
                  sidesList.add(index);
               if (false == sidesList.contains(nextIndex))
                  sidesList.add(nextIndex);
            }
         }
      }
      
      return sidesList;
   }
   
} 

class Message {
   static String getErrorMessage(LineSegment first, LineSegment second) {
      ValidateParameters.checkNullPointer(first, second);
      
      Point firstExtremity_0    = first.getExtremity(0);
      Point firstExtremity_1    = first.getExtremity(1);
      Point thirdCollinearPoint = second.getExtremity(1);
      
      if (true == thirdCollinearPoint.equals(firstExtremity_0) || true == thirdCollinearPoint.equals(firstExtremity_1)) {
         thirdCollinearPoint = second.getExtremity(0);
      }
      
      String message = String.format(" 3 vertexes %s, %s, %s are collinear ", firstExtremity_0, firstExtremity_1, 
                  thirdCollinearPoint);
                  
      return message;
   }   
}
