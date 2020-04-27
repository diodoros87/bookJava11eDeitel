/* =====================================================================================
 *       Filename:  ComputerAidedInstructionTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.37 - test of learning one-digit integers
                                (greater or equals zero) multiplication
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;
 
public class ComputerAidedInstructionTest {
   
   public static boolean getResultsFromUser(ComputerAidedInstruction computerAidedInstruction) {
      String question = computerAidedInstruction.getQuestion();
      Integer resultFromUser = GettingDataFromStandardInput.getIntegerRejectOthersData(question, false);
      
      while (null != resultFromUser) {
         computerAidedInstruction.isCorrectResult(resultFromUser);
         System.out.printf(" %s %n", computerAidedInstruction.getAnswer());
         System.out.printf(" %s %n", computerAidedInstruction.getCurrentSummary());
         
         if (true == computerAidedInstruction.isNumberOfAnswersLimitReached()) {
            System.out.printf("%n %s %n", computerAidedInstruction.getLastSummary());
         }
         
         question = computerAidedInstruction.getQuestion();
         resultFromUser = GettingDataFromStandardInput.getIntegerRejectOthersData(question, false);
      }
      
      return false;      
   }
   
   public static void main(String[] args) {
      ComputerAidedInstruction computerAidedInstruction = new ComputerAidedInstruction();
      System.out.printf("******** %s %n", ComputerAidedInstruction.START_INFO);
      
      getResultsFromUser(computerAidedInstruction);
   } 
   
}
