/* =====================================================================================
 *       Filename:  IntegerSetTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.14 - test class of integer's set
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class IntegerSetTest {

   public static void main(String[] args) {
      IntegerSet first = new IntegerSet();
      printIntegerSet("first IntegerSet after construct: ", first);
      
      first.insertElement(4);
      first.insertElement(4);
      first.deleteElement(9);
      printIntegerSet("first IntegerSet after insert 4: ", first);
      
      for (int element = IntegerSet.MIN; element <= IntegerSet.MAX; element += 2) {
         first.insertElement(element);
      }
      
      printIntegerSet("first IntegerSet after insert even elements: ", first);
      
      first.deleteElement(4);
      first.deleteElement(5);
      first.deleteElement(6);
      printIntegerSet("first IntegerSet after delete 4, 5, 6: ", first);
      
      for (int element = 14; element <= IntegerSet.MAX; element += 2) {
         first.deleteElement(element);
      }
      
      printIntegerSet("first IntegerSet after delete elements: ", first);
      
      IntegerSet second = new IntegerSet(1, 2, 3, 4);
      printIntegerSet("second IntegerSet after insert 4 elements: ", second);
      
      compareIntegerSets(first, second);
      second.reset();
      compareIntegerSets(first, second);
      
      first.reset();
      compareIntegerSets(first, second);
      
      for (int element = IntegerSet.MIN; element <= IntegerSet.MAX - 80; element++) {
         first.insertElement(element);
         second.insertElement(++element);
      }
      
      compareIntegerSets(first, second);
      
      for (int element = IntegerSet.MIN; element <= IntegerSet.MAX - 80; element++) {
         second.insertElement(element);
         first.insertElement(++element);
      }
      
      compareIntegerSets(first, second);
      
      first.reset();
      int[] numbers = {98, 87, 100};
      first.setIntegers(numbers);
      printIntegerSet("first IntegerSet after insert 98, 87, 100: ", first);
      
      invalidOperations();
   } 
   
   private static void compareIntegerSets(IntegerSet first, IntegerSet second) {
      System.out.println("--------------- COMPARE INTEGER SETS:");
      printIntegerSet("First ", first);
      printIntegerSet("Second ", second);
      System.out.println("Is first set equals to second? " + IntegerSet.isEqualTo(first, second));
      System.out.println("Intersection of first and second sets " + IntegerSet.intersection(first, second));
      System.out.println("Union of first and second sets  " + IntegerSet.union(first, second));
   }

   private static void printIntegerSet(String header, IntegerSet integerSet) {
      System.out.print(header);
      System.out.println(integerSet);
      System.out.println();
   } 
   
   private static void invalidOperations() {
      try {
         IntegerSet invalidIntegerSet = new IntegerSet(122, 4); // invalid value
         printIntegerSet("IntegerSet after construct: ", invalidIntegerSet);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while initializing integerSet: %s%n",
            e.getMessage());
      } 
      
      try {
         IntegerSet invalidIntegerSet = new IntegerSet(-32, 10); // invalid value
         printIntegerSet("IntegerSet after construct: ", invalidIntegerSet);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while initializing integerSet: %s%n",
            e.getMessage());
      }
      
      IntegerSet integerSet = new IntegerSet(2, 10);
      printIntegerSet("IntegerSet after construct: ", integerSet);
      
      try {
         integerSet.insertElement(999);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while inserting element to integerSet: %s%n",
            e.getMessage());
      } 
      
      try {
         integerSet.deleteElement(-399);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while inserting element to integerSet: %s%n",
            e.getMessage());
      } 
   }  
} 
