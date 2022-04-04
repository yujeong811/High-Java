package kr.or.ddit.basic.crypto;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		String plainText = "Hello, World! 가나다라 1234 %^&*_+";
		
		// 암호화에 사용하는 키값 설정 (16자리 이상으로 한다.)
		String key = "a1b2c3d4e5f6g7h8"; 
		
		System.out.println("단방향 암호화 연습...");
		String result = CryptoUtil.sha512(plainText);
		
		System.out.println("원본 데이터 : " + plainText);
		System.out.println("SHA-512방식 : " + result);
//		System.out.println("SHA-256방식 : " + result);
//		System.out.println("MD5방식 : " + result);
		
		System.out.println("양방향 암호화 연습 (AES256방식)...");
		System.out.println("원본 데이터 : " + plainText);
		String encryptedStr = CryptoUtil.encryptoAES256(plainText, key);
		System.out.println("AES-256 암호화 : " + encryptedStr);
		
		String decryptedStr = CryptoUtil.decryptoAES256(encryptedStr, key);
		System.out.println("AES-256 복호화 : " + decryptedStr);	
	}
}