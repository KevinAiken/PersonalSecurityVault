package vault.view;

import javafx.fxml.FXML;
import vault.MainApp;

public class EncryptController {
	
	private MainApp mainApp;
	
	public EncryptController() {
	}
	
	@FXML
	private void initialize() {
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleBack() {
		mainApp.showTools();
	}
}
