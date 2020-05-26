/* =====================================================================================
 *       Filename:  ProgramExecution.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.37 - calculate number at index (entered by
                              User) in Fibonacci sequence 
                                 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package simpletron;

import java.io.PrintStream;
import java.util.Scanner;

import static simpletron.ComputerSimulator.*;
 
public class ProgramExecution {
   private final PrintStream printStream;
   private final PrintStream errorPrintStream;
   private final Scanner scanner;
   
   private static final int ACCUMULATOR_MAX_VALUE = MAX_DATA;
   private static final int ACCUMULATOR_MIN_VALUE = MIN_DATA;
   
   private static final String AFTER_FINISHED_PROGRAM_MESSAGE = String.format("*** Program execution has finished ***%n");
   
   private int accumulator          = 0;
   private int instructionCounter   = 0;
   private int operationCode        = 0;
   private int operand              = 0;
   private int instructionRegister  = 0;

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
   
   public ProgramExecution(PrintStream printStream, Scanner scanner, PrintStream errorPrintStream) {
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
      try {
         executeProgramInstructions(memory);
      }
      catch (Exception exception) {
         errorPrintStream.println("Simpletron terminated in unexpected mode");
         errorPrintStream.println(exception.getMessage());
         exception.printStackTrace();
      } 
      
      printCoreDump(memory);
   }
   
   private void executeProgramInstructions(int[] memory) {
      instructionCounter = FIRST_INSTRUCTION_LOCATION;
      boolean running = true;
      
      while (instructionCounter < memory.length && true == running) {
         instructionRegister = memory[instructionCounter];
         operationCode = instructionRegister / 100;
         operand = instructionRegister % 100;
         
         switch(operationCode) {
            case READ:
               enterInteger(memory);
               break;
            case WRITE:
               printStream.printf(" in memory's location %02d is %+05d %n", operand, memory[operand]);
               break;
            case LOAD:
               accumulator = memory[operand];
               break;
            case STORE:
               memory[operand] = accumulator;
               break;
            case ADD:
               add(memory);
               break;
            case SUBTRACT:
               subtract(memory);
               break;
            case DIVIDE:
               divide(memory);
               break;
            case MULTIPLY:
               multiply(memory);
               break;
            case BRANCH:
               instructionCounter = operand;
               
               continue;
            case BRANCHNEG:
               if (0 > accumulator) {
                  instructionCounter = operand;
                  continue;
               }
               
               break;
            case BRANCHZERO:
               if (0 == accumulator) {
                  instructionCounter = operand;
                  continue;
               }
               
               break;
            case HALT:
               printStream.println(AFTER_FINISHED_PROGRAM_MESSAGE);
               running = false;
               
               break;
            default:
               throw new IllegalArgumentException("Unrecognized operation code " + operationCode);
         }
         
         instructionCounter++;
      }

   }
   
   private void enterInteger(int[] memory) {
      
      printStream.printf("Enter integer: ");
      while (true == scanner.hasNextLine()) {

         if (true == isCorrectInteger(memory)) {
            break;
         }
         
         printStream.printf("Enter integer: ");
      }
         
   }
   
   private boolean isCorrectInteger(int[] memory) {
      
      if (true == scanner.hasNextInt()) {
         int integer = scanner.nextInt();
         scanner.nextLine();   // to clear input data - nextInt() leaves whitespaces in input
         
         return isCorrectInteger(integer, memory);
      }
      else if (true == scanner.hasNextDouble()) {
         errorPrintStream.printf("%n???????? ERROR: Value of %f entered by User is type double. ", scanner.nextDouble());
         errorPrintStream.printf("This is incorrect. Double values are not using in this version of Simpletron%n");
         scanner.nextLine();   // to clear input data - NextDouble() leaves whitespaces in input
      }
      else {
         errorPrintStream.printf("%n???????? ERROR: Value of \'%s\' is not using in this version of Simpletron%n",
                                             scanner.nextLine());
      }
      
      return false;

   }
   
   private boolean isCorrectInteger(int integer, int[] memory) {
      if (ACCUMULATOR_MIN_VALUE <= integer && integer <= ACCUMULATOR_MAX_VALUE) {
         memory[operand] = integer;
         
         return true;
      }
      
      errorPrintStream.printf("%n???????? ERROR: data word must be in range from %d to %d%n",
                                       ACCUMULATOR_MIN_VALUE, ACCUMULATOR_MAX_VALUE);
      return false;
   }
   
   private void add(int[] memory) {
      if (ACCUMULATOR_MAX_VALUE - memory[operand] < accumulator) {
         throw new ArithmeticException(String.format("Arithmetic overflow while sum %d < %d + %d",
                                 ACCUMULATOR_MAX_VALUE, accumulator, memory[operand]));
      }
      if (ACCUMULATOR_MIN_VALUE - memory[operand] > accumulator) {
         throw new ArithmeticException(String.format("Arithmetic overflow while sum %d > %d + %d",
                                 ACCUMULATOR_MIN_VALUE, accumulator, memory[operand]));
      }
      
      accumulator += memory[operand];
   }
   
   private void subtract(int[] memory) {
      if (ACCUMULATOR_MAX_VALUE + memory[operand] < accumulator) {
         throw new ArithmeticException(String.format("Arithmetic overflow while subtract %d < %d - %d",
                                 ACCUMULATOR_MAX_VALUE, accumulator, memory[operand]));
      }
      if (ACCUMULATOR_MIN_VALUE + memory[operand] > accumulator) {
         throw new ArithmeticException(String.format("Arithmetic overflow while subtract %d > %d - %d",
                                 ACCUMULATOR_MIN_VALUE, accumulator, memory[operand]));
      }
      
      accumulator -= memory[operand];
   }
   
   private void multiply(int[] memory) {

      if (accumulator > 1) 
         if (memory[operand] > 1) 
            if (ACCUMULATOR_MAX_VALUE / memory[operand] < accumulator)
               throw new ArithmeticException(String.format("Arithmetic overflow while multiply %d < %d * %d",
                                 ACCUMULATOR_MAX_VALUE, accumulator, memory[operand]));
               
      if (accumulator < -1) 
         if (memory[operand] < -1) 
            if (ACCUMULATOR_MAX_VALUE / memory[operand] > accumulator)
               throw new ArithmeticException(String.format("Arithmetic overflow while multiply %d < %d * %d",
                                 ACCUMULATOR_MAX_VALUE, accumulator, memory[operand]));
               
      if (accumulator < -1)  
         if (memory[operand] > 1)
            if (ACCUMULATOR_MIN_VALUE / memory[operand] > accumulator)
               throw new ArithmeticException(String.format("Arithmetic overflow while multiply %d > %d * %d",
                                 ACCUMULATOR_MIN_VALUE, accumulator, memory[operand]));
               
      if (accumulator > 1)
         if (memory[operand] < -1)
            if (ACCUMULATOR_MIN_VALUE / memory[operand] < accumulator)
               throw new ArithmeticException(String.format("Arithmetic overflow while multiply %d > %d * %d",
                                 ACCUMULATOR_MIN_VALUE, accumulator, memory[operand]));
               
      accumulator *= memory[operand];
   }
   
   private void divide(int[] memory) {
      if (0 == memory[operand]) {
         throw new ArithmeticException("Divisor (quotient) can not be zero");
      }
      
      accumulator /= memory[operand];
   }
   
   private void printCoreDump(int[] memory) {
      printStream.printf("%n CORE DUMP %n");
      printStream.printf("REGISTERS: %n");
      printStream.printf("%-20s %+05d %n", "accumulator", accumulator);
      printStream.printf("%-20s %02d %n", "instructionCounter", instructionCounter);
      printStream.printf("%-20s %+05d %n", "instructionRegister", instructionRegister);
      printStream.printf("%-20s %02d %n", "operationCode", operationCode);
      printStream.printf("%-20s %02d %n", "operand", operand);
      printStream.println();
      printStream.println("MEMORY:");
      printStream.printf("   ");
      for (int counter = 0; counter < 10; counter++) {
         printStream.printf("%6d", counter);
      }
      printStream.println();
      
      int memoryLocation = 0;
      for (int row = 0; row < 10; row++) {
         printStream.printf(" %2d", 10 * row);
         
         for (int column = 0; column < 10; column++) {
            printStream.printf(" %+05d", memory[memoryLocation]);
            memoryLocation++;
         }
         
         printStream.println();
      }
   }
   
   public void reset() {
      this.accumulator          = 0;
      this.instructionCounter   = 0;
      this.operationCode        = 0;
      this.operand              = 0;
      this.instructionRegister  = 0;
   }
    
} 
