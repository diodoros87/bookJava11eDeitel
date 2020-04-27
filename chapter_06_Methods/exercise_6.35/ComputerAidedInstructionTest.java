/* =====================================================================================
 *       Filename:  ComputerAidedInstructionTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.35 - test of learning one-digit integers
                                (greater or equals zero) multiplication
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;
 
public class ComputerAidedInstructionTest {
   
   public static boolean getResultFromUser(ComputerAidedInstruction computerAidedInstruction) {
      final String question = computerAidedInstruction.getQuestion();
      Integer resultFromUser = GettingDataFromStandardInput.getIntegerRejectOthersData(question, false);
      
      while (null != resultFromUser && false == computerAidedInstruction.isCorrectResult(resultFromUser)) {
         System.out.printf("%n %s %n", computerAidedInstruction.getAnswer());
         resultFromUser = GettingDataFromStandardInput.getIntegerRejectOthersData(question, false);
      }
      
      if (null == resultFromUser) {
         return false;
      }
      else {
         System.out.printf("%n %s %n", computerAidedInstruction.getAnswer());
         return true;
      }
   }
   
   public static void main(String[] args) {
      ComputerAidedInstruction computerAidedInstruction = new ComputerAidedInstruction();
      System.out.printf("******** %s %n", ComputerAidedInstruction.START_INFO);
      
      do {
      } while (true == getResultFromUser(computerAidedInstruction));
      
   } 
   
}
