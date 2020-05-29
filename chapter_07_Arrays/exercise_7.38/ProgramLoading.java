/* =====================================================================================
 *       Filename:  ProgramLoading.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.38 - simulation of machine language programming
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
   
   private static final int END_OF_DATA_TO_LOAD = 1234567;
   
   private static final String INITIAL_MESSAGE = String.format("*** Welcome to Simpletron! ***%n") +
                                                 String.format("*** Please enter your program one instruction ***%n") +
                                                 String.format("*** (or data word) at a time. It will be display ***%n") +
                                                 String.format("*** the memory's location number and a question mark (?) ***%n") +
                                                 String.format("*** You then type the word for that location in memory. ***%n") +
                                                 String.format("*** Available locations in memory: from %03x to %03x. ***%n", 0, MEMORY_ELEMENTS - 1) +
                                                 String.format("*** Correct format instruction's length has only %d characters %n", INSTRUCTION_LENGTH) +
                                                 String.format("*** first %d characters for operation code's hex value%n", OPERATION_CODE_LENGTH) +
                                                 String.format("*** last %d characters for memory's location hex value%n", MEMORY_LOCATION_LENGTH) +
                                                 String.format("*** Type %d to stop entering your program. ***%n", END_OF_DATA_TO_LOAD);
   
   private static final String AVAILABLE_INSTRUCTIONS = String.format("--- Available instructions%n") +
                                                        String.format(" %03x - READ INT %n", READ_INT) +
                                                        String.format(" %03x - WRITE INT %n", WRITE_INT) +
                                                        String.format(" %03x - READ FLOAT %n", READ_FLOAT) +
                                                        String.format(" %03x - WRITE FLOAT %n", WRITE_FLOAT) +
                                                        String.format(" %03x - READ STRING %n", READ_STRING) +
                                                        String.format(" %03x - WRITE STRING %n", WRITE_STRING) +
                                                        String.format(" %03x - WRITE LINE %n", WRITE_LINE) +
                                                        
                                                        String.format(" %03x - ADD %n", ADD) +
                                                        String.format(" %03x - SUBTRACT %n", SUBTRACT) +
                                                        String.format(" %03x - DIVIDE %n", DIVIDE) +
                                                        String.format(" %03x - MODULO %n", MODULO) +
                                                        String.format(" %03x - MULTIPLY %n", MULTIPLY) +
                                                        String.format(" %03x - EXPONENTIATION %n", EXPONENTIATION) +
                                                        
                                                        String.format(" %03x - LOAD %n", LOAD) +
                                                        String.format(" %03x - STORE %n", STORE) +
                                                        
                                                        String.format(" %03x - BRANCH %n", BRANCH) +
                                                        String.format(" %03x - BRANCHNEG %n", BRANCHNEG) +
                                                        String.format(" %03x - BRANCHZERO %n", BRANCHZERO) +
                                                        
                                                        String.format(" %03x - HALT %n", HALT);
                                                        
   private static final String AFTER_ENTERING_PROGRAM_MESSAGE = String.format("*** Program's loading completed ***%n") +
                                                                String.format("*** Program's executing begins ***%n");
   
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
      
      String endOfData = ((Integer)END_OF_DATA_TO_LOAD).toString();
      
      int enteredInstructionsCounter = FIRST_INSTRUCTION_LOCATION;
      
      printStream.printf("%03x ? ", enteredInstructionsCounter);
      while (enteredInstructionsCounter < MEMORY_ELEMENTS && 
             true == scanner.hasNextLine()) {
             
         String dataWord = scanner.nextLine();
         if (true == endOfData.equals(dataWord)) {
            break;
         }
         
         if (true == isCorrectDataWord(dataWord, memory, enteredInstructionsCounter)) {
            enteredInstructionsCounter++;
         }
         
         printStream.printf("%03x ? ", enteredInstructionsCounter);
      } 
      
      printStream.printf("%n %s %n", AFTER_ENTERING_PROGRAM_MESSAGE);
   }
   
   private boolean isCorrectDataWord(String dataWord, int[] memory, int enteredInstructionsCounter) {      
      try {
         int integer = Integer.parseInt(dataWord, 10);

         return isCorrectDecimalNumber(integer, memory, enteredInstructionsCounter);
      }
      catch (NumberFormatException numberFormatException) {   
         return isCorrectOperation(dataWord, memory, enteredInstructionsCounter);
      }
   }
   
   private boolean isCorrectDecimalNumber(int integer, int[] memory, int enteredInstructionsCounter) {
      if (MIN_DATA <= integer && integer <= MAX_DATA) {
         memory[enteredInstructionsCounter] = integer;
         
         return true;
      }
   
      errorPrintStream.printf("%n ERROR: correct decimal number must be in range from %d to %d%n",
                                    MIN_DATA, MAX_DATA);
      return false;
   }
   
   private boolean isCorrectOperation(String dataWord, int[] memory, int enteredInstructionsCounter) {
      if (false == isCorrectInstructionLength(dataWord)) {
         return false;
      }
      if (false == HexToInt.isHexFormat(dataWord)) {
         errorPrintStream.printf("%n ERROR: data word with operation must be in hex format representation%n");
         return false;
      }
      
      String operationCodeString = dataWord.substring(0, OPERATION_CODE_LENGTH);
      int operationCode = HexToInt.calculateDecimalFromHex(operationCodeString);
      
      if (false == validateOperationCode(operationCode)) {
         return false;
      } //  incorrect operation code is allowing, in stage execution will be exception throw
      
      String memoryLocationString = dataWord.substring(MEMORY_LOCATION_LENGTH);
      int memoryLocation = HexToInt.calculateDecimalFromHex(memoryLocationString);
   
      if (true == validateMemoryLocation(memoryLocation)) {
         memory[enteredInstructionsCounter] = ComputerSimulator.calculateInstruction(operationCode, memoryLocation);
         
         return true;
      }

      return false;
   }
   
   private boolean validateOperationCode(int operationCode) {
      final int MAX_OPERATION_CODE = MAX_DATA / ORDER_OF_MAGNITUDE;
      
      if (1 > operationCode || operationCode > MAX_OPERATION_CODE) {
         errorPrintStream.printf("%n ERROR: Operation code must be in range from %03x to %03x %n", 1, MAX_OPERATION_CODE);
                                       
         return false;
      }
      
      checkOperationCode(operationCode);
      
      return true;
   }
   
   private boolean checkOperationCode(int operationCode) {
      switch (operationCode) {
         case READ_INT:
         case WRITE_INT:
         case READ_FLOAT:
         case WRITE_FLOAT:
         case READ_STRING:
         case WRITE_STRING:
         case WRITE_LINE:
         case LOAD:
         case STORE:
         case ADD:
         case SUBTRACT:
         case DIVIDE:
         case MODULO:
         case MULTIPLY:
         case EXPONENTIATION:
         case BRANCH:
         case BRANCHNEG:
         case BRANCHZERO:
         case HALT:
            
            return true;
            
         default:
            errorPrintStream.printf("%n ERROR: Unrecognized operation code %03x %n", operationCode);
            return false;
      }
   }
   
   private boolean validateMemoryLocation(int memoryLocation) {
      if (0 <= memoryLocation && memoryLocation <= MEMORY_ELEMENTS - 1) {
         return true;
      }
      
      errorPrintStream.printf("%n ERROR: memory location must be in range from %03x to %03x %n",
                                       0, MEMORY_ELEMENTS - 1);
      return false;
   }
   
   private boolean isCorrectInstructionLength(String dataWord) {
      if (dataWord.length() == INSTRUCTION_LENGTH) {
         return true;
      }
      
      errorPrintStream.printf("%n???????? ERROR: data word with operation must be %d characters length%n",
                                       INSTRUCTION_LENGTH);
      return false;
   }
    
} 
