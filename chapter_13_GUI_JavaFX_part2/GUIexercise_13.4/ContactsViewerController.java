/* =====================================================================================
 *       Filename:  ContactsViewerController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.4 - managing of contacts list
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.util.Vector;
import java.util.Objects;

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
      initializeTextFieldEdition();
   }
   
   private void initializeTextFieldEdition() {
      editTextField(emailTextField); 
      editTextField(telephoneTextField);     
      editTextField(firstNameTextField); 
      editTextField(lastNameTextField);  
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
      
      firstNameTextField.setText(firstName);
      lastNameTextField.setText(lastName);
      emailTextField.setText(email);
      telephoneTextField.setText(telephone);
   }
   
   private void initializeListOfContacts() {
      Vector<Contact> contactsVector = getContacts();
      
      CONTACTS_LIST.setAll(contactsVector);
      contactsListView.setItems(CONTACTS_LIST);
      setContactDataOfList(1, false, false);
      setCurrentlySelectedContact(1, false);
      setSelectionMode(SelectionMode.MULTIPLE);
   }
   
   public void setContactDataOfList(int index, boolean clearingOtherSelections, boolean setCurrentlySelected) {
      if (index >= 0 && index < CONTACTS_LIST.size()) {
         if (setCurrentlySelected)
            setCurrentlySelectedContact(index, clearingOtherSelections);
         Contact contact = CONTACTS_LIST.get(index);
         setContactData(contact);
      }
   }
   
   private Vector<Contact> getContacts() {
      int initialCapacity   = 10;
      int capacityIncrement = initialCapacity;
      Vector<Contact> contactsVector = new Vector<Contact>(initialCapacity, capacityIncrement);
      
      contactsVector.insertElementAt​(new Contact("Lucius", "Seneca", "", 15), 0);
      contactsVector.insertElementAt​(new Contact("Marcus", "Aurelius", "", 24), 1);
      contactsVector.insertElementAt​(new Contact("Marcus", "Cicero", "", 22), 2);
      contactsVector.addElement​(new Contact("Francois", "Voltaire", "", 1707));
      contactsVector.addElement​(new Contact("", "Plato", "", 395));
      contactsVector.add(new Contact("", "Aristotle", "", 335));
      
      return contactsVector;
   }
   
   private void editTextField(final TextField TEXT_FIELD) {  // START OF BODY OF FUNCTION 

      class TextFieldChangeListener implements ChangeListener<String> {  // START OF LOCAL INNER CLASS
         Contact selectedContact = getCurrentlySelectedContact();
         
         @Override
         public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
            if (true == isErrorString(newValue)) {
               
               return;
            }
            
            this.selectedContact = getCurrentlySelectedContact();
            
            if (     TEXT_FIELD == ContactsViewerController.this.telephoneTextField) {
               setTelephone(newValue);
            }
            else if (TEXT_FIELD == ContactsViewerController.this.emailTextField) {
               selectedContact.setEmail(newValue);
               TEXT_FIELD.setText(selectedContact.getEmail());
            }
            else {
               setName(newValue);
            }
         }
                  
         private boolean isErrorString(String string) {
            if (     ContactsViewerController.this.telephoneTextField == TEXT_FIELD) {
               return StringComparator.areEquals(string, Contact.WRONG_TELEPHONE_ERROR);
            }
            else if (ContactsViewerController.this.firstNameTextField == TEXT_FIELD) {
               return StringComparator.areEquals(string, Contact.WRONG_FIRST_NAME_ERROR);
            }
            else if (ContactsViewerController.this.lastNameTextField  == TEXT_FIELD) {
               return StringComparator.areEquals(string, Contact.WRONG_LAST_NAME_ERROR);
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
      CONTACTS_LIST.add(new Contact("", "Newcontactoedit", "", 0));
      setContactDataOfList(CONTACTS_LIST.size() - 1, clearingOtherSelections, true);
   }

   @FXML
   void contextMenuDelete(ActionEvent event) {
      ObservableList<Contact> selectedContacts = getCurrentlySelectedMultipleContacts();
      CONTACTS_LIST.removeAll(selectedContacts);
   }
}

class StringComparator {
   public static boolean areEquals(final String FIRST, final String SECOND) {
      Objects.requireNonNull(FIRST);
      Objects.requireNonNull(SECOND);
      
      if (FIRST.equals(SECOND)) {
         return true;
      }
      
      return false;
   }
}

