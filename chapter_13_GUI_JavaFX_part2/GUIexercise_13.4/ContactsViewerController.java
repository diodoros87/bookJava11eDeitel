/* =====================================================================================
 *       Filename:  ContactsViewerController.java
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

import java.util.Vector;

import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

public class ContactsViewerController {
   // instance variables for interacting with GUI
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
   
   // stores the list of Contact Objects
   private final ObservableList<Contact> CONTACTS_LIST = FXCollections.observableArrayList();

   // initialize controller
   public void initialize() {
      initializeListOfContacts();
      initializeSelectionListChanges();
      //initializeEditTextFields();         
   }
   
   private void initializeSelectionListChanges() {
      
      class ContactChangeListener implements ChangeListener<Contact> {
         private ContactChangeListener() {}
         
         @Override                                                     
         public void changed(ObservableValue<? extends Contact> observableValue, Contact oldValue, Contact newValue) {
            setContactData(newValue);
         }
      }
      
      MultipleSelectionModel<Contact> multipleSelectionModel = contactsListView.getSelectionModel();
      ReadOnlyObjectProperty<Contact> property = multipleSelectionModel.selectedItemProperty();
      
      // when ListView selection changes, show selected contact's data in GridPane
      property.addListener(new ContactChangeListener());  
   }
   
   private Contact getCurrentlySelectedContact() {
      MultipleSelectionModel<Contact> multipleSelectionModel = contactsListView.getSelectionModel();
      ReadOnlyObjectProperty<Contact> property = multipleSelectionModel.selectedItemProperty();
      Contact contact = property.getValue();
      
      return contact;
   }
   
   private void setContactData(Contact contact) {
      String firstName = contact.getFirstName();
      String lastName  = contact.getLastName();
      String email     = contact.getEmail(); 
      String telephone = String.format("%d", contact.getTelephone());                      
      
      firstNameTextField.setText(firstName);
      lastNameTextField.setText(lastName);
      emailTextField.setText(email);
      telephoneTextField.setText(telephone);
   }
   
   private void initializeListOfContacts() {
      Vector<Contact> contactsVector = getContacts();
      
      // populate the ObservableList<Book>
      CONTACTS_LIST.setAll(contactsVector);
      contactsListView.setItems(CONTACTS_LIST); // bind booksListView to books
      setContactDataOfList(0);
   }
   
   public void setContactDataOfList(int index) {
      if (index >= 0 && index < CONTACTS_LIST.size()) {
         Contact contact = CONTACTS_LIST.get(index);
         setContactData(contact);
      }
   }
   
   private Vector<Contact> getContacts() {
      int initialCapacity   = 10;
      int capacityIncrement = initialCapacity;
      Vector<Contact> contactsVector = new Vector<Contact>(initialCapacity, capacityIncrement);
      
      contactsVector.insertElementAt​(new Contact("Seneca", "Lucius", "", 15), 0);
      contactsVector.insertElementAt​(new Contact("Marcus", "Aurelius", "", 24), 1);
      contactsVector.insertElementAt​(new Contact("Marcus", "Cicero", "", 22), 2);
      contactsVector.addElement​(new Contact("Francois", "Voltaire", "", 1707));
      contactsVector.addElement​(new Contact("", "Plato", "", 395));
      contactsVector.add(new Contact("", "Aristotle", "", 335));
      
      return contactsVector;
   }
   
   private void editTextField(final TextField TEXT_FIELD) {  // START OF BODY OF FUNCTION 

      class TextFieldChangeListener implements ChangeListener<String> {  // START OF LOCAL INNER CLASS
         private final Contact selectedContact = getCurrentlySelectedContact();
         
         @Override
         public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
            if (TEXT_FIELD == telephoneTextField && false == isErrorString(newValue)) {
               setTelephone(newValue);
            }
            else {
               selectedContact.setEmail(newValue);
            }
         }
                  
         private boolean isErrorString(String string) {
            if (string.equals(Contact.WRONG_TELEPHONE_ERROR)) {
               return true;
            }
            
            return false;
         }
         
         private void setTelephone(String string) {
            long integer = getIntegerValue(string);
            
            try {
               selectedContact.setTelephone(integer);
            } 
            catch (IllegalArgumentException exception) {
               requestToEnterNumber(TEXT_FIELD, exception.getMessage());
               
               throw exception;
            }
         }
         
         private long getIntegerValue(String string) {
            long integer;
            
            try {
               integer = Long​.parseLong​(string);
            } 
            catch (NumberFormatException exception) {
               requestToEnterNumber(TEXT_FIELD, "Enter integer");
               
               throw exception;
            }
            
            return integer;
         }
      }  // END OF LOCAL INNER CLASS

            
      StringProperty property = TEXT_FIELD.textProperty();
      property.addListener(new TextFieldChangeListener()); 
         
   } // END OF BODY OF FUNCTION  
   
   private void requestToEnterNumber(TextField textField, String message) {
      textField.setText(message);
      textField.selectAll();
      textField.requestFocus();
   }
}
