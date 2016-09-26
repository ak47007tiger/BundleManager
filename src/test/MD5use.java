package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5use {

	public static void main(String[] args) {
		MD5use md5use = new MD5use();
		String md51 = md5use.get(md5use.fromFile(new File("md5/same1.txt")));
		String md52 = md5use.get(md5use.fromFile(new File("md5/same2.dat")));
		System.out.println(md51.equals(md52));
	}
	
	public byte[] fromFile(File src){
		try {
			byte[] b = new byte[(int)src.length()];
			FileInputStream fin = new FileInputStream(src);
			fin.read(b);
			fin.close();
			return b;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public String get(byte[] input){
		try {
			MessageDigest mdInst;
			mdInst = MessageDigest.getInstance("MD5");
			byte[] output = mdInst.digest(input);
			StringBuffer sb = new StringBuffer();
			for(byte b : output){
				sb.append(Integer.toHexString(b & 0x000000ff));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
