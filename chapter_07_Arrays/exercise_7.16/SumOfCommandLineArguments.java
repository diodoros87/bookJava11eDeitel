/* =====================================================================================
 *       Filename:  SumOfCommandLineArguments.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.16 - calculating sum of double values in
                                command-line arguments
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class SumOfCommandLineArguments { 
   
   public static double sum(final String[] ARGS) {
      double sum = 0.0; 
      double potentialIngredient;

      // calculate sum using the enhanced for statement
      for (String string : ARGS) {
      
         try {
            potentialIngredient = Double.parseDouble(string);
            
            if (true == ((Double)potentialIngredient).isNaN() || 
               potentialIngredient == Double.POSITIVE_INFINITY ||
               potentialIngredient == Double.NEGATIVE_INFINITY) {
               System.out.printf("Argument %f in command line was skipped during calculate sum of numbers%n", potentialIngredient);   
            }
            else {
               sum += potentialIngredient;
            }
            
         } catch (NumberFormatException numberFormatException) {                  
            System.err.println(numberFormatException);    
            System.out.printf("Argument %s in command line was skipped during calculate sum of numbers%n", string);
         }
                   
      }

      return sum;
   } 

   public static void main(final String[] ARGS) {
      System.out.printf("RESULT: Sum of numbers in command-line arguments is %f %n", sum(ARGS));
   } 
   
} 
