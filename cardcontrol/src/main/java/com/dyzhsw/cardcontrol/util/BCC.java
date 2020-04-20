package com.dyzhsw.cardcontrol.util;

import org.apache.commons.codec.DecoderException;

/**
 * @author: guyuqiao
 * @date: 2019年1月8日 下午4:58:17
 */
public class BCC {
	/*
	 * BCC异或校验
	 */
	public static String getBCC(byte[] data) {
		String ret = "";
		byte BCC[] = new byte[1];
		for (int i = 0; i < data.length; i++) {
			BCC[0] = (byte) (BCC[0] ^ data[i]);
		}
		String hex = Integer.toHexString(BCC[0] & 0xFF);
		int len = hex.length();
		for(int i=0 ; i < 4 - len; i++) {
			hex = '0' + hex;
		}
		ret += hex.toUpperCase();
		return ret;
	}
	
	/**
	 * 16进制转换byte
	 * 
	 * @param msg
	 * @return
	 * @throws DecoderException
	 */
	public static byte[] convertHexToByte(String msg) throws DecoderException {
		if (msg == null || msg.length() < 1) {
			throw new DecoderException();
		}
		int len = msg.length() / 2;
		byte[] b = new byte[len];
		for (int i = 0; i < len; i++) {
			String s = msg.substring(i * 2, i * 2 + 2);
			b[i] = Integer.valueOf(s, 16).byteValue();
		}
		return b;
	}
	public static String stringToHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

}
