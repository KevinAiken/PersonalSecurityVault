package vault.view;

import java.io.File;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert.AlertType;
import vault.MainApp;
import vault.model.Key;

public class KeysController {
	@FXML
    private TableView<Key> keyTable;
    @FXML
    private TableColumn<Key, String> keyNameColumn;
    @FXML
    private TableColumn<Key, String> keyTypeColumn;
    
    @FXML
    private Label keyNameLabel;
    @FXML
    private Label keyTypeLabel;
    @FXML
    private Label keyValueLabel;
    @FXML
    private Label keyPairValueLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private Label notesLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public KeysController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        keyNameColumn.setCellValueFactory(cellData -> cellData.getValue().keyNameProperty());
        keyTypeColumn.setCellValueFactory(cellData -> cellData.getValue().keyTypeProperty());
        

        showKeyDetails(null);

        keyTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showKeyDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
       
        keyTable.setItems(mainApp.getKeyData());
    }
    
    private void showKeyDetails(Key key) {
        if (key != null) {
            // Fill the labels with info from the key object
            keyNameLabel.setText(key.getKeyName());
            keyTypeLabel.setText(key.getKeyType());
            keyValueLabel.setText(key.getKeyValue());
            keyPairValueLabel.setText(key.getKeyPairValue());
            notesLabel.setText(key.getNotes());
            lengthLabel.setText(key.getKeyLength());
        } else {
            // key is null, remove all the text.
            keyNameLabel.setText("");
            keyTypeLabel.setText("");
            keyValueLabel.setText("");
            keyPairValueLabel.setText("");
            lengthLabel.setText("");
            notesLabel.setText("");         
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteKey() {
        int selectedIndex = keyTable.getSelectionModel().getSelectedIndex();
        keyTable.getItems().remove(selectedIndex);
        mainApp.encryptKeyData();
    }
    
    @FXML
    private void handleBack() {
    	mainApp.showMain();
    }
    
    @FXML 
    private void handleNewKey() {
    	Key tempKey = new Key();
    	boolean okClicked = mainApp.showKeyEditDialog(tempKey);
    	if(okClicked) {
    		mainApp.getKeyData().add(tempKey);
    		mainApp.encryptKeyData();
    	}
    }
    
    @FXML
    private void exportKey() {
    	FileChooser fileChooser = new FileChooser();
        int selectedIndex = keyTable.getSelectionModel().getSelectedIndex();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage().getScene().getWindow());
       
        if(file != null){
            try {
        		JAXBContext jaxbContext = JAXBContext.newInstance(Key.class);
        		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        		jaxbMarshaller.marshal(mainApp.getKeyData().get(selectedIndex), file);

            } catch (JAXBException e) {
        		e.printStackTrace();
        	}
        }
    }
    
    private File fileToImport;
    @FXML
    private void importKey() {
    	FileChooser chooser = new FileChooser();
    	fileToImport = chooser.showOpenDialog(mainApp.getPrimaryStage().getScene().getWindow());
    	try {
    		JAXBContext jaxbContext = JAXBContext.newInstance(Key.class);

    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		Key key = (Key) jaxbUnmarshaller.unmarshal(fileToImport);
    		mainApp.getKeyData().add(key);
    		mainApp.encryptKeyData();
    	  } catch (JAXBException e) {
    		e.printStackTrace();
    	  }
    }
    
    @FXML 
    private void editKey() {
    	Key selectedKey = keyTable.getSelectionModel().getSelectedItem();
    	if (selectedKey != null) {
    		boolean okClicked = mainApp.showKeyEditDialog(selectedKey);
    		if (okClicked) {
    			showKeyDetails(selectedKey);
    			mainApp.encryptKeyData();
    		}
    	} else {
    		// no selection
    		Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Key Selected");
            alert.setContentText("Please select a key in the table.");

            alert.showAndWait();
    	}
    }
    
    @FXML
    private void generateKey() {
    	Key tempKey = new Key();
    	boolean okClicked = mainApp.showKeyGenerateDialog(tempKey);
    	if(okClicked) {
    		mainApp.getKeyData().add(tempKey);
    		mainApp.encryptKeyData();
    	}
    }
}
