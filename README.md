# PersonalSecurityVault
Personal Security Vault for HKBU's 4017 Computer Security class

## Project Description 
The Vault securely manages account data and encryption keys for users. There are also a variety of tools included in the program, such as key generation, basic password generation, and digital signature generation and verification. 

## Development Environment
Developed with Eclipse 4.7 with the e(fx)clipse plugin, Scene Builder 8.0, and JDK 8 including JavaFX 8.

## User guide
The first time the user opens the application they will be presented with a screen to register an account. Their account data will be stored the folder "AikenVault" within the user's home directory. They can then login and add account information and keys to the program, which will be encrypted into the "AikenVault" folder immediately. Passwords can be generated within the account management page, and keys can be generated within the key management page, including DES keys, 3DES keys, AES keys, and RSA key pairs. 

Internet connectivity is not necessary to use the application, as all user data is stored locally.

## Application Screenshots
![Login Screen](https://github.com/KevinAiken/PersonalSecurityVault/blob/master/PasswordSecurityVaultPics/login.PNG)

![Account Info screen](https://github.com/KevinAiken/PersonalSecurityVault/blob/master/PasswordSecurityVaultPics/accountInfo.PNG)

![Key management Screen](https://github.com/KevinAiken/PersonalSecurityVault/blob/master/PasswordSecurityVaultPics/keymenu.PNG)
