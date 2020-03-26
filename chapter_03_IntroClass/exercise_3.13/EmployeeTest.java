/* =====================================================================================
 *       Filename:  EmployeeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.13 - Inputting and outputting 
                                floating-point numbers with Employee objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class EmployeeTest {

   public static void main(String[] args) {
      manageCorrectData();
      
      createIncorrectData();
      
      Employee createdEmployeeByUser = UserInterface.createEmployeeByUser();
      UserInterface.printSalaryPerYear(createdEmployeeByUser);
      
      UserInterface.modifyEmployeeDataByUser(createdEmployeeByUser);
      UserInterface.printSalaryPerYear(createdEmployeeByUser);
       
   } 
   
   private static void manageCorrectData() {
      System.out.printf("+++	 Display initial salaries of employees with correct data:%n");
      
      Employee marcus = testingDisplayEmployee(new Employee("Marcus", "Aurelius", 50.004),
                                             "Marcus", "Aurelius", 50);
      Employee seneca = testingDisplayEmployee(new Employee("Lucius", "Seneca", 7.537),
                                             "Lucius", "Seneca", 7.54);
      
      // trying to set only one of instance's variable 
      modifyFirstName(marcus, "M", "Marcus");
      modifyFirstName(marcus, "marcus", "Marcus");
      modifyFirstName(marcus, "MarcusA", "Marcus");
      
      modifyLastName(seneca, "Seneca ", "Seneca");
      modifyLastName(seneca, " SenecaLucius", "Seneca");
      modifyLastName(seneca, "Seneka", "Seneka");
      
      modifySalaryPerMonth(marcus, 0.001, 50);
      modifySalaryPerMonth(marcus, 0, 50);
      modifySalaryPerMonth(marcus, -34.45, 50);
      modifySalaryPerMonth(marcus, 4.4, 4.4);
      
      // 10 percentage salary increase
      marcus.setSalaryPerMonth( 1.1 * marcus.getSalaryPerMonth());
      seneca.setSalaryPerMonth( 1.1 * seneca.getSalaryPerMonth());
      
      System.out.printf("%n+++	 Display salaries of employees after 10 percentage salary increase:%n");
      UserInterface.printSalaryPerYear(marcus);
      UserInterface.printSalaryPerYear(seneca); 
   }
   
   private static void createIncorrectData() {
      System.out.printf("%n+++	 Display initial salaries of employees with incorrect data:%n");
      
      Employee diogenes = testingDisplayEmployee(new Employee("Diogenes", "Sinope", -87.997),
                                                            "Diogenes", "Sinope", 0);
      Employee spaceFirstName = testingDisplayEmployee(new Employee("Nicolaus Copernicus", "Copernicus", 5),
                                                               "", "Copernicus", 5);
      Employee spaceLastName = testingDisplayEmployee(new Employee("Nicolaus", " Copernicus", 7),
                                                            "Nicolaus", "", 7);
      Employee lowerLetter = testingDisplayEmployee(new Employee("nicolaus", "copernicus", 6), "", "", 6);
      
      Employee empty = testingDisplayEmployee(new Employee("", "", -5.804), "", "", 0);
      
      Employee mixDigitsLetters = testingDisplayEmployee(new Employee
         ("2mixDigitsLetters", "mixDigits5Letters8", 8.444), "", "", 8.44);
      
      Employee johnis = testingDisplayEmployee(new Employee("John's", "lastName", 0.1248), "", "", 0.12);
      
      Employee dollar = testingDisplayEmployee(new Employee("$", "Dollar", 0.128), "", "Dollar", 0.13);
   }
   
   private static Employee testingDisplayEmployee(Employee employee, 
                  String expectedFirstName, String expectedLastName, double expectedSalaryPerMonth) {
                              
      AssertTesting.expectedResults(employee, expectedFirstName, expectedLastName, expectedSalaryPerMonth);
      UserInterface.printSalaryPerYear(employee);
      
      return employee;
   }
   
   private static void modifyFirstName(Employee employee, String firstName, String expectedValue) {
      System.out.printf("%n###	 Try to set new employee's first name \'%s\'%n", firstName);
      employee.setFirstName(firstName);
      AssertTesting.expectedFirstName(employee, expectedValue);
      UserInterface.printSalaryPerYear(employee);
   }
   
   private static void modifyLastName(Employee employee, String lastName, String expectedValue) {
      System.out.printf("%n###	 Try to set new employee's last name \'%s\'%n", lastName);
      employee.setLastName(lastName);
      AssertTesting.expectedLastName(employee, expectedValue);
      UserInterface.printSalaryPerYear(employee);
   }
   
   private static void modifySalaryPerMonth(Employee employee, double salaryPerMonth, double expectedValue) {
      System.out.printf("%n###	 Try to set new employee's salary per month %f%n", salaryPerMonth);
      employee.setSalaryPerMonth(salaryPerMonth);
      AssertTesting.expectedSalaryPerMonth(employee, expectedValue);
      UserInterface.printSalaryPerYear(employee);
   }
   
} 
