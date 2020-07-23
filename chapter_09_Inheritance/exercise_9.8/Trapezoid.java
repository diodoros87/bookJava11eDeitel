/* =====================================================================================
 *       Filename:  Trapezoid.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - Trapezoid class inherits from Quadrilateral
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.lang.Object;

public class Trapezoid extends Quadrilateral {

   public Trapezoid(Point... pointsArray) {
      validatePoints(pointsArray);
      
      Point.assignPoints(getVertices(), pointsArray, NUMBER_OF_VERTICES);
   }
   /*
   public static void validatePoints(Point... pointsArray) {
      Quadrilateral.validatePoints(pointsArray);
      
      String message = String.format("Identical vertices %s was detected. %n", pointsArray[index]);
                        + " Each of vertices in quadrilateral must be unique";
                        
      throw new IllegalArgumentException(message);
   }*/
} 
