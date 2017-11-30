package vault.view;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import vault.MainApp;
import javafx.stage.FileChooser;

public class VerifySignatureController {
	
	private MainApp mainApp;
	@FXML
	private Label sigLabel;
	@FXML
	private Label fileLabel;
	@FXML
	private Label verifyText;
	@FXML
	private ChoiceBox keyChoice;
	@FXML
	private ChoiceBox hashChoice;
	
	private ObservableList<String> keyChoices= FXCollections.observableArrayList();
	private ObservableList<String> hashChoices = FXCollections.observableArrayList();

	
	public VerifySignatureController() {
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
	
	File fileSig;
	@FXML
	private void handleSigChoose() {
		FileChooser sigChooser = new FileChooser();
		fileSig = sigChooser.showOpenDialog(mainApp.getPrimaryStage().getScene().getWindow());
		sigLabel.setText(fileSig.getPath());
	}
	
	File fileToVerify;
	@FXML
	private void handleVerifyChoose() {
		FileChooser verChooser = new FileChooser();
		fileToVerify = verChooser.showOpenDialog(mainApp.getPrimaryStage().getScene().getWindow());
		fileLabel.setText(fileToVerify.getPath());
	}
	
	@FXML
	private void handleVerification() {
		Signature sig = null;
		if(hashChoice.getValue().equals("SHA1")) {
			try {
				sig = Signature.getInstance("SHA1withRSA");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sig = Signature.getInstance("MD5withRSA");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int i = 0;
		for(i = 0; i < mainApp.getKeyData().size(); i++) {
			if(mainApp.getKeyData().get(i).getKeyName().equals(keyChoice.getValue())) {
				byte[] decodedKey = Base64.getDecoder().decode(mainApp.getKeyData().get(i).getKeyPairValue());
				
				KeyFactory kf;
				try {
					kf = KeyFactory.getInstance("RSA");	
					PublicKey publicKey;
				try {
					publicKey = kf.generatePublic(new X509EncodedKeySpec(decodedKey));
				try {
					sig.initVerify(publicKey);
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				} catch (NoSuchAlgorithmException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
			}
		}
		
		try {
			byte[] data = null;
			Path pathVerify = Paths.get(fileToVerify.getPath());
			data = Files.readAllBytes(pathVerify);
			sig.update(data);
			Path path = Paths.get(fileSig.getPath());
			byte[] signature;
			signature = Files.readAllBytes(path);
			Boolean isVerified = sig.verify(signature);
			verifyText.setText(isVerified.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	
	@FXML
	private void handleBack() {
		mainApp.showTools();
	}
}

