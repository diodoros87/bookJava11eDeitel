/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.20 - class of user inferface in
                                program with SalaryCalculator objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {
   
   public static String promptFormatting(String employeeData) {
      return String.format("Enter %s of employee: ", employeeData);
   }
   
   public static void modifyEmployeeDataByUser(SalaryCalculator employee) {
      System.out.printf("%n****** Enter data of employee to display employee's weekly salary.%n");
   
      String lastName = GettingDataFromStandardInput.getString(promptFormatting("last name"));
      double salaryPerHour = GettingDataFromStandardInput.getDouble(promptFormatting("salary per hour"));
      double workingHours = GettingDataFromStandardInput.getDouble(promptFormatting("number of working hours in week"));
      
      employee.setLastName(lastName);
      employee.setSalaryPerHour(salaryPerHour);
      employee.setWorkingHours(workingHours);
   }
      
   public static void displayEmployeeSalary(SalaryCalculator employee) {
      System.out.printf("-------Weekly salary for %s is %.2f %n", employee.getLastName(), employee.getTotalSalary()); 
   }
   
}
