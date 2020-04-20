package com.dyzhsw.cardcontrol.util;

import java.math.BigDecimal;

import org.apache.commons.codec.DecoderException;

public class CRC8 {

	public static String FindCRC(byte[] data) throws DecoderException {
		int CRC = 0;
		int genPoly = 0X07;// 多项式
		for (int i = 0; i < data.length; i++) {
			CRC ^= data[i];
			for (int j = 0; j < 8; j++) {
				if ((CRC & 0x80) != 0) {
					CRC = (CRC << 1) ^ genPoly;
				} else {
					CRC <<= 1;
				}
			}
		}
		CRC &= 0xff;// 保证CRC余码输出为2字节。
		String crc16 = Integer.toHexString(CRC);// 把10进制的结果转化为16进制
		// 如果想要保证校验码必须为2位，可以先判断结果是否比16小，如果比16小，可以在16进制的结果前面加0
		if (CRC < 16) {
			crc16 = "0" + crc16;
		}
		return crc16.toUpperCase();
	}

	/**
	 * 16位 高位在前低位在后
	 * 
	 * @param data
	 * @return
	 */
	public static String CRC16(byte[] data) {
		byte[] buf = new byte[data.length];// 存储需要产生校验码的数据
		for (int i = 0; i < data.length; i++) {
			buf[i] = data[i];
		}
		int len = buf.length;
		int crc = 0xFFFF;// 16位
		for (int pos = 0; pos < len; pos++) {
			if (buf[pos] < 0) {
				crc ^= (int) buf[pos] + 256; // XOR byte into least sig. byte of
												// crc
			} else {
				crc ^= (int) buf[pos]; // XOR byte into least sig. byte of crc
			}
			for (int i = 8; i != 0; i--) { // Loop over each bit
				if ((crc & 0x0001) != 0) { // If the LSB is set
					crc >>= 1; // Shift right and XOR 0xA001
					crc ^= 0xA001;
				} else
					// Else LSB is not set
					crc >>= 1; // Just shift right
			}
		}
		String c = Integer.toHexString(crc);

		if (c.length() == 3) {
			c = "0" + c;
			c = c.substring(0, 2) + c.substring(2, 4);
		} else if (c.length() == 2) {
			c = "0" + c.substring(0, 1) + "0" + c.substring(1, 2);
		}
		return c.toUpperCase();
	}

	/**
	 * 16位 低位在前高位在后
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	public static String CRC16(byte[] data, int length) {
		int div = 0, mod = 0;
		int crc_val = 0xFFFF;
		int i = 0, j = 0;
		for (i = 0; i < length; i++) {
			crc_val = crc_val ^ data[i];
			for (j = 0; j < 8; j++) {
				div = crc_val >> 1;
				mod = crc_val - (div << 1);
				if (mod == 1) {
					div = div ^ 0xA001;
				}
				crc_val = div;
			}
		}
		String c = Integer.toHexString(crc_val);

		if (c.length() == 4) {
			c = c.substring(2, 4) + c.substring(0, 2);
		} else if (c.length() == 3) {
			c = "0" + c;
			c = c.substring(2, 4) + c.substring(0, 2);
		} else if (c.length() == 2) {
			c = "0" + c.substring(1, 2) + "0" + c.substring(0, 1);
		}
		return c;
	}

	/**
	 * 16位 高位在前低位在后
	 * 
	 * @param dataChar
	 * @param length
	 * @return
	 */
	public static String CRC16(char[] dataChar, int length) {
		byte[] data = new byte[dataChar.length];
		for (int i = 0; i < dataChar.length; i++) {
			data[i] = (byte) dataChar[i];
		}
		int div = 0, mod = 0;
		int crc_val = 0xFFFF;
		int i = 0, j = 0;
		for (i = 0; i < length; i++) {
			crc_val = crc_val ^ (data[i]);
			for (j = 0; j < 8; j++) {
				div = crc_val >> 1;
				mod = crc_val - (div << 1);
				if (mod == 1) {
					div = div ^ 0xA001;
				}
				crc_val = div;
			}
		}
		String c = Integer.toHexString(crc_val);
		if (c.length() == 3) {
			c = "0" + c;
		} else if (c.length() == 2) {
			c = "00" + c;
		}

		return c;
	}

	public static byte[] convertHexToByte(String msg) throws DecoderException {
		if (msg == null || msg.length() < 1) {
			throw new DecoderException();
		}
		int len = msg.length() / 2;
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			String s = msg.substring(i * 2, i * 2 + 2);
			bytes[i] = Integer.valueOf(s, 16).byteValue();
		}
		return bytes;
	}

	/**
	 *  小数转换为16进制数（扩大相应倍数）
	 * @param convertDate 原值
	 * @param precision 精度
	 * @return
	 */
	public static String decimalConvertInt(BigDecimal convertVal, int precision, int byteNum) {
		Double multiple = Math.pow(10, new Double("" + precision).doubleValue());
		String str = "" + convertVal.multiply(new BigDecimal(multiple.intValue())).intValue();
		String convertResult = Integer.toHexString(Integer.valueOf(str));
		for (int i = convertResult.length(); i < 2 * byteNum; i++) {
			convertResult = "0" + convertResult;
		}
		return convertResult;
	}
	/**
	 * 字符数字转换为hex表示
	 * @param convertVal 需要转换的值
	 * @param byteNum 占用字节数
	 * @return
	 * @throws DecoderException
	 */
	public static String convertDigitToHex(String convertVal, int byteNum) throws DecoderException{
		if (convertVal == null || convertVal.trim().isEmpty()) {
			throw new DecoderException();
		}
		String convertResultHex = Integer.toHexString(Integer.valueOf(convertVal));
		for (int i = convertResultHex.length(); i < 2 * byteNum; i++) {
			convertResultHex = "0" + convertResultHex;
		}
		return convertResultHex;
	}
}
