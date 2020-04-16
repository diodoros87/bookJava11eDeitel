/* =====================================================================================
 *       Filename:  GlobalWarmingQuizTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.31 - global warming single choice quiz 
                                                tested by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class GlobalWarmingQuizTest {
                                                
   private static final int abnormalTerminationCode = 1; // status code indicates abnormal termination

   public static void main(String[] args) {
      runQuiz();
   } 
   
   public static short getAnswer() {
      final String quitInfo = String.format(" Enter %d for quit", GlobalWarmingQuiz.quitProgram);
      final String answerInfo = String.format(" Enter integer from %d to %d for answer, only first entered integer will be accepted: ",
                                                GlobalWarmingQuiz.firstAnswer, GlobalWarmingQuiz.fourthAnswer);
      final String inputDataRangeQuit = String.format("To quit enter sequence other than integers in range from %d to %d",
                                                Short.MIN_VALUE, Short.MAX_VALUE);
                                                
      System.out.printf ("  %s %n%n", inputDataRangeQuit);
      
      short answer;
      
      do {
         answer = GettingDataFromStandardInput.getShortInteger(String.format("%s %n%s", quitInfo, answerInfo));
         if (true == GlobalWarmingQuiz.isQuitProgram(answer)) {
            abnormalTermination();
         }
         
      } while (false == GlobalWarmingQuiz.isAvailableAnswer(answer));
               
      return answer;
   }
   
   public static void runQuiz () {
      System.out.println(GlobalWarmingQuiz.welcome);
      System.out.println(GlobalWarmingQuiz.questionsNumberInfo);
      System.out.println(GlobalWarmingQuiz.questionPointInfo);
      System.out.println("After answering all the questions summary will be printed");
      skipToNextStage();
      
      GlobalWarmingQuiz quiz = new GlobalWarmingQuiz();
      mainProgramLoop(quiz);
      quiz.printSummary();
      
   }
   
   public static void mainProgramLoop(GlobalWarmingQuiz quiz) {
      short answer;
      
      for (short questionCounter = 1; questionCounter <= GlobalWarmingQuiz.numberOfQuestions; questionCounter++) {
         GlobalWarmingQuiz.printQuestion(questionCounter);
         GlobalWarmingQuiz.printAnswers(questionCounter);
         answer = getAnswer();
         GlobalWarmingQuiz.printAnswerRating(questionCounter, answer);
         GlobalWarmingQuiz.printCorrectAnswer(questionCounter);
         quiz.calculatePoints(questionCounter, answer);
         skipToNextStage();
      }
   }
   
   public static void skipToNextStage() {
      String isProgramContinue = GettingDataFromStandardInput.getString(String.format
                              ("%n %s %d to quit %n", "***** To continue press ENTER or only", GlobalWarmingQuiz.quitProgram));
      if (((Short)(GlobalWarmingQuiz.quitProgram)).toString().equals(isProgramContinue)) {
         abnormalTermination();
      }
   }
   
   public static void abnormalTermination() {
      System.out.println("****** Quiz is interrupted ");
      System.exit(abnormalTerminationCode);
   }
   
} 
