/* =====================================================================================
 *       Filename:  ComputerAidedInstruction.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.36 - class of learning one-digit integers
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
   
   private static final String YES_0 = "Very good";
   private static final String YES_1 = "Perfectly";
   private static final String YES_2 = "Correctly";
   private static final String YES_3 = "True";
   
   private static final String NO_0 = "Wrong, try again";
   private static final String NO_1 = "No, do not give up";
   private static final String NO_2 = "Incorrect, You have another chance";
   private static final String NO_3 = "False, once more";
   
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
   
   public void setAnswer(boolean isCorrectAnswer) {
      switch (randomNumbers.nextInt(4)) {
         case 0:
            answer = true == isCorrectAnswer ? YES_0 : NO_0;
            break;
         case 1:
            answer = true == isCorrectAnswer ? YES_1 : NO_1;
            break;
         case 2:
            answer = true == isCorrectAnswer ? YES_2 : NO_2;
            break;
         case 3:
            answer = true == isCorrectAnswer ? YES_3 : NO_3;
            break;
         default:
            System.err.println("*** ERROR: This instruction should NOT be executed in method setAnswer");
      }
   }
   
   public boolean isCorrectResult(int multiplicationResult) {
      if (this.multiplicationResult != multiplicationResult) {
         setAnswer(false);
         return false;
      }
      
      setAnswer(true);
      
      do {
         
      } while(false == setFactors(getRandomDigit(), getRandomDigit()));
      
      return true;
   }

   public static int getRandomDigit() {
      return randomNumbers.nextInt(10);
   } 
} 
