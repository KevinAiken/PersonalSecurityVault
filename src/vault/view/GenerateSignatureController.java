package vault.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import vault.MainApp;

public class GenerateSignatureController {
	
	@FXML
	private ChoiceBox<String> keyChoice;
	@FXML 
	private ChoiceBox<String> hashChoice;
	@FXML
	private Label saveLabel;
	@FXML
	private Label chooseLabel;
	
	private MainApp mainApp;
	
	private ObservableList<String> keyChoices= FXCollections.observableArrayList();
	private ObservableList<String> hashChoices = FXCollections.observableArrayList();
	
	public GenerateSignatureController() {
	}
	
	@FXML
	private void initialize() {
		hashChoices.addAll("SHA1", "MD5");
		hashChoice.setItems(hashChoices);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		int i = 0;
		for(i = 0; i < mainApp.getKeyData().size(); i++) {
			if(mainApp.getKeyData().get(i).getKeyType().equals("RSA")) {
				keyChoices.add(mainApp.getKeyData().get(i).getKeyName());
			}
		}
		keyChoice.setItems(keyChoices);
	}
	
	File saveLocation;
	@FXML
	private void handleSaveChoose() {
		FileChooser fileChooser = new FileChooser();
      
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT Files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File saveLocation = fileChooser.showSaveDialog(mainApp.getPrimaryStage().getScene().getWindow());
        saveLabel.setText(saveLocation.getPath());
	}
	
	File fileToImport;
	@FXML
	private void handleFileChoose() {
		FileChooser chooser = new FileChooser();
    	fileToImport = chooser.showOpenDialog(mainApp.getPrimaryStage().getScene().getWindow());
    	chooseLabel.setText(fileToImport.getPath());
	}
	@FXML
	private void handleGenerate() {
		Signature rsa = null;
		//code to produce hash here
		if(hashChoice.getValue().equals("SHA1")) {
			try {
				rsa = Signature.getInstance("SHA1withRSA");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				rsa = Signature.getInstance("MD5withRSA");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int i = 0;
		for(i = 0; i < mainApp.getKeyData().size(); i++) {
			if(mainApp.getKeyData().get(i).getKeyName().equals(keyChoice.getValue())) {
				byte[] decodedKey = Base64.getDecoder().decode(mainApp.getKeyData().get(i).getKeyValue());
				
				KeyFactory kf;
				try {
					kf = KeyFactory.getInstance("RSA");
					PrivateKey privateKey;
					privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
					rsa.initSign(privateKey);
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
			}
		}
		byte[] Signature = null;
		Path path = Paths.get(fileToImport.getPath());
		byte[] data;
		try {
			data = Files.readAllBytes(path);
			rsa.update(data);
			Signature = rsa.sign();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (FileOutputStream fos = new FileOutputStream(saveLabel.getText())) {
			   
			   fos.write(Signature);
			   fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void handleBack() {
		mainApp.showTools();
	}
}
