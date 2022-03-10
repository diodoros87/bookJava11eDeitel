/* =====================================================================================
 *       Filename:  ColorChooserModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.7 - class that create data to
                                 the ColorChooser GUI (model component of MVC pattern)
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Objects;

public class ColorChooserModel {
   ColorComponent red   = new ColorComponent(55, "red");
   ColorComponent green = new ColorComponent(7, "green");
   ColorComponent blue  = new ColorComponent(220, "blue");
   AlphaComponent alpha = new AlphaComponent(0.40);
   
   static int getColorComponentMin() {
      return ColorComponent.MIN;
   }
   
   static int getColorComponentMax() {
      return ColorComponent.MAX;
   }
   
   static int getColorComponentIncrement() {
      return ColorComponent.INCREMENT;
   }
   
   int getRedValue() {
      return red.value;
   }
   
   void setRedValue(int value) {
      red.setValue(value);
   }
   
   String getRedName() {
      return red.NAME;
   }
   
   int getGreenValue() {
      return green.value;
   }
   
   void setGreenValue(int value) {
      green.setValue(value);
   }
   
   String getGreenName() {
      return green.NAME;
   }
   
   int getBlueValue() {
      return blue.value;
   }
   
   void setBlueValue(int value) {
      blue.setValue(value);
   }
   
   String getBlueName() {
      return blue.NAME;
   }
   
   static double getAlphaComponentMin() {
      return AlphaComponent.MIN;
   }
   
   static double getAlphaComponentMax() {
      return AlphaComponent.MAX;
   }
   
   static double getAlphaComponentIncrement() {
      return AlphaComponent.INCREMENT;
   }
   
   static String getAlphaComponentName() {
      return AlphaComponent.NAME;
   }
   
   void setAlphaValue(double value) {
      alpha.setValue(value);
   }
   
   double getAlphaValue() {
      return alpha.value;
   }
   
   private class AlphaComponent {
      private static final double    MIN = 0.0;
      private static final double    MAX = 1.0;
      private static final double    INCREMENT = 0.01;
      private static final String    NAME  = "alpha";
      
      private double    value = 0.0;
      
      private AlphaComponent(double value) {
         validateValue(value);
         this.value = value;
      }
      
      private final void setValue(double value) {
         validateValue(value);
         this.value = value;
      }
      
      private final void validateValue(double value) {
         if (value < MIN || value > MAX) {
            throw new IllegalArgumentException("value must be in range from " + MIN + " to " + MAX);
         }
      }
   }
   
   private class ColorComponent {
      private static final int    MIN = 0;
      private static final int    MAX = 255;
      private static final int    INCREMENT = 1;
      
      private int    value = 0;
      private final  String NAME;
      
      private ColorComponent(int value, String name) {
         validateName(name);
         validateValue(value);
         this.value = value;
         this.NAME  = name;
      }
      
      private final void validateName(String name) {
         Objects.requireNonNull(name);
      }
      
      private final void setValue(int value) {
         validateValue(value);
         this.value = value;
      }
      
      private final void validateValue(double value) {
         if (value < MIN || value > MAX) {
            throw new IllegalArgumentException("value must be in range from " + MIN + " to " + MAX);
         }
      }
   } 
   
}
