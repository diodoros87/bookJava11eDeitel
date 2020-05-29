/* =====================================================================================
 *       Filename:  ComputerSimulatorTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.38 - test of machine language programming
                                simulation
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

package simpletron;
 
public class ComputerSimulatorTest {
   
   public static void main(String[] args) {
      java.io.PrintStream printStream = System.out;
      java.util.Scanner scannerToLoadProgram = new java.util.Scanner(System.in);
      java.io.PrintStream errorPrintStream = System.err;
      java.util.Scanner scannerToExecuteProgram = new java.util.Scanner(System.in);
      
      ComputerSimulator computerSimulator = new ComputerSimulator(printStream, scannerToLoadProgram, errorPrintStream,
                                                                  printStream, scannerToExecuteProgram, errorPrintStream);
      computerSimulator.run();
   } 
   
} 
