package vault;

import java.io.File;


import java.io.IOException;
import java.io.OutputStream;

import javax.crypto.CipherOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vault.model.Account;
import vault.model.AccountWrapper;
import vault.model.Key;
import vault.model.User;
import vault.view.AccountEditDialogController;
import vault.view.AccountsController;
import vault.view.CheckSumController;
import vault.view.KeysController;
import vault.view.LoginController;
import vault.view.MainController;
import vault.view.SetupController;
import vault.view.ToolController;

public class MainApp extends Application {

	private Boolean setup = false;
    private Stage primaryStage;
    private BorderPane rootLayout;
 
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Vault");

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/psvIcon.png"));
        
        initRootLayout();
        
        // If user data already exits, go to login, otherwise register user
        if(new File(System.getProperty("user.home")+"/AikenVault/userdata.xml/").exists()) {
        	showLogin();
        } else {
        	showSetup();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the login page inside the root layout.
     */
    public void showLogin() {
        try {
            // Load login page
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            // Set login page into the center of root layout.
            rootLayout.setCenter(login);
            
            // Give the controller access to the main app
    		LoginController controller = loader.getController();
    		controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the setup page inside the root layout
     */
    public void showSetup() {
    	try {
    		// Load setup page
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/Setup.fxml"));
    		AnchorPane setup = (AnchorPane) loader.load();
    		
    		// Set setup page into the center of root layout.
    		rootLayout.setCenter(setup);
    		
    		// Give the controller access to the main app
    		SetupController controller = loader.getController();
    		controller.setMainApp(this);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Shows the account overview inside the root layout.
     */
    public void showAccountOverview() {
        try {
        	//generateInitialData();
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Accounts.fxml"));
            AnchorPane accounts = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(accounts);

            // Give the controller access to the main app.
            AccountsController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the account overview inside the root layout.
     */
    public void showMain() {
        try {
        	//generateInitialData();
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Main.fxml"));
            AnchorPane main = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(main);

            // Give the controller access to the main app.
            MainController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    /**
     * Shows keys inside the root layout
     */
    public void showKeys() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/Keys.fxml"));
    		AnchorPane main = (AnchorPane) loader.load();
    		
    		rootLayout.setCenter(main);
    		
    		KeysController controller = loader.getController();
    		controller.setMainApp(this);
    	} catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    /**
     * Shows tools inside the root layout
     */
    public void showTools() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/Tools.fxml"));
    		AnchorPane main = (AnchorPane) loader.load();
    		
    		rootLayout.setCenter(main);
    		
    		ToolController controller = loader.getController();
    		controller.setMainApp(this);
    	} catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    /**
     * Shows tools inside the root layout
     */
    public void showChecksum() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/CheckSum.fxml"));
    		AnchorPane main = (AnchorPane) loader.load();
    		
    		rootLayout.setCenter(main);
    		
    		CheckSumController controller = loader.getController();
    		controller.setMainApp(this);
    	} catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    
	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void saveUserDataToStream(File file, User user) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(User.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
	        // Marshaling and saving XML to the file.
	        m.marshal(user, file);
	        
	    } catch (Exception e) { // catches ANY exception
	    	System.out.println(e);
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());
	
	        alert.showAndWait();
	    }
	}
	

	
	private ObservableList<Account> accountData = FXCollections.observableArrayList();
	private ObservableList<Key> keyData = FXCollections.observableArrayList();
	
	public void generateInitialData() {
		accountData.add(new Account("Google", "kevinaiken", "mypassword123"));
		accountData.add(new Account("Apple", "Kevinmichael", "passtwofor"));
		keyData.add(new Key("my key", "421830210321", "this key unlocks things", "DES"));
	}
	
	/**
     * Returns the data as an observable list of accounts.
     * @return
     */
    public ObservableList<Account> getAccountData() {
        return accountData;
    }
    
    /**
     * Returns the data as an observable list of keys. 
     * @return
     */
    public ObservableList<Key> getKeyData() {
        return keyData;
    }
    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public void loadAccountDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(AccountWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            AccountWrapper wrapper = (AccountWrapper) um.unmarshal(file);

            accountData.clear();
            accountData.addAll(wrapper.getPersons());

          

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void encryptAccountData() {
        try {
        	OutputStream mystream;
            JAXBContext context = JAXBContext
                    .newInstance(AccountWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            AccountWrapper wrapper = new AccountWrapper();
            wrapper.setAccounts(accountData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, mystream);

            CipherOutputStream myEncryptedStream = new CipherOutputStream(mystream, cipher);
            
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    public boolean showAccountEditDialog(Account account) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AccountEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AccountEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAccount(account);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}