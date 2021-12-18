/* =====================================================================================
 *       Filename:  ExceptionsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 11.21 - catching exception by function caller
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.IllegalFormatConversionException;
 
public class ExceptionsTest {
   public static void main(String[] args) {
      
      functionCaller(2);
      functionCaller(5); 
      functionCaller(1);
   }
   
   public static void functionCaller(int index) {
      String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
      assert(methodName.equals("functionCaller"));
      try {
         throwExceptionToFunctionCaller(index);
      } 
      catch (IllegalFormatConversionException exception) { 
         System.err.println("In functionCaller() was handled " + exception);
         exception.printStackTrace();
      }
      finally {
         System.out.println("\nFinally in method: " + methodName);
      }
   }
   
   public static void throwExceptionToFunctionCaller(int index) {
      int array[] = { 4, 6, 7 };
      String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
      assert(methodName.equals("throwExceptionToFunctionCaller"));
      try {
         System.out.printf("%nIn index %d is %d%n", index, array[index]);
         System.out.printf("%nIn index %f is %d%n", index, array[index]);
      }
      catch (ArrayIndexOutOfBoundsException exception) { 
         System.err.println("In throwExceptionToFunctionCaller() was handled " + exception);
         exception.printStackTrace();
      }
      finally {
         System.out.println("\n1 Finally in method: " + methodName);
      }
      
      try {
         System.out.printf("%nIn index %f is %d%n", index, array[index]);
      }
      catch (ArrayIndexOutOfBoundsException exception) { 
         System.err.println("In throwExceptionToFunctionCaller() was handled " + exception);
         exception.printStackTrace();
      }
      finally {
         System.out.println("\n2 Finally in method: " + methodName);
      }
      
   }
   
} 
