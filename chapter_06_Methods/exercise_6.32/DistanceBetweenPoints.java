/* =====================================================================================
 *       Filename:  DistanceBetweenPoints.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.32 - calculate of distance between points
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
import pairPackage.DoubleTypePair;
import dataFromStringPackage.GettingDataFromString;

public class DistanceBetweenPoints {   
   
   public static void main(String[] args) {
      System.out.printf("*** This program calculate of distance between points according to pair of numbers entered by User.%n");
      
      final String QUIT_INFO = String.format("%s: %n %s", "To quit enter only End-Of-Transmission (EOT) character",
                                             "Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows");
                                             
      DoubleTypePair first  = null;
      DoubleTypePair second = null;
      DoubleTypePair pair;
      GettingDataFromString stringScanner = getStringScanner(QUIT_INFO);
      
      while (null != stringScanner) {
      
         pair = stringScanner.getOnlyDoubleTypePair();
         if (true == validatePairContent(pair)) {
            if (null == first) {
               first = pair;
            }
            else if (null == second) {
               second = pair;
            }
            
            if (null != first && null != second) {   
               printPointsDistance(first, second);
               
               first = null;
               second = null;
            }
         }
         
         stringScanner = getStringScanner(QUIT_INFO);
      }
      
      System.out.println();
   } 
   
   public static void printPointsDistance(DoubleTypePair first, DoubleTypePair second) {
      if (true == validatePairContent(first) && true == validatePairContent(second)) {
      
         Point firstPoint = new Point(first.getFirstNumber(), first.getSecondNumber());
         Point secondPoint = new Point(second.getFirstNumber(), second.getSecondNumber());
         
         System.out.printf("%n$$$ RESULT: Distance between (%f, %f) and (%f, %f) is %f %n",
               firstPoint.getX(), firstPoint.getY(),
               secondPoint.getX(), secondPoint.getY(),
               distance(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY()));
      }
   }
   
   public static double distance (double x1, double y1, double x2, double y2) {
      return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
   } 
   
   public static GettingDataFromString getStringScanner(String quitInfo) {
      final String PROMPT = "Enter only pair of numbers (as point's coordinates) separated by whitespaces: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s", quitInfo, PROMPT);
      
      String answerString = GettingDataFromStandardInput.getStringWaitingForInput(QUIT_INFO_PROMPT);
      
      if (null == answerString) {
         return null;
      }
      
      return new GettingDataFromString(answerString);
   }
   
   public static boolean validatePair(DoubleTypePair pair) {
      if (null == pair) {
         System.err.printf("%n****** ERROR: Parameter pair can not be null in method validatePair%n");
         return false;
      }
      
      if (true == pair.isNullInPair()) {
         System.err.printf("%n****** ERROR: Every numbers in pair can not be null in method validatePair%n");
         return false;
      }
      
      return true;
   }
   
   public static boolean validatePairContent(DoubleTypePair point) {
      if (false == validatePair(point)) {
         return false;
      }
      
      if (true == validatePointCoordinate(point.getFirstNumber())) {
         if (true == validatePointCoordinate(point.getSecondNumber())) {
            return true;
         }
      }
      
      return false;
   }
   
   public static boolean validatePointCoordinate(double coordinate) {
      if (Double.isNaN(coordinate) || 
          Double.NEGATIVE_INFINITY == coordinate ||
          Double.POSITIVE_INFINITY == coordinate) {
          System.err.printf("%n****** ERROR: point's coordinate can not be %f %n", coordinate);
          
          return false;
      }
      
         return true;
   }
   
}
