/* =====================================================================================
 *       Filename:  GlobalWarmingQuizMultipleChoice.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.31 - class of multiple choice global warming quiz
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class GlobalWarmingQuizMultipleChoice {

   public static final String welcome = "###   Welcome in global warming multiple choice quiz   ###";
   public static final String questionPointInfo = " For each correct answer to the question You will receive 1 point";
   public static final String manyCorrectAnswersPointInfo = " In case of question with more than one correct answers " + 
                                                   "You will receive 1 point only for all correct answers, otherwise no point";
   
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
   public static final String questionsNumberInfo = String.format(" In this quiz are %d questions ", numberOfQuestions);
   
   public static final String tabulator = "\t";
   public static final String connector = " is ";
   
   private short points = 0;
   
   private static String firstQuestionCorrectAnswers = "";
   private static String secondQuestionCorrectAnswers = "";
   private static String thirdQuestionCorrectAnswers = "";
   private static String fourthQuestionCorrectAnswers = "";
   private static String fifthQuestionCorrectAnswers = "";
   
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
      System.out.printf("%n%n##################       Question number %d:%n", questionNumber);
      
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
            System.out.println(fourthAnswer + ". Nothing of the above ");
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
            System.out.println(thirdAnswer + ". Water vapor ");
            System.out.println(fourthAnswer + ". Nothing of the above ");
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
   
   public void printAnswerRating (short questionNumber, boolean firstSelection, boolean secondSelection,
                                                               boolean thirdSelection, boolean fourthSelection) {
      System.out.printf("********* Selection for the question is %B %n",
      areCorrectSelections (firstSelection, secondSelection, thirdSelection, fourthSelection, questionNumber, true));
   }
   
   public static void printCorrectAnswers (short questionNumber) {
      String answers = "";
      
      switch (questionNumber) {
         case firstQuestion:
            answers = firstQuestionCorrectAnswers;
            break;
         case secondQuestion:
            answers = secondQuestionCorrectAnswers;
            break;
         case thirdQuestion:
            answers = thirdQuestionCorrectAnswers;
            break;
         case fourthQuestion:
            answers = fourthQuestionCorrectAnswers;
            break;
         case fifthQuestion:
            answers = fifthQuestionCorrectAnswers;
            break;
         default:
            System.err.println("No question with number " + questionNumber);
      }
      
      System.out.printf("For the question %d correct ", questionNumber);
      
      if (answers.indexOf(tabulator) > 1) {
         System.out.printf("answers are: %n\'%s\' ", answers);
      }
      else {
         System.out.printf("answer is: %n\'%s\' ", answers);
      }
   }

   public boolean areCorrectSelections (boolean firstSelection, boolean secondSelection, boolean thirdSelection,
                                             boolean fourthSelection, short questionNumber, boolean isToString) {
      boolean answer1st = false;
      boolean answer2nd = false;
      boolean answer3rd = false;
      boolean answer4th = false;
      
      switch (questionNumber) { 
         case firstQuestion:
            answer1st = true;
            if (true == isToString) {
               firstQuestionCorrectAnswers = firstAnswer + connector + true;
            }
            break;
      
         case secondQuestion:
            answer1st = true;
            answer2nd = true;
            answer3rd = true;
            
            if (true == isToString) {
               secondQuestionCorrectAnswers  = firstAnswer + connector + true;
               secondQuestionCorrectAnswers += tabulator + secondAnswer + connector + true;
               secondQuestionCorrectAnswers += tabulator + thirdAnswer + connector + true;
            }
            break;
         
         case thirdQuestion:
            answer3rd = true;
            if (true == isToString) {
               thirdQuestionCorrectAnswers = thirdAnswer + connector + true;
            }
            break;
            
         case fourthQuestion:
            answer1st = true;
            answer2nd = true;
            answer3rd = true;
            
            if (true == isToString) {
               fourthQuestionCorrectAnswers  = firstAnswer + connector + true;
               fourthQuestionCorrectAnswers += tabulator + secondAnswer + connector + true;
               fourthQuestionCorrectAnswers += tabulator + thirdAnswer + connector + true;
            }
            break;
            
         case fifthQuestion:
            answer2nd = true;
            if (true == isToString) {
               fifthQuestionCorrectAnswers = secondAnswer + connector + true;
            }
            break;

         default:
            System.err.println("No question with number " + questionNumber);
            return false;
      }
      
      if (answer1st == firstSelection) {
         if (answer2nd == secondSelection) {
            if (answer3rd == thirdSelection) {
               if (answer4th == fourthSelection) {
                  points++; //  number of points increase for correct selections
                  return true;
               }
            }
         }
      }
         
      return false;
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
