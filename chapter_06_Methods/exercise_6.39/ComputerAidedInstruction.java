/* =====================================================================================
 *       Filename:  ComputerAidedInstruction.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.39 - class of learning arithmetic operations 
                                 of integers (greater or equals zero) 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ComputerAidedInstruction {
   public static final String START_INFO = "Program was written to help in learning of arithmetic operations";
   public static final String LEVELS_INFO = String.format("%s: %n %s",
                              "The program has 2 levels for arithmetic operations non-negative integers", "1-digit and 2-digits");
                              
   public static final String ADDITION_STRING = "addition";
   public static final String SUBTRACTION_STRING = "subtraction";
   public static final String MULTIPLICATION_STRING = "multiplication";
   public static final String DIVISION_STRING = "division";
   public static final String MIXED_STRING = "mixed arithmetic operations";
   
   public static final String ARITHMETIC_MODES_INFO = String.format("%s: %n %s, %s, %s, %s, %s of last mentioned%n",
                              "The program has 5 different modes of arithmetic operations", ADDITION_STRING, 
                              SUBTRACTION_STRING, MULTIPLICATION_STRING, DIVISION_STRING, MIXED_STRING);
   
   private static final String YES_0 = "Very good";
   private static final String YES_1 = "Perfectly";
   private static final String YES_2 = "Correctly";
   private static final String YES_3 = "True";
   
   private static final String NO_0 = "Wrong, try again";
   private static final String NO_1 = "No, do not give up";
   private static final String NO_2 = "Incorrect, You have another chance";
   private static final String NO_3 = "False, once more";
   
   private ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
   
   private String answer = "";
   
   private int allAnswers  = 0;
   private int correctAnswers = 0;
   private static final int ANSWERS_LIMIT = 10;
   
   private static final String ANSWERS_LIMIT_REACHED = String.format("*** Number of answers reached %d%n %s",
                              ANSWERS_LIMIT, " Let another student to try");
                              
   ArithmeticOperations getArithmeticOperations() {
      return this.arithmeticOperations;
   }
                               
   private void reset(boolean isNextLevel) {
      if (true == isNumberOfAnswersLimitReached()) {
         this.allAnswers = 0;
         this.correctAnswers = 0;
         arithmeticOperations.setNextLevel(isNextLevel);
      }
   }
   
   public String generateCurrentSummary() {
      return String.format("%n %s: %d   %s: %d %n","correctAnswers", correctAnswers, "all answers", allAnswers);
   }
   
   public String generateQuestion() {
      return String.format("How much is %d %s %d? ", arithmeticOperations.getFirstElement(),
                                    getArithmeticDescription(arithmeticOperations.getCurrentArithmeticOperation()),
                                    arithmeticOperations.getSecondElement());
   }
   
   private String getArithmeticDescription(int operation) {
      switch (operation) {
         case ArithmeticOperations.ADDITION:
            return  "plus";
         case ArithmeticOperations.SUBTRACTION:
            return  "minus";
         case ArithmeticOperations.MULTIPLICATION:
            return  "multiply by";
         case ArithmeticOperations.DIVISION:
            return  "divided by";
         default:
            System.err.println("*** ERROR: This instruction should NOT be executed in method getArithmeticDescription(int)");
            return  "unknown operation";
      }
   }
   
   public String getAnswer() {
      return this.answer;
   }
   
   private void setAnswer(boolean isCorrectAnswer) {
      switch (ArithmeticOperations.randomNumbers.nextInt(4)) {
         case 0:
            this.answer = true == isCorrectAnswer ? YES_0 : NO_0;
            break;
         case 1:
            this.answer = true == isCorrectAnswer ? YES_1 : NO_1;
            break;
         case 2:
            this.answer = true == isCorrectAnswer ? YES_2 : NO_2;
            break;
         case 3:
            this.answer = true == isCorrectAnswer ? YES_3 : NO_3;
            break;
         default:
            System.err.println("*** ERROR: This instruction should NOT be executed in method setAnswer");
      }
   }
   
   public String generateLastSummary() {
      if (this.allAnswers < ANSWERS_LIMIT) {
         return "*** Too early to print learning's summary";
      }
      
      double correctAnswersRatio = 100.0 * correctAnswers / allAnswers;
      
      return generateLastSummary(correctAnswersRatio);
   }
   
   private String generateLastSummary(double correctAnswersRatio) {
      if (correctAnswersRatio > 75) {
         if (false == arithmeticOperations.getNextLevel()) {
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
      if (this.allAnswers >= ANSWERS_LIMIT) {
         return true;
      }
      else {
         return false;
      }
   }
   
   public boolean isCorrectResult(int arithmeticResult) {
      if (true == isNumberOfAnswersLimitReached()) {
         System.err.println(ANSWERS_LIMIT_REACHED);
         return false;
      }
      
      this.allAnswers++;
      boolean isCorrectResult = arithmeticOperations.getArithmeticResult() == arithmeticResult;
      
      setAnswer(isCorrectResult);
      
      if (true == isCorrectResult) {
         this.correctAnswers++;
      }
      else {
         return false;
      }
      
      arithmeticOperations.generateNextResult();
      
      return true;
   }
    
} 
