/* =====================================================================================
 *       Filename:  ExceptionB.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 11.17 - ExceptionB class 
                                inherits from ExceptionA
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ExceptionB extends ExceptionA {
   
   public ExceptionB() {
      
   }    
   
   public ExceptionB(String message) {
      super(message);
   } 
   
   public ExceptionB(Throwable cause) {
      super(cause);
   }    
   
   public ExceptionB(String message, Throwable cause) {
      super(message, cause);
   }                                                             
} 
