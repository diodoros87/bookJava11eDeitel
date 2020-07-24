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

public abstract class Quadrilateral {
   public  static final int NUMBER_OF_VERTICES = 4;
   
   private final String CLASS_NAME = this.getClass().getName();
   private final Point[] VERTICES  = new Point[NUMBER_OF_VERTICES];  
   
   /*
    *  SIDES[0] create from VERTICES[0] VERTICES[1]
    *  SIDES[1] create from VERTICES[1] VERTICES[2]
    *  SIDES[2] create from VERTICES[2] VERTICES[3]
    *  SIDES[3] create from VERTICES[3] VERTICES[0]
   */
   private final LineSegment[] SIDES = new LineSegment[NUMBER_OF_VERTICES];  
   
   protected Quadrilateral(Point... pointsArray) {
      //CLASS_NAME = this.getClass().getName();
      validatePoints(pointsArray);
      createSides(pointsArray);
      validateSides();
      Point.assignPoints(VERTICES, pointsArray, NUMBER_OF_VERTICES);
      //Point.assignPoints(getVertices(), pointsArray, NUMBER_OF_VERTICES);
   }

   @Override 
   public String toString() {
      return String.format(" %s %s %s %s %s", CLASS_NAME,
         VERTICES[0], VERTICES[1], VERTICES[2], VERTICES[3]);
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
            
            if (Line.LinesRelation.IDENTICALLY == sideLine.getRelation(otherSideLine)) {
               String message = Message.getErrorMessage(SIDES[index], SIDES[nextIndex]);
                        
               throw new IllegalArgumentException(message);
            }
         }
      }
   }
   
   private final void detectForbiddenSidesIntersections() {
      Point intersectionPoint = SIDES[0].getIntersectionPoint(SIDES[2]);
      if (null != intersectionPoint) {
         throw new IllegalArgumentException(String.format("Intersect between %s and %s in %s ", 
                                                SIDES[0], SIDES[2], intersectionPoint));
      }
      
      intersectionPoint = SIDES[1].getIntersectionPoint(SIDES[3]);
      if (null != intersectionPoint) {
         throw new IllegalArgumentException(String.format("Intersect between %s and %s in %s ", 
                                                SIDES[1], SIDES[3], intersectionPoint));
      }
   }
   
   protected int calculateSidesRelations(Line.LinesRelation linesRelation) {
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
   
} 

class Message {
   static String getErrorMessage(LineSegment first, LineSegment second) {
      ValidateParameters.checkNullPointer(first, second);
      
      Point[] firstExtremities  = first.getExtremities();
      Point[] secondExtremities = second.getExtremities();
      Point   thirdCollinearPoint = secondExtremities[1];
      
      if (true == thirdCollinearPoint.equals(firstExtremities[0]) || true == thirdCollinearPoint.equals(firstExtremities[1])) {
         thirdCollinearPoint = secondExtremities[0];
      }
      
      String message = String.format(" 3 vertexes %s, %s, %s are collinear ", firstExtremities[0], firstExtremities[1], 
                  thirdCollinearPoint);
                  
      return message;
   }   
}
