/* =====================================================================================
 *       Filename:  QualityPoints.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.28 - print grade for number of points
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class QualityPoints {

   public static final String SHORT_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Short.MAX_VALUE);
   public static final String SHORT_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Short.MIN_VALUE);
   
   public static void main(String[] args) {
      System.out.printf("*** This program prints grade for number of points as integer entered by User.%n");
      
      final String QUIT_INFO = "To quit enter sequence other than integer from below range:";
      final String PROMPT = "Only first entered integer will be accepted. Enter integer: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n %s", QUIT_INFO, SHORT_MAX_VALUE_STRING, SHORT_MIN_VALUE_STRING, PROMPT);
      
      do {
         short points = GettingDataFromStandardInput.getShortInteger(QUIT_INFO_PROMPT);
         short grade = qualityPoints(points);
         if (grade >= 0)
            System.out.printf("%n$$$ For %d points grade is %d%n", points, qualityPoints(points));
      } while (true);

   } 
   
   public static short qualityPoints(short points) {
      final short max = 100;
      final short min = 0;
      
      if (100 >= points && points >= 90) {
         return 4;
      }
      else if (89 >= points && points >= 80) {
         return 3;
      }
      else if (79 >= points && points >= 70) {
         return 2;
      }
      else if (69 >= points && points >= 60) {
         return 1;
      }
      else if (60 > points && points >= 0) {
         return 0;
      }
      else {
         System.out.printf("%n*** ERROR: points must be in range from %d to %d%n", min, max);
         return -1;
      }
   }
   
} 
