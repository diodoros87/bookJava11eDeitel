import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ImageTextCell extends ListCell<Contact> {
   private VBox vbox = new VBox(8.0); // 8 points of gap between controls
   private ImageView thumbImageView = new ImageView(); // initially empty
   private Label label = new Label();

   // constructor configures VBox, ImageView and Label
   public ImageTextCell() {
      vbox.setAlignment(Pos.CENTER); // center VBox contents horizontally

      thumbImageView.setPreserveRatio(true);
      thumbImageView.setFitHeight(100.0); // thumbnail 100 points tall
      vbox.getChildren().add(thumbImageView); // attach to Vbox

      label.setWrapText(true); // wrap if text too wide to fit in label
      label.setTextAlignment(TextAlignment.CENTER); // center text
      vbox.getChildren().add(label); // attach to VBox

      setPrefWidth(USE_PREF_SIZE); // use preferred size for cell width
   }

   // called to configure each custom ListView cell
   @Override 
   protected void updateItem(Contact item, boolean empty) {
      // required to ensure that cell displays properly
      super.updateItem(item, empty);

      if (empty || item == null) {
         setGraphic(null); // don't display anything
      }
      else {
         String path = item.getImageFilePath();
         if (path == null || path.isEmpty()) {
            label.setText(item.getLastName());
            setGraphic(label);
         }
         else {
            System.err.println(" updateItem: " + path);
            try {
               //thumbImageView.setImage(new Image(path));
               Image image = new Image(path);
               thumbImageView.setImage(image);
               String url =  image.getUrl​();
               System.err.println(" url: " + url);
               label.setText(item.getLastName()); // configure Label's text
               setGraphic(vbox); // attach custom layout to ListView cell
            } catch (IllegalArgumentException | NullPointerException exception) {
               label.setText(exception.toString());
               //label.setText(item.getLastName());
               setGraphic(label);
            }
         }
      }
   }
}
