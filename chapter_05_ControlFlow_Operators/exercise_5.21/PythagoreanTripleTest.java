/* =====================================================================================
 *       Filename:  PythagoreanTripleTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.21 - test of search pythagorean triples
                                for all 3 integers greater than zero
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PythagoreanTripleTest {

   public static void main(String[] args) {
   
      final int begin = 1;   
      final int last = 500;
      
      PythagoreanTriple.printPythagoreanTripleBruteForce(begin, last);
      PythagoreanTriple.printUniquePythagoreanTriple(begin, last);
      PythagoreanTriple.printUniquePythagoreanTripleSortedAsc(begin, last);
     
   } 
   
} 
