/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.13 - class of user inferface in
                                program with Employee objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {

   private static final String firstNamePrompt      = "Enter first name for employee: ";
   private static final String lastNamePrompt       = "Enter last name for employee: ";
   private static final String salaryPerMonthPrompt = "Enter salary per month for employee: ";

   public static Employee createEmployeeByUser() {
   
      System.out.printf("%n****** Enter data of employee (first name, last name, salary per month)%n");
      
      String firstName      = GettingDataFromStandardInput.getString(firstNamePrompt);
      String lastName       = GettingDataFromStandardInput.getString(lastNamePrompt);
      double salaryPerMonth = GettingDataFromStandardInput.getDouble(salaryPerMonthPrompt);
      
      Employee newEmployee  = new Employee(firstName, lastName, salaryPerMonth);
      return newEmployee;
   }
   
   public static Employee modifyEmployeeDataByUser(Employee employee) {
   
      System.out.printf("%n****** Enter data of employee (first name, last name, salary per month)" 
                + String.format(" to modify data of employee \'%s\' \'%s\'%n",
                                 employee.getFirstName(), employee.getLastName()));
      
      modifyFirstName(employee);
      modifyLastName(employee);
      modifySalaryPerMonth(employee);
      
      return employee;
   }
   
   public static Employee modifyFirstName(Employee employee) {
   
      String firstName = GettingDataFromStandardInput.getString(firstNamePrompt);
      employee.setFirstName(firstName);
    
      return employee;
   }
   
   public static Employee modifyLastName(Employee employee) {
   
      String lastName = GettingDataFromStandardInput.getString(lastNamePrompt);
      employee.setLastName(lastName);
    
      return employee;
   }
   
   public static Employee modifySalaryPerMonth(Employee employee) {
   
      double salaryPerMonth = GettingDataFromStandardInput.getDouble(salaryPerMonthPrompt);
      employee.setSalaryPerMonth(salaryPerMonth);
    
      return employee;
   }
   
   public static void displayEmployeeData(Employee employee) {
      System.out.printf("-------Employee's data: %n"); 
      System.out.printf("first name: \'%s\'\t last name: \'%s\'\t salary per month: %.2f%n", 
         employee.getFirstName(), employee.getLastName(), employee.getSalaryPerMonth());
   }
   
   public static void printSalaryPerYear(Employee employee) {
      displayEmployeeData(employee);
      System.out.printf("Salary per year is: %.2f%n", employee.getSalaryPerYear()); 
   }
   
}
