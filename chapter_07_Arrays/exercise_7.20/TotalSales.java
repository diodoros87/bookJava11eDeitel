/* =====================================================================================
 *       Filename:  TotalSales.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.20 - calculating of total sales per salesman
                                and per product
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class TotalSales {
   
   public static final int    NUMBER_OF_PRODUCTS = 5;
   public static final int    NUMBER_OF_SALESMEN = 4;

   public static void salesAssignment(double[][] sales) {
      double sale = 0;
      for (int product = 0; product < sales.length; product++, sale += 0.5) {
         for (int salesman = 0; salesman < sales[product].length; salesman++, sale += 10) {
            sales[product][salesman] = sale;
         }
      }

   }
   
   public static void main(String[] args) { 
      System.out.printf("*** This program calculate of total sales per %d salesmen and per %d products %n",
                                    NUMBER_OF_SALESMEN, NUMBER_OF_PRODUCTS);
                                    
      double[][] sales = new double [NUMBER_OF_PRODUCTS][NUMBER_OF_SALESMEN];
      salesAssignment(sales);
      
      printTotalSalesSummary(sales, 
                           String.format("%nSummary of total sales of %d salesmen and %d products has printed below: %n",
                           NUMBER_OF_SALESMEN, NUMBER_OF_PRODUCTS));
         
   }
   
   private static void printTotalSalesSummary(double[][] sales, final String TITLE) {
      System.out.println(TITLE);
      System.out.printf("%25s", "");
      for (int salesman = 1; salesman <= NUMBER_OF_SALESMEN; salesman++) {
         System.out.printf("%15s", String.format("Salesman %2d", salesman));
      }
      System.out.printf(" %25s %n", "Total sales of product");
      
      double salesmanTotalSales[] = new double[NUMBER_OF_SALESMEN];
      
      for (int product = 0, sale = 0; product < NUMBER_OF_PRODUCTS; product++) {
         double productTotalSales = 0;
         
         System.out.printf("%-25s", String.format("Product %2d", product + 1));
         for (int salesman = 0; salesman < NUMBER_OF_SALESMEN; salesman++) {
            productTotalSales            += sales[product][salesman];
            salesmanTotalSales[salesman] += sales[product][salesman];
            
            System.out.printf("%15.2f", sales[product][salesman]);
         }
         
         System.out.printf("%25.2f %n", productTotalSales);
      }
      
      System.out.printf("%-25s", "Total sales of salesman");
      for (int salesman = 0; salesman < NUMBER_OF_SALESMEN; salesman++) {
         System.out.printf("%15.2f", salesmanTotalSales[salesman]);
      }
      
      System.out.println();
   }
   
} 
