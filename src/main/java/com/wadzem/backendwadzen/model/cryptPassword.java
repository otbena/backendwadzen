package com.wadzem.backendwadzen.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class cryptPassword {


	private static String securekey ="kTg2Nn7wUZO%Q6Xc+1lenkZTQ9@ZDf9a2/RBRiqJBCI?X6o=";
		
	public static String getKey(String password) throws NoSuchAlgorithmException {
		
		password  = password + securekey;
		
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(password.getBytes());
	    
	    byte byteData[] = md.digest();

	    //convert the byte to hex format method 1
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++) {
	     sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	    }
	 
	    return sb.toString();
		
	}

}
