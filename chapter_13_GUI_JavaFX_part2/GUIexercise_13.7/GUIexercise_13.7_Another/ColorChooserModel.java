/* =====================================================================================
 *       Filename:  ColorChooserModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.7 - class that create scene to
                                 the ColorChooser GUI (view component of MVC pattern)
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Objects;

public class ColorChooserModel {
   ColorComponent red   = new ColorComponent(0, "red");
   ColorComponent green = new ColorComponent(0, "green");
   ColorComponent blue  = new ColorComponent(0, "blue");
   
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
   
   String getRedName() {
      return red.name;
   }
   
   int getGreenValue() {
      return green.value;
   }
   
   String getGreenName() {
      return green.name;
   }
   
   int getBlueValue() {
      return blue.value;
   }
   
   String getBlueName() {
      return blue.name;
   }
   
   private class ColorComponent {
      private static final int    MIN = 0;
      private static final int    MAX = 255;
      private static final int    INCREMENT = 1;
      
      private int    value = 0;
      private String name  = null;
      
      private ColorComponent(int value, String name) {
         validateName(name);
         validateValue(value);
         this.value = value;
         this.name  = name;
      }
      
      private final void validateName(String name) {
         Objects.requireNonNull(name);
      }
      
      private final void validateValue(int value) {
         if (value < MIN || value > MAX) {
            throw new IllegalArgumentException("value must be in range from " + MIN + " to " + MAX);
         }
      }
   } 
   
}
