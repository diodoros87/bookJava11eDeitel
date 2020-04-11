/* =====================================================================================
 *       Filename:  Factorial.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.13 - class of factorial
                                
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Factorial {
   private static final String arithmeticOverflowError = "Factorial can not be proper calculate due to arithmetic overflow";
   
   private short        integer    = -1;
   private boolean      arithmeticOverflow = false;
      
   public short getInteger () {
      return integer;
   }
   
   public void setInteger (short integer) {
      this.integer = integer;
      arithmeticOverflow = false;
   }
   
   private long getFactorial () {
      long factorial = 1;
      short counter = 2;
      
      while (counter <= integer) {
         if (factorial <= Long.MAX_VALUE / counter) {  // to detect arithmetic overflow
            factorial *= counter;
            counter++;
         }
         else {
            System.err.printf(String.format("*** %s: %s%d%n", arithmeticOverflowError,
                              "while calculating factorial for ", counter));
            arithmeticOverflow = true;
            break;
         }
      }
      
      return factorial;
   }
   
   public void printFactorialResult() {
      if (integer < 0) {
         System.out.printf(" (%d)! = ", integer);
         System.err.println("*** Factorial can not be calculate for integers less than zero");
      }
      else {
         System.out.printf("%5d! = ", integer);
         long result = getFactorial();
         if (false == arithmeticOverflow) {
            System.out.printf("%,d%n", result);
         }
      }
   }
   
   public void printFactorialResults(short first, short last) {
      System.out.printf("%n--- Calculate factorial from %d to %d%n", first, last);
      
      for (short counter = first; counter <= last; counter++) {
         setInteger(counter);
         printFactorialResult();
      }
   }
   
}
