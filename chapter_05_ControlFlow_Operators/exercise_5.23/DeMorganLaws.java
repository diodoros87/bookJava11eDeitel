/* =====================================================================================
 *       Filename:  DeMorganLaws.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.23 - Printing results of De Morgan's laws
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Random;
 
public class DeMorganLaws {
   
   public static void main(String[] args) {
      Random pseudoRandom = new Random();
      
      final int a = pseudoRandom.nextInt(22);
      final int b = pseudoRandom.nextInt(22);
      final int g = pseudoRandom.nextInt(22);
      final int i = pseudoRandom.nextInt(22);
      final int j = pseudoRandom.nextInt(22);
      final int x = pseudoRandom.nextInt(22);
      final int y = pseudoRandom.nextInt(22);
      
      System.out.println("After generate pseudorandom int values:");
      System.out.println("a = " + a + "   b = " + b + "   x = " + x + "   y = " + y);
      System.out.println("g = " + g + "   i = " + i + "   j = " + j);
      
      String expressionString =           "!(x < 5) && !(y >= 7)";
      boolean expression                 = !(x < 5) && !(y >= 7);
      String equivalentExpressionString = "!(x < 5 || y >= 7)";
      boolean equivalentExpression       = !(x < 5 || y >= 7);
      testExpressions(expressionString, expression, equivalentExpressionString, equivalentExpression);
      
      expressionString           = "!(a == b) || !(g != 5)";
      expression                  = !(a == b) || !(g != 5);
      equivalentExpressionString = "!(a == b && g != 5)";
      equivalentExpression        = !(a == b && g != 5);
      testExpressions(expressionString, expression, equivalentExpressionString, equivalentExpression);
      
      expressionString           = "!((x <= 8) && (y > 4))";
      expression                  = !((x <= 8) && (y > 4));
      equivalentExpressionString = "!(x <= 8) || !(y > 4)";
      equivalentExpression        = !(x <= 8) || !(y > 4);
      testExpressions(expressionString, expression, equivalentExpressionString, equivalentExpression);
      
      expressionString           = "!((i > 4) || (j <= 6))";
      expression                  = !((i > 4) || (j <= 6));
      equivalentExpressionString = "!(i > 4) && !(j <= 6)";
      equivalentExpression        = !(i > 4) && !(j <= 6);
      testExpressions(expressionString, expression, equivalentExpressionString, equivalentExpression);
   } 
   
   public static void testExpressions(String expressionString, boolean expression,
                                      String equivalentExpressionString, boolean equivalentExpression) {
      System.out.println();
      
      System.out.print(expressionString + " is ");
      System.out.println(expression);
      
      System.out.print(equivalentExpressionString + " is ");
      System.out.println(equivalentExpression);
      
      System.out.print(expressionString + " == " + equivalentExpressionString + " is ");
      System.out.println(expression == equivalentExpression);
   }
   
} 
