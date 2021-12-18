/* =====================================================================================
 *       Filename:  ExceptionsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 11.17 - catching exceptions by Exception class
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.lang.reflect.Method;
import java.util.List;
import java.io.IOException;
 
public class ExceptionsTest {
   public static void main(String[] args) {
      
      catchSubclassExceptionsBySuperclass();
      
      try {
         List<Exception> exceptionsList = createExceptionsList();
         
         catchSubclassExceptionsBySuperclass(exceptionsList);
         catchSubclassExceptionsBySuperclass(null);
      }
      catch (Exception e) {
         System.err.println(e + " handled by Exception object");
         e.printStackTrace();
      }
   }
   
   public static void catchSubclassExceptionsBySuperclass(List<Exception> exceptionsList) {
      if (null == exceptionsList) {
         throw new NullPointerException();
      }
      assert(Thread.currentThread().getStackTrace()[1].getMethodName().equals("catchSubclassExceptionsBySuperclass"));
      printMethodName(Thread.currentThread().getStackTrace()[1].getMethodName());
      int index = 0;
      for (Exception e : exceptionsList) {
         try {
            throw e; 
         } 
         catch (Exception exception) { 
            System.err.printf("%2d. %s handled by Exception object %n", index++, exception);
            exception.printStackTrace();
         } 
      }
   }
   
   public static void catchSubclassExceptionsBySuperclass() {
      assert(Thread.currentThread().getStackTrace()[1].getMethodName().equals("catchSubclassExceptionsBySuperclass"));
      printMethodName(Thread.currentThread().getStackTrace()[1].getMethodName());
         
      try {
         throw new IOException(); 
      } 
      catch (Exception exception) { 
         System.err.println(exception + " handled by Exception object");
         exception.printStackTrace();
      } 
      
      try {
         throw new NullPointerException(); 
      } 
      catch (Exception exception) { 
         System.err.println(exception + " handled by Exception object");
         exception.printStackTrace();
      } 
      
      try {
         throw new ExceptionB(); 
      } 
      catch (Exception exception) { 
         System.err.println(exception + " handled by Exception object");
         exception.printStackTrace();
      } 
      
      try {
         throw new ExceptionA(); 
      } 
      catch (Exception exception) { 
         System.err.println(exception + " handled by Exception object");
         exception.printStackTrace();
      } 
      
      try {
         throw new ExceptionA((ExceptionA)null); 
      } 
      catch (Exception exception) { 
         System.err.println(exception + " handled by Exception object");
         exception.printStackTrace();
      } 
      
      try {
         throw new ExceptionA("String parameter message"); 
      } 
      catch (Exception exception) { 
         System.err.println(exception + " handled by Exception object");
         exception.printStackTrace();
      } 
      
      try {
         throw new ExceptionA("String parameter message", (Throwable)null); 
      } 
      catch (Exception exception) { 
         System.err.println(exception + " handled by Exception object");
         exception.printStackTrace();
      } 
   }
   
   public static List<Exception> createExceptionsList() {
      List<Exception> list = List.of(
         new IOException(), new NullPointerException(),
         new ExceptionA(), new ExceptionA("String parameter message"), 
         new ExceptionA("String parameter message", (Exception)null), new ExceptionA((ExceptionA)null), 
         new ExceptionB(), new ExceptionB("String parameter message"),
         new ExceptionB("String parameter message", (ExceptionB)null), new ExceptionB((Exception)null)
         );
      
      return list;   
   }
   
   public static String getNameOfMethod(String string) {
       String methodName = null;
       
       try {
          Method method = ExceptionsTest.class.getMethod(string);
          methodName = method.getName();
          assert(methodName.equals(string));
       } 
       catch (NoSuchMethodException | NullPointerException e) {
          System.err.println(e);
          e.printStackTrace();
          
          System.exit(1);
       }
       finally {
          System.out.println("Finally in method: " + Thread.currentThread().getStackTrace()[1].getMethodName());
       }  
       
       return methodName;
   }
   
   public static void printMethodName(String string) {
      String methodName = getNameOfMethod(string);
      
      System.out.printf("%n%nIn method \'%s\':%n", methodName);   
   }
} 
