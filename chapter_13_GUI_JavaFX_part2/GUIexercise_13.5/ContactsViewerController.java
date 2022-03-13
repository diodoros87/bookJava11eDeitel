/* =====================================================================================
 *       Filename:  ContactsViewerController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.5 - managing of contacts list
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.util.Stack;
import java.util.Objects;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
//import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.InvalidPathException;

import javafx.stage.FileChooser;
import javafx.scene.layout.BorderPane;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.stage.Window;

import javafx.collections.transformation.SortedList;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;

public class ContactsViewerController {
   //@FXML 
   //private BorderPane borderPane;
   
   @FXML
   private ListView<Contact> contactsListView;

   @FXML
   private TextField firstNameTextField;

   @FXML
   private TextField lastNameTextField;

   @FXML
   private TextField emailTextField;

   @FXML
   private TextField telephoneTextField;
   
   @FXML
   private Label imagePathFileLabel;
   // stores the list of Contact Objects
   private final ObservableList<Contact> CONTACTS_LIST = FXCollections.observableArrayList();
   //private ObservableList<Contact> CONTACTS_LIST      = null;
   //private SortedList<Contact>     sortedContactsList = null;
   
   //SortedList

   // initialize controller
   public void initialize() {
      initializeListOfContacts();
      //updateListWhenChangeContactOnList();
      updateContactDataViewWhenChangeContactOnList();
      initializeTextFieldEdition();
      setCellFactory(contactsListView); 
   }
   
   void setCellFactory(final ListView<Contact> list) {
      Objects.requireNonNull(list);
      list.setCellFactory(
         new Callback<ListView<Contact>, ListCell<Contact>>() {
            @Override
            public ListCell<Contact> call(ListView<Contact> listView) {
               return new ImageTextCell();
            }
         }
      );  
   }
   
   private void initializeTextFieldEdition() {
      editTextField(emailTextField); 
      editTextField(telephoneTextField);     
      editTextField(firstNameTextField); 
      editTextField(lastNameTextField);  
   }
   
   private void updateContactDataViewWhenChangeContactOnList() {
      
      class ContactChangeListener implements ChangeListener<Contact> {
         private ContactChangeListener() {}
         
         @Override                                                     
         public void changed(ObservableValue<? extends Contact> observableValue, Contact oldValue, Contact newValue) {
            if (null != newValue) {
               setContactData(newValue);
            }
         }
      }
      
      MultipleSelectionModel<Contact> multipleSelectionModel = contactsListView.getSelectionModel();
      ReadOnlyObjectProperty<Contact> property = multipleSelectionModel.selectedItemProperty();
      
      // when ListView selection changes, show selected contact's data in GridPane
      property.addListener(new ContactChangeListener());  
   }
   
   private void updateListWhenChangeContactOnList() {
      /*
      class Listener implements ListChangeListener<Contact> {
         
         private Listener() {}
         
         @Override                                                     
         public void onChanged(ObservableValue<? extends Contact> observableValue, Contact oldValue, Contact newValue) {
            FXCollections.sort(CONTACTS_LIST, new Contact.ContactComparator());
         }
      }*/
      
      // when ListView selection changes, show selected contact's data in GridPane
      //CONTACTS_LIST.addListener(new Listener());  
     
      CONTACTS_LIST.addListener(new ListChangeListener<Contact>() {
         @Override  
         public void onChanged(ListChangeListener.Change<? extends Contact> contact) {
            //Callback<ListView<Contact>, ListCell<Contact>> contactsListView.getCellFactory();
            
            while (contact.next()) {
               if (contact.wasUpdated() || contact.wasPermutated()) {
                  int index = contactsListView.getEditingIndex();
                  contactsListView.edit(index);
                  //updateItem();
                  FXCollections.sort(CONTACTS_LIST);
                  
                  break;
               }
            }
         }
      });

      
   }
   
   private Contact getCurrentlySelectedContact() {
      MultipleSelectionModel<Contact> multipleSelectionModel = contactsListView.getSelectionModel();
      //ReadOnlyObjectProperty<Contact> property = multipleSelectionModel.selectedItemProperty();
      //Contact contact = property.getValue();
      Contact contact = multipleSelectionModel.getSelectedItem();
      
      return contact;
   }
   
   private void setCurrentlySelectedContact(int index, boolean clearingOtherSelections) {
      MultipleSelectionModel<Contact> multipleSelectionModel = contactsListView.getSelectionModel();
      
      if (true == clearingOtherSelections) {
         multipleSelectionModel.clearAndSelect(index);
      }
      else {
         multipleSelectionModel.select(index);
      }
   }
   
   private void setSelectionMode(SelectionMode mode) {
      MultipleSelectionModel<Contact> multipleSelectionModel = contactsListView.getSelectionModel();
      multipleSelectionModel.setSelectionMode(mode);
   }
   
   private ObservableList<Contact> getCurrentlySelectedMultipleContacts() {
      MultipleSelectionModel<Contact> multipleSelectionModel = contactsListView.getSelectionModel();
      ObservableList<Contact> contacts = multipleSelectionModel.getSelectedItems();
      
      return contacts;
   }
   
   private void setContactData(Contact contact) {
      String firstName = contact.getFirstName();
      String lastName  = contact.getLastName();
      String email     = contact.getEmail(); 
      String telephone = String.format("%d", contact.getTelephone());
      String imageFilePath = contact.getImageFilePath();
      
      firstNameTextField.setText(firstName);
      lastNameTextField.setText(lastName);
      emailTextField.setText(email);
      telephoneTextField.setText(telephone);
      if (imageFilePath != null)
         this.imagePathFileLabel.setText(imageFilePath);
   }
   
   private void setEmptyTextFields() {
      firstNameTextField.setText("");
      lastNameTextField.setText("");
      emailTextField.setText("");
      telephoneTextField.setText("");
   }
   
   private void initializeListOfContacts() {
      Stack<Contact> contactsStack = getContacts();
      
      //CONTACTS_LIST = FXCollections.observableList(contactsStack);
      CONTACTS_LIST.setAll(contactsStack);
      contactsListView.setItems(CONTACTS_LIST);
      //sortedContactsList = new SortedList<Contact>(CONTACTS_LIST, new Contact.ContactComparator());
      //contactsListView.setItems(sortedContactsList);
      setContactDataOfList(0, false);
      setSelectionMode(SelectionMode.MULTIPLE);
      sortList();
   }
   
   private void sortList() {
      int size = CONTACTS_LIST.size();
      
      if (size > 1) {
         FXCollections.sort(CONTACTS_LIST);
      }
      else if (0 == size) {
         setEmptyTextFields();
      }
   }
   
   public void setContactDataOfList(int index, boolean clearingOtherSelections) {
      if (index >= 0 && index < CONTACTS_LIST.size()) {
      //if (index >= 0 && index < sortedContactsList.size()) {
         setCurrentlySelectedContact(index, clearingOtherSelections);
         Contact contact = CONTACTS_LIST.get(index);
         //Contact contact = sortedContactsList.get(index);
         setContactData(contact);
      }
   }
   
   private Stack<Contact> getContacts() {
      Stack<Contact> contactsStack = new Stack<Contact>();
      contactsStack.ensureCapacity(10);
      
      contactsStack.push​(new Contact("Lucius", "Seneca", "", 15, ""));
      contactsStack.push​(new Contact("Marcus", "Aurelius", "", 24, ""));
      contactsStack.push​(new Contact("Marcus", "Cicero", "", 22, ""));
      contactsStack.push​(new Contact("Francois", "Voltaire", "", 1707, ""));
      contactsStack.push​(new Contact("", "Plato", "", 395, ""));
      contactsStack.push(new Contact("", "Aristotle", "", 335, ""));
      
      return contactsStack;
   }
   
   private void editTextField(final TextField TEXT_FIELD) {  // START OF BODY OF FUNCTION 

      class TextFieldChangeListener implements ChangeListener<String> {  // START OF LOCAL INNER CLASS
         Contact selectedContact = getCurrentlySelectedContact();
         
         @Override
         public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
            if (0 == CONTACTS_LIST.size() || true == isErrorString(newValue) || null == newValue) {
               
               return;
            }
            
            this.selectedContact = getCurrentlySelectedContact();
            
            if (     TEXT_FIELD == ContactsViewerController.this.telephoneTextField) {
               setTelephone(newValue);
            }
            else if (TEXT_FIELD == ContactsViewerController.this.emailTextField) {
               selectedContact.setEmail(newValue);
            }
            else {
               setName(newValue);
            }
         }
                  
         private boolean isErrorString(String string) {
            if (     ContactsViewerController.this.telephoneTextField == TEXT_FIELD) {
               return string.equals(Contact.WRONG_TELEPHONE_ERROR);
            }
            else if (ContactsViewerController.this.firstNameTextField == TEXT_FIELD) {
               return string.equals(Contact.WRONG_FIRST_NAME_ERROR);
            }
            else if (ContactsViewerController.this.lastNameTextField  == TEXT_FIELD) {
               return string.equals(Contact.WRONG_LAST_NAME_ERROR);
            }
            
            return false;
         }
         
         private void setName(String string) {
            try {
               if (     TEXT_FIELD == ContactsViewerController.this.firstNameTextField) {
                  selectedContact.setFirstName(string);
               }
               else if (TEXT_FIELD == ContactsViewerController.this.lastNameTextField) {
                  selectedContact.setLastName(string);
                  ///int index = contactsListView.getEditingIndex();
                  ///contactsListView.edit(index);
                  //updateItem();
                  FXCollections.sort(CONTACTS_LIST);
               }
            } 
            catch (IllegalArgumentException exception) {
               requestToEnterNumber(exception.getMessage());
               
               throw exception;
            }
         }
         
         private void setTelephone(String string) {
            long integer = getIntegerValue(string);
            
            try {
               selectedContact.setTelephone(integer);
            } 
            catch (IllegalArgumentException exception) {
               requestToEnterNumber(exception.getMessage());
               
               throw exception;
            }
         }
         
         private long getIntegerValue(String string) {
            long integer;
            
            try {
               integer = Long​.parseLong​(string);
            } 
            catch (NumberFormatException exception) {
               requestToEnterNumber(Contact.WRONG_TELEPHONE_ERROR);
               
               throw exception;
            }
            
            return integer;
         }
         
         private void requestToEnterNumber(String message) {
            TEXT_FIELD.setText(message);
            TEXT_FIELD.selectAll();
            TEXT_FIELD.requestFocus();
         }
      }  // END OF LOCAL INNER CLASS

            
      StringProperty property = TEXT_FIELD.textProperty();
      property.addListener(new TextFieldChangeListener()); 
         
   } // END OF BODY OF FUNCTION  
   
   @FXML
   void contextMenuAdd(ActionEvent event) {
      boolean clearingOtherSelections = true;
      Contact newContact = new Contact("", "Newcontactoedit", "", 0, "");
      CONTACTS_LIST.add(newContact);
      int index = CONTACTS_LIST.indexOf(newContact);
      /////sortedContactsList.add(new Contact("", "Newcontactoedit", "", 0, ""));
      //setContactDataOfList(index, clearingOtherSelections);
      sortList();
      
      
      
      //FXCollections.sort(CONTACTS_LIST);
      //setContactDataOfList(sortedContactsList.size() - 1, clearingOtherSelections);
   }

   @FXML
   void contextMenuDelete(ActionEvent event) {
      ObservableList<Contact> selectedContacts = getCurrentlySelectedMultipleContacts();
      CONTACTS_LIST.removeAll(selectedContacts);
      if (0 == CONTACTS_LIST.size()) {
         setEmptyTextFields();
      }
      //sortList();
      //sortedContactsList.removeAll(selectedContacts);
   }
   
   @FXML
   private void chooseFileButtonPressed(ActionEvent e) {
      FileChooser fileChooser = new FileChooser();               
      fileChooser.setTitle("Select File");
      fileChooser.setInitialDirectory(new File(".")); 
      Window window = contactsListView.getScene().getWindow();
      File file = fileChooser.showOpenDialog(window); 
      Contact selectedContact = getCurrentlySelectedContact();
      try {
         selectedContact.setImageFilePath(file);
         this.imagePathFileLabel.setText(selectedContact.getImageFilePath());
      } catch (SecurityException | InvalidPathException exception) {
         this.imagePathFileLabel.setText(exception.toString());
      } catch (IllegalArgumentException | NullPointerException exception) {
         this.imagePathFileLabel.setText(exception.toString());
      }
   } 
   /*
   // display information about file or directory user specifies
   public void analyzePath(Path path) {
      Objects.requireNonNull(path);
      if (false == Files.exists(path))
         throw IllegalArgumentException("Path " + path.toAbsolutePath() + " does not exist");
         
      try {
         // if the file or directory exists, display its info
         if (false == Files.exists(path)) {
            // gather file (or directory) information
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("%s:%n", path.getFileName()));
            builder.append(String.format("%s a directory%n", 
               Files.isDirectory(path) ? "Is" : "Is not"));
            builder.append(String.format("%s an absolute path%n", 
               path.isAbsolute() ? "Is" : "Is not"));
            builder.append(String.format("Last modified: %s%n", 
               Files.getLastModifiedTime(path)));
            builder.append(String.format("Size: %s%n", Files.size(path)));
            builder.append(String.format("Path: %s%n", path));
            builder.append(String.format("Absolute path: %s%n", 
               path.toAbsolutePath()));

            if (Files.isDirectory(path)) { // output directory listing
               builder.append(String.format("%nDirectory contents:%n"));
               
               // object for iterating through a directory's contents
               DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
      
               for (Path p : directoryStream) {
                  builder.append(String.format("%s%n", p));
               }
            }
            
            Contact selectedContact = getCurrentlySelectedContact();
            selectedContact.setImageFilePath(path);

            // display file or directory info
            //textArea.setText(builder.toString()); 
         } 
         else { // Path does not exist
            //textArea.setText("Path does not exist");
         }   
      }
      catch (IOException ioException) {
         //textArea.setText(ioException.toString());
      }
   } 
   */
}
