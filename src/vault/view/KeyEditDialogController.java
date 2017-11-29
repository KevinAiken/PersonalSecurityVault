package vault.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vault.model.Key;

public class KeyEditDialogController {
	
	@FXML
	private TextField keyNameField;
	@FXML
	private TextField keyTypeField;
	@FXML
	private TextField keyValueField;
	@FXML
	private TextField keyPairValueField;
	@FXML
	private TextField notesField;
	
	private Stage dialogStage;
	private Key key;
	private boolean okClicked = false;
	
	@FXML
	private void initialize() {
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setKey(Key key) {
		this.key = key;
		
		keyNameField.setText(key.getKeyName());
		keyTypeField.setText(key.getKeyType());
		keyValueField.setText(key.getKeyValue());
		keyPairValueField.setText(key.getKeyPairValue());
		notesField.setText(key.getNotes());
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	
	@FXML
	private void handleOk() {
		key.setKeyName(keyNameField.getText());
		key.setKeyType(keyTypeField.getText());
		key.setKeyValue(keyValueField.getText());
		key.setKeyPairValue(keyPairValueField.getText());
		key.setNotes(notesField.getText());
		
		okClicked = true;
		dialogStage.close();
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
