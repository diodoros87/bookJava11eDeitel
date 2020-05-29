/* =====================================================================================
 *       Filename:  ProgramExecution.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.38 - simulation of machine language programming
                                 - stage program execution
                           
                             
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
      reset();
      
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
      
      while (instructionCounter < memory.length) {
         instructionRegister = memory[instructionCounter];
         operationCode = instructionRegister / ORDER_OF_MAGNITUDE;
         operand = instructionRegister % ORDER_OF_MAGNITUDE;
         
         execute(memory);
      }

   }
   
   private int execute (int[] memory) {
      switch(operationCode) {
         case READ_INT:
            enterInteger(memory);
            return ++instructionCounter;
         case WRITE_INT:
            printStream.printf(" in memory's location %03x is %+05d %n", operand, memory[operand]);
            return ++instructionCounter;
         case READ_FLOAT:
            //enterFloat(memory);
            return ++instructionCounter;
         case WRITE_FLOAT:
            //printStream.printf(" in memory's location %03x is %+05e %n", operand, memory[operand]);
            return ++instructionCounter;
         case READ_STRING:
            enterString(memory);
            return ++instructionCounter;
         case WRITE_STRING:
            printString(memory);
            return ++instructionCounter;
         case WRITE_LINE:
            printStream.println();
            return ++instructionCounter;
         case LOAD:
            accumulator = memory[operand];
            return ++instructionCounter;
         case STORE:
            memory[operand] = accumulator;
            return ++instructionCounter;
         case ADD:
            add(memory);
            return ++instructionCounter;
         case SUBTRACT:
            subtract(memory);
            return ++instructionCounter;
         case DIVIDE:
            divide(memory);
            return ++instructionCounter;
         case MODULO:
            modulo(memory);
            return ++instructionCounter;
         case MULTIPLY:
            multiply(memory, memory[operand]);
            return ++instructionCounter;
         case EXPONENTIATION:
            exponentiation(memory, memory[operand]);
            return ++instructionCounter;
         case BRANCH:
            instructionCounter = operand;
            
            return instructionCounter;
         case BRANCHNEG:
            if (0 > accumulator) {
               instructionCounter = operand;
               return instructionCounter;
            }
            
            return ++instructionCounter;
         case BRANCHZERO:
            if (0 == accumulator) {
               instructionCounter = operand;
               return instructionCounter;
            }
            
            return ++instructionCounter;
         case HALT:
            printStream.println(AFTER_FINISHED_PROGRAM_MESSAGE);
            instructionCounter = memory.length;
            
            return instructionCounter;
         default:
            throw new IllegalArgumentException(String.format("Unrecognized operation code %03x %n", operationCode));
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
   
   private void enterString(int[] memory) {
      printStream.printf("Enter string: ");
      
      if (true == scanner.hasNextLine()) {
         String string = scanner.nextLine();
         final int stringLength = string.length();
         
         if (stringLength > 0) {
            memory[operand] = stringLength * ORDER_OF_MAGNITUDE;
         }
         
         for (int index = 0; index < stringLength; index++) {
            if (index % 2 == 0) {
               memory[operand] += string.charAt(index);
            }
            else {
               memory[++operand] = string.charAt(index) * ORDER_OF_MAGNITUDE;
            }
         }
      }
   }
   
   private void printString(int[] memory) {
      printStream.printf(" in memory's location %03x is: \'", operand);
      
      char character;
      final int stringLength = memory[operand] / ORDER_OF_MAGNITUDE;
      for (int counter = 0; counter < stringLength; counter++) {
         if (counter % 2 == 0) {
            character = (char)(memory[operand] % ORDER_OF_MAGNITUDE);
         }
         else {
            character = (char)(memory[++operand] / ORDER_OF_MAGNITUDE);
         }
         
         printStream.printf("%c", character);
      }
      
      printStream.printf("\' %n");
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
   
   private void multiply(int[] memory, int factor) {

      if (accumulator > 1) 
         if (factor > 1) 
            if (ACCUMULATOR_MAX_VALUE / factor < accumulator)
               throw new ArithmeticException(String.format("Arithmetic overflow while multiply %d < %d * %d",
                                 ACCUMULATOR_MAX_VALUE, accumulator, factor));
               
      if (accumulator < -1) 
         if (factor < -1) 
            if (ACCUMULATOR_MAX_VALUE / factor > accumulator)
               throw new ArithmeticException(String.format("Arithmetic overflow while multiply %d < %d * %d",
                                 ACCUMULATOR_MAX_VALUE, accumulator, factor));
               
      if (accumulator < -1)  
         if (factor > 1)
            if (ACCUMULATOR_MIN_VALUE / factor > accumulator)
               throw new ArithmeticException(String.format("Arithmetic overflow while multiply %d > %d * %d",
                                 ACCUMULATOR_MIN_VALUE, accumulator, factor));
               
      if (accumulator > 1)
         if (factor < -1)
            if (ACCUMULATOR_MIN_VALUE / factor < accumulator)
               throw new ArithmeticException(String.format("Arithmetic overflow while multiply %d > %d * %d",
                                 ACCUMULATOR_MIN_VALUE, accumulator, factor));
               
      accumulator *= factor;
   }
   
   private void exponentiation(int[] memory, int exponent) {
      if (0 > exponent) {
         throw new IllegalArgumentException(" Exponent must be integer not less than zero");
      }
      if (exponent == 0 && accumulator == 0) {
         throw new ArithmeticException("0 to power of 0 is indeterminate form");
      }
      if (accumulator == 0) {
         return;
      }
      if (exponent == 0) {
         accumulator = 1;
         return;
      }
      
      int value = accumulator;
      for (int counter = 2; counter <= exponent; counter++) {
         multiply(memory, value);
      }
   }
   
   private void divide(int[] memory) {
      if (0 == memory[operand]) {
         throw new ArithmeticException("Divisor (quotient) can not be zero");
      }
      
      accumulator /= memory[operand];
   }
   
   private void modulo(int[] memory) {
      if (0 == memory[operand]) {
         throw new ArithmeticException("Divisor (quotient) can not be zero");
      }
      
      accumulator %= memory[operand];
   }
   
   private void printCoreDump(int[] memory) {
      printStream.printf("%n CORE DUMP %n");
      printStream.printf("REGISTERS: %n");
      printStream.printf("%-25s %+07d %n", "accumulator", accumulator);
      printStream.printf("%-25s %03x %n", "instructionCounter hex", instructionCounter);
      printStream.printf("%-25s %+07d %n", "instructionRegister", instructionRegister);
      printStream.printf("%-25s %03x %n", "operationCode hex", operationCode);
      printStream.printf("%-25s %03x %n", "operand hex", operand);
      printStream.println();
      printStream.println("MEMORY:");
      printStream.printf(" %3s", " ");
      for (int counter = 0; counter < 16; counter++) {
         printStream.printf(" %7x", counter);
      }
      printStream.println();
      
      int memoryLocation = 0;
      for (int row = 0; memoryLocation < memory.length; row++) {
         printStream.printf(" %3x", 16 * row);
         
         for (int column = 0; column < 16 && memoryLocation < memory.length; column++) {
            printStream.printf(" %+07d", memory[memoryLocation]);
            memoryLocation++;
         }
         
         printStream.println();
      }
      
      printStream.println();
   }
   
   private void reset() {
      this.accumulator          = 0;
      this.instructionCounter   = 0;
      this.operationCode        = 0;
      this.operand              = 0;
      this.instructionRegister  = 0;
   }
    
} 
