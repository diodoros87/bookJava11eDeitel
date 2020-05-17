/* =====================================================================================
 *       Filename:  FibonacciSequence.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.29b - calculate maximum number in Fibonacci
                                sequence for Java integers types 
                                 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class FibonacciSequence {
   
   public static void main(String[] args){ 
      System.out.printf("*** This program calculate maximum numbers in Fibonacci sequence for Java integers types %n");
      
      fibonacciShort();
      fibonacciInteger();
      fibonacciLong();
   }

   
   public static void fibonacciLong() {
      long temp;
      long lastElement = 1;
      long lastButOneElement = 0;
      int  index = 1; 
      
      System.out.printf("***  index in Fibonacci sequence: 0.   0 %n");
      System.out.printf("***  index in Fibonacci sequence: 1.   1 %n");
      
      do {
         temp = lastButOneElement;
         lastButOneElement = lastElement;
         lastElement      += temp;
         index++;
         System.out.printf("***  index in Fibonacci sequence: %2d.   %d %n",
                     index, lastElement);
      } while (false == isArithmeticOverflow(lastElement, lastButOneElement));
      
      System.out.printf("*** maximum number in Fibonacci sequence for Long type is %,d at index %d in Fibonacci sequence%n%n",
                     lastElement, index);
      System.out.printf("*** Long.MAX_VALUE =  %,d %n%n", Long.MAX_VALUE);
   }
   
   
   public static void fibonacciInteger() {
      int temp;
      int lastElement = 1;
      int lastButOneElement = 0;
      int index = 1; 
      
      do {
         temp = lastButOneElement;
         lastButOneElement = lastElement;
         lastElement      += temp;
         index++;
      } while (false == isArithmeticOverflow(lastElement, lastButOneElement));
      
      System.out.printf("*** maximum number in Fibonacci sequence for Integer type is %,d at index %d in Fibonacci sequence%n%n",
                     lastElement, index);
      System.out.printf("*** Integer.MAX_VALUE =  %,d%n%n", Integer.MAX_VALUE);
   }
   
   public static void fibonacciShort() {
      short temp;
      short lastElement = 1;
      short lastButOneElement = 0;
      short index = 1; 
      
      do {
         temp = lastButOneElement;
         lastButOneElement = lastElement;
         lastElement      += temp;
         index++;
      } while (false == isArithmeticOverflow(lastElement, lastButOneElement));
      
      System.out.printf("*** maximum number in Fibonacci sequence for Short type is %,d at index %d in Fibonacci sequence%n%n",
                     lastElement, index);
      System.out.printf("*** Short.MAX_VALUE =  %,d%n%n", Short.MAX_VALUE);               
   }
   
   public static boolean isArithmeticOverflow(long lastElement, long lastButOneElement) {
      if (lastElement > Long.MAX_VALUE - lastButOneElement) {
         return true;
      }
      
      return false;
   }
   
   public static boolean isArithmeticOverflow(int lastElement, int lastButOneElement) {
      if (lastElement > Integer.MAX_VALUE - lastButOneElement) {
         return true;
      }
      
      return false;
   }
   
   public static boolean isArithmeticOverflow(short lastElement, short lastButOneElement) {
      if (lastElement > Short.MAX_VALUE - lastButOneElement) {
         return true;
      }
      
      return false;
   }
    
} 
