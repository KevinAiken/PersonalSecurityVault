package vault.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import vault.MainApp;
import vault.model.Account;


public class AccountsController {
	@FXML
    private TableView<Account> accountTable;
    @FXML
    private TableColumn<Account, String> accountNameColumn;
    @FXML
    
    private Label accountNameLabel;
    @FXML
    private Label userIDLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label urlLabel;
    @FXML
    private Label notesLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AccountsController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        accountNameColumn.setCellValueFactory(cellData -> cellData.getValue().accountNameProperty());
        
     // Clear person details.
        showAccountDetails(null);

        // Listen for selection changes and show the person details when changed.
        accountTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAccountDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
       
        accountTable.setItems(mainApp.getAccountData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showAccountDetails(Account account) {
        if (account != null) {
            // Fill the labels with info from the person object.
            accountNameLabel.setText(account.getAccountName());
            userIDLabel.setText(account.getUserID());
            passwordLabel.setText(account.getPass());
            notesLabel.setText(account.getNotes());
            urlLabel.setText(account.getUrl());
        } else {
            // account is null, remove all the text.
            accountNameLabel.setText("");
            userIDLabel.setText("");
            passwordLabel.setText("");
            notesLabel.setText("");
            urlLabel.setText("");         
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteAccount() {
        int selectedIndex = accountTable.getSelectionModel().getSelectedIndex();
        accountTable.getItems().remove(selectedIndex);
        mainApp.encryptAccountData();
    }
    
    @FXML
    private void handleBack() {
    	mainApp.showMain();
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNew() {
        Account tempAccount = new Account();
        boolean okClicked = mainApp.showAccountEditDialog(tempAccount);
        if (okClicked) {
            mainApp.getAccountData().add(tempAccount);
            // ENCRYPT ALL DATA HERE
            mainApp.encryptAccountData();
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEdit() {
        Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            boolean okClicked = mainApp.showAccountEditDialog(selectedAccount);
            if (okClicked) {
                showAccountDetails(selectedAccount);
                //MARSHALL AND ENCRYPT HERE
                mainApp.encryptAccountData();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Account Selected");
            alert.setContentText("Please select an account in the table.");

            alert.showAndWait();
        }
    }
    
}