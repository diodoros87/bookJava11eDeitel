/* =====================================================================================
 *       Filename:  IntegerSet.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.14 - class of integer numbers set
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class IntegerSet {

   public static final int MIN = 0;
   public static final int MAX = 100;
   
   public static final int MAX_NUMBER_OF_INTEGERS = MAX - MIN + 1;
   
   private boolean integers[]; 
   private int integersInSet;
   
   public IntegerSet() {
      integers = new boolean [MAX_NUMBER_OF_INTEGERS]; 
      integersInSet = 0;
      
      java.util.Arrays.fill(integers, false);
   }
   
   public IntegerSet(int... numbers) {
      this();
      
      for (int element : numbers) {
         insertElement(element);
      }
   }
   
   public void setIntegers(int... numbers) {
      for (int element : numbers) {
         insertElement(element);
      }
   }
   
   public int getIntegersInSet() {
      return integersInSet;
   }
   
   public static IntegerSet union(IntegerSet first, IntegerSet second) {
      IntegerSet union = new IntegerSet();
      
      for (int element = MIN; element <= MAX; element++) {
         if (true == first.integers[element] || true == second.integers[element]) {
            union.insertElement(element);
         }
      }
      
      return union;
   }
   
   public static IntegerSet intersection(IntegerSet first, IntegerSet second) {
      IntegerSet intersection = new IntegerSet();
      
      for (int element = MIN; element <= MAX; element++) {
         if (true == first.integers[element] && true == second.integers[element]) {
            intersection.insertElement(element);
         }
      }
      
      return intersection;
   }
   
   public static boolean isEqualTo(IntegerSet first, IntegerSet second) {
      for (int element = MIN; element <= MAX; element++) {
         if (first.integers[element] != second.integers[element]) {
            return false;
         }
      }
      
      return true;
   }
   
   public void insertElement(int element) {
      validateElement(element);
      
      if (false == integers[element]) {
         integers[element] = true;
         integersInSet++;
      }
   }
   
   public void deleteElement(int element) {
      validateElement(element);
      
      if (true == integers[element]) {
         integers[element] = false;
         integersInSet--;
      }
   }
   
   private void validateElement(int element) {
      if (false == isElementInRange(element)) {
         throw new IllegalArgumentException("Element " + element + " is not in range from " + MIN + " to " + MAX);
      }
   }
   
   public boolean isElementInRange(int element) {
      if (MIN > element || element > MAX) {
         return false;
      }
      
      return true;
   }
   
   public void reset() {
      java.util.Arrays.fill(integers, false);
      integersInSet = 0;
   }
   
   public String toString() {
      String result = String.format("IntegerSet's data: ");
      
      if (0 == integersInSet) {
         result += "---";     // no return to check integersInSet really count number in set
      }
      
      for (int element = MIN; element <= MAX; element++) {
         if (true == integers[element]) {
            result += " " + element;
         }
      }
      
      result += String.format("%n");
      result += "In integer set are " + getIntegersInSet() + " elements";
      
      return result;
   } 
} 
