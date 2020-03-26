/* =====================================================================================
 *       Filename:  GlobalPopulationGrowthCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.34 - calculate global population growth
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class GlobalPopulationGrowthCalculator {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get two integers from User.");
      System.out.printf ("   First integer represents total number of humans currently living%n");
      System.out.printf ("   Second integer represents difference between total numbers of humans per one year%n");
      System.out.printf ("   This difference should be around from -100 percent (all people dead)%n");
      System.out.printf ("   to 5 percent of first entered value%n");
      System.out.printf ("2. Calculate total number of humans through next years is determined by growth population rate.%n");
      System.out.printf ("   Assuming that growth population rate is constant through next 5 years.%n");
      System.out.printf ("   Calculate total number of humans after 1, 2, 3, 4, 5 years and display results.%n%n");
      
      System.out.println("*** Conditions for correct entering input data of integer accept only:");
      System.out.println("   - characters of digits ");
      System.out.println("   - eventually for numbers less than zero before number only one character \'-\'");
      System.out.println("   - eventually for numbers greater than zero before number only one character \'+\'");
      System.out.println("   - eventually for zero before number only one character: \'+\' otherwise \'-\' ");
      System.out.printf ("   - maximum value of entered number can not be more than %d %n", Long.MAX_VALUE);
      System.out.printf ("   - minimum value of entered number can not be less than %d %n", Long.MIN_VALUE);
      System.out.println("   Examples of accepted number formats are in quotes: ");
      System.out.println("   \'+1\' \'17\' \'-51\' \'+0\' \'-00\' \'-023\' \'+0005\'");
      
      System.out.println("   space, tabulator, new line character are skipped");
      System.out.println("   number of arguments entered by User can not be more than 2"); 
      
      System.out.print  ("Enter total number of humans currently living: ");
      
      Scanner input              = new Scanner(System.in);
      boolean isCorrectInputData = input.hasNextLong();   // to check input data
      
      if (false == isCorrectInputData) {
            System.err.println("First value entered by User is incorrect"); 
      }
      if (true == isCorrectInputData) {
      
         long populationNumber = input.nextLong();         
         if (populationNumber < 100) {
            System.err.println("Total number of humans currently living can not be less than 100");
         }
         if (populationNumber >= 100) {
         
            String nonWhitespaceRegex = "[^\\s]"; 
            String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
            if (null != inputData) {
                  System.err.println("Too many arguments entered by User"); 
            }
            if (null == inputData) {
               System.out.print("Enter difference (as integer) between total numbers of humans per one year: ");
               
               isCorrectInputData = input.hasNextLong();
               if (false == isCorrectInputData) {
                  System.err.println("Second value entered by User is incorrect"); 
               }
               if (true == isCorrectInputData) {
               
                  long growthPopulationNumber = input.nextLong();
                  if (growthPopulationNumber < -populationNumber) {
                     System.err.println("Difference between total numbers of humans per one year can not be less than");
                     System.err.println("(minus) population number");
                  }
                  if (growthPopulationNumber > 0.05 * populationNumber) {
                     System.err.println("Difference between total numbers of humans per one year can not be greater than");
                     System.err.println(" 5 percent of population number");
                  }
                  if (growthPopulationNumber >= -populationNumber && growthPopulationNumber <= 0.05 * populationNumber) 
                  {
                     inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
                     if (null != inputData) {
                        System.err.println("Number of arguments entered by User can not be more than 2"); 
                     }
                     if (null == inputData) {
                        
                        double growthPopulationRate = growthPopulationNumber / (double)populationNumber;
                        
                        populationNumber += growthPopulationNumber;
                        System.out.printf("%n After %d year population number is %d %n", 1, populationNumber);
                        populationNumber += growthPopulationRate * populationNumber;
                        System.out.printf("%n After %d years population number is %d %n", 2, populationNumber);
                        populationNumber += growthPopulationRate * populationNumber;
                        System.out.printf("%n After %d years population number is %d %n", 3, populationNumber);
                        populationNumber += growthPopulationRate * populationNumber;
                        System.out.printf("%n After %d years population number is %d %n", 4, populationNumber);
                        populationNumber += growthPopulationRate * populationNumber;
                        System.out.printf("%n After %d years population number is %d %n", 5, populationNumber);
                     }
                  }
               }
               
            }
         }
      }
 
      input.close();
   }
   
}
