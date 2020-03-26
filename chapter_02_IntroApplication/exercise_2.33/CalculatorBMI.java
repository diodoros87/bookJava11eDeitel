/* =====================================================================================
 *       Filename:  CalculatorBMI.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.33 - calculate BMI
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class CalculatorBMI {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get two integers greater than zero from User.");
      System.out.printf ("   First integer represents human's weight in kilograms%n");
      System.out.printf ("   Second integer represents human's height in centimetres%n");
      System.out.printf ("2. Calculate body mass index and display result and result's category.%n%n");
      
      System.out.println("*** Conditions for correct entering input data of integer accept only:");
      System.out.println("   - characters of digits ");
      System.out.println("   - eventually for numbers less than zero before number only one character \'-\'");
      System.out.println("   - eventually for numbers greater than zero before number only one character \'+\'");
      System.out.println("   - eventually for zero before number only one character: \'+\' otherwise \'-\' ");
      System.out.printf ("   - maximum value of entered number can not be more than %d %n", Integer.MAX_VALUE);
      System.out.printf ("   - minimum value of entered number can not be less than %d %n", Integer.MIN_VALUE);
      System.out.println("   Examples of accepted number formats are in quotes: ");
      System.out.println("   \'+1\' \'17\' \'-51\' \'+0\' \'-00\' \'-023\' \'+0005\'");
      
      System.out.println("   space, tabulator, new line character are skipped");
      System.out.println("   number of arguments entered by User can not be more than 2"); 
      
      System.out.print  ("Enter human's weight in kilograms: ");
      
      Scanner input              = new Scanner(System.in);
      boolean isCorrectInputData = input.hasNextInt();   // to check input data
      
      if (false == isCorrectInputData) {
            System.err.println("First value entered by User is incorrect"); 
      }
      if (true == isCorrectInputData) {
      
         int weight = input.nextInt();         
         if (weight <= 0) {
            System.err.println("Human's weight in kilograms can not be less than zero or equal zero");
         }
         if (weight > 0) {
         
            String nonWhitespaceRegex = "[^\\s]"; 
            String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
            if (null != inputData) {
                  System.err.println("Too many arguments entered by User"); 
            }
            if (null == inputData) {
               System.out.print  ("Enter human's height in centimetres: ");
               
               isCorrectInputData = input.hasNextInt();
               if (false == isCorrectInputData) {
                  System.err.println("Second value entered by User is incorrect"); 
               }
               if (true == isCorrectInputData) {
               
                  int height = input.nextInt();
                  if (height <= 0) {
                     System.err.println("Human's height in centimetres can not be less than zero or equal zero");
                  }
                  if (height > 0) {
                  
                     inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
                     if (null != inputData) {
                        System.err.println("Number of arguments entered by User can not be more than 2"); 
                     }
                     if (null == inputData) {
                     
                        double bodyMassIndex = weight / (height * height / (double)(100 * 100));
                        String category = "";
                        
                        {  // to avoid conflict between:
                           // - rounding in System.out.printf with %.1f and 
                           // - in classify (according to ranges of BMI values) value of bodyMassIndex
                           if (bodyMassIndex >= (int)bodyMassIndex + 0.95)    
                              bodyMassIndex = (int)bodyMassIndex + 1;
                           if (bodyMassIndex >= 18.45 && bodyMassIndex < 18.5)
                              bodyMassIndex = 18.5;
                        }
                        
                        
                        if (bodyMassIndex < 15)
                           category = "very severely underweight";
                        if (bodyMassIndex >= 15 && bodyMassIndex < 16)
                           category = "severely underweight";
                        if (bodyMassIndex >= 16 && bodyMassIndex < 18.5)
                           category = "underweight";
                        if (bodyMassIndex >= 18.5 && bodyMassIndex < 25)
                           category = "normal (healthy) weight";
                        if (bodyMassIndex >= 25 && bodyMassIndex < 30)
                           category = "overweight";
                        if (bodyMassIndex >= 30 && bodyMassIndex < 35)
                           category = "obese class I";
                        if (bodyMassIndex >= 35 && bodyMassIndex < 40)
                           category = "obese class II";
                        if (bodyMassIndex >= 40 && bodyMassIndex < 45)
                           category = "obese class III";
                        if (bodyMassIndex >= 45 && bodyMassIndex < 50)
                           category = "obese class IV";
                        if (bodyMassIndex >= 50)
                           category = "obese class V";
                     
                        System.out.printf("%n Body mass index is %.1f ", bodyMassIndex);
                        System.out.printf("%n This result belongs to the category of %s %n", category);
                        
                        System.out.printf("%n This result has been classified according to ranges of BMI values: %n");
                        System.out.println("very severely underweight : less than 15");
                        System.out.println("severely underweight: contains 15 and less than 16");
                        System.out.println("underweight : contains 16 and less than 18.5");
                        System.out.println("normal (healthy) weight: contains 18.5 and less than 25");
                        System.out.println("overweight: contains 25 and less than 30");
                        System.out.println("obese class I : contains 30 and less than 35");
                        System.out.println("obese class II: contains 35 and less than 40");
                        System.out.println("obese class III : contains 40 and less than 45");
                        System.out.println("obese class IV: contains 45 and less than 50");
                        System.out.println("obese class V : contains 50 and greater than 50");
                        
                     }
                  }
               }
               
            }
         }
      }
 
      input.close();
   }
   
}
