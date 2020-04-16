/* =====================================================================================
 *       Filename:  GlobalWarmingQuizMultipleChoiceTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.31 - global warming multiple choice quiz 
                                                tested by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class GlobalWarmingQuizMultipleChoiceTest {
                                                
   private static final int abnormalTerminationCode = 1; // status code indicates abnormal termination
   
   private boolean firstSelection = false;
   private boolean secondSelection = false;
   private boolean thirdSelection = false;
   private boolean fourthSelection = false;

   public static void main(String[] args) {
      GlobalWarmingQuizMultipleChoiceTest quizTest = new GlobalWarmingQuizMultipleChoiceTest();
      quizTest.runQuiz(quizTest);
   } 
   
   public static GettingDataFromString getStringScanner() {
   
      final String quitInfo = String.format(" Enter %d for quit", GlobalWarmingQuizMultipleChoice.quitProgram);
      final String incorrectAnswerInfo = String.format("If User will not entering integers from %d to %d",
                                 GlobalWarmingQuizMultipleChoice.firstAnswer, GlobalWarmingQuizMultipleChoice.fourthAnswer) +
                                                       " separated by whitespaces, selection will be incorrect";
      final String ignoredInputDataInfo = "Values of floating-point or string type are ignored";
      final String answerInfo = String.format(" Enter integers separated by whitespaces from %d to %d for answers: ",
                                                GlobalWarmingQuizMultipleChoice.firstAnswer, GlobalWarmingQuizMultipleChoice.fourthAnswer);
      
      GettingDataFromString stringScanner = null;
      String answerString = GettingDataFromStandardInput.getString(String.format("%s.%n %s.%n %s.%n%s",
                                                      quitInfo, incorrectAnswerInfo, ignoredInputDataInfo, answerInfo));
      
      if (null == answerString) {
         abnormalTermination();
      }
      else {
         stringScanner = new GettingDataFromString(answerString);
      }
      
      return stringScanner;
   }
   
   public void getSelections(GlobalWarmingQuizMultipleChoiceTest quizTest) {
      GettingDataFromString stringScanner = getStringScanner();
      short answer;
      
      do {
         answer = stringScanner.getShortIntegerRejectOthersData(true);
         if (true == GlobalWarmingQuizMultipleChoice.isQuitProgram(answer)) {
            abnormalTermination();
         }
         
         if (answer != GettingDataFromString.endOfDataValueOfShort) {
            quizTest.setSelectionToAnswer(answer);
         }
         else {
            break;
         }
         
      } while (true);
               
   }
   
   public void setSelectionToAnswer (short answerNumber) {
      if (false == GlobalWarmingQuizMultipleChoice.isAvailableAnswer(answerNumber)) {
         return;
      }
      
      switch (answerNumber) {
         case GlobalWarmingQuizMultipleChoice.firstAnswer:
            firstSelection = true;
            break;
         case GlobalWarmingQuizMultipleChoice.secondAnswer:
            secondSelection = true;
            break;
         case GlobalWarmingQuizMultipleChoice.thirdAnswer:
            thirdSelection = true;
            break;
         case GlobalWarmingQuizMultipleChoice.fourthAnswer:
            fourthSelection = true;
      }
      
   }
   
   public void resetSelections () {
      firstSelection = false;
      secondSelection = false;
      thirdSelection = false;
      fourthSelection = false;
   }
   
   public void runQuiz (GlobalWarmingQuizMultipleChoiceTest quizTest) {
      System.out.println(GlobalWarmingQuizMultipleChoice.welcome);
      System.out.println(GlobalWarmingQuizMultipleChoice.questionsNumberInfo);
      System.out.println(GlobalWarmingQuizMultipleChoice.questionPointInfo);
      System.out.println(GlobalWarmingQuizMultipleChoice.manyCorrectAnswersPointInfo);
      System.out.println("After answering all the questions summary will be printed");
      skipToNextStage();
      
      GlobalWarmingQuizMultipleChoice quiz = new GlobalWarmingQuizMultipleChoice();
      quizTest.mainProgramLoop(quizTest, quiz);
      quiz.printSummary();
      
   }
   
   public void mainProgramLoop(GlobalWarmingQuizMultipleChoiceTest quizTest, GlobalWarmingQuizMultipleChoice quiz) {
      
      for (short questionCounter = 1; questionCounter <= GlobalWarmingQuizMultipleChoice.numberOfQuestions; questionCounter++) {
         quizTest.resetSelections();
         GlobalWarmingQuizMultipleChoice.printQuestion(questionCounter);
         GlobalWarmingQuizMultipleChoice.printAnswers(questionCounter);
         quizTest.getSelections(quizTest);
         quiz.printAnswerRating(questionCounter, firstSelection, secondSelection, 
                                             thirdSelection, fourthSelection);
         GlobalWarmingQuizMultipleChoice.printCorrectAnswers(questionCounter);
         skipToNextStage();
      }
   }
   
   public static void skipToNextStage() {
      String isProgramContinue = GettingDataFromStandardInput.getStringWaitingForInput(String.format
                              ("%n %s %d to quit %n", "***** To continue press ENTER or only", GlobalWarmingQuizMultipleChoice.quitProgram));

      if (((Short)(GlobalWarmingQuizMultipleChoice.quitProgram)).toString().equals(isProgramContinue)) {
         abnormalTermination();
      }
   }
   
   public static void abnormalTermination() {
      System.out.println("****** Quiz is interrupted ");
      System.exit(abnormalTerminationCode);
   }
   
} 
