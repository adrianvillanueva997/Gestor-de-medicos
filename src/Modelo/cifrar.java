package Modelo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class cifrar{
	
	Fichero fich;
	
	protected static String cifradoGuardarPass(Usuario us){
		
		String password = us.getContrasena();
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            sha256.update(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] digest = sha256.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            sb.append(String.format("%02x", digest[i]));
        }
        String hash = sb.toString(); //2bb80d5...527a25b
        System.out.println("Contraseña desencriptada: " + password + "\n");
        System.out.println("Contraseña encriptada: " + hash + "\n");
        Fichero.updatePassword(us.getId_usuario(), hash);
        
		return hash;
	}
	
	protected static String cifradoLogin(String pass){	
		
		String password = pass;
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            sha256.update(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] digest = sha256.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            sb.append(String.format("%02x", digest[i]));
        }
        String hash = sb.toString();
        System.out.println("Contraseña desencriptada: " + password + "\n");
        System.out.println("Contraseña encriptada: " + hash + "\n");
        
		return hash;
	}
}