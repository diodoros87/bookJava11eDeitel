/* =====================================================================================
 *       Filename:  EmployeeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.17 - Employee class test program
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import validateParametersPackage.ValidateParameters;

public class EmployeeTest {
   public static void main(String[] args) {
      CompensationModel[] compensationModelArray = createCompensationModelArray();
      Employee[] employeeArray = createEmployeeArray(compensationModelArray);
      
      printArray("", employeeArray);
      
      shuffleArray(compensationModelArray);
      setCompensationModelOfEmployees(employeeArray, compensationModelArray);
      
      printArray("After changing of compensation model:", employeeArray);
   }
   
   public static CompensationModel[] createCompensationModelArray() {
      CompensationModel[] compensationModelArray = new CompensationModel[] {
         new CommissionCompensationModel(1000, 0.25),
         new BasePlusCommissionCompensationModel(5000, .04, 300),
         new HourlyCompensationModel(16.75, 40),  
         new SalariedCompensationModel(800.00)  
      };
      
      return compensationModelArray;
   }
   
   public static Employee[] createEmployeeArray(CompensationModel[] compensationModelArray) {
      ValidateParameters.checkNullPointer((Object[])compensationModelArray);
      
      Employee[] employeeArray = new Employee[] {
         new Employee("Bob", "Lewis", "333-33-3333", compensationModelArray[0]),
         new Employee("Tom", "Jerry", "888-11-3333", compensationModelArray[1]),
         new Employee("Karen", "Price", "222-22-2222", compensationModelArray[2]),  
         new Employee("John", "Smith", "111-11-1111", compensationModelArray[3])   
      };
      
      return employeeArray;   
   }
   
   public static void setCompensationModelOfEmployees(Employee[] employees, 
                                             CompensationModel[] compensationModels) {
      ValidateParameters.checkNullPointer((Object[])employees);
      ValidateParameters.checkNullPointer((Object[])compensationModels);
      
      final int MODIFICATIONS = Math.min(employees.length, compensationModels.length); 
                                                
      for(int index = 0; index < MODIFICATIONS; index++) {
         employees[index].setCompensationModel(compensationModels[index]);
      }
   }
   
   public static void shuffleArray (Object[] array) {
      ValidateParameters.checkNullPointer(array);
      
      Object temporary;
      for(int index = 1; index < array.length; index += 2) {
         temporary        = array[index - 1];
         array[index - 1] = array[index];
         array[index]     = temporary;
      }
   }
   
   public static void printArray (String title, Object[] array) {
      ValidateParameters.checkNullPointer(title);
      ValidateParameters.checkNullPointer(array);
      
      System.out.printf("%s %n%n", title);
      for(int index = 0; index < array.length; index++) {
         System.out.printf("%s %n%n", array[index]);
      }
   }
} 
