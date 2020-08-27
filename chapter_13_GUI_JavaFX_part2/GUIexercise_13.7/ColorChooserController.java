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
   private int red      = 0;
   private int green    = 0;
   private int blue     = 0;
   private double alpha = 1.0;
   
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
      bindSlidersToTextFields();
      bindTextFieldsToSliders();
   }
   
   private void bindSlidersToTextFields() {
      bindSliderToTextField(redTextField, redSlider, "%.0f");
      bindSliderToTextField(greenTextField, greenSlider, "%.0f");
      bindSliderToTextField(blueTextField, blueSlider, "%.0f");
      bindSliderToTextField(alphaTextField, alphaSlider, "%.2f");
   }
   
   private void bindTextFieldsToSliders() {
      bindTextFieldToSlider(redTextField, redSlider);
      bindTextFieldToSlider(greenTextField, greenSlider);
      bindTextFieldToSlider(blueTextField, blueSlider);
      bindTextFieldToSlider(alphaTextField, alphaSlider);
   }
   
   private void bindSliderToTextField(final TextField TEXT_FIELD, final Slider SLIDER, String format) {
      DoubleProperty property = SLIDER.valueProperty();
      property.addListener(
         new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
               double number = SLIDER.getValue();
               String numberString = String.format(format, number);
               TEXT_FIELD.setText(numberString);
            }
         }
      );
   }
   
   private void bindTextFieldToSlider(final TextField TEXT_FIELD, final Slider SLIDER) {  // START OF BODY OF FUNCTION 
      StringProperty property = TEXT_FIELD.textProperty();
      
      property.addListener(  // START OF CALL OF FUNCTION 
      
         new ChangeListener<String>() {    // START OF ANONYMOUS INNER CLASS
            private final double MIN  = SLIDER.getMin();
            private final double MAX  = SLIDER.getMax();
            
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
               if (false == isErrorString(newValue)) {
                  double number;
                  
                  if (TEXT_FIELD == alphaTextField) {
                     number = getBindingDoubleValue(TEXT_FIELD, newValue);
                  }
                  else {
                     number = getBindingIntegerValue(TEXT_FIELD);
                  }
                  
                  setCorrectSliderValue(number);
               }
            }
            
            private double getBindingDoubleValue(final TextField TEXT_FIELD, String newValue) {
               double number;
               
               try {
                  number = Double.parseDouble(newValue);
               } 
               catch (NumberFormatException exception) {
                  requestToEnterNumber(TEXT_FIELD, "Enter number");
                  
                  throw exception;
               }
               
               return number;
            }
            
            private void setCorrectSliderValue(double number) {
               try {
                  validateBindingDoubleValue(number);
               }
               catch (IllegalArgumentException exception) {
                  requestToEnterNumber(TEXT_FIELD, exception.getMessage());
                  
                  throw exception;
               }
               
               SLIDER.setValue(number);
            }
                     
            private boolean isErrorString(String string) {
               String minString = "number must be >= " + MIN;
               String maxString = "number must be <= " + MAX;
               
               if (string.equals(minString) || string.equals(maxString)) {
                  return true;
               }
               
               return false;
            }
            
            private void validateBindingDoubleValue(double number) {
               if (number < MIN) {
                  throw new IllegalArgumentException("number must be >= " + MIN);
               }
               else if (number > MAX) {
                  throw new IllegalArgumentException("number must be <= " + MAX);
               }
            }
            
            private int getBindingIntegerValue(final TextField TEXT_FIELD) {
               String text = TEXT_FIELD.getText();
               int number;
               
               try {
                  number = Integer.parseInt(text);
               } 
               catch (NumberFormatException exception) {
                  requestToEnterNumber(TEXT_FIELD, "Enter integer");
                  
                  throw exception;
               }
               
               return number;
            }
         }  // END OF ANONYMOUS INNER CLASS
      ); // END OF CALL OF FUNCTION 
   } // END OF BODY OF ANOTHER FUNCTION 
   
   private void requestToEnterNumber(TextField textField, String message) {
      textField.setText(message);
      textField.selectAll();
      textField.requestFocus();
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
