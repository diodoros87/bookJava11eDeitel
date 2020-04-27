/* =====================================================================================
 *       Filename:  ComputerAidedInstruction.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.38 - class of learning integers
                                (greater or equals zero) multiplication
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;

public class ComputerAidedInstruction {
   public static final String START_INFO = "Program was written to help in learning of multiplication";
   public static final String LEVELS_INFO = "The program has 2 levels for multiplying numbers: 1-digit and 2-digits";
   
   private static final SecureRandom randomNumbers = new SecureRandom();
   
   private static final String YES_0 = "Very good";
   private static final String YES_1 = "Perfectly";
   private static final String YES_2 = "Correctly";
   private static final String YES_3 = "True";
   
   private static final String NO_0 = "Wrong, try again";
   private static final String NO_1 = "No, do not give up";
   private static final String NO_2 = "Incorrect, You have another chance";
   private static final String NO_3 = "False, once more";
   
   private boolean nextLevel = false;
   
   private int firstFactor  = getRandomNumber();
   private int secondFactor = getRandomNumber();
   private int multiplicationResult = this.firstFactor * this.secondFactor;
   
   private String question = String.format("How much is %d times %d? ", this.firstFactor, this.secondFactor);
   private String answer = "";
   
   private int answers  = 0;
   private int correctAnswers = 0;
   private static final int ANSWERS_LIMIT = 10;
   
   private static final String ANSWERS_LIMIT_REACHED = String.format("*** Number of answers reached %d%n %s",
                              ANSWERS_LIMIT, " Let another student to try");
                               
   private void reset(boolean nextLevelPromotion) {
      if (true == isNumberOfAnswersLimitReached()) {
         this.answers = 0;
         this.correctAnswers = 0;
         setNextLevel(nextLevelPromotion);
      }
   }
   
   public void setNextLevel(boolean nextLevelPromotion) {
      this.nextLevel = nextLevelPromotion;
      generateNextFactors();
   }
   
   public boolean getNextLevel() {
      return nextLevel;
   }
   
   private boolean validateFactors(int firstFactor, int secondFactor) {
      if ((this.firstFactor == firstFactor || this.secondFactor == firstFactor) && 
         (this.firstFactor + this.secondFactor == firstFactor + secondFactor)) {
         // to eliminate previous identical factors
         return false;
      }
      
      return true;
   }
   
   private boolean setNextQuestion(int firstFactor, int secondFactor) {
      if (false == validateFactors(firstFactor, secondFactor)) {
         return false;
      }
      
      this.firstFactor  = firstFactor;
      this.secondFactor = secondFactor;
      this.multiplicationResult = this.firstFactor * this.secondFactor;
      this.question = String.format("How much is %d times %d? ", this.firstFactor, this.secondFactor);
      
      return true;
   }
   
   public String getCurrentSummary() {
      return String.format("%n %s: %d   %s: %d %n","correctAnswers", correctAnswers, "all answers", answers);
   }
   
   public String getQuestion() {
      return question;
   }
   
   public String getAnswer() {
      return answer;
   }
   
   public String getLastSummary() {
      if (answers < ANSWERS_LIMIT) {
         return "*** Too early to print learning's summary";
      }
      
      double correctAnswersRatio = 100.0 * correctAnswers / answers;
      
      return getLastSummary(correctAnswersRatio);
   }
   
   private String getLastSummary(double correctAnswersRatio) {
      if (correctAnswersRatio > 75) {
         if (false == nextLevel) {
            reset(true);
            return "Congratulations, go to next level.";
         }
         else {
            reset(false);
            return "Congratulations, let another student to try";
         }
      }
      else {
         reset(false);
         return "Ask the teacher to help in learning. Let another student to try";
      }
   }
   
   public boolean isNumberOfAnswersLimitReached() {
      if (answers >= ANSWERS_LIMIT) {
         return true;
      }
      else {
         return false;
      }
   }
   
   private void setAnswer(boolean isCorrectAnswer) {
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
      if (true == isNumberOfAnswersLimitReached()) {
         System.err.println(ANSWERS_LIMIT_REACHED);
         return false;
      }
      answers++;
      boolean isCorrectResult = this.multiplicationResult == multiplicationResult;
      
      setAnswer(isCorrectResult);
      
      if (true == isCorrectResult) {
         correctAnswers++;
      }
      else {
         return false;
      }
      
      generateNextFactors();   
      
      return true;
   }
   
   private void generateNextFactors() {
      do {
      
      } while(false == setNextQuestion(getRandomNumber(), getRandomNumber()));
   }

   public int getRandomNumber() {
      return nextLevel ? 10 + randomNumbers.nextInt(90) : randomNumbers.nextInt(10);
   } 
} 
