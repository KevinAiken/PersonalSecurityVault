package vault.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import vault.MainApp;
import vault.model.Account;
import vault.model.Key;

public class KeysController {
	@FXML
    private TableView<Key> keyTable;
    @FXML
    private TableColumn<Key, String> keyNameColumn;
    @FXML
    private TableColumn<Key, String> keyTypeColumn;
    
    @FXML
    private Label keyNameLabel;
    @FXML
    private Label keyTypeLabel;
    @FXML
    private Label keyValueLabel;
    @FXML
    private Label keyPairValueLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private Label notesLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public KeysController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        keyNameColumn.setCellValueFactory(cellData -> cellData.getValue().keyNameProperty());
        keyTypeColumn.setCellValueFactory(cellData -> cellData.getValue().keyTypeProperty());
        
     // Clear person details.
        showKeyDetails(null);

        // Listen for selection changes and show the person details when changed.
        keyTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showKeyDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
       
        keyTable.setItems(mainApp.getKeyData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showKeyDetails(Key key) {
        if (key != null) {
            // Fill the labels with info from the person object.
            keyNameLabel.setText(key.getKeyName());
            keyTypeLabel.setText(key.getKeyType());
            keyValueLabel.setText(key.getKeyValue());
            keyPairValueLabel.setText(key.getKeyPairValue());
            notesLabel.setText(key.getNotes());
        } else {
            // account is null, remove all the text.
            keyNameLabel.setText("");
            keyTypeLabel.setText("");
            keyValueLabel.setText("");
            keyPairValueLabel.setText("");
            notesLabel.setText("");         
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteKey() {
        int selectedIndex = keyTable.getSelectionModel().getSelectedIndex();
        keyTable.getItems().remove(selectedIndex);
    }
    
    @FXML
    private void handleBack() {
    	mainApp.showMain();
    }
    
    @FXML 
    private void handleNewKey() {
    	System.out.println("Not implemented yet");
    }
    
    @FXML
    private void exportKey() {
    	System.out.println("not implemented yet");
    }
    
    @FXML 
    private void editKey() {
    	System.out.println("Not implemented yet");
    }
}
