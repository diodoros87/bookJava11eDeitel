/* =====================================================================================
 *       Filename:  PythagoreanTriple.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.21 - class of search pythagorean triples
                                for all 3 integers greater than zero
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PythagoreanTriple {
   
   public static void expectedParameters(int begin, int last) {
      expectedGreaterThanValue(begin, 0);
      expectedGreaterThanValue(last, begin);
   } 
   
   public static void expectedGreaterThanValue(int number, int value) {
      assert(number > value) : 
                     String.format("%d <= %d%n", number, value);
   } 
   
   public static boolean parametersValidation(int begin, int last) {
      expectedParameters(begin, last);
      if (begin > 0 && last > begin) {
         return true;
      }
      
      return false;
   }

   public static void printPythagoreanTripleBruteForce(int begin, int last) {
      if (false == parametersValidation(begin, last)) {
         return;
      }
      
      System.out.printf("*** Result of search pythagorean triples from %d to %d according to algorithm of brute force is below%n",
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
   }
                              
   public static void printUniquePythagoreanTriple(int begin, int last) {  
      if (false == parametersValidation(begin, last)) {
         return;
      }
      
      System.out.printf("*** Result of search unique pythagorean triples from %d to %d is below%n",
                              begin, last);
      
      int firstNumber, firstSquare;
      int secondNumber, secondSquare;
      int thirdNumber, thirdSquare;
      int triplesCounter = 0;
      
      for (firstNumber = begin + 2; firstNumber <= last; firstNumber++) {
         for (secondNumber = begin + 1; secondNumber < firstNumber; secondNumber++) {
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
   
   public static void printUniquePythagoreanTripleSortedAsc(int begin, int last) {  
      if (false == parametersValidation(begin, last)) {
         return;
      }
      
      System.out.printf("*** Result of search unique pythagorean triples sorted asc from %d to %d is below%n",
                              begin, last);
      
      int firstNumber, firstSquare;
      int secondNumber, secondSquare;
      int thirdNumber, thirdSquare;
      int triplesCounter = 0;
      
      for (firstNumber = begin; firstNumber <= last - 2; firstNumber++) {
         for (secondNumber = firstNumber + 1; secondNumber <= last - 1; secondNumber++) {
            for (thirdNumber = secondNumber + 1; thirdNumber <= last; thirdNumber++) {
            
               firstSquare = firstNumber * firstNumber;
               secondSquare = secondNumber * secondNumber;
               thirdSquare = thirdNumber * thirdNumber;
               
               if (firstSquare + secondSquare == thirdSquare) {
                  System.out.printf("%5d >>>  %5d %5d %5d %n",++triplesCounter, firstNumber, secondNumber, thirdNumber);
               }
            }
         }
      }
      
      System.out.printf("*** Result of search unique pythagorean triples sorted asc from %d to %d is above%n%n",
                              begin, last);
     
   } 
   
} 
