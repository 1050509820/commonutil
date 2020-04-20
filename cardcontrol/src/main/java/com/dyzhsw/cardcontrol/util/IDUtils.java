package com.dyzhsw.cardcontrol.util;

import java.util.UUID;

public class IDUtils {

	public static String createUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
