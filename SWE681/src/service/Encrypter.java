package service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Encrypter {
	
	public String encrypt(String password) {
		MessageDigest msgdgst = null;
		
		try {
			msgdgst = MessageDigest.getInstance("SHA-256");
		}
		catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		try {
			msgdgst.update(password.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		byte rawPwd[] = msgdgst.digest();
		String hashPwd = (new BASE64Encoder()).encode(rawPwd);
		return hashPwd;
	}

}
