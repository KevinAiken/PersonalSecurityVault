package vault.view;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vault.model.Key;

public class GenerateKeyDialogController {
	@FXML
	private ChoiceBox<String> keyChoice;
	
	@FXML
	private TextField keyNameField;
	@FXML
	private TextField keyNotesField;
	
	private Stage dialogStage;
	private Key key;
	private boolean okClicked = false;
	private ObservableList<String> choices = FXCollections.observableArrayList();

	
	@FXML
	private void initialize() {
		choices.addAll("DES", "3DES with 112-bit key",  
				"AES with 128-bit key", "AES with 192-bit key", "AES with 256-bit key",
				"1024-bit RSA key pair", "2048-bit RSA key pair", "4096-bit RSA Key pair");		
		keyChoice.setItems(choices);	
		keyChoice.setValue("DES");
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setKey(Key key) {
		this.key = key;
		
		keyNameField.setText(key.getKeyName());
		keyNotesField.setText(key.getNotes());
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML 
	private void handleOK() {
		SecretKey secretKeyRaw = null;
		KeyPair secretKeyPair = null;
		key.setKeyName(keyNameField.getText());
		key.setNotes(keyNotesField.getText());
		if(keyChoice.getValue().equals("DES")) {
			KeyGenerator keyGen;
			try {
				keyGen = KeyGenerator.getInstance("DES");
				keyGen.init(56); // for example
				secretKeyRaw = keyGen.generateKey();
				String secretKey = Base64.getEncoder().encodeToString(secretKeyRaw.getEncoded());
				key.setKeyType("DES");
				key.setKeyValue(secretKey);
				key.setKeyLength("56");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(keyChoice.getValue().equals("3DES with 112-bit key")) {
			KeyGenerator keyGen;
			try {
				keyGen = KeyGenerator.getInstance("DESede");
				keyGen.init(112); // for example
				secretKeyRaw = keyGen.generateKey();
				String secretKey = Base64.getEncoder().encodeToString(secretKeyRaw.getEncoded());
				key.setKeyType("3DES");
				key.setKeyValue(secretKey);
				key.setKeyLength("112");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(keyChoice.getValue().equals("AES with 128-bit key")) {
			KeyGenerator keyGen;
			try {
				keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128); // for example
				secretKeyRaw = keyGen.generateKey();
				String secretKey = Base64.getEncoder().encodeToString(secretKeyRaw.getEncoded());
				key.setKeyType("AES");
				key.setKeyValue(secretKey);
				key.setKeyLength("128");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(keyChoice.getValue().equals("AES with 192-bit key")) {
			KeyGenerator keyGen;
			try {
				keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(192); // for example
				secretKeyRaw = keyGen.generateKey();
				String secretKey = Base64.getEncoder().encodeToString(secretKeyRaw.getEncoded());
				key.setKeyType("AES");
				key.setKeyValue(secretKey);
				key.setKeyLength("192");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(keyChoice.getValue().equals("AES with 256-bit key")) {
			KeyGenerator keyGen;
			try {
				keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(256); // for example
				secretKeyRaw = keyGen.generateKey();
				String secretKey = Base64.getEncoder().encodeToString(secretKeyRaw.getEncoded());
				key.setKeyType("AES");
				key.setKeyValue(secretKey);
				key.setKeyLength("256");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(keyChoice.getValue().equals("1024-bit RSA key pair")) {
			KeyPairGenerator keyGen;
			try {
				keyGen = KeyPairGenerator.getInstance("RSA");
				keyGen.initialize(1024);
				secretKeyPair = keyGen.genKeyPair();
				key.setKeyType("RSA");
				key.setKeyValue(secretKeyPair.getPrivate().toString());
				key.setKeyLength("1024");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} else if(keyChoice.getValue().equals("2048-bit RSA key pair")) {
		} else if(keyChoice.getValue().equals("4096-bit RSA key pair")) {
		}
		okClicked = true;
		dialogStage.close();
	}
	
				
		
	@FXML 
	private void handleCancel() {
		dialogStage.close();
	}
}
