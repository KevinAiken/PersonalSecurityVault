package vault.view;

import javafx.fxml.FXML;
import vault.MainApp;

public class ToolController {
	
	private MainApp mainApp;
	
	public ToolController(){
	}
	
	@FXML
	private void initialize() {
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleVerify() {
		//mainApp.showVerifySignature();
	}
	
	@FXML
	private void handleChecksum() {
		mainApp.showChecksum();
	}
	
	@FXML
	private void handleEncrypt() {
		//mainApp.showEncrypt();
	}
	
	@FXML
	private void handleGenerateSignature() {
		//mainApp.showGenerateSignature();
	}
	
	@FXML
	private void handleBack() {
		mainApp.showMain();
	}
}
