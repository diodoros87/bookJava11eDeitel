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

public class Quadrilateral {
   public  static final int NUMBER_OF_VERTICES = 4;
   
   private final String CLASS_NAME = this.getClass().getName();
   private final Point[] VERTICES = new Point[NUMBER_OF_VERTICES];  
   
   protected Point[] getVertices() {
      return VERTICES;
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
         throw new IllegalArgumentException(CLASS_NAME + " must contain " + NUMBER_OF_VERTICES + " vertices");
      }
   }
   
} 
