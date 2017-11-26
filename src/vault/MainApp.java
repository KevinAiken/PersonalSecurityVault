package vault;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Boolean setup = true;
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Vault");

        initRootLayout();
        
        if(setup) {
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
    	} catch (IOException e) {
    		e.printStackTrace();
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