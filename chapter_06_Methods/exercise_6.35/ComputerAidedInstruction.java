/* =====================================================================================
 *       Filename:  ComputerAidedInstruction.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.35 - class of learning one-digit integers
                                (greater or equals zero) multiplication
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;

public class ComputerAidedInstruction {
   public static final String START_INFO = "Program was written to help in learning of multiplication";
   
   // create secure random number generator for use in method getRandomDigit
   private static final SecureRandom randomNumbers = new SecureRandom();
   
   private static final String YES = "Very good";
   private static final String NO = "No, try again";
   
   private int firstFactor  = getRandomDigit();
   private int secondFactor = getRandomDigit();
   private int multiplicationResult = this.firstFactor * this.secondFactor;
   
   private String question = String.format("How much is %d times %d? ", this.firstFactor, this.secondFactor);
   private String answer = "";
   
   private boolean setFactors(int firstFactor, int secondFactor) {
      if (firstFactor < 0 || firstFactor > 9) {
         return false;
      }
      if (secondFactor < 0 || secondFactor > 9) {
         return false;
      }
      
      if ((this.firstFactor == firstFactor || this.secondFactor == firstFactor) && 
         this.firstFactor + this.secondFactor == firstFactor + secondFactor) {
         // to eliminate previous identical factors
         return false;
      }
      
      this.firstFactor  = firstFactor;
      this.secondFactor = secondFactor;
      this.multiplicationResult = this.firstFactor * this.secondFactor;
      this.question = String.format("How much is %d times %d? ", this.firstFactor, this.secondFactor);
      
      return true;
   }
   
   public String getQuestion() {
      return question;
   }
   
   public String getAnswer() {
      return answer;
   }
   
   public boolean isCorrectResult(int multiplicationResult) {
      if (this.multiplicationResult != multiplicationResult) {
         this.answer = NO;
         return false;
      }
      
      this.answer = YES;
      
      do {
         
      } while(false == setFactors(getRandomDigit(), getRandomDigit()));
      
      return true;
   }

   public static int getRandomDigit() {
      return randomNumbers.nextInt(10);
   } 
} 
