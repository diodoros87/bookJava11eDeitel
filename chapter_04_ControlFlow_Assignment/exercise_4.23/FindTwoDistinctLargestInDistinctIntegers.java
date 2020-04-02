/* =====================================================================================
 *       Filename:  FindTwoDistinctLargestInDistinctIntegers.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.23 - Finding two distinct largest integers in 
                                 10 distinct integers entered by User using
                                    counter-controlled iteration
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class FindTwoDistinctLargestInDistinctIntegers {

   static final short numberOfIntegers = 10;

   public static void main(String[] args) {
      System.out.printf("*** This program finds two distinct largest integers in %d distinct integers entered by User.%n", numberOfIntegers);
      System.out.printf("   - maximum value of entered number can not be more than %d %n", Long.MAX_VALUE);
      System.out.printf("   - minimum value of entered number can not be less than %d %n", Long.MIN_VALUE);
      
      short       counter = 1;
      Long        integer = GettingDataFromStandardInput.getLongIntegerRejectOthersData(
                              String.format("*** %d distinct integers still need to be correctly entered by User %n ", numberOfIntegers),
                              false);
      System.out.printf("%nValue of %d entered by User was accepted. %n", integer);
      
      long        largest               = integer;
      long        nextAfterLargest      = Long.MIN_VALUE;
      String      enteredIntegersString = String.join(integer.toString(), " ", " "); // examples of using: ' 4 ', ' -67 '  
      String      candidateToConcatenateString = "";
      
      while (counter < numberOfIntegers) { 
      
         integer = GettingDataFromStandardInput.getLongIntegerRejectOthersData(
                              String.format("*** %d distinct integers still need to be correctly entered by User %n ", numberOfIntegers - counter),
                              false);

         candidateToConcatenateString = String.join(integer.toString(), " ", " "); // spaces to separate numbers in string 
         if (true == enteredIntegersString.contains(candidateToConcatenateString)) {
            System.err.printf ("%n Integer %d is not distinct than previously entered and can not be accepted. %n", integer); 
         }
         else {
            System.out.printf("%nValue of %d entered by User was accepted. %n", integer);
            enteredIntegersString = enteredIntegersString.concat(candidateToConcatenateString);
            
            if (largest < integer) {
               nextAfterLargest    = largest;
               largest             = integer;
            }
            else if (nextAfterLargest < integer) {
               nextAfterLargest = integer;
            }
            
            counter++;
         }
      }
      
      System.out.printf("*** The largest integer is %d %n", largest);
      System.out.printf("*** Smaller than largest integer but largest than other integers is %d %n", nextAfterLargest);
   } 
   
} 
