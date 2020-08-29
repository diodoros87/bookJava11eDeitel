/* =====================================================================================
 *       Filename:  ColorChooserController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.7 - Controller that handles events generate 
                                by User while manipulating options of color selection
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.text.NumberFormat;
import java.text.Format;

import java.util.Objects;

public class ColorChooserController {
   // instance variables for interacting with GUI components
   private Slider redSlider;
   private Slider greenSlider;
   private Slider blueSlider;
   private Slider alphaSlider;
   private TextField redTextField;  
   private TextField greenTextField;
   private TextField blueTextField; 
   private TextField alphaTextField;
   private Rectangle colorRectangle;
   
   private ColorChooserView sceneCreator = null;

   // instance variables for managing 
   private int red      = 12;
   private int green    = 12;
   private int blue     = 12;
   private double alpha = 0.0;
   
   private static final NumberFormat numberFormat  = NumberFormat.getNumberInstance();
   private static final NumberFormat integerFormat = NumberFormat.getIntegerInstance();
   
   ColorChooserController(ColorChooserView sceneCreator) {
      this.sceneCreator = Objects.requireNonNull(sceneCreator);
      
      redSlider   = sceneCreator.getRedSlider();
      greenSlider = sceneCreator.getGreenSlider();
      blueSlider  = sceneCreator.getBlueSlider();
      alphaSlider = sceneCreator.getAlphaSlider();
      redTextField = sceneCreator.getRedTextField();  
      greenTextField = sceneCreator.getGreenTextField(); 
      blueTextField = sceneCreator.getBlueTextField();
      alphaTextField = sceneCreator.getAlphaTextField();
      colorRectangle = sceneCreator.getColorRectangle();
   }
   
   void initialize() {
      initializeColorValuesBinding();
      initializeSlidersAboutColors();
      
      red      = redSlider.valueProperty().intValue();
      green    = greenSlider.valueProperty().intValue();
      blue     = blueSlider.valueProperty().intValue();
      alpha    = alphaSlider.valueProperty().doubleValue();
      
      setNewColor();
   }
   
   private void initializeColorValuesBinding() {
      // bind TextField values to corresponding Slider values
      initializeColorValuesBinding(redTextField, redSlider, integerFormat);
      initializeColorValuesBinding(greenTextField, greenSlider, integerFormat);
      initializeColorValuesBinding(blueTextField, blueSlider, integerFormat);
      
      numberFormat.setMinimumFractionDigits​​(2);
      numberFormat.setMaximumFractionDigits​(2);
      initializeColorValuesBinding(alphaTextField, alphaSlider, numberFormat);
   }
   
   private void initializeColorValuesBinding(TextField textField, Slider slider, Format format) {
      DoubleProperty sliderValue = slider.valueProperty();
      
      StringProperty stringValue = textField.textProperty();
      stringValue.bindBidirectional(sliderValue, format);
   }
   
   private void initializeSlidersAboutColors() {
      // 4 listeners that set Rectangle's fill based on Slider changes
      redSlider.valueProperty().addListener(                              
         new ChangeListener<Number>() {                                   
            @Override                                                     
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {                        
               red            = newValue.intValue();
               sceneCreator.setRedValue(red);
               setNewColor();
            }
         }
      );                                                                  
      greenSlider.valueProperty().addListener(
         new ChangeListener<Number>() {                                   
            @Override                                                     
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {                        
               green = newValue.intValue();
               sceneCreator.setGreenValue(green);
               setNewColor();
            }
         }
      );                                                                  
      blueSlider.valueProperty().addListener(
         new ChangeListener<Number>() {                                   
            @Override                                                     
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {                        
               blue = newValue.intValue();
               sceneCreator.setBlueValue(blue);
               setNewColor();
            }
         }
      );                                                                  
      alphaSlider.valueProperty().addListener(
         new ChangeListener<Number>() {                                   
            @Override                                                     
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {                        
               alpha = newValue.doubleValue();
               sceneCreator.setAlphaValue(alpha);
               setNewColor();
            }
         }
      );                                                                  
   }
   
   private void setNewColor() {
      Color newColor = Color.rgb(red, green, blue, alpha);                                 
      colorRectangle.setFill(newColor);
   }     
}
