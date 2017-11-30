package vault.view;

import java.io.File;

import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import vault.MainApp;
import vault.model.Password;
import vault.model.User;


public class LoginController {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	
	// Reference to the main app
	private MainApp mainApp;
	
	/**
	 * Constructor
	 */
	public LoginController(){
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
	 * Called when the user clicks the login button
	 */
	@FXML
	private void handleLogin() {
		//compare username to saved username
		//encrypt password with salt and compare
		// if correct run decryption on the user data
		File file = new File(System.getProperty("user.home")+"/AikenVault/userdata.xml/");
		User user = loadUserDataFromFile(file);

		Password passAttempt = new Password(password.getText(), user.getPassSalt());	
		User userAttempt = new User(username.getText(), passAttempt);

		if(Arrays.equals(userAttempt.getPassHash(),(user.getPassHash()))
				&& userAttempt.getUsername().equals(user.getUsername())) {
			user.setPassword(password.getText());
			mainApp.thisUser = user;
			File accountFile = new File(System.getProperty("user.home")+"/AikenVault/encryptedAccounts.txt/");
			File keyFile = new File(System.getProperty("user.home")+"/AikenVault/encryptedKeys.txt/");
			if (keyFile.exists()) {
				mainApp.decryptKeyData();
			}
			if (accountFile.exists()) {
				mainApp.decryptAccountData();
			}
			mainApp.showMain();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Incorrect Credentials");
	        alert.setContentText("Please enter valid credentials");

	        alert.showAndWait();
			
		}
	}
	
	/**
	 * Loads userLogin data from the specified file. The current userLogin data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public User loadUserDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(User.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	    
	        User wrapper = (User) um.unmarshal(file);
	        return wrapper;

	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
	        return null;
	    }
	}
	
	
}
