/* =====================================================================================
 *       Filename:  PayableInterfaceTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.18 - Payable interface test program processing
                                Invoices and Employees polymorphically
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import validateParametersPackage.ValidateParameters;

public class PayableInterfaceTest {
   public static void main(String[] args) {
      Payable[] payableArray = createPayableArray();
         
      processPayableObjects("Invoices and Employees processed polymorphically:", payableArray); 
   }
   
   public static void processPayableObjects (String title, Payable[] payableArray) {
      ValidateParameters.checkNullPointer(title);
      ValidateParameters.checkNullPointer((Object[])payableArray);
      
      System.out.printf("%s %n%n", title);
      for (Payable currentPayable : payableArray) {
         System.out.printf("%n%s %n", currentPayable.toString());
         
         if (currentPayable instanceof Employee) {
            Employee employee = (Employee)currentPayable;
            
            CompensationModel compensationModel = employee.getCompensationModel();
            // determine whether element is  BasePlusCommissionCompensationModel
            if (compensationModel instanceof BasePlusCommissionCompensationModel) {
               BasePlusCommissionCompensationModel basePlusModel = 
                                 (BasePlusCommissionCompensationModel)compensationModel;
                                 
               basePlusModel.setBaseSalary(1.10 * basePlusModel.getBaseSalary());

               System.out.printf("new base salary with 10%% increase is: $%,.2f%n", basePlusModel.getBaseSalary());
                  
               System.out.printf("%n%s %n", currentPayable.toString());
            } 
         }
      } 
   }
   
   public static void fillPayableArray (Payable[] payableArray, Invoice[] invoiceArray, Employee[] employeeArray) {
      ValidateParameters.checkNullPointersInArrays(invoiceArray, employeeArray);
      ValidateParameters.checkNullPointerOnlyArray(payableArray);
      
      try {
         int startIndex = 0;
         fillArray(payableArray, startIndex, invoiceArray);
         if (employeeArray.length > 0 && payableArray.length > invoiceArray.length) {
            startIndex = invoiceArray.length;
            fillArray(payableArray, startIndex, employeeArray);

            employeeArray[0].setCompensationModel(new HourlyCompensationModel(9999, 99));  //to test clone() method of class Employee
         }
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
   
   public static void fillArray (Object[] destination, int startIndex, Object[] SOURCE)
                                                throws CloneNotSupportedException {
      if (0 > startIndex) {
         throw new ArrayIndexOutOfBoundsException(String.format("Requirement: startIndex (%d) >= 0", startIndex));
      }
      
      ValidateParameters.checkNullPointer(SOURCE);
      ValidateParameters.checkNullPointerOnlyArray(destination);
      
      if (startIndex >= destination.length) {
         throw new ArrayIndexOutOfBoundsException(String.format(
                  "Requirement: startIndex (%d) < destination.length (%d)", startIndex, destination.length));
      }
      
      final int INDEX_LIMIT = Math.min(SOURCE.length, destination.length - startIndex);
      
      for (int index = 0; index < INDEX_LIMIT; index++) {
         if (false == SOURCE[index] instanceof Employee) {
            destination[startIndex + index] = SOURCE[index];
         }
         else {
            Employee sourceEmployee         = (Employee)SOURCE[index];
            destination[startIndex + index] = (Employee)sourceEmployee.clone();
         }
         
         //destination[startIndex + index] = SOURCE[index];  //to test clone() method of class Employee
      }
   }
   
   public static Payable[] createPayableArray() {
      CompensationModel[] compensationModelArray = createCompensationModelArray();
      Employee[]          employeeArray          = createEmployeeArray(compensationModelArray);
      Invoice[]           invoiceArray           = createInvoiceArray();
      
      Payable[] payableArray = new Payable[employeeArray.length + invoiceArray.length];
      
      fillPayableArray(payableArray, invoiceArray, employeeArray);
      
      return payableArray;
   }
   
   public static Invoice[] createInvoiceArray() {
      Invoice[] invoiceArray = new Invoice[] {
         new Invoice("01234", "seat", 2, 375.00),
         new Invoice("56789", "tire", 4, 79.95)  
      };
      
      return invoiceArray;
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
   
   private static Employee[] createEmployeeArray(CompensationModel[] compensationModelArray) {
      ValidateParameters.checkNullPointer((Object[])compensationModelArray);
      
      Employee[] employeeArray = new Employee[] {
         new Employee("Bob", "Lewis", "333-33-3333", compensationModelArray[0]),
         new Employee("Tom", "Jerry", "888-11-3333", compensationModelArray[1]),
         new Employee("Karen", "Price", "222-22-2222", compensationModelArray[2]),  
         new Employee("John", "Smith", "111-11-1111", compensationModelArray[3])   
      };
      
      return employeeArray;   
   } 
} 
