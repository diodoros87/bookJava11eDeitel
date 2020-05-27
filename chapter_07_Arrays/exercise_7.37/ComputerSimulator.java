/* =====================================================================================
 *       Filename:  ComputerSimulator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.37 - simulation of machine language programming
                                 
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package simpletron;
 
import java.io.PrintStream;
import java.util.Scanner;
 
public class ComputerSimulator {
   
   private final ProgramLoading programLoading;
   private final ProgramExecution programExecution;
   
   public static final int MEMORY_ELEMENTS = 100;                                             
   private int[] memory = new int[MEMORY_ELEMENTS];
   
   public static final int MAX_DATA    = +9999;
   public static final int MIN_DATA    = -9999;
   
   public static final int READ        = 10;
   public static final int WRITE       = 11;
   public static final int LOAD        = 20;
   public static final int STORE       = 21;
   public static final int ADD         = 30;
   public static final int SUBTRACT    = 31;
   public static final int DIVIDE      = 32;
   public static final int MULTIPLY    = 33;
   public static final int BRANCH      = 40;
   public static final int BRANCHNEG   = 41;
   public static final int BRANCHZERO  = 42;
   public static final int HALT        = 43;
   
   public static final int FIRST_INSTRUCTION_LOCATION = 0;
   public static final int START_VALUE = 0;
   
   public ComputerSimulator(PrintStream loadingPrintStream, Scanner loadingScanner, PrintStream loadingErrorPrintStream,
                           PrintStream executingPrintStream, Scanner executingScanner, PrintStream executingErrorPrintStream) {
                           
      this.programLoading = new ProgramLoading(loadingPrintStream, loadingScanner, loadingErrorPrintStream);
      this.programExecution = new ProgramExecution(executingPrintStream, executingScanner, executingErrorPrintStream);
      
   }
   
   public ComputerSimulator(PrintStream printStream, Scanner scanner, PrintStream errorPrintStream) {
      this(printStream, scanner, errorPrintStream,
                        printStream, scanner, errorPrintStream);
   }
   
   public void run() {
      //loadExamplePrograms();

      setStartValue();
      programLoading.run(this.memory);
      programExecution.run(this.memory);
      
   }
   
   private void loadExamplePrograms() {
      loadFirstProgram(-4, -2);
      programExecution.run(this.memory);
      
      loadSecondProgram(999, 12);
      programExecution.run(this.memory);
      
      loadSecondProgram(9, 12);
      programExecution.run(this.memory);
      
      loadThirdProgram(-1, 10);
      programExecution.run(this.memory);
      
      loadFourthProgram(7);
      programExecution.run(this.memory);
      
      loadFifthProgram();
      programExecution.run(this.memory);
   }
   
   private void loadFirstProgram(int firstNumber, int secondNumber) {
      setStartValue();
      
      memory[0] = 1007;
      memory[1] = 1008;
      memory[2] = 2007;
      memory[3] = 3008;
      memory[4] = 2109;
      memory[5] = 1109;
      memory[6] = 4300;
      
      memory[7] = firstNumber;
      memory[8] = secondNumber;
   }
   
   private void loadSecondProgram(int firstNumber, int secondNumber) {
      setStartValue();
      
      memory[0] = 1009;
      memory[1] = 1010;
      memory[2] = 2009;
      memory[3] = 3110;
      memory[4] = 4107;
      memory[5] = 1109;
      memory[6] = 4300;
      memory[7] = 1110;
      memory[8] = 4300;
      
      memory[9] = firstNumber;
      memory[10] = secondNumber;
   }
      
   private void loadThirdProgram(int endOfDataMarker, int maxIngredients) {
      setStartValue();
      
      memory[0]  = 2040;
      memory[1]  = 3141;
      memory[2]  = 4216;
      memory[3]  = 1043;
      memory[4]  = 2043;
      memory[5]  = 3139;
      memory[6]  = 4216;
      memory[7]  = 2043;
      memory[8]  = 4103;
      memory[9]  = 4203;
      memory[10] = 3042;
      memory[11] = 2142;
      memory[12] = 2041;
      memory[13] = 3044;
      memory[14] = 2141;
      memory[15] = 4000;
      memory[16] = 1142;
      memory[17] = 4300;
      
      memory[39] = endOfDataMarker;
      memory[40] = maxIngredients;
      memory[41] = 0;
      memory[42] = 0;
      //memory[43];
      memory[44] = 1;
   }
   
   private void loadFourthProgram(int maxIngredients) {
      setStartValue();
      
      memory[0]  = 2040;
      memory[1]  = 3141;
      memory[2]  = 4212;
      memory[3]  = 1043;
      memory[4]  = 2043;
      memory[5]  = 4203;
      memory[6]  = 3042;
      memory[7]  = 2142;
      memory[8]  = 2041;
      memory[9]  = 3044;
      memory[10] = 2141;
      memory[11] = 4000;
      memory[12] = 2042;
      memory[13] = 3241;
      memory[14] = 2142;
      memory[15] = 1142;
      memory[16] = 4300;
      
      memory[40] = maxIngredients;
      memory[41] = 0;
      memory[42] = 0;
      //memory[43];
      memory[44] = 1;
   }
   
   private void loadFifthProgram() {
      setStartValue();
      
      memory[0]  = 1040;
      memory[1]  = 2040;
      memory[2]  = 2142;
      memory[3]  = 4119;
      memory[4]  = 4219;
      memory[5]  = 2040;
      memory[6]  = 3141;
      memory[7]  = 4218;
      memory[8]  = 1043;
      memory[9]  = 2043;
      memory[10] = 3142;
      memory[11] = 4114;
      memory[12] = 2043;
      memory[13] = 2142;
      memory[14] = 2041;
      memory[15] = 3044;
      memory[16] = 2141;
      memory[17] = 4003;
      memory[18] = 1142;
      memory[19] = 4300;
      
      //memory[40] 
      memory[41] = 1;
      //memory[42] = 0;
      //memory[43];
      memory[44] = 1;
   }
   
   private void setStartValue() {
      for (int index = 0; index < MEMORY_ELEMENTS; index++) {
         memory[index] = START_VALUE;
      }
   }
} 
