package vault.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import vault.MainApp;

public class CheckSumController {
	private File fileToHash;
	
	@FXML 
	private Label fileLabel;
	
	@FXML
	private Label sha1Label;
	
	@FXML
	private Label md5Label;
	
	private MainApp mainApp;
	
	public CheckSumController() {
	}
	
	@FXML
	private void initialize(){
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleChooseFile() {
		FileChooser chooser = new FileChooser();
		fileToHash = chooser.showOpenDialog(mainApp.getPrimaryStage().getScene().getWindow());
		fileLabel.setText(fileToHash.toString());
	}
	
	@FXML
	private void handleSHA1() {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA1");
			
		    FileInputStream fis;
			try {
				fis = new FileInputStream(fileToHash);
				byte[] dataBytes = new byte[1024];

			    int nread = 0;

			    try {
					while ((nread = fis.read(dataBytes)) != -1) {
					  md.update(dataBytes, 0, nread);
					}
				} catch (IOException e) {
					e.printStackTrace();
				};

			    byte[] mdbytes = md.digest();

			    //convert the byte to hex 
			    StringBuffer sb = new StringBuffer("");
			    for (int i = 0; i < mdbytes.length; i++) {
			    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			    }
			    sha1Label.setText(sb.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}    
	}
	
	@FXML
	private void handleMD5() {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			
		    FileInputStream fis;
			try {
				fis = new FileInputStream(fileToHash);
				byte[] dataBytes = new byte[1024];
			    int nread = 0;
			    try {
					while ((nread = fis.read(dataBytes)) != -1) {
					  md.update(dataBytes, 0, nread);
					}
				} catch (IOException e) {
					e.printStackTrace();
				};

			    byte[] mdbytes = md.digest();

			    //convert the byte to hex 
			    StringBuffer sb = new StringBuffer("");
			    for (int i = 0; i < mdbytes.length; i++) {
			    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			    }
			    md5Label.setText(sb.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} 
	}
	
	@FXML
	private void handleBack() {
		mainApp.showTools();
	}
	
}
