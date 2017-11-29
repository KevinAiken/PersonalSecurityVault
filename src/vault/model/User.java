package vault.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
	private byte[] encryptSalt;
	private String password;
	
	
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
		this.encryptSalt = userPass.getEncryptSalt();
		this.password = userPass.getPassword();
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
	
	public byte[] getEncryptSalt() {
		return encryptSalt;
	}
	
	public String getPassword() {
		return password;
	}
	@XmlTransient
	public void setPassword(String password) {
		this.password = password;
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
	
	@XmlElement
	public void setEncryptSalt(byte[] encryptSalt) {
		this.encryptSalt = encryptSalt;
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
