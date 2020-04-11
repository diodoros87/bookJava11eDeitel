/* =====================================================================================
 *       Filename:  OddIntegersProduct.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.12 - calculate of odd integers product
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class OddIntegersProduct {

   public static void main(String[] args) {
   
      final int begin = 1;
      final int end = 15;
      
      System.out.printf("****** This program calculate of odd integers product from %d to %d %n", begin, end);
      
      int product = begin;
      
      for (int counter = begin + 2; counter <= end; counter += 2) { 
         product *= counter;
         System.out.printf("*** Product of odd integers from %d to %d is %d %n", begin, counter, product);
      }
     
      //System.out.printf("*** Product of odd integers from %d to %d is %d %n", begin, end, product);
   } 
   
} 
