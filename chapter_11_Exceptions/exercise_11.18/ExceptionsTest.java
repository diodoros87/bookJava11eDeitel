/* =====================================================================================
 *       Filename:  ExceptionsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 11.18 - order of exceptions catching in classes
                             relating by inheritance
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.io.IOException;
import java.io.FileNotFoundException;
 
public class ExceptionsTest {
   public static void main(String[] args) {
      
      Exception[] exceptionsArray = { new Exception(), new IOException(), new FileNotFoundException() };
      
      catchSubclassExceptionsBySuperclass(exceptionsArray);
      
      try {
         throw new FileNotFoundException(); 
      } 
      catch (FileNotFoundException exception) { 
         System.err.println(exception + " handled by FileNotFoundException object");
         exception.printStackTrace();
      }
      
      try {
         throw new FileNotFoundException(); 
      } 
      catch (IOException exception) { 
         System.err.println(exception + " handled by IOException object");
         exception.printStackTrace();
      }
       
   }
   
   public static void catchSubclassExceptionsBySuperclass(Exception[] exceptionsArray) {
      if (null == exceptionsArray) {
         throw new NullPointerException();
      }
      
      int index = 0;
      for (Exception e : exceptionsArray) {
         try {
            throw e; 
         } 
         catch (FileNotFoundException exception) { 
            PrintException.printException(e, exception);
         }
         catch (IOException exception) { 
            PrintException.printException(e, exception);
         }
         catch (Exception exception) { 
            PrintException.printException(e, exception);
         } 
      }
   }

}

class PrintException {
   private static int index = 0;
   static void printException(Exception throwed, Exception handledBy) {
      if (null ==  throwed || null ==  handledBy) {
         throw new NullPointerException();
      }
      System.err.printf("%2d. %s handled by %s %n", index++, throwed, handledBy);
      handledBy.printStackTrace();
   }
} 
