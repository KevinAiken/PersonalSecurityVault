package vault.view;

import javafx.fxml.FXML;
import vault.MainApp;

public class DecryptController {
	private MainApp mainApp;
	
	public DecryptController() {
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
