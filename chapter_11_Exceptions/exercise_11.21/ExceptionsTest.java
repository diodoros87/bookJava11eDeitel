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
   }
   
   public static void functionCaller(int index) {
      try {
         throwExceptionToFunctionCaller(index);
      } 
      catch (IllegalFormatConversionException exception) { 
         System.err.println(exception);
         exception.printStackTrace();
      }
      catch (ArrayIndexOutOfBoundsException exception) { 
         System.err.println(exception);
         exception.printStackTrace();
      }
   }
   
   public static void throwExceptionToFunctionCaller(int index) {
      int array[] = { 4, 6, 7 };
      
      try {
         System.out.printf("%nIn index %d is %d", index, array[index]);
      }
      catch (ArrayIndexOutOfBoundsException exception) { 
         System.err.println(exception);
         exception.printStackTrace();
      }
      
      System.out.printf("%nIn index %f is %d", index, array[index]);
   }
   
} 
