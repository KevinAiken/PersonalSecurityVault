package vault.view;

import javafx.fxml.FXML;
import vault.MainApp;

public class MainController {
	
	private MainApp mainApp;
	
	public MainController(){
	}
	
	@FXML
	private void initialize() {
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML 
	private void handleAccounts() {
		mainApp.showAccountOverview();
	}
	
	@FXML 
	private void handleKeys() {
		mainApp.showKeys();
	}
	
	@FXML 
	private void handleTools() {
		mainApp.showTools();
	}
	
}
