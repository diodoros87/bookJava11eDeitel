/* =====================================================================================
 *       Filename:  DicesRollingFrequency.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.17 - calculating frequency of sum on 
                                dices during rolling 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;
 
public class DicesRollingFrequency {
   public static final SecureRandom randomNumbers = new SecureRandom();

   public static final int MIN = 1;
   public static final int MAX = 6;
   public static final int RANGE = MAX - MIN + 1;
   
   public static final int NUMBER_OF_DICES = 2;
   public static final int FREQUENCY_ARRAY_LENGTH = 1 + NUMBER_OF_DICES * MAX;
   public static final int NUMBER_OF_ROLLING = 360_000;
   
   // roll dice, calculate sum
   public static int rollDices() {
      int sum = 0;
      
      for (int counter = 0; counter < NUMBER_OF_DICES; counter++) {
         sum += MIN + randomNumbers.nextInt(RANGE);
      }

      return sum; 
   } 
   
   public static void main(String[] args) { 
      System.out.printf("*** This program calculate frequency of sum generated during rolling %d dices %n", NUMBER_OF_DICES);
      
      int[] frequency = new int[FREQUENCY_ARRAY_LENGTH];
      
      for (int counter = 0; counter < NUMBER_OF_ROLLING; counter++) {
         ++frequency[rollDices()];
      }
      
      System.out.printf("%nFrequency of sum generated during rolling %d dices has been printed below: %n", NUMBER_OF_DICES);
      System.out.printf("%20s %20s%n", "Sum on dices", "Frequency");
      for (int index = NUMBER_OF_DICES * MIN; index <= NUMBER_OF_DICES * MAX; index++) {
         System.out.printf("%20s %,20d %n", index, frequency[index]);
      }
         
   }
   
} 
