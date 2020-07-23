/* =====================================================================================
 *       Filename:  LineSegment.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - class about line segment
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class LineSegment {
   public  static final int NUMBER_OF_EXTREMITIES = 2;

   private final Point[] EXTREMITIES = new Point[NUMBER_OF_EXTREMITIES]; 
   //private final Line LINE;
   
   public LineSegment(Point... pointsArray) {
      LineSegment.validatePoints(pointsArray);
      
      Point.assignPoints(this.EXTREMITIES, pointsArray, NUMBER_OF_EXTREMITIES);
   }
   
   @Override 
   public String toString() {
      return String.format(" Line segment %s %s", EXTREMITIES[0], EXTREMITIES[1]);
   } 
   
   public static void validatePoints(Point... pointsArray) {
      Point identicalPoint = Point.getIdenticalPoint(pointsArray);
      if (null != identicalPoint) {
         String message = String.format("Identical extremities %s was detected. %n", identicalPoint)
                        + " Each extremities in line segment must be unique";
                        
         throw new IllegalArgumentException(message);
      }
      
      if (NUMBER_OF_EXTREMITIES != pointsArray.length) {
         throw new IllegalArgumentException("Line segment must contain " + NUMBER_OF_EXTREMITIES + " extremities");
      }
   }
}
