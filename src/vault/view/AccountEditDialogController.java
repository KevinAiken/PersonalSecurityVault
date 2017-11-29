package vault.view;

import java.security.SecureRandom;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vault.model.Account;


/**
 * Dialog to edit details of a person.
 */
public class AccountEditDialogController {

    @FXML
    private TextField accountNameField;
    @FXML
    private TextField userIDField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField urlField;
    @FXML
    private TextField notesField;


    private Stage dialogStage;
    private Account account;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setAccount(Account account) {
        this.account = account;

        accountNameField.setText(account.getAccountName());
        userIDField.setText(account.getUserID());
        passwordField.setText(account.getPass());
        urlField.setText(account.getUrl());
        notesField.setText(account.getNotes());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void handleGenerate() {
    	SecureRandom sr = new SecureRandom();
		byte[] pass = new byte[16];
		sr.nextBytes(pass);
	
		String generatedPass = "";
		int i;
		for(i = 0; i < pass.length; i++) {
			generatedPass += (Byte.toString(pass[i]));
			
		}
		
		generatedPass = generatedPass.substring(0, 11);
		
    	passwordField.setText(generatedPass);
    }
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        account.setAccountName(accountNameField.getText());
        account.setUserID(userIDField.getText());
        account.setPass(passwordField.getText());
        account.setUrl(urlField.getText());
        account.setNotes(notesField.getText());

        okClicked = true;
        dialogStage.close();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
