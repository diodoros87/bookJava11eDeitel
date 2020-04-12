/* =====================================================================================
 *       Filename:  PythagoreanTriple.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.21 - search pythagorean triples from 1 to 500
                                for all 3 integers greater than zero
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PythagoreanTriple {

   public static void main(String[] args) {
   
      final int begin = 1;   
      final int last = 500;
      
      System.out.printf("****** This program search pythagorean triples from %d to %d %n%n",
                              begin, last);
      
      int firstNumber, firstSquare;
      int secondNumber, secondSquare;
      int thirdNumber, thirdSquare;
      int triplesCounter = 0;
      
      for (firstNumber = begin; firstNumber <= last; firstNumber++) {
         for (secondNumber = begin; secondNumber <= last; secondNumber++) {
            for (thirdNumber = begin; thirdNumber <= last; thirdNumber++) {
            
               firstSquare = firstNumber * firstNumber;
               secondSquare = secondNumber * secondNumber;
               thirdSquare = thirdNumber * thirdNumber;
               
               if (firstSquare == secondSquare + thirdSquare ||
                   secondSquare == firstSquare + thirdSquare ||
                   thirdSquare == secondSquare + firstSquare ) {
                   
                  System.out.printf("%5d >>>  %5d %5d %5d %n",++triplesCounter, firstNumber, secondNumber, thirdNumber);
               }
            }
         }
      }
      
      System.out.printf("*** Result of search pythagorean triples from %d to %d according to algorithm of brute force is above%n%n",
                              begin, last);
                              
                              
      
      triplesCounter = 0;
      
      for (firstNumber = begin; firstNumber <= last; firstNumber++) {
         for (secondNumber = begin; secondNumber < firstNumber; secondNumber++) {
            for (thirdNumber = begin; thirdNumber < secondNumber; thirdNumber++) {
            
               firstSquare = firstNumber * firstNumber;
               secondSquare = secondNumber * secondNumber;
               thirdSquare = thirdNumber * thirdNumber;
               
               if (firstSquare == secondSquare + thirdSquare) {
                  System.out.printf("%5d >>>  %5d %5d %5d %n",++triplesCounter, firstNumber, secondNumber, thirdNumber);
               }
            }
         }
      }
      
      System.out.printf("*** Result of search unique pythagorean triples from %d to %d is above%n%n",
                              begin, last);
     
   } 
   
} 
