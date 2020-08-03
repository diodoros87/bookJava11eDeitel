/* =====================================================================================
 *       Filename:  ExceptionsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 11.16 - ExceptionA hierarchy test program
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class ExceptionsTest {
   public static void main(String[] args) {
      
      try {
         throw new ExceptionB(); 
      } 
      catch (ExceptionA exception) { 
         System.err.println("ExceptionB subclass handled by ExceptionA superclass: " + exception);
      } 
      
      try {
         throw new ExceptionC(); 
      } 
      catch (ExceptionA exception) { 
         System.err.println("ExceptionC subclass handled by ExceptionA superclass: " + exception);
      } 
      
      try {
         throw new ExceptionA(); 
      } 
      catch (ExceptionA exception) { 
         System.err.println("ExceptionA superclass handled by ExceptionA superclass: " + exception);
      } 
      
      try {
         throw new ExceptionA(); 
      } 
      catch (Exception exception) { 
         System.err.println("ExceptionA subclass handled by Exception superclass: " + exception);
      } 

   }
} 
