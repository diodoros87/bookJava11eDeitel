/* =====================================================================================
 *       Filename:  VariableLengthArgumentsList.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.14 - calculate of integers product by method
                                using variable-length arguments list
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class VariableLengthArgumentsList {

   public static long product(short... integers) throws Exception {
      if (integers.length < 2) {
         throw new Exception("number of factors in multiplication can not be less than 2");
      }
      
      long product = 1; 

      // calculate product using the enhanced for statement
      for (short factor : integers) {
         product *= factor;           
      }

      return product;
   } 

   public static void main(String[] args) throws Exception {
      System.out.printf("*** This program calculate of integers product by method using variable-length arguments list%n");
      
      short integer1 = 10;
      short integer2 = 25;
      short integer3 = 33;
      short integer4 = -24;

      System.out.printf(" integer1 = %3d%n integer2 = %3d%n integer3 = %3d%n integer4 = %3d%n%n",
         integer1, integer2, integer3, integer4);

      System.out.printf("Product of %d and %d is %d%n", integer1, integer2,
         product(integer1, integer2)); 
      System.out.printf("Product of %d, %d and %d is %d%n", integer1, integer2, integer3,
         product(integer1, integer2, integer3));
      System.out.printf("Product of %d, %d, %d and %d is %d%n", integer1, integer2, integer3,
         integer4, product(integer1, integer2, integer3, integer4));
         
      
      System.out.printf("Product of %d is %d%n", integer1, product(integer1));
      System.out.printf("Product of nothing is %d%n", product());
   } 
   
} 
