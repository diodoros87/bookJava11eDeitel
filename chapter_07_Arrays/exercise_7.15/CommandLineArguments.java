/* =====================================================================================
 *       Filename:  CommandLineArguments.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.15 - trying of initializing an array using
                                command-line arguments
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class CommandLineArguments { 
   public static final int ARRAY_DEFAULT_SIZE = 10;

   public static void main(final String[] ARGS) {
      System.out.printf("*** This program tries of initializing an array using command-line arguments%n");

      // get array size from first command-line argument
      final int ARRAY_LENGTH = getSizeOfArray(ARGS);
      final int[] ARRAY = new int[ARRAY_LENGTH];
      
      System.out.printf("Content of ARRAY has been printed below:");
      System.out.println();
      System.out.printf("index %7s%n", "value");
      for (int index = 0; index < ARRAY.length; index++) {
         System.out.printf("%5d%8d%n", index, ARRAY[index]);
      }
      
   } 
   
   public static int getSizeOfArray(final String[] ARGUMENTS) {
      int sizeOfArray = ARRAY_DEFAULT_SIZE;
      
      try {
         sizeOfArray = Integer.parseInt(ARGUMENTS[0]); 
      } 
      catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {                  
         System.err.println(arrayIndexOutOfBoundsException);   
         System.out.printf("Array will be initialized with default size of %d%n", sizeOfArray);
      }
      catch (NumberFormatException numberFormatException) {                  
         System.err.println(numberFormatException);    
         System.out.printf("Array will be initialized with default size of %d%n", sizeOfArray);
      }
      
      if (0 >= sizeOfArray) {
         System.err.println("First argument in command line has incorrect value of " + ARGUMENTS[0]);
         sizeOfArray = ARRAY_DEFAULT_SIZE;
         System.out.printf("Array will be initialized with default size of %d%n", sizeOfArray);
      }
      
      return sizeOfArray;
   }
   
} 
