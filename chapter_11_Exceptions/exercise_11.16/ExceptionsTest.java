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
 
import java.lang.reflect.Method;
 
public class ExceptionsTest {
   public static void main(String[] args) {
      throwCatchIdenticalException();
      throwCatchSubclassBySuperclass();
   }
   
   public static void throwCatchIdenticalException() {
      printMethodName("throwCatchIdenticalException");
         
      try {
         throw new ExceptionC(); 
      } 
      catch (ExceptionC exception) { 
         System.err.println("C handled by C: " + exception);
      } 
      
      try {
         throw new ExceptionB(); 
      } 
      catch (ExceptionB exception) { 
         System.err.println("B handled by B: " + exception);
      } 
      
      try {
         throw new ExceptionA(); 
      } 
      catch (ExceptionA exception) { 
         System.err.println("A handled by A: " + exception);
      } 
      
      try {
         throw new Exception(); 
      } 
      catch (Exception exception) { 
         System.err.println("Exception object handled by Exception object: " + exception);
      } 
   }
   
   public static void throwCatchSubclassBySuperclass() {
      printMethodName("throwCatchSubclassBySuperclass");
      
      try {
         throw new ExceptionC(); 
      } 
      catch (ExceptionB exception) { 
         System.err.println("C handled by B: " + exception);
      } 
      
      try {
         throw new ExceptionC(); 
      } 
      catch (ExceptionA exception) { 
         System.err.println("C handled by A: " + exception);
      } 
      
      try {
         throw new ExceptionC(); 
      } 
      catch (Exception exception) { 
         System.err.println("C handled by Exception object: " + exception);
      } 
      
      try {
         throw new ExceptionB(); 
      } 
      catch (ExceptionA exception) { 
         System.err.println("B handled by A: " + exception);
      } 
      
      try {
         throw new ExceptionB("String parameter"); 
      } 
      catch (Exception exception) { 
         System.err.println("B handled by Exception object: " + exception);
      } 
      
      try {
         throw new ExceptionA("String parameter"); 
      } 
      catch (Exception exception) { 
         System.err.println("A handled by Exception object: " + exception);
      } 
   }
   
   public static String getNameOfMethod(String string) {
       String methodName = null;
       
       try {
          Method method = ExceptionsTest.class.getMethod(string);
          methodName = method.getName();
       } 
       catch (NoSuchMethodException | NullPointerException e) {
          System.err.println(e);
          e.printStackTrace();
          
          System.exit(1);
       }
       finally {
          System.out.println("Finally");
       }  
       
       return methodName;
   }
   
   public static void printMethodName(String string) {
      String methodName = getNameOfMethod(string);
      
      System.out.printf("%n%nIn method \'%s\':%n", methodName);   
   }
} 
