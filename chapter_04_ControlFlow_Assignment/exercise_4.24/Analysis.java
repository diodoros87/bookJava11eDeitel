/* =====================================================================================
 *       Filename:  Analysis.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.24 - Analysis of examination results using 
                                nested control statements with validation results
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;

public class Analysis {

   public static void main(String[] args) {
   
      final short numberOfStudents = 10;
      System.out.printf("*** This program calculate passes and failures of results %d students.%n", numberOfStudents);

      // initializing variables in declarations
      short passes = 0; 
      short failures = 0;
      short studentCounter = 1; 
      short result = 0;

      // process 10 students using counter-controlled loop
      while (studentCounter <= numberOfStudents) {
         // prompt user for input and obtain value from user
         result = GettingDataFromStandardInput.getShortInteger("Enter student's result (1 = pass, 2 = fail): ");

         // if...else is nested in the while statement           
         if (result == 1) {         
            passes++; 
            // increment studentCounter so loop eventually terminates
            studentCounter++;
         }
         else if (result == 2) {                       
            failures++; 
            // increment studentCounter so loop eventually terminates
            studentCounter++;
         }
         else {
            System.err.printf("Value of %d can not be correct result of student.%n", result);
            System.err.printf("Acceptable results are: 1 and 2%n");
         }
      } 

      // termination phase; prepare and display results
      System.out.printf("Passed: %d%nFailed: %d%n", passes, failures);

      // determine whether more than 8 students passed
      if (passes > 8) {
         System.out.println("Bonus to instructor!");
      }
   } 
}
