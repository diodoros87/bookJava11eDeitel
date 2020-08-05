/* =====================================================================================
 *       Filename:  ExceptionsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 11.20 - rethrowing exception, chained exceptions
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.io.IOException;
 
public class ExceptionsTest {
   public static void main(String[] args) {
      try {
         rethrowException();
      } 
      catch (Exception exception) {
         printDataOfException(exception);
      }
      
      ChainedExceptions.test();
   } 
   
   public static void printDataOfException(Exception exception) {
      if (null == exception) {
         throw new NullPointerException();
      }
      
      exception.printStackTrace(); 

      // obtain the stack-trace information
      StackTraceElement[] traceElements = exception.getStackTrace();
      
      System.out.printf("%nStack trace from getStackTrace for \'%s\':%n", exception);
      Throwable cause = exception.getCause();
      System.out.printf("\tCause by: %s%n", cause == null ? "no cause" : cause);
      
      System.out.printf("%-20s\tFile\t\t\tLine\t%-20s%n", "Class", "Method");
      for (StackTraceElement element : traceElements) {
         System.out.printf("%-20s\t", element.getClassName());
         System.out.printf("%s\t", element.getFileName());
         System.out.printf("%s\t", element.getLineNumber());
         System.out.printf("%-20s%n", element.getMethodName());
      }
   }
   
   public static void printDataOfExceptionAndCauses(Exception exception) {
      Throwable throwableObject = exception;
      
      while (null != throwableObject) {
         if (throwableObject instanceof Exception) {
            printDataOfException((Exception)throwableObject);
         }
         
         throwableObject = throwableObject.getCause();
      }
   }

   public static void rethrowException() throws Exception {
      try { 
         throwException();
      } 
      catch (Exception exception) { // catch exception thrown in try
         System.err.println("Exception handled in method rethrowException");
         throw exception; // rethrow for further processing
      } 
   }

   public static void throwException() throws Exception {
      throw new Exception("Exception thrown in throwException");
   }  
} 



class ChainedExceptions {
   public static void test() {
      try {
         System.out.printf("%n%nTest of chained exceptions:%n");
         method1(); 
      } 
      catch (Exception exception) { 
         ExceptionsTest.printDataOfExceptionAndCauses(exception);
      } 
   } 

   public static void method1() {
      try {
         method2(); 
      } 
      catch (IOException exception) { 
         throw new SecurityException("Exception thrown in method1", exception);
      } 
   }

   public static void method2() throws IOException {
      try {
         method3();
      } 
      catch (NullPointerException exception) { 
         throw new IOException("Exception thrown in method2", exception);
      }
   } 

   public static void method3() {
      throw new NullPointerException("Exception thrown in method3");
   } 
}
