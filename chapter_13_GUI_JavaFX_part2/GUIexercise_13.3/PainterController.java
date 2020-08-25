/* =====================================================================================
 *       Filename:  PainterController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.3 - Drawing circles with various colors
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PainterController {
   // instance variables that refer to GUI components
   @FXML private RadioButton smallRadioButton;
   @FXML private RadioButton mediumRadioButton;
   @FXML private RadioButton largeRadioButton;
   @FXML private ToggleGroup sizeToggleGroup;
   
   @FXML private Pane drawingAreaPane;
   
   @FXML private Slider redSlider;
   @FXML private Slider greenSlider;
   @FXML private Slider blueSlider;
   @FXML private Slider alphaSlider;
   @FXML private TextField redTextField;  
   @FXML private TextField greenTextField;
   @FXML private TextField blueTextField; 
   @FXML private TextField alphaTextField;
   @FXML private Rectangle colorRectangle;

   // instance variables for managing Painter state
   private int red      = 0;
   private int green    = 0;
   private int blue     = 0;
   private double alpha = 1.0;
   
   private PenSize radius     = PenSize.MEDIUM; // radius of circle
   private Color   brushColor = Color.BLACK; // drawing color
   
   public void initialize() {
      initializePenSizeToggleGroup();   
      initializeColors();   
   }
   
   private void initializeColors() {
      initializeColorValuesBinding();
      initializeSlidersAboutColors();
      
      brushColor = Color.rgb(red, green, blue, alpha);
      colorRectangle.setFill(brushColor);
   }
   
   private void initializeColorValuesBinding() {
      // bind TextField values to corresponding Slider values
      initializeColorValuesBinding(redTextField, redSlider, "%.0f");
      initializeColorValuesBinding(greenTextField, greenSlider, "%.0f");
      initializeColorValuesBinding(blueTextField, blueSlider, "%.0f");
      initializeColorValuesBinding(alphaTextField, alphaSlider, "%.2f");
   }
   
   private void initializeColorValuesBinding(TextField textField, Slider slider, String format) {
      StringProperty stringValue = textField.textProperty();
      DoubleProperty sliderValue = slider.valueProperty();
      StringBinding sliderValueStringBinding = sliderValue.asString(format);
      
      stringValue.bind(sliderValueStringBinding);
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
      brushColor     = newColor;
   }     
   
   private void initializePenSizeToggleGroup() {
      smallRadioButton.setUserData(PenSize.SMALL);
      mediumRadioButton.setUserData(PenSize.MEDIUM);
      largeRadioButton.setUserData(PenSize.LARGE);
      
      setSelectedPenSize();
   }
   
   private void setSelectedPenSize() {
      Toggle selected = sizeToggleGroup.getSelectedToggle();
      radius          = (PenSize) selected.getUserData();
   }
   
   // handles drawingArea's onMouseDragged MouseEvent
   @FXML
   private void drawingAreaMouseDragged(MouseEvent e) {
      double x = e.getX();
      double y = e.getY();
      int penRadius = radius.getRadius();
      Circle newCircle = new Circle(x, y, penRadius, brushColor);
      ObservableList<Node> children = drawingAreaPane.getChildren();
      
      children.add(newCircle); 
   }
      
   // handles size RadioButton's ActionEvents
   @FXML
   private void sizeRadioButtonSelected(ActionEvent e) {
      setSelectedPenSize();
   } 
      
   // handles Undo Button's ActionEvents
   @FXML
   private void undoButtonPressed(ActionEvent event) {
      ObservableList<Node> children = drawingAreaPane.getChildren();
      int count = children.size();

      // if there are any shapes remove the last one added
      if (count > 0) {
         children.remove(count - 1);
      }
   }
   
   // handles Clear Button's ActionEvents
   @FXML
   private void clearButtonPressed(ActionEvent event) {
      ObservableList<Node> children = drawingAreaPane.getChildren();
      children.clear(); // clear the canvas
   }
}

