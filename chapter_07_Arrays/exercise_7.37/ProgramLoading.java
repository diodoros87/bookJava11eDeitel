/* =====================================================================================
 *       Filename:  ProgramLoading.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.37 - simulation of machine language programming
                                 - stage program loading
                                 
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
 
package simpletron;
import java.io.PrintStream;
import java.util.Scanner;

import static simpletron.ComputerSimulator.*;
 
public class ProgramLoading {
   private final PrintStream printStream;
   private final PrintStream errorPrintStream;
   private final Scanner scanner;
   
   private static final int END_OF_DATA_TO_LOAD_MARK = -12345;
   
   private static final String INITIAL_MESSAGE = String.format("*** Welcome to Simpletron! ***%n") +
                                                 String.format("*** Please enter your program one instruction ***%n") +
                                                 String.format("*** (or data word) at a time. It will be display ***%n") +
                                                 String.format("*** the memory's location number and a question mark (?) ***%n") +
                                                 String.format("*** You then type the word for that location in memory. ***%n") +
                                                 String.format("*** Type %d to stop entering your program. ***%n", END_OF_DATA_TO_LOAD_MARK);
   
   
//    private static final READ        = 10;
//    private static final WRITE       = 11;
//    private static final LOAD        = 20;
//    private static final STORE       = 21;
//    private static final ADD         = 30;
//    private static final SUBTRACT    = 31;
//    private static final DIVIDE      = 32;
//    private static final MULTIPLY    = 33;
//    private static final BRANCH      = 40;
//    private static final BRANCHNEG   = 41;
//    private static final BRANCHZERO  = 42;
//    private static final HALT        = 43;
   
   private static final String AVAILABLE_INSTRUCTIONS = String.format("--- Available instructions%n") +
                                                        String.format(" %d - READ %n", READ) +
                                                        String.format(" %d - WRITE %n", WRITE) +
                                                        String.format(" %d - LOAD %n", LOAD) +
                                                        String.format(" %d - STORE %n", STORE) +
                                                        String.format(" %d - ADD %n", ADD) +
                                                        String.format(" %d - SUBTRACT %n", SUBTRACT) +
                                                        String.format(" %d - DIVIDE %n", DIVIDE) +
                                                        String.format(" %d - MULTIPLY %n", MULTIPLY) +
                                                        String.format(" %d - BRANCH %n", BRANCH) +
                                                        String.format(" %d - BRANCHNEG %n", BRANCHNEG) +
                                                        String.format(" %d - BRANCHZERO %n", BRANCHZERO) +
                                                        String.format(" %d - HALT %n", HALT);
                                                        
   private static final String AFTER_ENTERING_PROGRAM_MESSAGE = String.format("*** Program loading completed ***%n") +
                                                                String.format("*** Program execution begins ***%n");
   
   public ProgramLoading(PrintStream printStream, Scanner scanner, PrintStream errorPrintStream) {
      if (null == printStream) {
         throw new NullPointerException("print stream can not be null.");
      }
      if (null == scanner) {
         throw new NullPointerException("Input's scanner can not be null.");
      }
      if (null == errorPrintStream) {
         throw new NullPointerException("Error print stream can not be null.");
      }
      
      this.printStream = printStream;
      this.scanner = scanner;
      this.errorPrintStream = errorPrintStream;
   }
   
   public void run(int[] memory) {
      enterProgramInstructions(memory);
   }
   
   private void enterProgramInstructions(int[] memory) {
      printStream.println(INITIAL_MESSAGE);
      printStream.println(AVAILABLE_INSTRUCTIONS);
      
      int enteredInstructionsCounter = FIRST_INSTRUCTION_LOCATION;
      
      printStream.printf("%02d ? ", enteredInstructionsCounter);
      while (enteredInstructionsCounter < MEMORY_ELEMENTS && 
             true == scanner.hasNextLine()) {
         
         if (true == isIntegerEntered()) {
         
            int enteredInteger = scanner.nextInt();
            scanner.nextLine();   // to clear input data - nextInt() leaves whitespaces in input
            
            if (enteredInteger == END_OF_DATA_TO_LOAD_MARK) {
               break;
            }
            else if (true == isCorrectDataWord(enteredInteger)) {
               memory[enteredInstructionsCounter] = enteredInteger;
               enteredInstructionsCounter++;
            }
         }
         
         printStream.printf("%02d ? ", enteredInstructionsCounter);
      } 
      
      printStream.printf("%n %s %n", AFTER_ENTERING_PROGRAM_MESSAGE);
   }
   
   private boolean isIntegerEntered() {
      if (true == scanner.hasNextInt()) {
         return true;
      }
      else if (true == scanner.hasNextDouble()) {
         errorPrintStream.printf("%n???????? ERROR: Value of %f is type double. ", scanner.nextDouble());
         errorPrintStream.printf("This is incorrect. Double values are not using in this version of Simpletron%n");
         scanner.nextLine();   // to clear input data - NextDouble() leaves whitespaces in input
      }
      else {
         errorPrintStream.printf("%n???????? ERROR: Value of \'%s\' is not using in this version of Simpletron%n",
                                             scanner.nextLine());
      }
      
      return false;
   }
   
   private boolean isCorrectDataWord(int integer) {
      if (MIN_DATA <= integer && integer <= MAX_DATA) {
         return true;
      }
      
      errorPrintStream.printf("%n???????? ERROR: data word must be in range from %d to %d%n",
                                       MIN_DATA, MAX_DATA);
      return false;
   }
    
} 
