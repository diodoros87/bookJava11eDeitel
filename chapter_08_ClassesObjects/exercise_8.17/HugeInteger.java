/* =====================================================================================
 *       Filename:  HugeInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.17 - huge integer class 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Arrays;

public class HugeInteger {
   public static final byte MAX_ARRAY_LENGTH = 40; 
   
   private byte[] integerArray;
   private byte   signum;   // less than 0 for integers < 0, more than 0 for integers > 0, 0 for 0 
   
   public HugeInteger() {
      integerArray = new byte[MAX_ARRAY_LENGTH];
      
      for (byte index = 0; index < MAX_ARRAY_LENGTH; index++) {
         integerArray[index] = 0;
      }
      
      signum = 0;
   }
   
   public HugeInteger(byte[] integerArray, byte signum) {
      this();
      
      if (null == integerArray) {
         throw new NullPointerException("Requirement: reference to array can not be null");
      }
      if (MAX_ARRAY_LENGTH < integerArray.length) {
         throw new IllegalArgumentException(String.format("Requirement: array.length <= %d", MAX_ARRAY_LENGTH));
      }
      
      byte number;
      byte numberOfZeros = 0;
      for (int sourceIndex = integerArray.length - 1; sourceIndex >= 0 ; sourceIndex--) {
         number = integerArray[sourceIndex];
         if (number == 0) {
            numberOfZeros++;
         }
         else if (number < 0 || number > 9) {
            throw new IllegalArgumentException("Requirement: in array must be only integers from 0 to 9");
         }
      }
      if (numberOfZeros == integerArray.length && signum != 0) {
         throw new IllegalArgumentException("Requirement: signum must be zero for array with only zeros");
      }
      if (numberOfZeros < integerArray.length && signum == 0) {
         throw new IllegalArgumentException("Requirement: signum can not be zero for array with element other than zero");
      }
      
      byte destIndex = MAX_ARRAY_LENGTH - 1;
      for (int sourceIndex = integerArray.length - 1; sourceIndex >= 0 ; sourceIndex--) {
         number = integerArray[sourceIndex];
         this.integerArray[destIndex] = number;
         destIndex--;
      }
      
      this.signum = signum;
   } 
   
   private HugeInteger(byte[] integerArray) {
      this();
      
      if (null == integerArray) {
         throw new NullPointerException("Requirement: reference to array can not be null");
      }
      if (MAX_ARRAY_LENGTH < integerArray.length) {
         throw new IllegalArgumentException(String.format("Requirement: array.length <= %d", MAX_ARRAY_LENGTH));
      }
      
      byte number;
      byte numberOfZeros = 0;
      byte destIndex = MAX_ARRAY_LENGTH - 1;
      for (int sourceIndex = integerArray.length - 1; sourceIndex >= 0 ; sourceIndex--) {
         number = integerArray[sourceIndex];
         if (number == 0) {
            numberOfZeros++;
         }
         else if (number < 0 || number > 9) {
            throw new IllegalArgumentException("Requirement: in array must be only integers from 0 to 9");
         }
         
         this.integerArray[destIndex] = number;
         destIndex--;
      }
      if (numberOfZeros == integerArray.length) {
         this.signum = 0;
      }
      if (numberOfZeros < integerArray.length) {
         this.signum = +1;
      }
   }
   
   public HugeInteger(HugeInteger hugeInteger) {
      this(hugeInteger.integerArray, hugeInteger.signum);
   }
   
   public void set(HugeInteger hugeInteger) {
      if (null == hugeInteger) {
         throw new NullPointerException("Requirement: reference to HugeInteger can not be null");
      }
      
      System.arraycopy(hugeInteger.integerArray, 0, this.integerArray, 0, integerArray.length);
      this.signum = hugeInteger.signum;
   }
   
   public void setIntegerArray(byte[] integerArray) {  // assume that integerArray is positive number, change of signum is allowed by setSignum()
      if (null == integerArray) {
         throw new NullPointerException("Requirement: reference to array can not be null");
      }
      if (MAX_ARRAY_LENGTH < integerArray.length) {
         throw new IllegalArgumentException(String.format("Requirement: array.length <= %d", MAX_ARRAY_LENGTH));
      }
      
      Arrays.fill(this.integerArray, (byte)0);  // not call resetNumberToZero() due to change signum (in setIntegerArray() save previous signum)
      
      byte number;
      byte destIndex = MAX_ARRAY_LENGTH - 1;
      for (int sourceIndex = integerArray.length - 1; sourceIndex >= 0 ; sourceIndex--) {
         number = integerArray[sourceIndex];
         
         if (number < 0 || number > 9) {
         
            resetNumberToZero();    // similar to constructor, reset to zero
            throw new IllegalArgumentException("Requirement: in array must be only integers from 0 to 9");
         }
         this.integerArray[destIndex] = number;
         destIndex--;
      }
      
      if (this.signum == 0 && false == isZero()) {
         this.signum = +1;
      }
      else if (this.signum != 0 && true == isZero()) {
         this.signum = 0;
      }
   }
   
   public byte[] getIntegerArrayCopy() {
      byte[] integerArrayCopy = new byte[MAX_ARRAY_LENGTH];
      System.arraycopy(this.integerArray, 0, integerArrayCopy, 0, MAX_ARRAY_LENGTH);
      
      return integerArrayCopy;
   }

   public void validateSignum(byte signum) {
      if (signum == 0 && false == isZero()) {
         throw new IllegalArgumentException("Requirement: can not set signum to zero for nonzero integer");
      }
      else if (signum != 0 && true == isZero()) {
         throw new IllegalArgumentException("Requirement: can not set signum to other than zero for zero integer");
      }
   }
   
   public void setSignum(byte signum) {
      validateSignum(signum);
      
      this.signum = signum;
   }
   
   public byte getSignum() {
      return signum;
   }
   
   public void resetNumberToZero() {
      signum = 0;
      Arrays.fill(integerArray, (byte)0);
   }
   
   public void parse(String string) {
      HugeIntegerParsing.validateString(string);
      
      resetNumberToZero();
      byte iterationStopIndex = 0;
      char character = string.charAt(0);
      if (character == '+') {
         this.signum = +1;
         iterationStopIndex++;
      }
      else if (character == '-') {
         this.signum = -1;
         iterationStopIndex++;
      }
      
      for (int stringIndex = string.length() - 1, arrayIndex = MAX_ARRAY_LENGTH - 1;
                           stringIndex >= iterationStopIndex; stringIndex--, arrayIndex--) {

         character = string.charAt(stringIndex);
         this.integerArray[arrayIndex] = (byte)(character - '0');
      }
      
      if (true == isZero()) {
         this.signum = 0;
      }
      else if (this.signum != -1) {   // string without leading char '+' or '-' is '+'
         this.signum = +1;
      }
   }
   
   public boolean isZero() {
      return HugeInteger.isZero(this);
   }
   
   public static boolean isZero(HugeInteger hugeInteger) {
      for (byte digit : hugeInteger.integerArray) {
         if (0 != digit) {
            //assert(hugeInteger.signum != 0);
            
            return false;
         }
      }
      
      //assert(hugeInteger.signum == 0);
      return true;
   }
   
   public byte compareSignum(HugeInteger hugeInteger) {
      return HugeInteger.compareSignum(this, hugeInteger);
   }
   
   public static byte compareSignum(HugeInteger first, HugeInteger second) {
      if (first.signum > 0) {
         if (second.signum > 0) {
            return 0;
         }
         else {  // second.signum <= 0
            return +1;
         }
      }
      else if (first.signum == 0) {
         if (second.signum > 0) {
            return -1;
         }
         else if (second.signum == 0) {
            return 0;
         }
         else { // second.signum < 0
            return +1;
         }
      }
      else {  // first.signum < 0
         if (second.signum >= 0) {
            return -1;
         }
         else { // second.signum < 0
            return 0;
         }
      }
   }
   
   public boolean isEqualTo(HugeInteger hugeInteger) {
      return HugeInteger.isEqualTo(this, hugeInteger);
   }
   
   public static boolean isEqualTo(HugeInteger first, HugeInteger second) {
      byte firstIntegerDigit;
      byte secondIntegerDigit;
      for (byte index = 0; index < first.integerArray.length; index++) {
         firstIntegerDigit  = first.integerArray[index];
         secondIntegerDigit = second.integerArray[index];
         
         if (firstIntegerDigit != secondIntegerDigit) {
            return false;
         }
      }
      
      byte comparingSignumResult = HugeInteger.compareSignum(first, second);
      if (0 != comparingSignumResult) {
         return false;
      }
      
      //assert(0 == comparingSignumResult);
      return true;
   }
   
   public boolean isNotEqualTo(HugeInteger hugeInteger) {
      return HugeInteger.isNotEqualTo(this, hugeInteger);
   }
   
   public static boolean isNotEqualTo(HugeInteger first, HugeInteger second) {
      boolean notEquality = ! isEqualTo(first, second);
      
      return notEquality;
   }
   
   public boolean isGreaterThan(HugeInteger hugeInteger) throws Exception {
      return HugeInteger.isGreaterThan(this, hugeInteger);
   }
   
   public static boolean isGreaterThan(HugeInteger first, HugeInteger second) throws Exception {
      byte comparingSignumResult = HugeInteger.compareSignum(first, second);
      if (-1 == comparingSignumResult) {
         return false;
      }
      else if (+1 == comparingSignumResult) {
         return true;
      }
      // after calling HugeInteger.compareSignum recognize both integers with identical nonzero signum 
      
      assert(first.signum == second.signum);
      assert(first.signum != 0);
      byte identicalIntegersSignum = first.signum; 
      
      if (identicalIntegersSignum > 0) {
         return isAbsoluteValueGreaterThan(first, second);
      }
      else if (identicalIntegersSignum < 0) {
         return isAbsoluteValueLessThan(first, second);
      }
      else {  // identicalIntegersSignum == 0
         throw new Exception("signum of integer = 0");
      }
   }
   
   public static boolean isAbsoluteValueGreaterThan(HugeInteger first, HugeInteger second) {
      byte firstIntegerDigit;
      byte secondIntegerDigit;
      for (byte index = 0; index < first.integerArray.length; index++) {
         firstIntegerDigit  = first.integerArray[index];
         secondIntegerDigit = second.integerArray[index];
         
         if (firstIntegerDigit > secondIntegerDigit) {
            return true;
         }
         else if (firstIntegerDigit < secondIntegerDigit) {
            return false;
         }
      }
      
      return false;
   }
   
   public static boolean isAbsoluteValueLessThan(HugeInteger first, HugeInteger second) {
      byte firstIntegerDigit;
      byte secondIntegerDigit;
      for (byte index = 0; index < first.integerArray.length; index++) {
         firstIntegerDigit  = first.integerArray[index];
         secondIntegerDigit = second.integerArray[index];
         
         if (firstIntegerDigit < secondIntegerDigit) {
            return true;
         }
         else if (firstIntegerDigit < secondIntegerDigit) {
            return false;
         }
      }
      
      return false;
   }
   
   public boolean isLessThanOrEqualTo(HugeInteger hugeInteger) throws Exception {
      return HugeInteger.isLessThanOrEqualTo(this, hugeInteger);
   }
   
   public static boolean isLessThanOrEqualTo(HugeInteger first, HugeInteger second) throws Exception {
      boolean notGreaterThan = ! isGreaterThan(first, second);
      
      return notGreaterThan;
   }
   
   public boolean isLessThan(HugeInteger hugeInteger) throws Exception {
      return HugeInteger.isLessThan(this, hugeInteger);
   }
   
   public static boolean isLessThan(HugeInteger first, HugeInteger second) throws Exception {
      byte comparingSignumResult = HugeInteger.compareSignum(first, second);
      if (-1 == comparingSignumResult) {
         return true;
      }
      else if (+1 == comparingSignumResult) {
         return false;
      }
      // after calling HugeInteger.compareSignum recognize both integers with identical nonzero signum 
      
      assert(first.signum == second.signum);
      assert(first.signum != 0);
      byte identicalIntegersSignum = first.signum; 
      
      if (identicalIntegersSignum < 0) {
         return isAbsoluteValueGreaterThan(first, second);
      }
      else if (identicalIntegersSignum > 0) {
         return isAbsoluteValueLessThan(first, second);
      }
      else {  // identicalIntegersSignum == 0
         throw new Exception("signum of integer = 0");
      }
   }
   
   public boolean isGreaterThanOrEqualTo(HugeInteger hugeInteger) throws Exception {
      return HugeInteger.isGreaterThanOrEqualTo(this, hugeInteger);
   }
   
   public static boolean isGreaterThanOrEqualTo(HugeInteger first, HugeInteger second) throws Exception {
      boolean notLessThan = ! isLessThan(first, second);
      
      return notLessThan;
   }
   
   public void add(HugeInteger hugeInteger) {
      set(HugeInteger.add(this, hugeInteger));
   }
   
   public static HugeInteger add(HugeInteger first, HugeInteger second) {
      HugeInteger result;
      byte comparingSignumResult = HugeInteger.compareSignum(first, second);
      
      if (0 == comparingSignumResult) {    // integers with identical signums
         result = addAbsoluteValues(first, second);
      }
      else {  // integers with different signums
         result = HugeInteger.subtractAbsoluteValues(first, second);
      }
      
      setSignumForAddingNonZeroResult(first, second, result, comparingSignumResult);
      
      return result;
   }
   
   private static void setSignumForAddingNonZeroResult(HugeInteger first, HugeInteger second,
                                          HugeInteger result, byte comparingSignumResult) {
      if (result.signum == 0) {   // skip setSignum() for zero, zero has correct signum = 0 in private HugeInteger(byte[]) constructor
         return;
      }
      
      if (0 == comparingSignumResult) {        // integers with identical signums
         result.setSignum(first.signum);
      }
      else {  // integers with different signums
         if (true == isAbsoluteValueGreaterThan(first, second)) {
            result.setSignum(first.signum);
         }
         else {
            result.setSignum(second.signum);
         }
      }
   }
   
   public void subtract(HugeInteger hugeInteger) {
      set(HugeInteger.subtract(this, hugeInteger));
   }
   
   public static HugeInteger subtract(HugeInteger first, HugeInteger second) {
      HugeInteger result;
      byte comparingSignumResult = HugeInteger.compareSignum(first, second);
      
      if (0 == comparingSignumResult) {    // integers with identical sigmums
         result = subtractAbsoluteValues(first, second);
      }
      else {  // integers with different sigmums
         result = HugeInteger.addAbsoluteValues(first, second);
      }
      
      setSignumForSubtractingNonZeroResult(first, second, result, comparingSignumResult);
      
      return result;
   }
   
   private static void setSignumForSubtractingNonZeroResult(HugeInteger first, HugeInteger second,
                                          HugeInteger result, byte comparingSignumResult) {
      if (result.signum == 0) {   // skip setSignum() for zero, zero has correct signum = 0 in private HugeInteger(byte[]) constructor
         return;
      }
      
      if (0 == comparingSignumResult) {        // integers with identical signums
         if (true == isAbsoluteValueGreaterThan(first, second)) {
            result.setSignum(first.signum);
         }
         else {
            result.setSignum((byte)(-second.signum));
         }
      }
      else {  // integers with different signums
         if (0 == first.signum) {   // if minuend is zero (with signum zero), then signum's result is negate of subtrahend's signum
            result.setSignum((byte)(-second.signum));
         }
         else {
            result.setSignum(first.signum);
         }
      }
   }
   
   public static HugeInteger multiply(HugeInteger first, HugeInteger second) {
      HugeInteger result = multiplyAbsoluteValues(first, second);
      if (result.signum == 0) {   // skip setSignum() for zero, zero has correct signum = 0 in private HugeInteger(byte[]) constructor
         return result;
      }
      
      byte comparingSignumResult = HugeInteger.compareSignum(first, second);
      if (0 == comparingSignumResult) {        // integers with identical signums
         result.setSignum((byte)(+1));
      }
      else {  // integers with different signums
         result.setSignum((byte)(-1));
      }
      
      return result;
   }
   
   public static HugeInteger divide(HugeInteger first, HugeInteger second) {
      if (true == second.isZero()) {
         throw new ArithmeticException("Divisor can not be zero");
      }
      
      HugeInteger result = divideAbsoluteValues(first, second);
      if (result.signum == 0) {   // skip setSignum() for zero, zero has correct signum = 0 in private HugeInteger(byte[]) constructor
         return result;
      }
      
      byte comparingSignumResult = HugeInteger.compareSignum(first, second);
      if (0 == comparingSignumResult) {        // integers with identical signums
         result.setSignum((byte)(+1));
      }
      else {  // integers with different signums
         result.setSignum((byte)(-1));
      }
      
      return result;
   }
   
   public static HugeInteger addAbsoluteValues(HugeInteger first, HugeInteger second) {
      byte[] resultArray = new byte[MAX_ARRAY_LENGTH];
      int firstIntegerDigit;
      int secondIntegerDigit;
      int sumOfDigits = 0;
      int carrying = 0;
      for (int index = first.integerArray.length - 1; index >= 0; index--) {
         firstIntegerDigit  = first.integerArray[index];
         secondIntegerDigit = second.integerArray[index];
         sumOfDigits = firstIntegerDigit + secondIntegerDigit;
         
         resultArray[index] = (byte)((sumOfDigits + carrying) % 10);
         carrying = (sumOfDigits + carrying) / 10 ;
      }
      
      if (carrying > 0) {
         throw new ArithmeticException
                  (String.format("Arithmetic overflow while adding absolute value of %s and %s", first , second));
      }
      
      HugeInteger result = new HugeInteger(resultArray);
      return result;
   }
   
   public static HugeInteger subtractAbsoluteValues(HugeInteger first, HugeInteger second) {
      HugeInteger minuend    = getMaxOfAbsoluteValues(first, second);
      HugeInteger subtrahend = (first == minuend) ? second : first;
   
      byte[] resultArray = new byte[MAX_ARRAY_LENGTH];
      int minuendDigit;
      int subtrahendDigit;
      int differenceOfDigits = 0;
      int carrying = 0;
      for (int index = first.integerArray.length - 1; index >= 0; index--) {
         minuendDigit    = minuend.integerArray[index];
         subtrahendDigit = subtrahend.integerArray[index];
         differenceOfDigits = minuendDigit - subtrahendDigit;
         
         resultArray[index] = (byte)((differenceOfDigits - carrying) % 10);
         if (differenceOfDigits < 0) {
            resultArray[index] += 10;
            carrying = 1;
         }
         else {
            carrying = 0;
         }
      }
      
      HugeInteger result = new HugeInteger(resultArray);
      return result;
   }
   
   public static HugeInteger multiplyAbsoluteValues(HugeInteger first, HugeInteger second) {
      detectMultiplicationArithmeticOverflow(first, second);
      
      byte[][] resultArray = new byte[MAX_ARRAY_LENGTH][MAX_ARRAY_LENGTH];
      HugeInteger[] hugeIntegersArray = new HugeInteger[MAX_ARRAY_LENGTH];
      int firstIntegerDigit;
      int secondIntegerDigit;
      int productOfDigits = 0;
      int carrying = 0;
      int orderOfMagnitude = 0;
      int columnInResultArray;
      
      for (int firstIndex = first.integerArray.length - 1; firstIndex >= 0; firstIndex--) {
         carrying = 0;
         firstIntegerDigit = first.integerArray[firstIndex];
         
         for (int secondIndex = second.integerArray.length - 1; secondIndex >= 0; secondIndex--) {
            columnInResultArray = secondIndex - orderOfMagnitude;
            if (columnInResultArray < 0) {
               detectMultiplicationArithmeticOverflow(carrying, first, second);
               break;
            }
            
            secondIntegerDigit = second.integerArray[secondIndex];
            productOfDigits = firstIntegerDigit * secondIntegerDigit;
            
            resultArray[firstIndex][columnInResultArray] = (byte)((productOfDigits + carrying) % 10);
            carrying = (productOfDigits + carrying) / 10;
         }
         
         
         hugeIntegersArray[firstIndex] = new HugeInteger(resultArray[firstIndex]);
         orderOfMagnitude++;
      }
      
      detectMultiplicationArithmeticOverflow(carrying, first, second);
      
      HugeInteger result = sumOfMultiplicationResults(hugeIntegersArray);
      
      return result;
   }
   
   private static HugeInteger sumOfMultiplicationResults(HugeInteger[] hugeIntegersArray) {
      HugeInteger result = new HugeInteger();
      for (int index = hugeIntegersArray.length - 1; index >= 0; index--) {
         result = addAbsoluteValues(result, hugeIntegersArray[index]);
      }
      
      return result;
   }
   
   private static HugeInteger getMaxOfAbsoluteValues(HugeInteger first, HugeInteger second) {
      boolean firstGreaterThanSecond = isAbsoluteValueGreaterThan(first, second);
      if (true == firstGreaterThanSecond) {
         return first;
      }
      else {
         return second;
      }
   }
   
   private static void detectMultiplicationArithmeticOverflow(int carrying, HugeInteger first, HugeInteger second) {
      if (carrying > 0) {
         throw new ArithmeticException
               (String.format("Arithmetic overflow while multiplying absolute value of %s and %s", first , second));
      }
   }
   
   public static void detectMultiplicationArithmeticOverflow(HugeInteger first, HugeInteger second) {
      int firstIntegerDigit;
      int secondIntegerDigit;
      
      for (int firstIndex = 0; firstIndex < first.integerArray.length; firstIndex++) {
         firstIntegerDigit = first.integerArray[firstIndex];
         if (firstIntegerDigit > 0) {
         
            for (int secondIndex = 0; secondIndex < second.integerArray.length; secondIndex++) {
               secondIntegerDigit = second.integerArray[secondIndex];
               
               if (secondIntegerDigit > 0) {
                  int firstOrderOfMagnitude  = first.integerArray.length - 1 - firstIndex;
                  int secondOrderOfMagnitude = second.integerArray.length - 1 - secondIndex;
                  
                  if (firstOrderOfMagnitude + secondOrderOfMagnitude >= MAX_ARRAY_LENGTH) {
                     throw new ArithmeticException
                        (String.format("Arithmetic overflow while multiplying absolute value of %s and %s", first , second));
                  }
               }
            }
            
         }
      }
   }
   
   public void negate() {
      this.signum = (byte)(-this.signum);
   }
   
   public static HugeInteger negate(HugeInteger hugeInteger) {
      byte negateSignum = (byte)(-hugeInteger.signum);
      
      return new HugeInteger(hugeInteger.integerArray, negateSignum);
   }
   
   @Override
   public String toString() {
      String result = "";
      
      if (this.signum > 0) {
         result += "+";
      } else if (this.signum < 0) {
         result += "-";
      }
      
      Byte number;
      byte skippedValue = 0;
      byte index = HugeIntegerParsing.skipLeadingIntegersWithValue(this.integerArray, skippedValue);

      while (index < MAX_ARRAY_LENGTH) {
         number = this.integerArray[index];
         result += number.toString();
         index++;
      }

      return result;
   }  
} 
