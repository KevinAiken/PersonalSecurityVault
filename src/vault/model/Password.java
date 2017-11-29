package vault.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Password object
 * Contains a hash and a salt
 * 20,000 iterations
 * 
 * NIST Recommendations 2016:
 * 32 Bit Salt minimum
 * 10,000 iteration PBKDF2 
 * Using a keyed hmac hash with sha-1, sha-2, or sha-3
 * 
 * 
 * @author Aiken
 */
public class Password {
	
	private final byte[] hash;
	private final byte[] salt;
	
	// Highest number of iterations that did not
	// result in significant login lag
	private static final int ITERATIONS = 20000;
	private static final int KEY_LENGTH = 256;
	
	/**
	 * Default constructor
	 */
	public Password(String password) {
		this.salt = generateSalt();
		this.hash = generateSecureHash(password, salt);
		password = null;
	}
	
	/**
	 * constructor for using a preset salt
	 */
	public Password(String password, byte[] salt) {
		this.salt = salt;
		this.hash = generateSecureHash(password, this.salt);
		password = null;
	}
	
	/**
	 * constructor for a password object with hash already calculated
	 */
	public Password(byte[] hash, byte[] salt) {
		this.salt = salt;
		this.hash = hash;
	}
	
	public byte[] getSalt() {
		return salt;
	}
	
	public byte[] getHash() {
		return hash;
	}

	public boolean compare(String passAttempt) {
		if (Arrays.equals(generateSecureHash(passAttempt, this.salt),((this.hash)))) {
			return true;
		} else {
			return false;
		}
	}
	
	private static byte[] generateSecureHash(String password, byte[] salt) {
		char[] passwordChars = password.toCharArray();
		password = null;
		
		PBEKeySpec spec = new PBEKeySpec(
				passwordChars, 
				salt,
				ITERATIONS,
				KEY_LENGTH
		);
		SecretKeyFactory key;
		try {
			//Password Based Key derivation function using sha256 hash
			key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			byte[] hashedPassword;
			try {
				hashedPassword = key.generateSecret(spec).getEncoded();
				return hashedPassword;
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Produces a salt
	 */
	private static byte[] generateSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
}
