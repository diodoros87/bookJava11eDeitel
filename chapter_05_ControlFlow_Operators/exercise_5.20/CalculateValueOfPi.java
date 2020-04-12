/* =====================================================================================
 *       Filename:  CalculateValueOfPi.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.20 - calculate value of pi
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class CalculateValueOfPi {

   public static void main(String[] args) {
   
      final int numberElements = 200_000;     // case of even number of elements
      final int lastElement = 2 * numberElements - 1;  // in case of even number of elements
      int element = 1;   
      double pi = 0;    
      int counter = 0;
      
      System.out.printf("****** This program calculate value of pi for first %d elements according to Leibniz formula %n", numberElements);
      
      while (element < lastElement) { 
         pi += 4.0 / element;
         element += 2;
         pi -= 4.0 / element;
         element += 2;
         
         counter += 2;
         System.out.printf("%d %.6f ", counter, pi);
      }
     
     System.out.printf("%n%nAfter sum of %d elements pi is %.6f", counter, pi);
     
     printSumOfElements(400_000);
     
   } 

   
   private static void printSumOfElements(long howManyElements) {  
      long numberElements = 0;
      double pi = 0;
      
      while (numberElements < howManyElements) {
         pi += 4.0 / (2 * (++numberElements) - 1);
         pi -= 4.0 / (2 * (++numberElements) - 1);
      }
      
      System.out.printf("%n%n *** After sum of %d elements pi is %.6f %n", numberElements, pi);
   }
   
} 
