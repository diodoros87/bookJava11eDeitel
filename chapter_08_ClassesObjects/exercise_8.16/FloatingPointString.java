/* =====================================================================================
 *       Filename:  FloatingPointString.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.16 - class to make string from floating 
                                number
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.util.ArrayList;

public class FloatingPointString {
   private double number;
   private int    precision;

   public FloatingPointString(long numerator, long denominator, int precision) {
      if (0 == denominator) {
         throw new IllegalArgumentException("Denominator can not be 0");
      } 
      if (0 > precision) {
         throw new IllegalArgumentException("Requirement: precision >= 0");
      } 
      
      this.number      = (double)numerator / denominator;
      this.precision   = precision;
   }
   
   public String toString() {
      double multipliedNumber = getMultipliedNumber();
      ArrayList<Character> numberCharacterArray = numberToArrayList(multipliedNumber);
      
      setRoundNumberToArrayList(numberCharacterArray, multipliedNumber);
      
      String numberString = getStringFromArrayList(numberCharacterArray);
      return numberString;
   }
   
   private double getMultipliedNumber() {
      final double MAGNITUDE_ORDER = Math.pow(10, precision);
      double multipliedNumber      = number * MAGNITUDE_ORDER;

      return multipliedNumber;
   }
   
   public static String getStringFromArrayList(ArrayList<Character> characterArray) {
      if (null == characterArray) {
         throw new NullPointerException("array list is null");
      }
      
      String result = "";
      for (char element : characterArray) {
         result += element;
      }
      
      return result;
   }
   
   private ArrayList<Character> numberToArrayList(double multipliedNumber) {
      ArrayList<Character> numberCharacterArray = new ArrayList<>();
      int startDigitIndex = 0;
      
      if (this.number < 0) {
         numberCharacterArray.add('-');
         startDigitIndex++;
      }
      else if (this.number > 0) {
         numberCharacterArray.add('+');
         startDigitIndex++;
      }
      
      if (1 > Math.abs(this.number)) {
         numberCharacterArray.add('0');
         startDigitIndex++;
      }
      
      double absoluteNumber = Math.abs(multipliedNumber);
      char character;
      for(int index = 0; (int)absoluteNumber >= 1 || index < this.precision; index++) {
         character = getCharFromLastDigitNumber(absoluteNumber);
         numberCharacterArray.add(startDigitIndex, character);
         absoluteNumber /= 10;
      }

      return numberCharacterArray;
   }
   
   private void setRoundNumberToArrayList(ArrayList<Character> numberCharacterArray, double multipliedNumber) {
      long afterRounding = Math.round(multipliedNumber);
      char lastCharacterAfterRounding = getCharFromLastDigitNumber(afterRounding);

      numberCharacterArray.set(numberCharacterArray.size() - 1, lastCharacterAfterRounding);
      
      if (this.precision > 0) {
         int dotPosition = numberCharacterArray.size() - this.precision;
         numberCharacterArray.add(dotPosition, '.');
      }
   }
   
   public static char getCharFromLastDigitNumber(double number) {
      long absoluteNumber = (long)Math.abs(number);  // cast to long (not int) to cut less digits
      char digitCharacter = (char)((int)'0' + absoluteNumber % 10);
      
      return digitCharacter;
   } 
} 
