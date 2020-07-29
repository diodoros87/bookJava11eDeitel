/* =====================================================================================
 *       Filename:  PayrollSystemTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.13 - Employee hierarchy test program
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PayrollSystemTest {
   public static void main(String[] args) {
      // create subclass objects                                          
      SalariedEmployee salariedEmployee =                                 
         new SalariedEmployee("John", "Smith", "111-11-1111", new Date(1, 21, 1970), 800.00);    
      HourlyEmployee hourlyEmployee =                                     
         new HourlyEmployee("Karen", "Price", "222-22-2222", new Date(3, 24, 1977), 16.75, 40);  
      CommissionEmployee commissionEmployee =                             
         new CommissionEmployee(                                          
         "Sue", "Jones", "333-33-3333", new Date(6, 2, 1980), 10000, .06);                      
      BasePlusCommissionEmployee basePlusCommissionEmployee =             
         new BasePlusCommissionEmployee(                                  
         "Bob", "Lewis", "444-44-4444", new Date(11, 1, 1990), 5000, .04, 300);                  

      System.out.println("Employees processed individually:");

      System.out.printf("%n%s%n%s: $%,.2f%n%n", 
         salariedEmployee, "earned", salariedEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n",
         hourlyEmployee, "earned", hourlyEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n",
         commissionEmployee, "earned", commissionEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n", 
         basePlusCommissionEmployee, 
         "earned", basePlusCommissionEmployee.earnings());

      // create four-element Employee array
      Employee[] employees = new Employee[4]; 

      // initialize array with Employees        
      employees[0] = salariedEmployee;          
      employees[1] = hourlyEmployee;            
      employees[2] = commissionEmployee;        
      employees[3] = basePlusCommissionEmployee;

      System.out.printf("Employees processed polymorphically:%n%n");

      // generically process each element in array employees
      for (Employee currentEmployee : employees) {
         System.out.println(currentEmployee); // invokes toString

         // determine whether element is a BasePlusCommissionEmployee
         if (currentEmployee instanceof BasePlusCommissionEmployee) {
            // downcast Employee reference to 
            // BasePlusCommissionEmployee reference
            BasePlusCommissionEmployee employee = 
               (BasePlusCommissionEmployee) currentEmployee;

            employee.setBaseSalary(1.10 * employee.getBaseSalary());

            System.out.printf(
               "new base salary with 10%% increase is: $%,.2f%n",
               employee.getBaseSalary());
         } 

         System.out.printf(
            "earned $%,.2f%n%n", currentEmployee.earnings());
      } 

      // get type name of each object in employees array
      for (int j = 0; j < employees.length; j++) {      
         System.out.printf("Employee %d is a %s%n", j,  
            employees[j].getClass().getName());         
      }
      
      setPremiumForEmployees(employees);                     
   } 
   
   private static void setPremiumForEmployees(Employee... employees) {
      final double PREMIUM     = 100;
      final int MONTHS_IN_YEAR = 12;
      for (int month = 1; month <= MONTHS_IN_YEAR; month++) {
         System.out.printf("%n%n\t\t\t Employees earnings for month = %d have listed below:%n", month);
         
         for (Employee currentEmployee : employees) {
            System.out.println(currentEmployee); 
            
            boolean birthDateMonthForEmployee = isBirthDateMonthForEmployee(currentEmployee, month);

            if (true == birthDateMonthForEmployee) {
               System.out.printf("premium $%,.2f for month = %d%n", PREMIUM, month);
                  
               currentEmployee.setPremium(PREMIUM);
            } 

            System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
            
            if (true == birthDateMonthForEmployee) {    
               currentEmployee.setPremium(0);
            }
         } 
      }   
   } 
   
   private static boolean isBirthDateMonthForEmployee (Employee employee, int month) {
      int birthDateMonthForEmployee = employee.getBirthDate().getMonth();
      
      return birthDateMonthForEmployee == month;
   }                    
} 
