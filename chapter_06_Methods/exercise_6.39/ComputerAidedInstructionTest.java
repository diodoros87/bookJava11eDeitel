/* =====================================================================================
 *       Filename:  ComputerAidedInstructionTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.39 - test of learning arithmetic operations
                                of integers (greater or equals zero) 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;
 
public class ComputerAidedInstructionTest {
   
   private static void printSelectionLevelMenu() {
      System.out.printf("%n^^^ %s %n", ComputerAidedInstruction.LEVELS_INFO);
      System.out.printf("### 1. 1-digit%n");
      System.out.printf("### 2. 2-digits%n");
      System.out.printf("*** Enter only 1 or only 2 for select level, for quit enter other sequences%n");
   }

   public static boolean selectLevel(ArithmeticOperations arithmeticOperations) {
      printSelectionLevelMenu();
      
      String menuSelection = GettingDataFromStandardInput.getStringWaitingForInput("");
      
      if (null != menuSelection) {
         
         if (menuSelection.equals("1")) {
            arithmeticOperations.setNextLevel(false);
            return true;
         }
         else if (menuSelection.equals("2")) {
            arithmeticOperations.setNextLevel(true);
            return true;
         }
         
      }
      
      return false;
   }
   
   public static String getString (int integer) {
      return ((Integer)integer).toString();
   }
   
   private static void printSelectionArithmeticModeMenu() {
      System.out.printf("%n^^^ %s %n", ComputerAidedInstruction.ARITHMETIC_MODES_INFO);
      
      System.out.printf("### for %s enter only %d %n", ComputerAidedInstruction.ADDITION_STRING,
                                                ArithmeticOperations.ADDITION);
      System.out.printf("### for %s enter only %d %n", ComputerAidedInstruction.SUBTRACTION_STRING,
                                                ArithmeticOperations.SUBTRACTION);
      System.out.printf("### for %s enter only %d %n", ComputerAidedInstruction.MULTIPLICATION_STRING,
                                                ArithmeticOperations.MULTIPLICATION);
      System.out.printf("### for %s enter only %d %n", ComputerAidedInstruction.DIVISION_STRING,
                                                ArithmeticOperations.DIVISION);
      System.out.printf("### for %s enter only %d %n", ComputerAidedInstruction.MIXED_STRING,
                                                ArithmeticOperations.MIXED);
      System.out.printf("*** for quit enter other sequences%n");
   }
   
   public static boolean selectArithmeticMode(ArithmeticOperations arithmeticOperations) {
      printSelectionArithmeticModeMenu();
      
      String menuSelection = GettingDataFromStandardInput.getStringWaitingForInput("");
      
      if (null != menuSelection) {
         
         if (menuSelection.equals(getString(ArithmeticOperations.ADDITION))) {
            arithmeticOperations.setArithmeticMode(ArithmeticOperations.ADDITION);
            return true;
         }
         else if (menuSelection.equals(getString(ArithmeticOperations.SUBTRACTION))) {
            arithmeticOperations.setArithmeticMode(ArithmeticOperations.SUBTRACTION);
            return true;
         }
         else if (menuSelection.equals(getString(ArithmeticOperations.MULTIPLICATION))) {
            arithmeticOperations.setArithmeticMode(ArithmeticOperations.MULTIPLICATION);
            return true;
         }
         else if (menuSelection.equals(getString(ArithmeticOperations.DIVISION))) {
            arithmeticOperations.setArithmeticMode(ArithmeticOperations.DIVISION);
            return true;
         }
         else if (menuSelection.equals(getString(ArithmeticOperations.MIXED))) {
            arithmeticOperations.setArithmeticMode(ArithmeticOperations.MIXED);
            return true;
         }
         
      }
      
      return false;
   }
   
   public static boolean getResultsFromUser(ComputerAidedInstruction computerAidedInstruction,
                                             ArithmeticOperations arithmeticOperations) {
       
      boolean nextLevelFirstSelection = arithmeticOperations.getNextLevel();
      String question = computerAidedInstruction.generateQuestion();
      Integer resultFromUser = GettingDataFromStandardInput.getIntegerRejectOthersData(question, false);
      
      while (null != resultFromUser) {
         computerAidedInstruction.isCorrectResult(resultFromUser);
         System.out.printf(" %s %n", computerAidedInstruction.getAnswer());
         System.out.printf(" %s %n", computerAidedInstruction.generateCurrentSummary());
         
         if (true == computerAidedInstruction.isNumberOfAnswersLimitReached()) {
            System.out.printf("%n %s %n", computerAidedInstruction.generateLastSummary());
            
            // after answers limit reached program for User is over in cases:
            if (true == nextLevelFirstSelection ||   // if User select next level, this is the last level 
               (false == nextLevelFirstSelection && false == arithmeticOperations.getNextLevel())) { 
               // if User select first level (and User not go to next level) this is the last level
               return true;
            }
         }
         
         question = computerAidedInstruction.generateQuestion();
         resultFromUser = GettingDataFromStandardInput.getIntegerRejectOthersData(question, false);
      }
      
      return false;      
   }
   
   public static void main(String[] args) {
      System.out.printf("******** %s %n", ComputerAidedInstruction.START_INFO);
      
      ComputerAidedInstruction computerAidedInstruction = new ComputerAidedInstruction();
      ArithmeticOperations arithmeticOperations = computerAidedInstruction.getArithmeticOperations();
      
      boolean isProgramContinue;
      
      do {
         isProgramContinue = selectArithmeticMode(arithmeticOperations);
         if (true == isProgramContinue) {
            isProgramContinue = selectLevel(arithmeticOperations);
         }
         if (true == isProgramContinue) {
            isProgramContinue = getResultsFromUser(computerAidedInstruction, arithmeticOperations);
            GettingDataFromStandardInput.clearNextLineWaitingForInput();
         }
      } while (true == isProgramContinue);
      
   } 
   
}
