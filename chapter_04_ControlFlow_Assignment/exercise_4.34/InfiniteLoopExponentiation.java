/* =====================================================================================
 *       Filename:  InfiniteLoopExponentiation.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.34 - print results of exponentiation in 
                                 infinite loop
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */ 

public class InfiniteLoopExponentiation {   

   public static void main(String[] args) {
   
      final short base = 2;
      long result = base;
      
      while (true) {
         System.out.printf("%d  ", result);
         result *= base;
      }
      
//       while (result <= 999999999) {
//          System.out.printf("%d  ", result);
//          result *= base;
//       }
		
   }
   
}
