/* =====================================================================================
 *       Filename:  ComputerAidedInstructionTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.38 - test of learning integers
                                (greater or equals zero) multiplication
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;
 
public class ComputerAidedInstructionTest {

   public static boolean selectLevel(ComputerAidedInstruction computerAidedInstruction) {
      System.out.printf("%n%s %n", ComputerAidedInstruction.LEVELS_INFO);
      System.out.printf("### 1. 1-digit%n");
      System.out.printf("### 2. 2-digits%n");
      System.out.printf("*** Enter only 1 or only 2 for select level, for quit enter other sequences%n");
      
      String menuSelection = GettingDataFromStandardInput.getStringWaitingForInput("");
      
      if (null != menuSelection) {
         
         if (menuSelection.equals("1")) {
            computerAidedInstruction.setNextLevel(false);
            return true;
         }
         else if (menuSelection.equals("2")) {
            computerAidedInstruction.setNextLevel(true);
            return true;
         }
         
      }
      
      return false;
   }
   
   public static boolean getResultsFromUser(ComputerAidedInstruction computerAidedInstruction) {
      String question = computerAidedInstruction.getQuestion();
      Integer resultFromUser = GettingDataFromStandardInput.getIntegerRejectOthersData(question, false);
      boolean nextLevelFirstSelection = computerAidedInstruction.getNextLevel();
      
      while (null != resultFromUser) {
         computerAidedInstruction.isCorrectResult(resultFromUser);
         System.out.printf(" %s %n", computerAidedInstruction.getAnswer());
         System.out.printf(" %s %n", computerAidedInstruction.getCurrentSummary());
         
         if (true == computerAidedInstruction.isNumberOfAnswersLimitReached()) {
            System.out.printf("%n %s %n", computerAidedInstruction.getLastSummary());
            
            // after answers limit reached program for User is over in cases:
            if (true == nextLevelFirstSelection ||   // if User select next level, this is the last level 
               (false == nextLevelFirstSelection && false == computerAidedInstruction.getNextLevel())) { 
               // if User select first level (and User not go to next level) this is the last level
               return true;
            }
         }
         
         question = computerAidedInstruction.getQuestion();
         resultFromUser = GettingDataFromStandardInput.getIntegerRejectOthersData(question, false);
      }
      
      return false;      
   }
   
   public static void main(String[] args) {
      ComputerAidedInstruction computerAidedInstruction = new ComputerAidedInstruction();
      System.out.printf("******** %s %n", ComputerAidedInstruction.START_INFO);
      
      boolean isProgramContinue = selectLevel(computerAidedInstruction);
      
      while (true == isProgramContinue) {
         isProgramContinue = getResultsFromUser(computerAidedInstruction);
         if (true == isProgramContinue) {
            GettingDataFromStandardInput.clearNextLineWaitingForInput();
            isProgramContinue = selectLevel(computerAidedInstruction);
         }
      }
      
   } 
   
}
