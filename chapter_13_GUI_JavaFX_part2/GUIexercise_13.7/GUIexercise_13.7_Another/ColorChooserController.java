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
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.text.NumberFormat;
import java.text.Format;

public class ColorChooserController {
   // instance variables for interacting with GUI components
   @FXML private Slider redSlider;
   @FXML private Slider greenSlider;
   @FXML private Slider blueSlider;
   @FXML private Slider alphaSlider;
   @FXML private TextField redTextField;  
   @FXML private TextField greenTextField;
   @FXML private TextField blueTextField; 
   @FXML private TextField alphaTextField;
   @FXML private Rectangle colorRectangle;

   // instance variables for managing 
   private int red      = 12;
   private int green    = 12;
   private int blue     = 12;
   private double alpha = 0.0;
   
   private static final NumberFormat numberFormat  = NumberFormat.getNumberInstance();
   private static final NumberFormat integerFormat = NumberFormat.getIntegerInstance();
   
   public void initialize() {
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
               setNewColor();
            }
         }
      );                                                                  
      greenSlider.valueProperty().addListener(
         new ChangeListener<Number>() {                                   
            @Override                                                     
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {                        
               green = newValue.intValue();
               setNewColor();
            }
         }
      );                                                                  
      blueSlider.valueProperty().addListener(
         new ChangeListener<Number>() {                                   
            @Override                                                     
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {                        
               blue = newValue.intValue();
               setNewColor();
            }
         }
      );                                                                  
      alphaSlider.valueProperty().addListener(
         new ChangeListener<Number>() {                                   
            @Override                                                     
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {                        
               alpha = newValue.doubleValue();
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
