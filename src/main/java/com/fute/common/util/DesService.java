package com.fute.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesService {

	public String decode(String secretText) throws Exception {
		return new String(decode(hexStringToBytes(secretText)), CHARS_ENCODING);
	}

	public String encode(String clearText) throws Exception {
		return bytesToHexString(encode(clearText.getBytes(CHARS_ENCODING)));
	}

	private byte[] encode(byte[] byteArr) throws Exception {
		return encodeCipher.doFinal(byteArr);
	}

	private byte[] decode(byte[] byteArr) throws Exception {
		return decodeCipher.doFinal(byteArr);
	}

	private static String bytesToHexString(byte[] byteArr) throws Exception {
		int length = byteArr.length;
		StringBuilder sb = new StringBuilder(length * 2);
		for (byte bit : byteArr) {
			String cell = Integer.toHexString(bit & 0xFF);
			if (cell.length() < 2)
				sb.append(0);
			sb.append(cell);
		}
		return sb.toString();
	}

	private byte[] hexStringToBytes(String hexStr) throws Exception {
		byte[] opArr = hexStr.getBytes(CHARS_ENCODING);
		int length = opArr.length;
		byte[] byteArr = new byte[length / 2];
		for (int i = 0; i < length; i = i + 2) {
			String tmp = new String(opArr, i, 2);
			byteArr[i / 2] = (byte) Integer.parseInt(tmp, 16);
		}
		return byteArr;
	}

	public DesService() throws Exception {
		DESKeySpec spec = new DESKeySpec(SECRET_KEY.getBytes(CHARS_ENCODING));
		SecretKeyFactory factory = SecretKeyFactory
				.getInstance(CRYPT_ALGORITHM);
		SecretKey key = factory.generateSecret(spec);
		decodeCipher = Cipher.getInstance(CRYPT_ALGORITHM);
		decodeCipher.init(Cipher.DECRYPT_MODE, key);
		encodeCipher = Cipher.getInstance(CRYPT_ALGORITHM);
		encodeCipher.init(Cipher.ENCRYPT_MODE, key);
	}

	public DesService(String desKey) {
		try {
			DESKeySpec spec = new DESKeySpec(desKey.getBytes(CHARS_ENCODING));
			SecretKeyFactory factory = SecretKeyFactory
					.getInstance(CRYPT_ALGORITHM);
			SecretKey key = factory.generateSecret(spec);
			decodeCipher = Cipher.getInstance(CRYPT_ALGORITHM);
			decodeCipher.init(Cipher.DECRYPT_MODE, key);
			encodeCipher = Cipher.getInstance(CRYPT_ALGORITHM);
			encodeCipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static final String SECRET_KEY = "*39RgOd#";
	private static final String CHARS_ENCODING = "UTF-8";
	private static final String CRYPT_ALGORITHM = "DES";

	private Cipher decodeCipher = null;
	private Cipher encodeCipher = null;
	private static DesService des = new DesService("13572468");

	public static DesService getInstance() {
		if (des == null) {
			des = new DesService("13572468");
		}
		return des;
	}

	public static void main(String[] args) {

		try {
			System.out.println(DesService.getInstance().decode(
					"c2eb710704eb36ae"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// DesService des = new DesService();
		// des.d
		//
		// String KEY = "13572468";
		// String text = "2222";
		// try {
		// DesService desService = new DesService(KEY);
		// String encrypt = desService.encode(text);
		// System.out.println("encrypt: " + encrypt);
		// String decrypt = desService.decode(encrypt);
		// System.out.println("decrypt: " + decrypt);
		// } catch (Exception e) {
		// // ignore
		// }
	}
}
