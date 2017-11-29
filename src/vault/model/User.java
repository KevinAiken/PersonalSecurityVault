package vault.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Contains the username 
 * and password information
 * 
 * @author Aiken
 */
@XmlRootElement
public class User {
	private String username;
	private Password userPass;
	private byte[] passHash;
	private byte[] passSalt;
	
	
	/** 
	 * Default constructor
	 */
	public User() {
		this.username = null;
		this.userPass = null;
	}
	
	public User(String name, Password pass) {
		this.username = name;
		this.userPass = pass;
		this.passHash = userPass.getHash();
		this.passSalt = userPass.getSalt();
	}
	
	public byte[] getPassHash() {
		return passHash;
		//return this.userPass.getHash().toString();
	}
	
	public byte[] getPassSalt() {
		return passSalt;
		//return this.userPass.getSalt().toString();
	}
	
	public String getUsername() {
		return this.username;
	}
	
	@XmlElement
	public void setUsername(String userName) {
		this.username = userName;
	}
	
	@XmlElement
	public void setPassHash(byte[] passHash) {
		this.passHash = passHash;
	}
	
	@XmlElement
	public void setPassSalt(byte[] passSalt) {
		this.passSalt = passSalt;
	}
	/**
	 * Determines if login is valid
	 */
	public void Authenticate(String name, Password pass) {
	}
	
	public String toString() {
		String userString;
		userString = "Username: " + getUsername() + 
				"\nHash: " + Arrays.toString(getPassHash())
				+ "\nSalt: " + Arrays.toString(getPassSalt());
		return userString;
	}
	
}
