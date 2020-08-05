/* =====================================================================================
 *       Filename:  ExceptionsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 11.19 - throwing exception in constructor while
                             trying set of illegal argument
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class ExceptionsTest {
   public static void main(String[] args) {
      SomeClass someClassObject = null;
      
      for (int number = -1; number < 111 && null == someClassObject; number++) {
         try {
            someClassObject = new SomeClass(number); 
         } 
         catch (IllegalArgumentException exception) { 
            System.err.println(exception);
            exception.printStackTrace();
         }
      }
      
      if (null != someClassObject) {
         System.out.println(someClassObject.number + " is legal argument");
      }
      
      OtherClass otherClassObject = new OtherClass(+44);
      System.out.println(otherClassObject.number + " is legal argument");
      
      otherClassObject = new OtherClass(+5);
      System.out.println(otherClassObject.number + " is legal argument");
       
   }
   
} 

class SomeClass {
   int number = 99;
   
   SomeClass(int number) {
      validateNumber(number);
      
      this.number = number;
   }
   
   static void validateNumber(int number) {
      if (0 == number || 1 == Math.abs(number)) {
         throw new IllegalArgumentException(number + " is not legal argument");
      }
   }
   
}

class OtherClass {
   int number = 5;
   
   OtherClass(int number) {
      try {
         if (5 != number && 1 != Math.abs(number)) {
            throw new IllegalArgumentException(number + " is not legal argument");
         }
      }
      catch (IllegalArgumentException exception) { 
         System.err.println(exception);
         exception.printStackTrace();
         number = +1;
      }
      finally {
         this.number = number;
      }
      
      System.out.println(this.number + " is legal argument");
   }
   
}
