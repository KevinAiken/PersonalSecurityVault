package vault.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
	
	private final StringProperty accountName;
    private final StringProperty userID;
    private final StringProperty pass;
    private final StringProperty url;
    private final StringProperty notes;

	/**
	 * Default constructor
	 */
    public Account() {
        this(null, null, null);
    }
    
	public Account(String accountName, String uid, String pass) {
		this.accountName = new SimpleStringProperty(accountName);
		this.userID = new SimpleStringProperty(uid);
		this.pass = new SimpleStringProperty(pass);
		this.notes = new SimpleStringProperty("");
		this.url = new SimpleStringProperty("");
	}
	
	public void setAccountName(String name) {
		this.accountName.set(name);
	}
	
	public void setUserID(String uid) {
		this.userID.set(uid); 
	}
	
	public void setPass(String pass) {
		this.pass.set(pass);
	}
	public void setUrl(String url) {
		this.url.set(url);
	}
	public void setNotes(String notes) {
		this.notes.set(notes);
	}
	
	public String getAccountName() {
		return this.accountName.get();
	}
	
	public String getUserID() {
		return this.userID.get();
	}
	
	public String getPass() {
		return this.pass.get();
	}
	
	public String getUrl() {
		return this.url.get();
	}
	
	public String getNotes() {
		return this.notes.get();
	}
	
	public StringProperty accountNameProperty() {
        return accountName;
    }
	
	public StringProperty userIDProperty() {
        return userID;
    }
	
	public StringProperty passProperty() {
        return pass;
    }
	
	public StringProperty urlProperty() {
        return url;
    }
	
	public StringProperty notesProperty() {
        return notes;
    }
}
