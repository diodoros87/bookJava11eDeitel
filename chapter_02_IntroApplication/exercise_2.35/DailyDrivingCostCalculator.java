/* =====================================================================================
 *       Filename:  DailyDrivingCostCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.35 - calculate daily driving cost
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class DailyDrivingCostCalculator {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get five numbers not less than zero from User in purpose of calculate daily driving cost.");
      System.out.printf ("2. Calculate daily driving cost and display result.%n%n");
      
      System.out.println("*** Conditions for correct data entry:");
      System.out.println("a) First way of correct entering input data of number accept only: ");
      System.out.println("   - characters of digits ");
      System.out.println("   - eventually for numbers less than zero before number only one character \'-\'");
      System.out.println("   - eventually for numbers greater than zero before number only one character \'+\'");
      System.out.println("   - eventually for zero before number only one character: \'+\' otherwise \'-\' ");
      System.out.println("   - decimal separator as only one character \'.\'");
      System.out.println("   Examples of accepted number formats are in quotes: ");
      System.out.println("   \'+1\' \'17\' \'-51\' \'+23.783\' \'+3.380\' \'-2.7\' \'00.00\' \'+0\' \'-0.00\' \'+023.783\' \'+0005\'");
      System.out.println("b) Second way of correct entering input data (named as scientific notation) of number accept only: ");
      System.out.println("   - all rules for first way ");
      System.out.println("   - symbol of exponent as only one character: \'e\' otherwise \'E\'");
      System.out.println("   - exponent must be only integer number");
      System.out.printf ("   - maximum exponent a finite number may have %d %n", Double.MAX_EXPONENT);
      System.out.printf ("   - minimum exponent a normalized number may have %d %n", Double.MIN_EXPONENT);
      System.out.printf ("   - the largest positive finite value of number is %e %n", Double.MAX_VALUE);
      System.out.printf ("   - the smallest positive normal value of number is %e %n", Double.MIN_NORMAL);
      System.out.printf ("   - the smallest positive nonzero value of number is %e %n", Double.MIN_VALUE);
      System.out.println("   Examples of accepted number formats are in quotes: ");
      System.out.println("   \'+1E2\' \'1e7\' \'-5e-1\' \'+3.783E-21\' \'3.38e67\' \'-2.7e+7\' \'00.00e3\' \'0e+0\' \'-0.00E4\' \'+023.783e-00\' \'+0005E+04\'");
      
      System.out.println("   Space, tabulator are NOT skipped - entered space or tabulator get incorrect data"); 
      System.out.println("   Answers to questions about cost (fees) values entered by User should be ");
      System.out.println("   in the same currency (USD, EUR, PLN or other) but entering name of currency is forbidden");
            
      Scanner input              = new Scanner(System.in);     
      input.useDelimiter("\n");
      
      System.out.print("Enter number of average driving kilometers per day: ");
      
      boolean isCorrectInputData = input.hasNextDouble();   // to check input data
      if (false == isCorrectInputData) {
            System.err.println("First value entered by User is incorrect"); 
      }
      if (true == isCorrectInputData) {
      
         double drivingKilometers = input.nextDouble();
         if (0 > drivingKilometers) {
            System.err.println("Number of driving kilometers per day can not be less than zero");
         }
         if (0 <= drivingKilometers) {
            System.out.print("Enter number of average cost for fuel per litre: ");
            
            isCorrectInputData = input.hasNextDouble();
            if (false == isCorrectInputData) {
               System.err.println("Second value entered by User is incorrect"); 
            }
            if (true == isCorrectInputData) {
         
               double fuelCost = input.nextDouble();
               if (0 > fuelCost) {
                  System.err.println("Number of cost for fuel per litre can not be less than zero");
               }
               if (0 <= fuelCost) {
                  System.out.print("Enter number of average fuel usage in litres per 100 kilometers: ");
                  
                  isCorrectInputData = input.hasNextDouble();
                  if (false == isCorrectInputData) {
                     System.err.println("Third value entered by User is incorrect"); 
                  }
                  if (true == isCorrectInputData) {
                  
                     double fuelUsage = input.nextDouble();
                     if (0 >= fuelUsage) {
                        System.err.println("Number of fuel usage in litres per 100 kilometers can not be less than zero or equal zero");
                     }
                     if (0 < fuelUsage) {
                        System.out.print("Enter number of average parking fees per day: ");
                  
                        isCorrectInputData = input.hasNextDouble();
                        if (false == isCorrectInputData) {
                           System.err.println("Fourth value entered by User is incorrect"); 
                        }
                        if (true == isCorrectInputData) {
                        
                           double parkingFees = input.nextDouble();
                           if (0 > parkingFees) {
                              System.err.println("Number of parking fees per day can not be less than zero");
                           }
                           if (0 <= parkingFees) {
                              System.out.print("Enter number of average other driving fees per day: ");
                        
                              isCorrectInputData = input.hasNextDouble();
                              if (false == isCorrectInputData) {
                                 System.err.println("Fifth value entered by User is incorrect"); 
                              }
                              if (true == isCorrectInputData) {
                              
                                 double otherFees = input.nextDouble();
                                 if (0 > otherFees) {
                                    System.err.println("Number of other driving fees per day can not be less than zero");
                                 }
                                 if (0 <= otherFees) {
                                    
                                    double drivingCost = drivingKilometers / 100 * fuelUsage * fuelCost + parkingFees + otherFees;
                                    System.out.printf (" Daily driving cost is %.2f %n", drivingCost);
                                 }
                              }
                           }
                        }
                        
                     }
                  }
               }
            }
         }
      }
 
      input.close();
   }
   
}
