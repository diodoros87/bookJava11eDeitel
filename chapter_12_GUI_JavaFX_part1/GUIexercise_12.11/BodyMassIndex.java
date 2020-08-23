/* =====================================================================================
 *       Filename:  BodyMassIndex.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 12.11 - class to calculate body mass index
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
import java.util.Objects;

public class BodyMassIndex { 
   private static final RoundingMode ROUNDING_MODE  = RoundingMode.HALF_UP;
   private static final int PRECISION = 30;
   private static final MathContext MATH_CONTEXT  = new MathContext(PRECISION, ROUNDING_MODE);
   
   private static final BigDecimal BMI_METRIC_TO_IMPERIAL = new BigDecimal(703); 
   
   private MeasurementSystem measurementSystem = MeasurementSystem.METRIC;
   private BigDecimal weight;
   private BigDecimal height;
   
   public BodyMassIndex() {}
   
   public BodyMassIndex(BigDecimal weight, BigDecimal height, MeasurementSystem measurementSystem) {
      Objects.requireNonNull(weight);
      Objects.requireNonNull(height);
      Objects.requireNonNull(measurementSystem);
      
      validateGreaterThanZeroNumber(weight, "weight");
      validateGreaterThanZeroNumber(height, "height");
      
      this.measurementSystem = measurementSystem;
      this.weight = weight;
      this.height = height;
   }
   
   public BigDecimal getWeight() {
      return weight;
   }
   
   public void setWeight(BigDecimal weight) {
      Objects.requireNonNull(weight);
      
      validateGreaterThanZeroNumber(weight, "weight");
      
      this.weight = weight;
   }
   
   public BigDecimal getHeight() {
      return height;
   }
   
   public void setHeight(BigDecimal height) {
      Objects.requireNonNull(height);
      
      validateGreaterThanZeroNumber(height, "height");
      
      this.height = height;
   }
   
   public MeasurementSystem getMeasurementSystem() {
      return measurementSystem;
   }
   
   public void setMeasurementSystem(MeasurementSystem measurementSystem) {
      Objects.requireNonNull(measurementSystem);
      
      this.measurementSystem = measurementSystem;
   }
   
   public BigDecimal calculateBMI() {
      BigDecimal squareHeight = height.pow(2, MATH_CONTEXT);
      BigDecimal bmi          = divide(weight, squareHeight);

      if (MeasurementSystem.IMPERIAL == measurementSystem) {
         bmi = bmi.multiply(BMI_METRIC_TO_IMPERIAL);
      }
      
      return bmi;
   }
   
   public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
      Objects.requireNonNull(dividend);
      Objects.requireNonNull(divisor);
      
      BigDecimal result = null;
      
      try {
         result = dividend.divide(divisor, MATH_CONTEXT);
      }
      catch (ArithmeticException exception) {
         System.err.printf("%n%s%n", exception);
         exception.printStackTrace();
         
         throw new ArithmeticException("Divide by zero is forbidden");
      }
      
      return result;
   }
   
   public static void validateGreaterThanZeroNumber(BigDecimal number, String name) {
      Objects.requireNonNull(number);
      Objects.requireNonNull(name);
      if (+1 != number.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException(name + " must be > 0");
      }
   }
}
