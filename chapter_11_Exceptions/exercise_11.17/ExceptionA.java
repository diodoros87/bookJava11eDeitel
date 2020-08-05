/* =====================================================================================
 *       Filename:  BasePlusCommissionExceptionA.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 11.17 - ExceptionA superclass
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ExceptionA extends Exception {
   public ExceptionA() {
      
   }    
   
   public ExceptionA(String message) {
      super(message);
   } 
   
   public ExceptionA(Throwable cause) {
      super(cause);
   }    
   
   public ExceptionA(String message, Throwable cause) {
      super(message, cause);
   } 
} 
