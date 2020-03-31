/* =====================================================================================
 *       Filename:  SalaryCalculatorTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.20 - Solving the calculate salary of three 
                                employees using counter-controlled iteration
                                   with SalaryCalculator objects
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class SalaryCalculatorTest {

   public static void main(String[] args) {
      // initialization phase
      SalaryCalculator employee        = new SalaryCalculator();
      final short      employeesNumber = 3;
      short            employeeCounter = 0;  // initialize counter of employees to be entered next
      
      System.out.printf("****** This program calculate weekly salary of %d employees.%n", employeesNumber);
      // processing phase uses counter-controlled iteration
      while (employeeCounter < employeesNumber) {  // loop (employeesNumber - employeeCounter) times
         UserInterface.modifyEmployeeDataByUser(employee);
         UserInterface.displayEmployeeSalary(employee);
         employee.resetDataForNextEmployee();
         employeeCounter++;                        // increment counter by 1
      }
   } 
} 
