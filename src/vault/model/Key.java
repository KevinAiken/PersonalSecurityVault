package vault.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a key
 */
public class Key {
	private final StringProperty keyName;
	private final StringProperty keyValue;
	private final StringProperty keyPairValue;
	private final StringProperty keyNotes;
	private final StringProperty keyType;
	private final StringProperty keyLength;
	
	/**
	 * Default constructor.
	 */
	public Key() {
		this(null, null, null, null);
	}
	
	/**
	 * Constructor with some initial data.
	 */
	public Key(String keyName, String keyValue, String keyNotes, String keyType) {
		this.keyName = new SimpleStringProperty(keyName);
		this.keyValue = new SimpleStringProperty(keyValue);
		this.keyNotes = new SimpleStringProperty(keyNotes);
		this.keyType = new SimpleStringProperty(keyType);
		this.keyPairValue = new SimpleStringProperty("");
		this.keyLength = new SimpleStringProperty("");
	}
	public String getKeyName() {
        return keyName.get();
    }

    public void setKeyName(String keyName) {
        this.keyName.set(keyName);
    }

    public String getKeyValue() {
        return keyValue.get();
    }

    public void setKeyValue(String keyValue) {
        this.keyValue.set(keyValue);
    }
    
    public String getNotes() {
        return keyNotes.get();
    }

    public void setNotes(String keyNotes) {
        this.keyNotes.set(keyNotes);
    }
    
    public String getKeyType() {
        return keyType.get();
    }

    public void setKeyType(String keyType) {
        this.keyType.set(keyType);
    }
    
    public void setKeyPairValue(String keyPair) {
    	this.keyPairValue.set(keyPair);
    }
    
    public String getKeyPairValue() {
    	return keyPairValue.get();
    }
    
    public StringProperty keyNameProperty() {
        return keyName;
    }
    
    public StringProperty keyTypeProperty() {
        return keyType;
    }
    
    public StringProperty keyPairValueProperty() {
        return keyPairValue;
    }
    
    public StringProperty keyNotesProperty() {
        return keyNotes;
    }
    
    public StringProperty keyValueProperty() {
        return keyValue;
    }
    
    public StringProperty keyLengthProperty() {
        return keyLength;
    }
}
