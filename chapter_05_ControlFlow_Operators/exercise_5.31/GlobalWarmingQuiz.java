/* =====================================================================================
 *       Filename:  GlobalWarmingQuiz.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.31 - class of single choice global warming quiz
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class GlobalWarmingQuiz {

   public static final String welcome = "###   Welcome in global warming single choice quiz   ###";
   public static final String questionPointInfo = " For each correct answer to the question You will receive 1 point";
   
   public static final short quitProgram = 9;
   
   public static final short firstAnswer  = 1;
   public static final short secondAnswer = 2;
   public static final short thirdAnswer  = 3;
   public static final short fourthAnswer = 4;
   
   public static final short firstQuestion  = 1;
   public static final short secondQuestion = 2;
   public static final short thirdQuestion  = 3;
   public static final short fourthQuestion = 4;
   public static final short fifthQuestion  = 5;
   
   public static final short numberOfQuestions = 5;
   public static final String questionsNumberInfo = String.format(" In this quiz is %d questions ", numberOfQuestions);
   
   private short points = 0;
   
   public short getPoints () {
      return points;
   }
   
   public static boolean isQuitProgram (short answerNumber) {
      if (quitProgram == answerNumber) {
         return true;
      }
      
      return false;
   }
   
   public static boolean isAvailableAnswer (short answerNumber) {
      switch (answerNumber) { 
         case firstAnswer:
         case secondAnswer:
         case thirdAnswer:
         case fourthAnswer:
            return true;

         default:
            System.err.println("No answer with number " + answerNumber);
            return false;
      }
   }
   
   public static void printQuestion (short questionNumber) {
      System.out.println("Question number " + questionNumber);
      
      switch (questionNumber) {
         case firstQuestion:
            System.out.println("Which of these countries emits the most carbon dioxide?");
            break;
         case secondQuestion:
            System.out.println("What can you do to help fight climate change?");
            break;
         case thirdQuestion:
            System.out.println("What is the Greenhouse Effect?");
            break;
         case fourthQuestion:
            System.out.println("Which of the following is a greenhouse gas?");
            break;
         case fifthQuestion:
            System.out.println("What percentage of the global greenhouse gas emissions does the transportation sector emit?");
            break;
         default:
            System.err.println("No question with number " + questionNumber);
      }
      
   }
   
   public static void printAnswers (short questionNumber) {
      
      switch (questionNumber) {
         case firstQuestion:
            System.out.println(firstAnswer + ". China");
            System.out.println(secondAnswer + ". USA");
            System.out.println(thirdAnswer + ". UK");
            System.out.println(fourthAnswer + ". Russia");
            break;
         case secondQuestion:
            System.out.println(firstAnswer + ". Divest from fossil fuel companies ");
            System.out.println(secondAnswer + ". Engage yourself in the science behind climate change");
            System.out.println(thirdAnswer + ". Vote for political candidates who will advocate for climate-related legislation" + 
                                                " and policy improvements");
            System.out.println(fourthAnswer + ". All of the above ");
            break;
         case thirdQuestion:
            System.out.println(firstAnswer + ". The name of climate change legislation that passed by congress ");
            System.out.println(secondAnswer + ". When you paint your house green to become an environmentalist ");
            System.out.println(thirdAnswer + ". When the gasses in our atmosphere trap heat and block it from escaping our planet ");
            System.out.println(fourthAnswer + ". When you build a greenhouse ");
            break;
         case fourthQuestion:
            System.out.println(firstAnswer + ". CO2 ");
            System.out.println(secondAnswer + ". CH4");
            System.out.println(thirdAnswer + ".  Water vapor ");
            System.out.println(fourthAnswer + ".  All of the above ");
            break;
         case fifthQuestion:
            System.out.println(firstAnswer + ". 1% ");
            System.out.println(secondAnswer + ". 14% ");
            System.out.println(thirdAnswer + ". 33% ");
            System.out.println(fourthAnswer + ". 70% ");
            break;
         default:
            System.err.println("No question with number " + questionNumber);
      }
      
   }
   
   public static void printAnswerRating (short questionNumber, short answerNumber) {
      System.out.printf("Answer for the question is %b %n", isCorrectAnswer (questionNumber, answerNumber));
   }
   
   public static void printCorrectAnswer (short questionNumber) {
      System.out.printf("Correct answer for the question is answer number %d %n", getCorrectAnswer (questionNumber));
   }
   
   public static short getCorrectAnswer (short questionNumber) {
      
      switch (questionNumber) {
         case firstQuestion:
            return firstAnswer;
         case secondQuestion:
            return fourthAnswer;
         case thirdQuestion:
            return thirdAnswer;
         case fourthQuestion:
            return fourthAnswer;
         case fifthQuestion:
            return secondAnswer;
         default:
            System.err.println("No question with number " + questionNumber);
            return -1;
      }
   }
   
   public static boolean isCorrectAnswer (short questionNumber, short answerNumber) {
      switch (questionNumber) { 
         case firstQuestion:
         case secondQuestion:
         case thirdQuestion:
         case fourthQuestion:
         case fifthQuestion:
            return answerNumber == getCorrectAnswer (questionNumber);

         default:
            System.err.println("No question with number " + questionNumber);
            return false;
      }
   }
   
   public void calculatePoints (short questionNumber, short answerNumber) {
      if (true == isCorrectAnswer(questionNumber, answerNumber)) {
         points++;
      }
   }
   
   public void printSummary () {
      String summary;
      
      if (points == numberOfQuestions) {
         summary = "Perfectly";
      }
      else if (points == numberOfQuestions - 1) {
         summary = "Very good";
      }
      else {
         summary = "It is advisible to know more about global warming";
      }
      
      System.out.println(summary + ": " + points + " points");
   }

}
