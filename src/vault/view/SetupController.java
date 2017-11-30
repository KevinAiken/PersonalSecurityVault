package vault.view;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import vault.MainApp;
import vault.model.Password;
import vault.model.User;

/*
 *  NIST Recommendations 2016:
 *  Minimum 8 characters
 *  Max 64 characters
 *  All ASCII and Unicode allowed
 * 	Don't restrict composition
 */

public class SetupController {
	
	@FXML
	private TextField username;
	@FXML 
	private PasswordField password;
	@FXML
	private PasswordField passwordConfirm;
	
	// Reference to the main app
	private MainApp mainApp;
	
	/**
	 * Constructor 
	 */
	public SetupController(){
	}
	
	/**
	 * Initializes controller once fxml is loaded
	 */
	@FXML
	private void initialize() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Called when user clicks register account
	 */
	@FXML
	private void handleRegister() {
		String usernameStatus = checkUsername(username.getText());
		String passwordStatus = checkPassword(password.getText(), passwordConfirm.getText());
		
		if(usernameStatus.equals("good") && passwordStatus.equals("good")){
			// Valid user and pass
			Password myPass = new Password(password.getText());
			User thisUser = new User(username.getText(), myPass);
			new File(System.getProperty("user.home")+"/AikenVault/").mkdir();
			File file = new File(System.getProperty("user.home")+"/AikenVault/userdata.xml/");
			mainApp.saveUserDataToStream(file, thisUser);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        if(!usernameStatus.equals("good")) {
	        	alert.setTitle("Username Issue");
	        	alert.setHeaderText(null);
	        	alert.setContentText(usernameStatus);
	        } else {
	        	alert.setTitle("Password Issue");
	        	alert.setHeaderText(null);
	        	alert.setContentText(passwordStatus);
	        }
	        alert.showAndWait();
		}
		
		mainApp.showLogin();
	}
	
	private String checkUsername(String username) {
		if(username == null){
			return "Please enter a username.";
		} else if(username.length() <= 1){
			return "Your username is too short.";
		} else if(username.length() > 64) {
			return "Your username is too long.";
		} else {
			return "good";
		}
	}
	
	private String checkPassword(String password, String passConfirm) {
		if(password.length() < 10 || password == null) {
			return "Your password is too short.";
		} else if (password.length() > 64) {
			return "Your password is too long.";
		} else if(!password.equals(passConfirm) || passConfirm == null){
			return "The passwords do not match.";
		} else {
		return "good";
		}
	}	
}
